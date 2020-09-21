package controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class SimpleClock {
	
	public static void startClock() {
		
		LocalDate startDate = LocalDate.of(2020, 9, 15);
		LocalTime startTime = LocalTime.MIN;
		LocalDateTime startDateAndTime = LocalDateTime.of(startDate, startTime);
		
		LocalDate endDate = LocalDate.of(2020, 9, 16);
		LocalTime endTime = LocalTime.MIN;
		LocalDateTime endDateAndTime = LocalDateTime.of(endDate, endTime);
		
		do {
			startDateAndTime = startDateAndTime.plusMinutes(1);
			System.out.println(startDateAndTime);
		} while (startDateAndTime.isBefore(endDateAndTime));
		
	}

	public static void main(String[] args) {
		
		startClock();
		
	}

}
