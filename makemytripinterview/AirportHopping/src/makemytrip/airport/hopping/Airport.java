package makemytrip.airport.hopping;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Airport {

	public String airportCode;
	
	public List<Flight> flights = new ArrayList<Flight>();
	
	public Airport(String code)
	{
		airportCode = code;
	}

	public String getAirportCode() {
		return airportCode;
	}

	public void setAirportCode(String airportCode) {
		this.airportCode = airportCode;
	}

	public List<Flight> getFlights() {
		return flights;
	}
	
	public List<Flight> getFlightsOfSameDay(String date) throws ParseException
	{
		SimpleDateFormat sdfo = new SimpleDateFormat("dd-MM-yyyy"); 
		Date d1 = sdfo.parse(date); 
		List<Flight> allFlights = new ArrayList<Flight>();
        for(Flight f : getFlights())
        {
        	if(sdfo.parse(f.getTravelDate()) == d1)
        	{
        		allFlights.add(f);
        	}
        }
        
        return allFlights;

	}

	
}
