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

import org.apache.commons.lang3.StringUtils;

public class AzureMapsApi {

	private final String STATE = "NSW";
	private final String API_KEY = "5nXsFMSUBlUyt_Hvq0fgM6u6tKXy80wgwWfvZaLJuj0";

	public void getIsochroneCoords(double lat, double lon, int timeBudgetInSeconds) throws IOException {
		URL url = new URL("https://atlas.microsoft.com/route/range/json?subscription-key=" + API_KEY
				+ "&api-version=1.0&query=" + lat + "," + lon + "&timeBudgetInSec=" + timeBudgetInSeconds);
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

	public void getGeoDataFromAddress(int streetNo, String streetName, String suburbName, int postCode)
			throws IOException {
		
		streetName = StringUtils.deleteWhitespace(streetName);
		suburbName = StringUtils.deleteWhitespace(suburbName);
		
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
		System.out.println(responseCode);

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

		AzureMapsApi azm = new AzureMapsApi();
		azm.getGeoDataFromAddress(13, "Bundle St", "Caddens", 2747);
		// azm.getIsochroneCoords(-33.8688, 151.2093, 6000);

	}

}
