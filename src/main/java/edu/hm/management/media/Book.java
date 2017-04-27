package edu.hm.management.media;

import java.util.ArrayList;
import java.util.List;

/**
 * Class represents a Book Object which has an author and an isbn.
 * @author Daniel Gabl
 *
 */
public class Book extends Medium {
	/**
	 * Author of this Book.
	 */
	private final String author;
	
	/**
	 * ID for this Book (unique).
	 */
	private final String isbn;
	
	/**
	 * Default Constructor for a Book Object, only for Jackson.
	 */
	private Book() {
		this("John Doe", "0", null);
	}
	
	/**
	 * List to save all Books.
	 */
	public static List<Book> books = new ArrayList<>();
	
	/**
	 * Extended Constructor for a Book Object.
	 * @param author Author of the Book.
	 * @param isbn ID of the Book.
	 */
	public Book(String author, String isbn, String title)  {
		super(title);
		this.author = author;
		this.isbn = isbn;
		
		// Erzeugtes Element in Liste speichern.
		//books.add(this);
	}
	
	/**
	 * Getter for the Author of the Book.
	 * @return author of book.
	 */
	public String getAuthor()  {
		return author;
	}
	
	/**
	 * Getter for ID of the Book.
	 * @return id of book.
	 */
	public String getIsbn()  {
		return isbn;
	}
	
	@Override
	public String toString()  {
		return String.format("This Book is called '%s', has the ID '' and was written by '%s'", super.getTitle(), isbn, author);
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
