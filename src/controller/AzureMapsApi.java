package controller;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.Coordinates;

public class AzureMapsApi {

	private final String STATE = "NSW";
	private final String API_KEY = "5nXsFMSUBlUyt_Hvq0fgM6u6tKXy80wgwWfvZaLJuj0";

	public void getIsochroneCoords(Coordinates coord, int timeBudgetInSeconds) throws IOException {
		URL url = new URL("https://atlas.microsoft.com/route/range/json?subscription-key=" + API_KEY
				+ "&api-version=1.0&query=" + coord + "&timeBudgetInSec=" + timeBudgetInSeconds);
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
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			JsonObject jsonObject = JsonParser.parseString(inline).getAsJsonObject();
			String prettyJsonString = gson.toJson(jsonObject);
			System.out.println(prettyJsonString);

		}

	}

	public Coordinates getCoordinatesFromAddress(int streetNo, String streetName, String suburbName, int postCode)
			throws IOException {
		
		streetName = streetName.replace(" ", "%");
		suburbName = suburbName.replace(" ", "%");
	
		URL url = new URL("https://atlas.microsoft.com/search/address/json?subscription-key=" + API_KEY
				+ "&api-version=1.0&language=en-US&query=" + streetNo + "," + streetName + "," + suburbName + ","
				+ STATE + "," + postCode);

		URLConnection conn = url.openConnection();
		HttpURLConnection http = (HttpURLConnection) conn;
		http.setRequestMethod("GET");
		http.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
		http.connect();
		int responseCode = http.getResponseCode();
		String inline = "";
		//System.out.println(responseCode + " getCoordinates");

		if (responseCode != 200) {
			throw new RuntimeException("HttpResponseCode: " + responseCode);
		} else {
			Scanner sc = new Scanner(url.openStream());
			while (sc.hasNext()) {
				inline += sc.nextLine();
			}
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			JsonObject jsonObject = JsonParser.parseString(inline).getAsJsonObject();
			JsonObject json = (JsonObject) ((JsonObject) jsonObject.getAsJsonArray("results").get(0)).get("position");
			double lat = json.get("lat").getAsDouble();
			double lon = json.get("lon").getAsDouble();
			Coordinates coord = new Coordinates(lat,lon);
			System.out.println("Address Location: "+coord);
			return coord;

		}
		
	}

	public static void main(String[] args) throws IOException {

		AzureMapsApi azm = new AzureMapsApi();
		Coordinates coord = azm.getCoordinatesFromAddress(13, "Bundle St", "Caddens", 2747);
		azm.getIsochroneCoords(coord, 6000);

	}

}
