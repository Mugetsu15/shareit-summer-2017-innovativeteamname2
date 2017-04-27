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
		if(!Disc.discs.contains(disc))  {
			boolean barcodeexists = false;
			for(Disc ds : Disc.discs)  {
				if(ds.getBarcode().equals(disc.getBarcode()))  {
					barcodeexists = true;
					break;
				}
			}
			if(!barcodeexists)  {
				Disc.discs.add(disc);
				return MediaServiceResult.OKAY;
			}
		}
		return MediaServiceResult.BADREQUEST;
	}

	@Override
	public Medium[] getBooks() {
		Medium[] media = new Medium[Book.books.size()];
		media = Book.books.toArray(media);
		return media;
	}

	@Override
	public Medium[] getDiscs() {
		Medium[] media = new Medium[Disc.discs.size()];
		media = Disc.discs.toArray(media);
		return media;
	}

	@Override
	public MediaServiceResult updateBook(Book book) {
		if(!Book.books.contains(book))  {
			for(int c = 0; c < Book.books.size(); c++)  {
				Book bk = Book.books.get(c);
				if(bk.getIsbn().equals(book.getIsbn()))  {
					Book.books.remove(c);
					
					String title = bk.getTitle();
					String author = bk.getAuthor();
					
					if(!bk.getAuthor().equals(book.getAuthor()))  {
						author = book.getAuthor();
					}
					if(!bk.getTitle().equals(book.getTitle()))  {
						title = book.getTitle();
					}
					
					Book newbook = new Book(author, book.getIsbn(), title);
					Book.books.add(newbook);
					
					return MediaServiceResult.OKAY;
				}
			}
		}
		return MediaServiceResult.BADREQUEST;
	}

	@Override
	public MediaServiceResult updateDisc(Disc disc) {
		if(!Disc.discs.contains(disc))  {
			for(int c = 0; c < Disc.discs.size(); c++)  {
				Disc ds = Disc.discs.get(c);
				if(ds.getBarcode().equals(disc.getBarcode()))  {
					Disc.discs.remove(c);
					
					String director = ds.getDirector();
					int fsk = ds.getFsk();
					String title = ds.getTitle();
					
					if(!ds.getDirector().equals(disc.getDirector()))  {
						director = disc.getDirector();
					}
					if(ds.getFsk() != disc.getFsk())  {
						fsk = disc.getFsk();
					}
					if(!ds.getTitle().equals(disc.getTitle()))  {
						title = disc.getTitle();
					}
					
					Disc newdisc = new Disc(director, disc.getBarcode(), fsk, title);
					Disc.discs.add(newdisc);
					
					return MediaServiceResult.OKAY;
				}
			}
		}
		return MediaServiceResult.BADREQUEST;
	}
	
	
}
