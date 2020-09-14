package controller;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Objects;

public class ClockTicker extends Clock {

	  public static void log(Object msg){
	    System.out.println(Objects.toString(msg));
	  }
	  
	  @Override public ZoneId getZone() {
	    return DEFAULT_TZONE;
	  }
	  
	  @Override public Clock withZone(ZoneId zone) {
	    return Clock.fixed(WHEN_STARTED, zone);
	  }
	  
	 
	  @Override public Instant instant() {
	    return nextInstant();
	  }
	  
	  //PRIVATE 
	  private final Instant WHEN_STARTED = Instant.now();
	  private final ZoneId DEFAULT_TZONE = ZoneId.systemDefault();
	  private long count = 0;
	  private LocalDateTime myDateTime;
	  
	  
	  /*
	   * The following all progress time at a different rate
	   * Rename the method to nextInstant() in order to have
	   * time progress at the given rate. The default will be 
	   * seconds
	   */
	  
	  //A ticker which returns time progressing in seconds
//	  private Instant nextInstant() {
//	    ++count;
//	    return WHEN_STARTED.plusSeconds(count);
//	 
//	  }
//	  
	  //A ticker which returns time progressing in minutes
	  private Instant nextInstant() {
		    ++count;
		    return WHEN_STARTED.plusSeconds(count * 60);
		 
		  }
//	  
//	  //A ticker which returns time progressing in hours
//	  public Instant nextInstant() {
//		    ++count;
//		    return WHEN_STARTED.plusSeconds(count * 3600);
//		 
//		  }
	  
	  public long getCount() {
		  return count;
	  }
	  
	  public LocalDateTime getCustomDateAndTime() {
		return myDateTime;
	  }
	  
	  
	  
	  public static void main(String[] args) {

		  
		  //A testing function which progresses time by one minute each time a a while loop executes
		  long runtime = 10;
		  int count = 0;
		  LocalDate ld = LocalDate.of( 2020 , 11 , 23 );
		  LocalTime lt = LocalTime.MIN;
		  LocalDateTime myDateTime = LocalDateTime.of(ld, lt);
		  do {
			  System.out.println(myDateTime);
			  myDateTime = myDateTime.plusMinutes(1);
			  count++;
		  } while (count <= runtime); 
		  	  
		  
	}
	  
	  
	 
}
