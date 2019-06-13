package adelium.cours.java;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import adelium.cours.java.dal.DAO_Personne;
import adelium.cours.java.metier.Personne;

@Path("/personnes")
public class PersonneService 
{
   
	@GET
	@Path("/IdMax")
	public int getIDMAxPersonne( )
	{
		DAO_Personne daop = new DAO_Personne();
		return daop.getIDPersonneMax();
		 
	}
	
	
	
	@GET
	@Path("{id}")
	@Produces("application/json")
	public Personne getPersonne(@PathParam("id") int id )
	{
		DAO_Personne daopersonne = new DAO_Personne();
		return daopersonne.Retreive(id);
	}
	
	@GET
	@Produces("application/json")
	public List<Personne> ListeDeToutesLesPersonnes()
	{
		DAO_Personne daopersonne = new DAO_Personne();
		return daopersonne.RetreiveAll();
	}
	
	@POST
	@Consumes("application/json")
	public int createPersonne(Personne p)
	{
		System.out.println("SW Create personne : " + p);
		DAO_Personne daopersonne = new DAO_Personne();
		return daopersonne.Create(p);
	}
	
	@PUT
	@Consumes("application/json")
	public int updatePersonne(Personne p)
	{
		DAO_Personne daopersonne = new DAO_Personne();
		return daopersonne.Update(p);
	}
	
	@DELETE
	public int deletePersonne(int id)
	{
		DAO_Personne daopersonne = new DAO_Personne();
		return daopersonne.Delete(id);
	}
	
}
