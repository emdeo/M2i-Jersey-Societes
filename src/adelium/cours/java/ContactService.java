package adelium.cours.java;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

@Path("/Contacts")
public class ContactService 
{
    static private Map<Integer,Contact> dbContacts = new ConcurrentHashMap<Integer,Contact>();
    static private AtomicInteger idCounter = new AtomicInteger();
    
    
    @POST
    //@Produces("application/xml")
    @Consumes("application/xml")   
    public Contact createContact(Contact contact)
    {
    	System.out.println("Ajout de contact en cours " + contact);
    	contact.setIdcontact(idCounter.incrementAndGet());
    	dbContacts.put(contact.getIdcontact(), contact);
		return contact;
    	
    }
    
    @GET
    @Path("{id}")
    @Produces("application/xml")
    public Contact getContact(@PathParam("id") int id)
    {
    	Contact contact =  	dbContacts.get(id);
		return contact;
    	
    }
    
    @PUT
    @Path("{id}")
    @Consumes("application/xml")
    public void updateContact(@PathParam("id") int id , Contact update)
    {
    	Contact courant  = 	dbContacts.get(id);
    	courant.setNom(update.getNom());
    	courant.setPrenom(update.getPrenom());
    	courant.setEmail(update.getEmail());
    	dbContacts.put(courant.getIdcontact(), courant);    	
    }
    
    @DELETE
    @Path("{id}")   
    public void deleteContact(@PathParam("id") int id )
    {
    	Contact courant  = 	dbContacts.remove(id);
    	
    	if (courant == null) throw new WebApplicationException(Response.Status.NOT_FOUND);
    	   	
    }
    
}
