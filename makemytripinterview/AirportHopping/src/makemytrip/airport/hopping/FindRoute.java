package makemytrip.airport.hopping;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FindRoute {
	
	public HashMap<String, Airport> allAirports = new HashMap<String, Airport>();

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		FindRoute fr = new FindRoute();
		Airport a1 = new Airport("BOM");
		Airport a2 = new Airport("Delhi");
		Airport a3 = new Airport("KOl");
		Airport a4 = new Airport("HUL");
		
		fr.allAirports.put("BOM", a1);		
		fr.allAirports.put("Delhi", a2);
		fr.allAirports.put("KOl", a3);
		fr.allAirports.put("HUL", a4);
		
		Flight f1 = new Flight(123, "BOM", "Delhi");
		
		Flight f2 = new Flight(234, "Delhi", "KOl");
		
		Flight f3 = new Flight(567, "KOl", "Delhi");
		
		
		a1.getFlights().add(f1);
		a2.getFlights().add(f2);
		a3.getFlights().add(f3);
		
			
		
		List<String> nonReachableAirports = fr.getUnreachableAirports("BOM");
		for(String s : nonReachableAirports)
		{
			System.out.println(s);
		}
		
		if(nonReachableAirports.isEmpty())
		{
			throw new Exception("No such airport axists!!");
		}

	}
	
	List<String> getUnreachableAirports(String city) throws ParseException
	{
		Airport airportToStart = allAirports.get(city);
		HashMap<String, Boolean> allVisitedAirports = new HashMap<String, Boolean>();
		Set<String> airports = allAirports.keySet();
		for(String airport : airports)
		{
			allVisitedAirports.put(airport, false);
		}
		
		markAllReachableAirport(airportToStart, allVisitedAirports);
		List<String> nonVisitedAirports = new ArrayList<String>();
		for(Map.Entry<String, Boolean> entry : allVisitedAirports.entrySet())
		{
			if(!entry.getValue())
			{
				nonVisitedAirports.add(entry.getKey());
			}
		}
		return nonVisitedAirports;
	}
	
	public void markAllReachableAirport(Airport airport, HashMap<String, Boolean> allVisitedAirports) throws ParseException
	{
		
		LinkedList<Airport> queue = new LinkedList<Airport>();
		allVisitedAirports.put(airport.getAirportCode(), true);
		queue.add(airport);
		
		while(queue.size() != 0)
		{
			Airport a1 = queue.poll();
			
			List<Flight> allFlights = a1.getFlights();
			for(Flight f : allFlights)
			{
				String reachableAirportCode = f.getArrivalAirport();
				if(!allVisitedAirports.get(reachableAirportCode))
				{
					allVisitedAirports.replace(reachableAirportCode, true);
					Airport airportToAddInQueue = allAirports.get(reachableAirportCode);
					queue.add(airportToAddInQueue);
				}
			}
		}
	}
}
