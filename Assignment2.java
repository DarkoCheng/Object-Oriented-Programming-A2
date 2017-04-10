package Assignment2;

import java.util.Scanner;

/** LibrarySearchDemo class
 *
 * @author Based on Fei Song, edited by me.
 *
 * Copyright: This code is intended to help you with the implementation of your assignments for CIS*2430 (Fall 2015).  
 *   You are allowed to use it as a starting point for your Assignments Two and Three, but you need to acknowledge it 
 *   in your README file.  Any use, sharing, or re-distribution beyond this course is prohibited.
 */

public class Assignment2 {
	public static void main( String[] args ){
		Scanner input = new Scanner( System.in );
		LibrarySearch library = new LibrarySearch();
		String command;
                if (args.length == 1){
                    try{
                        String file = args[0];
                        library.addFile(file);
                    }
                    catch(Exception e){
                        System.out.println("Canot open the file or cannot find the file");
                        System.exit(0);
                    }
                    System.out.println("Finish loading file");
                }
		do {
			System.out.print( "Enter add, search, or quit> " );
			command = input.nextLine();
			if( command.equalsIgnoreCase("add") || command.equalsIgnoreCase("a") )
				library.add( input );
			else if( command.equalsIgnoreCase("search") || command.equalsIgnoreCase("s") )
				library.search( input );			
		} while( !command.equalsIgnoreCase("quit") && !command.equalsIgnoreCase("q") );
	}
}
