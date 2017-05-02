package edu.hm.management.bib;

import edu.hm.management.media.Book;
import edu.hm.management.media.Disc;
import edu.hm.management.media.Medium;

public class MediaServiceImpl implements IMediaService {
	
	/**
	 * Default Constructor, only for Jackson
	 */
	public MediaServiceImpl()  {
		
	}
	
	/**
	 * Checks if a given ISBN number is valid or not
	 * @param isbn ISBN number to check
	 * @return true if valid, else false
	 */
	private boolean checkISBN13(String isbn)  {
		boolean flag = false;
		
		isbn = isbn.replace("-", "");
		
		int sum = 0;
		try  {
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
		} catch(NumberFormatException | StringIndexOutOfBoundsException e)  {
			flag = false;
		}
		
		// https://de.wikipedia.org/wiki/Internationale_Standardbuchnummer#ISBN-13
		// String isbn = "978-3-12-732320-7";

		return flag;
	}

	@Override
	public MediaServiceResult addBook(Book book) {
		if (!book.getAuthor().isEmpty() && !book.getIsbn().isEmpty() && !book.getTitle().isEmpty()) {
			if(!Book.books.contains(book))  {
				boolean isbnExist = false;
				for(Book bk : Book.books)  {
					if(bk.getIsbn().replace("-", "").equals(book.getIsbn().replace("-", "")))  {
						isbnExist = true;
						break;
					}
				}
				if(!isbnExist)  {
					if(checkISBN13(book.getIsbn()))  {
						Book.books.add(book);
						return MediaServiceResult.OKAY;
					}  else  {
						return MediaServiceResult.ISBNBROKEN;
					}
				}  else  {
					return MediaServiceResult.DUPLICATEISBN;
				}
			}
			return MediaServiceResult.DUPLICATEOBJ;
		}
		return MediaServiceResult.BADREQUEST;
	}

	@Override
	public MediaServiceResult addDisc(Disc disc) {
		if(!Disc.discs.contains(disc))  {
			boolean barcodeExists = false;
			for(Disc ds : Disc.discs)  {
				if(ds.getBarcode().replace("-", "").equals(disc.getBarcode().replace("-", "")))  {
					barcodeExists = true;
					break;
				}
			}
			if(!barcodeExists)  {
				if(checkISBN13(disc.getBarcode()))  {
					Disc.discs.add(disc);
					return MediaServiceResult.OKAY;
				}  else  {
					return MediaServiceResult.ISBNBROKEN;
				}
			}  else  {
				return MediaServiceResult.DUPLICATEISBN;
			}
		}
		return MediaServiceResult.DUPLICATEOBJ;
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
