package edu.hm.management.bib;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import edu.hm.management.bib.IMediaService;
import edu.hm.management.media.Book;
import edu.hm.management.media.Disc;

/**
 * Class manages media ressources.
 * @author Daniel Gabl
 *
 */
@Path("/media")
public class MediaResource {
	
	/**
	 * Media Interface.
	 */
	private final IMediaService service;
	
	/**
	 * Default Constructor. Creating a new media service.
	 */
	public MediaResource() {
		service = new MediaServiceImpl();
	}

	/**
	 * Extended Constructor. Saving a given media service.
	 */
	public MediaResource(IMediaService service) {
		this.service = service;
	}
	
	/**
	 * Creates a given Book.
	 * @param book Book to create.
	 * @return JSON response with status code and created json object.
	 */
	@POST
	@Path("/books")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createBook(Book book)  {
		MediaServiceResult result = service.addBook(book);
		
		JSONObject json = new JSONObject();
		json.put("code", result.getCode());
		json.put("detail", result.getNote());		
		
		return Response.status(result.getCode()).entity(json).build();
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
