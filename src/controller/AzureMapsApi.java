package controller;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.LinearRing;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.Polygon;
import org.locationtech.jts.geom.impl.CoordinateArraySequence;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

public class AzureMapsApi {

	private static final String STATE = "NSW";
	private static final String API_KEY = "5nXsFMSUBlUyt_Hvq0fgM6u6tKXy80wgwWfvZaLJuj0";

	

	/**
	 * A method which takes an address and calls the Azure Maps Api to return
	 * coordinates in latitude and longitude. It will replace all whitespace within
	 * the string such as between a street name and type e.g. "Boundary Rd" with a %
	 * to ensure the api call is successful. It will also remove all leading and
	 * trailing whitespace. It returns an object of type Coordinate from the JTS
	 * Topology library.
	 * 
	 * @param streetNo
	 * @param streetName
	 * @param suburbName
	 * @param postCode
	 * @return coord
	 * @throws IOException
	 */
	public static Coordinate getCoordinatesFromAddress(String streetNo, String streetName, String suburbName,
			String postCode ) throws IOException {

		streetNo = streetNo.replace(" ", "%").trim();
		streetName = streetName.replace(" ", "%").trim();
		suburbName = suburbName.replace(" ", "%").trim();
		postCode = postCode.replace(" ", "%").trim();
		

		URL url = new URL("https://atlas.microsoft.com/search/address/json?subscription-key=" + API_KEY
				+ "&api-version=1.0&language=en-US&query=" + streetNo + "," + streetName + "," + suburbName + ","
				+ STATE + "," + postCode );

		URLConnection conn = url.openConnection();
		HttpURLConnection http = (HttpURLConnection) conn;
		http.setRequestMethod("GET");
		http.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
		http.connect();
		int responseCode = http.getResponseCode();
		String inline = "";
		// System.out.println(responseCode + " getCoordinates");

		if (responseCode != 200) {
			throw new RuntimeException("HttpResponseCode: " + responseCode);
		} else {
			Scanner sc = new Scanner(url.openStream());
			while (sc.hasNext()) {
				inline += sc.nextLine();
			}

			JsonObject jsonObject = JsonParser.parseString(inline).getAsJsonObject();
			JsonObject json = (JsonObject) ((JsonObject) jsonObject.getAsJsonArray("results").get(0)).get("position");
			double lat = json.get("lat").getAsDouble();
			double lon = json.get("lon").getAsDouble();
			Coordinate coord = new Coordinate(lat, lon);
			// System.out.println("Address Location: " + coord);
			sc.close();
			return coord;

		}

	}

	/**
	 * A method which takes an object of type Coordinate which contains a latitude
	 * and longitude value and calls the Azure Maps API. The API call will then
	 * respond with latitude and longitude values for the boundaries of an isochrone
	 * which extend to a set integer defined as an integer timeBudgetInSeconds.
	 * 
	 * @param coord
	 * @param timeBudgetInSeconds
	 * @throws IOException
	 */
	public static JsonObject getIsochroneCoords(Coordinate coord, int timeBudgetInSeconds, LocalDateTime dateTime) throws IOException {
		URL url = new URL(
				"https://atlas.microsoft.com/route/range/json?subscription-key=" + API_KEY + "&api-version=1.0&query="
						+ coord.getX() + "," + coord.getY() + "&timeBudgetInSec=" + timeBudgetInSeconds +"&departAt="+ dateTime);

		URLConnection conn = url.openConnection();
		HttpURLConnection http = (HttpURLConnection) conn;
		http.setRequestMethod("GET");
		http.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
		http.connect();
		int responseCode = http.getResponseCode();
		String inline = "";

		if (responseCode != 200) {
			throw new RuntimeException("HttpResponseCode: " + responseCode);
		} else {
			Scanner sc = new Scanner(url.openStream());
			while (sc.hasNext()) {
				inline += sc.nextLine();

			}

			JsonObject jsonObject = JsonParser.parseString(inline).getAsJsonObject();
			sc.close();
			return jsonObject;

		}

	}

