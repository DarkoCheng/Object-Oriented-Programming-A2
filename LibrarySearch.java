package Assignment2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/** LibrarySearch class
 *
 * @author @author Based on Fei Song, edited by Darko.
 *
 * A class that adds, maintains, and searches for books and journals.
 *
 * Copyright: This code is intended to help you with the implementation of your assignments for CIS*2430 (Fall 2015).  
 *   You are allowed to use it as a starting point for your Assignments Two and Three, but you need to acknowledge it 
 *   in your README file.  Any use, sharing, or re-distribution beyond this course is prohibited.
 */

public class LibrarySearch {

        private ArrayList<Reference> references;
        HashMap<String,ArrayList<Integer>> hm = new HashMap<String,ArrayList<Integer>>();  // hash map wiz array as value
        ArrayList<Integer> value = new ArrayList<>();
	
	public static final String[] REFERENCE_TYPES = new String[]{"book", "journal", "b", "j"};

	/**
	 * Create an instance of LibrarySearch
	 */
	public LibrarySearch() {
                references = new ArrayList<Reference>();
	}

	/*
	 * Add a valid book
	 */
	private boolean addBook( Book book ) {
            String titleTokeniz[];
            int j;
            for( int i = 0; i < references.size(); i++ ) 
                    if( references.get(i).keyEquals(book) )
                            return false;
            references.add( book );
            titleTokeniz = book.getTitle().split("[ ,\n]+");
            for( j = 0; j < titleTokeniz.length; j++ ){
                if (hm.get(titleTokeniz) == null){   // a new key
                    hm.put(titleTokeniz[j].toString(), new ArrayList<Integer>());
                    hm.get(titleTokeniz[j].toString()).add(references.size());
                }
                else{                           // key already here
                    hm.get(titleTokeniz[j].toString()).add(references.size());
                }
            }
            Reference.catalogItem("output.txt", "book", book.getCallNumber(), book.getYear(), book.getPublisher(), book.getTitle(), book.getAuthors());
            return true;
	}
	
	/*
	 * Add a valid journal
	 */
	private boolean addJournal( Journal journal ) {
                String titleTokeniz[] = titleTokeniz = journal.getTitle().split("[ ,\n]+");;
                int j;
		for( int i = 0; i < references.size(); i++ ) 
			if( references.get(i).keyEquals(journal) )
				return false;
		references.add( journal );
                for( j = 0; j < titleTokeniz.length; j++ ){
                if (hm.get(titleTokeniz) == null){   // a new key
                    hm.put(titleTokeniz[j].toString(), new ArrayList<Integer>());
                    hm.get(titleTokeniz[j].toString()).add(references.size());
                }
                else{                           // key already here
                    hm.get(titleTokeniz[j].toString()).add(references.size());
                }
            }
                Reference.catalogItem("output.txt", "journal", journal.getCallNumber(), journal.getYear(), journal.getOrganizer(), journal.getTitle());
		return true;
	}
	
	/** 
	 * Create a book or journal from the input and add it to the appropriate list
	 */
	public void add( Scanner input ) {
		String type;
		do {
			System.out.print( "Enter a reference type (book or journal)> " );
			type = input.nextLine();
		} while( !matchedKeyword(type, REFERENCE_TYPES) );
		
                String callNumber = "";
                do {
			System.out.print( "Enter a call number> " );
			callNumber = input.nextLine();
                } while( callNumber.isEmpty() );
		
		String authors = "";
		if( type.equalsIgnoreCase("book") || type.equalsIgnoreCase("b") ) {
			System.out.print("Enter a list of authors separated by comma> ");
			authors = input.nextLine();
		}

                String title = "";
                do {
			System.out.print( "Enter a title> " );
			title = input.nextLine();
                } while( title.isEmpty() );

		String publisher = "";
		if( type.equalsIgnoreCase("book") || type.equalsIgnoreCase("b") ) {
			System.out.print("Enter a publisher> ");
			publisher = input.nextLine();
		}

		String organizer = "";
		if( type.equalsIgnoreCase("journal") || type.equalsIgnoreCase("j") ) {
			System.out.print("Enter an organizer> ");
			organizer = input.nextLine();
		}

		int year = 0;
		do {
			System.out.print("Enter a year (between 1000 and 9999)>");
			String yearValue = input.nextLine();
			year = yearValue.isEmpty() ? 0 : Integer.parseInt(yearValue);
		} while( year < 1000 || year > 9999 );
		
		boolean result = true;
		if( type.equalsIgnoreCase("book") || type.equalsIgnoreCase("b") ){
			result = addBook( new Book(callNumber, authors, title, publisher, year) );
                }
		else
			result = addJournal( new Journal(callNumber, title, organizer, year) );
		if( !result ) 
			System.out.println( "Add failed: there is another reference with the same call number and year" );
	}

