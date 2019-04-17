import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.Comparator;

public class MileRedeemer 
{
	// Instance variables declaration
	private int remaining_miles;
	private static ArrayList<Destination> destinationList = new ArrayList<>();
	private Destination destinationArray; 
	
	// Reads data from file and creates objects and sorts them
	public void readDestinations(Scanner fileScanner) throws IOException
	{
		while(fileScanner.hasNextLine())
		{
			
			String s = fileScanner.nextLine();			
			String[] items = s.split(";|-");
			destinationArray = new Destination(items[0],Integer.parseInt(items[1]),Integer.parseInt(items[2]),Integer.parseInt(items[3]),Integer.parseInt(items[4]),Integer.parseInt(items[5]));
			destinationList.add(destinationArray);
		}
		
		Collections.sort(destinationList,new MileageComparator());
		fileScanner.close();
	}
	
	
	// Returns cities names in sorted way
	public static String[] getCityNames()
	{
			String[] cities = new String[destinationList.size()]; 
			int i=0;
			for(Destination d:destinationList)
			{
				cities[i]=d.getDest_city();
		        i++;
		    }
			Arrays.sort(cities);
			return cities;
	}
	
	// Returns possible tickets to user
	public String[] redeemMiles(int miles,int month)
	{
		remaining_miles = miles;		
		String s="";
		ArrayList<String> str = new ArrayList<>();
		ArrayList<Destination> dest = new ArrayList<>();
		
		for(Destination d:destinationList)
		{
			if(remaining_miles>=0)
			{
				if(d.getFF_startMonth()<=month && d.getFF_endMonth()>=month)
				{
					if(remaining_miles - d.getFF_miles() >= 0)
					{
						remaining_miles= remaining_miles - d.getFF_miles();
						dest.add(d);
					}
				}
				else
				{
					if(remaining_miles - d.getNormal_miles()>=0)
					{
						remaining_miles = remaining_miles - d.getNormal_miles();
						dest.add(d);
					}
				}
			
			}
		}
		
		for(Destination d:dest)
		{
			if(remaining_miles>=d.getAdd_miles())
			{
				remaining_miles = remaining_miles - d.getAdd_miles();
				s = "*A trip to "+d.getDest_city()+" in First class";
				str.add(s);
			}
			else
			{
				s = "*A trip to "+d.getDest_city()+" in economy class";
				str.add(s);
			}
		}
		
		String[] st = new String[dest.size()];;

		int i=0;
		for(String ss: str)
		{
			st[i] = ss;
			i++;
		}
		
		return st;
	}
	
	
	
	// Returns remaining miles
	public int getRemainingMiles()
	{
		return remaining_miles;
	}
	
	public Destination findDestination(String cityName)
	{
		for(Destination d:destinationList)
		{
			if(cityName == d.getDest_city())
	                break;
	    }
	
		return destinationArray;
		
	}
	
	// Class that compares two destination objects
	public class MileageComparator implements Comparator<Destination> 
	{
		public int compare(Destination d1, Destination d2) 
	    {
	       return (d2.getNormal_miles() - d1.getNormal_miles());
	    }
	}
}


