package edu.hm.management.media;

/**
 * Class represents a Copy of a Medium which has a Medium Object and an owner.
 * @author Daniel Gabl
 *
 */
public class Copy {
	
	/**
	 * Medium which was copied.
	 */
	private final Medium medium;
	
	/**
	 * User who lend this copy.
	 */
	private final String owner;
	
	/**
	 * Constructor for a Copy Object.
	 * @param owner Owner of the Copy.
	 * @param medium Medium which was lend.
	 */
	Copy(String owner, Medium medium)  {
		this.owner = owner;
		this.medium = medium;
	}
	
	/**
	 * Getter for the Medium.
	 * @return medium.
	 */
	Medium getMedium()  {
		return medium;
	}
	
	/**
	 * Getter for the Username.
	 * @return username.
	 */
	String getUsername()  {
		return owner;
	}

}
