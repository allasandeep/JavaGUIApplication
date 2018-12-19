/*******************************************************************************************************
  *                                                                                                  *
  *  CSCI 470/502          Assignment 8           Fall 2018                                          *
  *                                                                                                  *
  * Programmer : Bodipudi Pragna (Z1851660) ,Gayathri Sanikommu (Z1822939), Sandeep Alla (Z1821331)  *
  *                                                                                                  *
  * Due Date : 11/28/2018 11:59PM                                                                    *
  *                                                                                                  *
  * Purpose : This program contains a "Destination" class which has a variables,constructor, get     *
  * and set methods       					                                                         *
  *                                                                                                  *
  *******************************************************************************************************/

public class Destination
{
	// Instance variables
	private String dest_city;
	private int normal_miles,add_miles,FF_startMonth,FF_endMonth,FF_miles;
	
   /*************************************************
	 * Constructor to initialize instance variables *
	*************************************************/
	public Destination(String dest_city,int normal_miles,int FF_miles,int add_miles,int FF_startMonth,int FF_endMonth)
	{
		this.dest_city = dest_city;
		this.normal_miles = normal_miles;
		this.FF_miles = FF_miles;
		this.add_miles = add_miles;
		this.FF_startMonth = FF_startMonth;
		this.FF_endMonth = FF_endMonth;
	}

	/***********************************************************************
	 * get method for an instance variable that returns instance variable *
	************************************************************************/
	public String getDest_city()
	{
		return this.dest_city;
	}

	/***********************************************************************
	 * get method for an instance variable that returns instance variable *
	************************************************************************/
	public int getNormal_miles()
	{
		return normal_miles;
	}
	
	/***********************************************************************
	 * get method for an instance variable that returns instance variable *
	************************************************************************/
	public int getFF_miles()
	{
		return FF_miles;
	}
	
	/***********************************************************************
	 * get method for an instance variable that returns instance variable *
	************************************************************************/
	public int getAdd_miles()
	{
		return add_miles;
	}
	
	/***********************************************************************
	 * get method for an instance variable that returns instance variable *
	************************************************************************/
	public int getFF_startMonth()
	{
		return FF_startMonth;
	}
	
	/***********************************************************************
	 * get method for an instance variable that returns instance variable *
	************************************************************************/
	public int getFF_endMonth()
	{
		return FF_endMonth;
	}
}
