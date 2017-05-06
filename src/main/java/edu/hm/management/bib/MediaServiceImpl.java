package edu.hm.management.bib;

import edu.hm.management.media.Book;
import edu.hm.management.media.Disc;
import edu.hm.management.media.Medium;

public class MediaServiceImpl implements IMediaService {
	
	/**
	 * Default Constructor, only for Jackson
	 */
	public MediaServiceImpl()  {
			Book bk1 = new Book("Author-909-4", "978-1-56619-909-4", "Title-909-4");
			Book bk2 = new Book("Author-9462-6", "978-1-4028-9462-6", "Title-9462-6");
			Book bk3 = new Book("Richard Castle", "978-3-8642-5007-1", "Heat Wave");
			
            Disc ds1 = new Disc("978-1-56619-909-4", "Director-909-4", 12, "Title-909-4");
            Disc ds2 = new Disc("978-1-4028-9462-6", "Director-9462-6", 18, "Title-9462-6");
            Disc ds3 = new Disc("8-88837-34272-8", "James Arthur", 0, "Impossible");
			
            addBook(bk1);
            addBook(bk2);
            addBook(bk3);
            
            addDisc(ds1);
            addDisc(ds2);
            addDisc(ds3);
	}
	
	/**
	 * Checks if a given ISBN number is valid or not
	 * @param isbn ISBN number to check
	 * @return true if valid, else false
	 */
	private boolean checkISBN13(String isbn)  {
		boolean flag = false;
		
		isbn = isbn.replace("-", "");
		isbn = isbn.replace(" ", "");
		
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
	
	/**
	 * Removes all spaces and minus from the given String.
	 * @param isbn ISBN number to clean
	 * @return cleaned ISBN
	 */
	private String cleanISBN(String isbn)  {
		isbn = isbn.replace("-", "");
		isbn = isbn.replace(" ", "");
		
		return isbn;
	}

	@Override
	public MediaServiceResult addBook(Book book) {
		if (!book.getAuthor().isEmpty() && !book.getIsbn().isEmpty() && !book.getTitle().isEmpty()) {
			if(!Book.books.contains(book))  {
				boolean isbnExist = false;
				for(Book bk : Book.books)  {
					if(cleanISBN(bk.getIsbn()).equals(cleanISBN(book.getIsbn())))  {
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
		if (!disc.getDirector().isEmpty() && !disc.getBarcode().isEmpty() && !disc.getTitle().isEmpty()) {
			if(!Disc.discs.contains(disc))  {
				boolean barcodeExists = false;
				for(Disc ds : Disc.discs)  {
					if(cleanISBN(ds.getBarcode()).equals(cleanISBN(disc.getBarcode())))  {
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
		return MediaServiceResult.BADREQUEST;
	}

	@Override
	public Medium[] getBooks() {
		Book[] media = new Book[Book.books.size()];
		media = Book.books.toArray(media);
		return media;
	}

	@Override
	public Medium[] getDiscs() {
		Disc[] media = new Disc[Disc.discs.size()];
		media = Disc.discs.toArray(media);
		return media;
	}

	@Override
	public MediaServiceResult updateBook(Book book) {
		if(!Book.books.contains(book))  {
			for(int c = 0; c < Book.books.size(); c++)  {
				Book bk = Book.books.get(c);
				if(cleanISBN(bk.getIsbn()).equals(cleanISBN(book.getIsbn())))  {
					String title = bk.getTitle();
					String author = bk.getAuthor();
					
					if(!bk.getAuthor().equals(book.getAuthor()) && !book.getAuthor().isEmpty())  {
						author = book.getAuthor();
					}
					if(!bk.getTitle().equals(book.getTitle()) && !book.getTitle().isEmpty())  {
						title = book.getTitle();
					}
					
					MediaServiceResult result = MediaServiceResult.DUPLICATEOBJ;
					
					if(!cleanISBN(bk.getIsbn()).equals(cleanISBN(book.getIsbn())))  {
						Book.books.remove(c);
						Book newbook = new Book(author, book.getIsbn(), title);
						result = addBook(newbook);
					}
					
					return result;
				}
			}
		}
		return MediaServiceResult.ISBNNOTFOUND;
	}

	@Override
	public MediaServiceResult updateDisc(Disc disc) {
		if(!Disc.discs.contains(disc))  {
			for(int c = 0; c < Disc.discs.size(); c++)  {
				Disc ds = Disc.discs.get(c);
				if(cleanISBN(ds.getBarcode()).equals(cleanISBN(disc.getBarcode())))  {
					String director = ds.getDirector();
					int fsk = ds.getFsk();
					String title = ds.getTitle();
					
					if(!ds.getDirector().equals(disc.getDirector()) && !disc.getDirector().isEmpty())  {
						director = disc.getDirector();
					}
					if(ds.getFsk() != disc.getFsk() && disc.getFsk() != 0)  {
						fsk = disc.getFsk();
					}
					if(!ds.getTitle().equals(disc.getTitle()) && !disc.getTitle().isEmpty())  {
						title = disc.getTitle();
					}
					
					MediaServiceResult result = MediaServiceResult.DUPLICATEOBJ;
					if(!cleanISBN(ds.getBarcode()).equals(cleanISBN(disc.getBarcode())))  {
						Disc.discs.remove(c);
						Disc newdisc = new Disc(disc.getBarcode(), director, fsk, title);
						result = addDisc(newdisc);
					}
					
					return result;
				}
			}
		}
		return MediaServiceResult.ISBNNOTFOUND;
	}

	@Override
	public Medium findBook(String isbn) {
		for (Book bk : Book.books)  {
			if(cleanISBN(bk.getIsbn()).equals(cleanISBN(isbn)))  {
				return bk;
			}
		}
		return null;
	}

	@Override
	public Medium findDisc(String barcode) {
		for (Disc ds : Disc.discs)  {
			if(cleanISBN(ds.getBarcode()).equals(cleanISBN(barcode)))  {
				return ds;
			}
		}
		return null;
	}
	
	
}
