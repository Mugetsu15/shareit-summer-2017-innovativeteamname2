package edu.hm.management.media;

import java.util.ArrayList;
import java.util.List;

/**
 * Class represents a Disc Object which has a barcode, a director and a fsk.
 * @author Daniel Gabl
 *
 */
public class Disc extends Medium {
	
	/**
	 * Barcode of the Disc (unique).
	 */
	private final String barcode;
	
	/**
	 * Creator of the Disc.
	 */
	private final String director;
	
	/**
	 * Age-Restriction for the Disc.
	 */
	private final int fsk;
	
	/**
	 * List to save all Discs.
	 */
	public static List<Disc> discs = new ArrayList<>();
	
	/**
	 * Default-Constructor for a Disc Object, only for Jackson.
	 */
	private Disc() {
		this("0", "John Doe", 0, null);
	}
	
	/**
	 * Extended Constructor for a Disc Object.
	 * @param barcode Barcode of a Disc
	 * @param director Director of a Disc
	 * @param fsk Age-Restriction of a Disc
	 * @param title Title of this Disc
	 */
	Disc(String barcode, String director, int fsk, String title)  {
		super(title);
		this.barcode = barcode;
		this.director = director;
		this.fsk = fsk;
		
		//discs.add(this);
	}
	
	/**
	 * Getter for the Barcode of a Disc.
	 * @return barcode of this disc.
	 */
	public String getBarcode()  {
		return barcode;
	}
	
	/**
	 * Getter for the Director of a Disc.
	 * @return director of a disc.
	 */
	public String getDirector()  {
		return director;
	}
	
	/**
	 * Getter for the Age-Restriction of a Disc.
	 * @return age restriction of a disc.
	 */
	public int getFsk()  {
		return fsk;
	}
	
	@Override
	public String toString()  {
		return String.format("Information about this Disc: Title: '%s', Barcode: '%s', Director: '%s', FSK: %d", super.getTitle(), barcode, director, fsk);
	}
	
	@Override
	public int hashCode()  {
		return super.hashCode();
	}
	
	@Override
	public boolean equals(Object obj)  {
		return super.equals(obj);
	}

}
