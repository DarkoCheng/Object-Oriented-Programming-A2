package Assignment2;

/** Journal class
 *
 * @author @author Based on Fei Song, edited by Darko.
 *
 * A class for representing and comparing journals.
 *
 * Copyright: This code is intended to help you with the implementation of your assignments for CIS*2430 (Fall 2015).  
 *   You are allowed to use it as a starting point for your Assignments Two and Three, but you need to acknowledge it 
 *   in your README file.  Any use, sharing, or re-distribution beyond this course is prohibited.
 */

public class Journal extends Reference implements Ireferences{
	private String organizer;   // organizer of a journal
	
	/**
	 * Create a journal with all the required fields
	 */
	public Journal(String callNumber, String title, String organizer, int year) {
		if( valid(callNumber, title, year) ) {
			setCallNumber(callNumber);
			setTitle(title);
			this.organizer = organizer;
                        setYear(year);
		} else {			
			System.out.println("Invalid values for creating a journal");
			System.exit(0);
		}
	}
	
	/**
	 * Create a journal with only callNumber, title, and year
	 */
	public Journal(String callNumber, String title, int year) {
		this(callNumber, title, "", year);
	}
	
	/**
	 * A static method for validating if the information for a journal is valid
	 */
	public static boolean valid(String callNumber, String title, int year) {
		return callNumber != null && !callNumber.isEmpty() && title != null && !title.isEmpty() && year >= 1000 & year <= 9999;
	}

	/**
	 * Set a new value for organizer
	 */
	public void setOrganizer(String organizer) {
		this.organizer = organizer;
	}

	/**
	 * Get the value of organizer
	 */
	public String getOrganizer() {
		return organizer;
	}

	/**
	 * Check for the equality of two journals
	 */
	public boolean equals(Journal other) {
		if (other == null)
			return false;
		else 
			return getCallNumber().equalsIgnoreCase(other.getCallNumber()) &&
			       getTitle().equalsIgnoreCase(other.getTitle()) &&
			       organizer.equalsIgnoreCase(other.organizer) &&
			       getYear() == other.getYear();
	}
	
	/**
	 * Show the content of a journal in a string
	 */
        @Override
	public String toString() {
		return "Journal: " + getCallNumber() + ". \"" +
			getTitle() + "\". " +
			organizer + ", " + getYear() + ".";
	}
}