	/**
	 * A method which accepts a JsonObject and builds a polygon out of the values of
	 * the json object by parsing the object and extracting the lat and long values
	 * from the Json returned by the api call. The method returns a polygon object.
	 * 
	 * @param jsonObj
	 * @return
	 */
	public static Polygon BuildPolygon(JsonObject jsonObj) {

		ArrayList<Coordinate> points = new ArrayList<Coordinate>();
		GeometryFactory gf = new GeometryFactory();

		String myJSONString = jsonObj.toString();
		JsonObject jsonObject = JsonParser.parseString(myJSONString).getAsJsonObject();
		JsonObject range = (JsonObject) ((JsonObject) jsonObject.getAsJsonObject("reachableRange"));
		JsonArray jsonArray = range.getAsJsonArray("boundary");

		for (int i = 0; i < jsonArray.size(); i++) {
			JsonPrimitive jsonLat = (JsonPrimitive) ((JsonObject) range.getAsJsonArray("boundary").get(i))
					.get("latitude");
			JsonPrimitive jsonLon = (JsonPrimitive) ((JsonObject) range.getAsJsonArray("boundary").get(i))
					.get("longitude");
			double lat = jsonLat.getAsDouble();
			double lon = jsonLon.getAsDouble();
			Coordinate c = new Coordinate(lat, lon);
			points.add(c);
		}

		JsonPrimitive lastLat = (JsonPrimitive) ((JsonObject) range.getAsJsonArray("boundary").get(0)).get("latitude");
		JsonPrimitive lastLon = (JsonPrimitive) ((JsonObject) range.getAsJsonArray("boundary").get(0)).get("longitude");
		double lat2 = lastLat.getAsDouble();
		double lon2 = lastLon.getAsDouble();
		Coordinate lastCoord = new Coordinate(lat2, lon2);

		points.add(lastCoord);

//		for (Coordinate c : points) {
//			System.out.println(c);
//		}

		Polygon poly = gf.createPolygon(
				new LinearRing(new CoordinateArraySequence(points.toArray(new Coordinate[points.size()])), gf), null);
		return poly;
	}

	public static boolean checkIfLocationInIsochrone(Polygon isochrone, Coordinate coordToTest) {

		GeometryFactory gf = new GeometryFactory();
		Point testPoint = gf.createPoint(coordToTest);
		if (testPoint.within(isochrone)) {

			return true;

		} else {

			return false;
		}
	}
	
	public static int getRouteTime(Coordinate startCoord, Coordinate endCoord) throws IOException {
		URL url = new URL("https://atlas.microsoft.com/route/directions/json?subscription-key=" + API_KEY
				+ "&api-version=1.0&query=" + startCoord.getX() + "," + startCoord.getY() + ":" + endCoord.getX() + "," + endCoord.getY());

		URLConnection conn = url.openConnection();
		HttpURLConnection http = (HttpURLConnection) conn;
		http.setRequestMethod("GET");
		http.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
		http.connect();
		int responseCode = http.getResponseCode();
		String inline = "";
		//System.out.println(responseCode);

		if (responseCode != 200) {
			throw new RuntimeException("HttpResponseCode: " + responseCode);
		} else {
			Scanner sc = new Scanner(url.openStream());
			while (sc.hasNext()) {
				inline += sc.nextLine();

			}

			JsonElement element = JsonParser.parseString(inline).getAsJsonObject();
			JsonObject rootObj = element.getAsJsonObject();
			JsonArray routes = rootObj.getAsJsonArray("routes");
			JsonElement summary = ((JsonObject) routes.get(0)).get("summary");
			JsonPrimitive travelTime = summary.getAsJsonObject().getAsJsonPrimitive("travelTimeInSeconds");
			int travelTimeInSeconds = travelTime.getAsInt();
			
//			Gson gson = new GsonBuilder().setPrettyPrinting().create();
//			String prettyJson = gson.toJson(summary);
//			System.out.println(prettyJson);
			sc.close();
			return travelTimeInSeconds;

		}

	}


	public static void main(String[] args) throws IOException {

		// test functionality for the api calls
		LocalDateTime departAt = LocalDateTime.of(2020, 12, 19, 16, 39, 57);
		Coordinate coord = getCoordinatesFromAddress("13", "Bundle St", "Caddens", "2747");
		JsonObject jsonObj = getIsochroneCoords(coord, 6000, departAt);
		Polygon p = BuildPolygon(jsonObj);
		
		Coordinate gstCoord = new Coordinate(-33.78, 150.74);

		// centre of isochrone. Should return true
		Coordinate centerCoord = new Coordinate(-33.77494, 150.7393);

		// edge point. Should return false
		Coordinate edgeCoord = new Coordinate(-34.3018, 150.9385);

		// coordinates for LosAngeles. Should return false
		Coordinate losAngelesCoord = new Coordinate(34.0522, 118.2437);
		
		System.out.println(getRouteTime(gstCoord, centerCoord));
//
//		System.out.println(checkIfLocationInIsochrone(p, centerCoord));
//		System.out.println(checkIfLocationInIsochrone(p, edgeCoord));
//		System.out.println(checkIfLocationInIsochrone(p, losAngelesCoord));
        
	}

}
