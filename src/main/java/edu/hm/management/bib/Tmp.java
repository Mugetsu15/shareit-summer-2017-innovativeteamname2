package edu.hm.management.bib;

public class Tmp {
	
	/**
	 * Checks if a given ISBN number is valid or not
	 * @param isbn ISBN number to check
	 * @return true if valid, else false
	 */
	private static boolean checkISBN13(String isbn)  {
		boolean flag = false;
		
		isbn = isbn.replace("-", "");
		
		int sum = 0;
        for(int c = 0; c < 12; c++ )  {
            int digit = Integer.parseInt(isbn.substring(c, c + 1));
            // Zur Berechnung der Prüfziffer bei der ISBN-13 werden alle zwölf Ziffern der noch unvollständigen ISBN addiert,
            // wobei die Ziffern mit gerader Position (also die zweite, vierte und so weiter) dreifachen Wert erhalten.
            int mult = 1;
            if((c+1) % 2 == 0)  {
            	mult = 3;
            }
            sum += digit * mult;
        }
        
        // Checksumme = (Die Zehnerpotenz, die größer als die Summe ist) - Summe
        int checksum = (sum / 10 + 1) * 10 - sum;
        
        //Ist das Endergebnis 10, ist die Prüfziffer 0.
        if(checksum == 10)  {
            checksum = 0;
        }
        
        if(checksum == Integer.parseInt(isbn.substring(12)))  {
        	flag = true;
        }

		return flag;
	}
	
	public static void main(String[] args)  {
		// https://de.wikipedia.org/wiki/Internationale_Standardbuchnummer#ISBN-13
		String isbn = "978-3-12-732320-7";
		System.out.println(checkISBN13(isbn));
	}
}