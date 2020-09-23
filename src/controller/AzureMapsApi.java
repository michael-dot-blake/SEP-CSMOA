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

	private static final String STATE = "NSW";
	private static final String API_KEY = "5nXsFMSUBlUyt_Hvq0fgM6u6tKXy80wgwWfvZaLJuj0";
	private static final int TIME_BUDGET = 6000;

	public static Coordinates getCoordinatesFromAddress(String streetNo, String streetName, String suburbName,
			String postCode) throws IOException {

		streetNo = streetNo.trim();
		streetName = streetName.replace(" ", "%").trim();
		suburbName = suburbName.replace(" ", "%").trim();
		postCode = postCode.trim();

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
			Coordinates coord = new Coordinates(lat, lon);
			System.out.println("Address Location: " + coord);
			sc.close();
			return coord;

		}

	}

	public static void getIsochroneCoords(Coordinates coord, int timeBudgetInSeconds) throws IOException {
		URL url = new URL("https://atlas.microsoft.com/route/range/json?subscription-key=" + API_KEY
				+ "&api-version=1.0&query=" + coord + "&timeBudgetInSec=" + TIME_BUDGET);
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

	public static void main(String[] args) throws IOException {

		// test functionality for the api calls
		Coordinates coord = getCoordinatesFromAddress("13", "Bundle St", "Caddens", "2747");
		getIsochroneCoords(coord, 6000);

	}

}
