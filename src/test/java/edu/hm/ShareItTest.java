package edu.hm;

import static org.junit.Assert.*;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.hm.management.bib.Fsk;
import edu.hm.management.bib.IMediaService;
import edu.hm.management.bib.MediaServiceImpl;
import edu.hm.management.bib.MediaServiceResult;
import edu.hm.management.media.Book;
import edu.hm.management.media.Disc;
import edu.hm.management.media.Medium;

/**
 * Test Class for Media Service.
 * @author Daniel Gabl
 *
 */
public class ShareItTest {
    
    /**
     * Media Interface.
     */
    private final IMediaService service = new MediaServiceImpl();
    
    private Book bk1 = new Book("Richard Castle", "978-3864250101", "Frozen Heat");
    private Book bk2 = new Book("Richard Castle", "978-3864252969", "Deadly Heat");

    private Disc ds1 = new Disc("978-3864250101", "Director-Frozen", Fsk.FSK16.getFsk(), "Title-Frozen");
    private Disc ds2 = new Disc("978-3864252969", "Director-Deadly", Fsk.FSK16.getFsk(), "Title-Deadly");

    /**
     * Test on addBook.
     * Möglicher Fehler: Ungültige ISBN
     * Möglicher Fehler: ISBN bereits vorhanden
     * Möglicher Fehler: Autor oder Titel fehlt
     */
    @Test
    public void testAddBook() {
        // ISBN wrong
        Book isbnFalse = new Book("Hans", "isbnistfalsch", "ISBN ist falsch");
        MediaServiceResult result = service.addBook(isbnFalse);
        assertTrue(MediaServiceResult.ISBNBROKEN.getNote() == result.getNote());
        
        // Author / Title missing
        Book missingAuthor = new Book("", bk1.getIsbn(), "Autor fehlt");
        result = service.addBook(missingAuthor);
        assertTrue(MediaServiceResult.BADREQUEST.getNote() == result.getNote());
        
        Book missingTitle = new Book("Titel fehlt", bk1.getIsbn(), "");
        result = service.addBook(missingTitle);
        assertTrue(MediaServiceResult.BADREQUEST.getNote() == result.getNote());
        
        // Default Case
        result = service.addBook(bk1);
        assertTrue(MediaServiceResult.OKAY.getNote() == result.getNote());
        result = service.addBook(bk2);
        assertTrue(MediaServiceResult.OKAY.getNote() == result.getNote());
        
        // Book already exists
        result = service.addBook(bk2);
        assertFalse(MediaServiceResult.OKAY.getNote() == result.getNote());
        assertTrue(MediaServiceResult.DUPLICATEOBJ.getNote() == result.getNote());
        
        // ISBN exists
        Book isbnDuplicate = new Book("Not Richard Castle", bk1.getIsbn(), "Not Frozen Heat");
        result = service.addBook(isbnDuplicate);
        assertTrue(MediaServiceResult.DUPLICATEISBN.getNote() == result.getNote());
    }
    
    /**
     * Test on addDisc.
     * Möglicher Fehler: Ungültige Barcode
     * Möglicher Fehler: Barcode bereits vorhanden
     * Möglicher Fehler: Director oder Titel fehlt
     */
    @Test
    public void testAddDisc() {
        // Barcode wrong
        Disc barcodeFalse = new Disc("8-88837-34272-8", "James Arthur", Fsk.FSK0.getFsk(), "Impossible");
        MediaServiceResult result = service.addDisc(barcodeFalse);
        assertTrue(MediaServiceResult.ISBNBROKEN.getNote() == result.getNote());
        
        // Director / Title missing
        Disc missingDirector = new Disc(ds1.getBarcode(), "Titel fehlt", Fsk.FSK0.getFsk(), "");
        result = service.addDisc(missingDirector);
        assertTrue(MediaServiceResult.BADREQUEST.getNote() == result.getNote());
        
        Disc missingTitle = new Disc(ds1.getBarcode(), "", Fsk.FSK0.getFsk(), "Director fehlt");
        result = service.addDisc(missingTitle);
        assertTrue(MediaServiceResult.BADREQUEST.getNote() == result.getNote());
        
        // Default Case
        result = service.addDisc(ds1);
        assertTrue(MediaServiceResult.OKAY.getNote() == result.getNote());
        result = service.addDisc(ds2);
        assertTrue(MediaServiceResult.OKAY.getNote() == result.getNote());
        
        // Disc already exists
        result = service.addDisc(ds2);
        assertFalse(MediaServiceResult.OKAY.getNote() == result.getNote());
        assertTrue(MediaServiceResult.DUPLICATEOBJ.getNote() == result.getNote());
        
        // Barcode exists
        Disc barcodeDuplicate = new Disc(ds1.getBarcode(), "Not Director-Frozen", Fsk.FSK16.getFsk(), "Not Title-Frozen");
        result = service.addDisc(barcodeDuplicate);
        assertTrue(MediaServiceResult.DUPLICATEISBN.getNote() == result.getNote());
    }
    
    /**
     * Test on getBooks.
     */
    @Test
    public void testGetBooks() {
        Medium[] books = service.getBooks();
        String booksJSON = objToJSON(books);
        String expected = "[{\"title\":\"Title-909-4\",\"author\":\"Author-909-4\",\"isbn\":\"978-1-56619-909-4\"},"
                + "{\"title\":\"Title-9462-6\",\"author\":\"Author-9462-6\",\"isbn\":\"978-1-4028-9462-6\"},"
                + "{\"title\":\"Heat Wave\",\"author\":\"Richard Castle\",\"isbn\":\"978-3-8642-5007-1\"}]";
        assertTrue(booksJSON.equals(expected));
    }
    
    /**
     * Test on getDiscs.
     */
    @Test
    public void testGetDiscs() {
        Medium[] discs = service.getDiscs();
        String discJSON = objToJSON(discs);
        String expected = "[{\"title\":\"Title-909-4\",\"barcode\":\"978-1-56619-909-4\",\"director\":\"Director-909-4\",\"fsk\":12},"
                + "{\"title\":\"Title-9462-6\",\"barcode\":\"978-1-4028-9462-6\",\"director\":\"Director-9462-6\",\"fsk\":18}]";
        assertTrue(discJSON.equals(expected));
    }
    
    /**
     * Test on updateBook.
     * 
     * Möglicher Fehler: ISBN nicht gefunden
     * Möglicher Fehler: Autor und Titel fehlen
     * Möglicher Fehler: Neue Daten entsprechen den alten Daten
     */
    @Test
    public void testUpdateBook()  {
        Book bk1Copy = new Book(bk1.getAuthor(), bk1.getIsbn(), bk1.getTitle());
        System.out.println(bk1.getIsbn());
        MediaServiceResult result = service.updateBook(bk1Copy);
        System.out.println(result);
        assertFalse(MediaServiceResult.OKAY.getNote() == result.getNote());
        assertTrue(MediaServiceResult.DUPLICATEOBJ.getNote() == result.getNote());
        
    }
    
    /**
     * Converts an Object into an JSON String.
     * @param obj Object to convert
     * @return JSON representation of given Object.
     */
    private String objToJSON(Object obj)  {
        ObjectMapper mapper = new ObjectMapper();

        //Object to JSON in String
        String jsonInString = "{code: 400, detail: \"Bad Request\"}";
        try {
            jsonInString = mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        
        return jsonInString;
    }

}
