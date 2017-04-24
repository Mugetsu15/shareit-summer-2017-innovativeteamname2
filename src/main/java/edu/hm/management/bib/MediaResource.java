package edu.hm.management.bib;


import javax.ws.rs.core.Response;

import edu.hm.management.media.Book;
import edu.hm.management.media.Disc;

/**
 * Class manages books.
 * @author Daniel Gabl
 *
 */
public class MediaResource {
	
	/**
	 * Creates a given Book.
	 * @param book Book to create.
	 * @return JSON response.
	 */
	Response createBook(Book book)  {
		return null;
	}
	
	/**
	 * Returns all books in our Service.
	 * @return JSON response.
	 */
	Response getBooks()  {
		return null;
	}
	
	/**
	 * Updates a given Book.
	 * @param book Book to update
	 * @return JSON response.
	 */
	Response updateBook(Book book)  {
		return null;
	}
	
	/**
	 * Creates a given Book.
	 * @param book Book to create.
	 * @return JSON response.
	 */
	Response createDisc(Disc disc)  {
		return null;
	}
	
	/**
	 * Returns all books in our Service.
	 * @return JSON response.
	 */
	Response getDiscs()  {
		return null;
	}
	
	/**
	 * Updates a given Book.
	 * @param book Book to update
	 * @return JSON response.
	 */
	Response updateDisc(Disc disc)  {
		return null;
	}
}
