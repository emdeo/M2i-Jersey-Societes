package adelium.cours.java;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/adelium")
public class RestService
{
    @GET
    @Produces(MediaType.TEXT_PLAIN)
	public String SalutationText()
    {
    	return "Bienvenue à la formation Services Web Rest";
    }
    
    @GET
    @Produces(MediaType.TEXT_XML)
	public String SalutationXML()
    {
    	String rep="<?xml version = '1.0' encoding = 'UTF-8' ?>";
    	
    	rep +=  "<salutation>Bienvenue à la formation Services Web Rest</salutation>";
        return rep;
    }
    
    @GET
    @Produces(MediaType.TEXT_HTML)
	public String SalutationHTML()
    {
    	return "<H1 style='color:blue'>Bienvenue à la formation Services Web Rest</H1>";
    }
	
    @GET
    @Produces(MediaType.APPLICATION_JSON)
	public String SalutationJSON()
    {
    	return "{\"message\":\"Bienvenue à la formation Services Web Rest\"}";
    }
    
    @POST
    @Produces(MediaType.TEXT_PLAIN)
	public String AjoutEntite()
    {
    	return " Entité ajoutée avec succès";
    }
    
    @PUT
    @Produces(MediaType.TEXT_PLAIN)
	public String ModifieEntite()
    {
    	return "Entité modifiée avec succès";
    }
    
    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
	public String SupprimeEntite()
    {
    	return "Entité suprimée avec succès";
    }
       
    
    
}
