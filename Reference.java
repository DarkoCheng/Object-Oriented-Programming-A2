/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignment2;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Darko
 */
public abstract class Reference implements Ireferences{

    private String callNumber;
    private String title;  
    private int year;
    
    public boolean setCallNumber(String callNumber) {
	if( callNumber == null || callNumber.isEmpty() ) {
		return false;
	} else {
		this.callNumber = callNumber;
		return true;
	}
    }
    public boolean setTitle(String title) {
        if( title == null || title.isEmpty() ) {
	    return false;
	} else {
	    this.title = title;
	    return true;
        }
    }
    public boolean setYear(int year) {
        if( year <1000 || year > 9999 ) {
	    return false;
	} else {
	    this.year = year;
	    return true;
	}
    }
    public String getCallNumber() {
        return callNumber;
    }
    public String getTitle() {
        return title;
    }
    public int getYear() {
        return year;
    }
    
    @Override
    public boolean keyEquals(Book other) {
		if( other == null )
		 	return false;
		else
			return getCallNumber().equalsIgnoreCase(other.getCallNumber()) &&
			       getYear() == other.getYear();
	}
    
    @Override
    public boolean keyEquals(Journal other) {
	if( other == null )
		 return false;
	else
		return getCallNumber().equalsIgnoreCase(other.getCallNumber()) && getYear() == other.getYear();
	}
    
    public static void catalogItem(String fileName, String type, String callnumber, int year, String publisher, String title, String authors){
         PrintWriter catalog;
         try {
            catalog = new PrintWriter(new BufferedWriter(new FileWriter(fileName, true)));
            catalog.println("type = " + "\"" + type + "\"");
            catalog.println("callnumber = " + "\"" + callnumber + "\"");
            catalog.println("authors = " + "\"" + authors + "\"");
            catalog.println("title = " + "\"" + title + "\"");
            catalog.println("publisher = " + "\"" + publisher  + "\"");
            catalog.println("year = " + "\"" + year  + "\"");
            catalog.println("\n");
            catalog.close();
        } catch (IOException ex) {
            Logger.getLogger(Reference.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void catalogItem(String fileName, String type, String callnumber, int year, String organizer, String title){
         PrintWriter catalog;
         try {
            catalog = new PrintWriter(new BufferedWriter(new FileWriter(fileName, true)));
            catalog.println("type = " + "\"" + type + "\"");
            catalog.println("callnumber = " + "\"" + callnumber + "\"");
            catalog.println("title = " + "\"" + title + "\"");
            catalog.println("organization = " + "\"" + organizer   + "\"");
            catalog.println("year = " + "\"" + year  + "\"");
            catalog.println("\n");
            catalog.close();
        } catch (IOException ex) {
            Logger.getLogger(Reference.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
