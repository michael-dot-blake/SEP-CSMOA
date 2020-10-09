package controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Polygon;

import com.google.gson.JsonObject;

import model.GST;


/**
 * @author Michael Blake
 * 
 * A class to test the functionality of sorting travel times
 * and returning the lowest number from the Azure Maps Api
 *
 */
public class TestRouteTimeSorting {

	/**
	 * Main test method. Will check if GSTs are within isochrone then sort
	 * them in ascending order of travel time. Once sorted, the method returns
	 * the GST object at index 0
	 * 
	 * @param jobCoord
	 * @param timeBudgetInSeconds
	 * @param departureTime
	 * @return GST
	 * @throws IOException
	 */
	public static GST findClosestGst(Coordinate jobCoord, int timeBudgetInSeconds, LocalDateTime departureTime)
			throws IOException {

		String path2 = "GSTFiles/gstTestData.csv";
		GSTFactory.readGSTsFromCSV(path2);

		JsonObject jsonObj = AzureMapsApi.getIsochroneCoords(jobCoord, timeBudgetInSeconds, departureTime);
		Polygon poly = AzureMapsApi.BuildPolygon(jsonObj);
		ArrayList<GST> nearbyGSTs = new ArrayList<GST>();

		for (GST g : GSTFactory.getGSTpool()) {
			Coordinate gstCoord = new Coordinate(g.getLat(), g.getLon());
			if (AzureMapsApi.checkIfLocationInIsochrone(poly, gstCoord)) {
				int travelTime = AzureMapsApi.getRouteTime(gstCoord, jobCoord);
				g.setTravelTime(travelTime);
				nearbyGSTs.add(g);

			}
		}
		
		System.out.println("GSTs in isochrone " + nearbyGSTs);

		System.out.println("Unsorted");
		for (GST gst : nearbyGSTs) {
			System.out.println(gst.getTravelTime() + " " + gst.getgSTid());
		}

		Collections.sort(nearbyGSTs);

		System.out.println("Sorted");
		for (GST gst : nearbyGSTs) {
			System.out.println(gst.getTravelTime() + " " + gst.getgSTid());
		}

		System.out.println("The Closest GST was " + nearbyGSTs.get(0));
		return nearbyGSTs.get(0);

	}

	public static void main(String[] args) throws IOException {
		
		Coordinate coord = AzureMapsApi.getCoordinatesFromAddress("15", "Sully St", "Randwick", "2031");
		System.out.println(coord);
		findClosestGst(coord, 1800, LocalDateTime.now());

	}

}