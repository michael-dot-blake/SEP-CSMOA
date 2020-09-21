package controller;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

public class PostRequest {
	
	public static void postJSON() throws IOException {
		URL url = new URL("https://atlas.microsoft.com/route/range/json?subscription-key=5nXsFMSUBlUyt_Hvq0fgM6u6tKXy80wgwWfvZaLJuj0&api-version=1.0&query=-33.8688,151.2093&timeBudgetInSec=6000");
		URLConnection con = url.openConnection();
		HttpURLConnection http = (HttpURLConnection)con;
		http.setRequestMethod("POST"); 
		http.setDoOutput(true);
		
		byte[] out = "{\"lat\":\"lng\"}" .getBytes(StandardCharsets.UTF_8);
		int length = out.length;

		http.setFixedLengthStreamingMode(length);
		http.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
		http.connect();
		try(OutputStream os = http.getOutputStream()) {
		    os.write(out);
		}
	}
	
	public static void main(String[] args) throws IOException {
		postJSON();
		
		
	}
}
