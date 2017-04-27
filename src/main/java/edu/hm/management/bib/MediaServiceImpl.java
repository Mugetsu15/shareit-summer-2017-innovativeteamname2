package edu.hm.management.bib;

import edu.hm.management.media.Book;
import edu.hm.management.media.Disc;
import edu.hm.management.media.Medium;

public class MediaServiceImpl implements IMediaService {
	
	/**
	 * Default Constructor, only for Jackson
	 */
	private MediaServiceImpl()  {
		
	}

	@Override
	public MediaServiceResult addBook(Book book) {
		if(!Book.books.contains(book))  {
			boolean isbnexist = false;
			for(Book bk : Book.books)  {
				if(bk.getIsbn().equals(book.getIsbn()))  {
					isbnexist = true;
					break;
				}
			}
			if(!isbnexist)  {
				Book.books.add(book);
				return MediaServiceResult.OKAY;
			}
		}
		return MediaServiceResult.BADREQUEST;
	}

	@Override
	public MediaServiceResult addDisc(Disc disc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Medium[] getBooks() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Medium[] getDiscs() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MediaServiceResult updateBook(Book book) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MediaServiceResult updateDisc(Disc disc) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
