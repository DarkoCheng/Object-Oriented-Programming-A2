package Assignment2;

/** Book class
 *
 * @author @author Based on Fei Song, edited by Darko.
 *
 * A class for representing and comparing books.
 * 
 * Copyright: This code is intended to help you with the implementation of your assignments for CIS*2430 (Fall 2015).  
 *   You are allowed to use it as a starting point for your Assignments Two and Three, but you need to acknowledge it 
 *   in your README file.  Any use, sharing, or re-distribution beyond this course is prohibited.
 */

public class Book extends Reference implements Ireferences{
	private String authors;     // one or multiple authors separated by commas
	private String publisher;   // publisher of a book
	
	/**
	 * Create a book with all the required fields
	 */
	public Book(String callNumber, String authors, String title, String publisher, int year) {
		if( valid(callNumber, title, year) ) {
                        setCallNumber(callNumber);
			this.authors = authors;
			setTitle(title);
			this.publisher = publisher;
			setYear(year);
		} else {			
			System.out.println("Invalid values for creating a book");
			System.exit(0);
		}
	}
	
	/**
	 * Create a book with only callNumber, title, and year
	 */
	public Book(String callNumber, String title, int year) {
		this(callNumber, "", title, "", year);
	}
	
	/**
	 * A static method for validating if the information for a book is valid
	 */
	public static boolean valid(String callNumber, String title, int year) {
		return callNumber != null && !callNumber.isEmpty() && title != null && !title.isEmpty() && year >= 1000 & year <= 9999;
	}
	
	/**
	 * Set a new value for authors
	 */
	public void setAuthors(String authors) {
		this.authors = authors;
	}

	/**
	 * Set a new value for publisher
	 */
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	/**
	 * Get the value of authors
	 */
	public String getAuthors() {
		return authors;
	}
	
	/**
	 * Get the value of publisher
	 */
	public String getPublisher() {
		return publisher;
	}
	
	/**
	 * Check for the equality of two books
	 */
	public boolean equals(Book other) {
		if (other == null)
			return false;
		else 
			return getCallNumber().equalsIgnoreCase(other.getCallNumber()) &&
			       authors.equalsIgnoreCase(other.authors) &&
			       getTitle().equalsIgnoreCase(other. getTitle()) &&
			       publisher.equalsIgnoreCase(other.publisher) &&
			       getYear() == other.getYear();
	}

	/**
	 * Show the content of a book in a string
     * @return 
	 */
        @Override
	public String toString() {
		return "Book: " + getCallNumber() + ". " +
                        authors + ". \"" +
			getTitle() + "\". " +
			publisher + ", " + getYear() + ".";
	}
}