	/* 
	 * Check if a keyword is on a list of tokens
	 */
	private boolean matchedKeyword( String keyword, String[] tokens ) {
		for( int i = 0; i < tokens.length; i++ ) 
			if( keyword.equalsIgnoreCase(tokens[i]) )
				return true;
		return false;
	}

	/*
	 * Check if all keywords are in a string 
	 */
	private boolean matchedKeywords( String[] keywords, String title ) {
		String[] tokens = title.split( "[ ,\n]+" );
		for( int i = 0; i < keywords.length; i++ ) 
			if( !matchedKeyword(keywords[i], tokens) )
				return false;
		return true;
	}

	/*
	 * Search for all books that satisfy a search request
	 */
	private void searchBooks( String callNumber, String[] keywords, int startYear, int endYear ) {
                for( int i = 0; i < references.size(); i++ ){
                if( (callNumber.equals("") || references.get(i).getCallNumber().equalsIgnoreCase(callNumber)) &&
                    (keywords == null || matchedKeywords(keywords, references.get(i).getTitle())) &&
                    (references.get(i).getYear() >= startYear && references.get(i).getYear() <= endYear) )
                    System.out.println( references.get(i) );
                }
	}

	/**
	 * Gather a search request and find the matched books and journals in the related list(s)
	 */ 
	public void search( Scanner input ) {
		
		System.out.print( "Enter a call number> " );
		String callNumber = input.nextLine();

		System.out.print( "Enter title keywords> " );
		String[] keywords = null;
		String line = input.nextLine();
		if( !line.isEmpty() ){
			keywords = line.split( "[ ,\n]+" );
                        
                }
		int startYear = Integer.MIN_VALUE, endYear = Integer.MAX_VALUE;
		boolean valid;
		do {
			valid = true;
			System.out.print("Enter a time period as startYear-endYear, or year> ");
			line = input.nextLine();
			if( !line.equals("") ) {
				int hyphen = line.indexOf('-');
				if( hyphen < 0 ) 
					startYear = endYear = Integer.parseInt(line);
				else {
					String startValue = line.substring(0, hyphen);
					startYear = startValue.equals("") ? Integer.MIN_VALUE : Integer.parseInt(startValue);
					String endValue = line.substring(hyphen + 1);
					endYear = endValue.equals("") ? Integer.MAX_VALUE : Integer.parseInt(endValue);
					if( startYear > endYear ) 
						valid = false;
				}
			}
		} while( !valid );

		System.out.println( "Matched references: " );
		searchBooks( callNumber, keywords, startYear, endYear );
	}
        
        public void addFile( String file ) throws FileNotFoundException{
            int type = 0;
            String next = "";
            String callNumber = "";
            String authors = "";
            String title = "";
            Scanner inputStream = null;
            int year;
            String organization = "";
            String publisher = "";
            inputStream = new Scanner(new FileInputStream(file));
            while(inputStream.hasNext()){
                next = inputStream.nextLine();
                if (next.contains("book") && next.contains("type")){
                    type = 0;
                    continue;
                }
                else if (next.contains("journal") && next.contains("type")){
                    type = 1;
                    continue;
                }
                if ( type == 0){
                    if (next.contains("callnumber")){
                        String newString = next.substring(next.indexOf("\"")+1, next.lastIndexOf("\""));
                        callNumber = newString;
                    }
                    else if (next.contains("authors")){
                        String newString = next.substring(next.indexOf("\"")+1, next.lastIndexOf("\""));
                        authors = newString;
                    }
                    else if (next.contains("title")){
                        String newString = next.substring(next.indexOf("\"")+1, next.lastIndexOf("\""));
                        title = newString;
                    }
                    else if (next.contains("publisher")){
                         String newString = next.substring(next.indexOf("\"")+1, next.lastIndexOf("\""));
                         publisher = newString;
                    }
                    else if (next.contains("year")){
                        String newString = next.substring(next.indexOf("\"")+1, next.lastIndexOf("\""));
                        year = Integer.parseInt(newString);
                        addBook( new Book(callNumber, authors, title, publisher, year) );
                    }  
                }
                else if (type == 1){
                    if (next.contains("callnumber")){
                        String newString = next.substring(next.indexOf("\"")+1, next.lastIndexOf("\""));
                        callNumber = newString;
                    }
                    else if (next.contains("title")){
                        String newString = next.substring(next.indexOf("\"")+1, next.lastIndexOf("\""));
                        title = newString;
                    }
                    else if (next.contains("organization")){
                        String newString = next.substring(next.indexOf("\"")+1, next.lastIndexOf("\""));
                         organization = newString;
                    }
                    else if (next.contains("year")){
                        String newString = next.substring(next.indexOf("\"")+1, next.lastIndexOf("\""));
                        year = Integer.parseInt(newString);
                        addJournal( new Journal(callNumber, title, organization, year) );
                    }  
                    
                }
            }
        }
}
