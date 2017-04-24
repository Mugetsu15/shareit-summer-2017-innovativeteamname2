package edu.hm.management.bib;

import edu.hm.management.media.Book;
import edu.hm.management.media.Disc;
import edu.hm.management.media.Medium;

public interface IMediaService {
	
	/**
	 * Adds a given book to our Service Routine
	 * @param book Book to add.
	 * @return
	 */
	MediaServiceResult addBook(Book book);
	
	/**
	 * Adds a given disc to our Service Routine
	 * @param disc Disc to add.
	 * @return
	 */
	MediaServiceResult addDisc(Disc disc);
	
	/**
	 * Returns all Books of our Service routine.
	 * @return all Books of our Service routine.
	 */
	Medium[] getBooks();
	
	/**
	 * Returns all Discs of our Service routine.
	 * @return all Discs of our Service routine.
	 */
	Medium[] getDiscs();
	
	/**
	 * Updates a given Book in our Service Routine.
	 * @param book Book to update.
	 * @return
	 */
	MediaServiceResult updateBook(Book book);
	
	/**
	 * Updates a given Disc in our Service Routine.
	 * @param disc Disc to update.
	 * @return
	 */
	MediaServiceResult updateDisc(Disc disc);
}
