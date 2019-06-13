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

import adelium.cours.java.dal.DAO_Societe;
import adelium.cours.java.metier.Personne;
import adelium.cours.java.metier.Societe;

@Path("/societes")
public class SocieteService 
{
	
	
	
	@GET
	@Path("{id}")
	@Produces("application/json")
	public Societe getSociete(@PathParam("id")int id)
	{
		DAO_Societe daosociete = new DAO_Societe();		
		return daosociete.Retreive(id) ;	
	}
	
	
	
	@GET
	@Produces("application/json")
	public List<Societe> listeDessocietes()
	{
		DAO_Societe daosociete = new DAO_Societe();		
		return daosociete.RetreiveAll() ;		
	}
	
	@POST	
	@Consumes("application/json")
	public int createSociete(Societe s)
	{
		DAO_Societe daosociete = new DAO_Societe();		
		return daosociete.Create(s) ;				
	}
	
	
	@PUT
	@Consumes("application/json")
	public int updateSociete(Societe s)
	{
		DAO_Societe daosociete = new DAO_Societe();		
		return daosociete.Update(s) ; 		
	}
	
	
	@DELETE
	@Path("{id}")
	public int SupprimeSociete(@PathParam("id")int id)
	{
		DAO_Societe daosociete = new DAO_Societe();		
		return daosociete.Delete(id) ;	
				
	}
	
	@GET
	@Path("/{id}/employes")
	@Produces("application/json")
	public List<Personne> RecupereTousLesEmployesDeLaSociete(@PathParam("id")int id)
	{
		DAO_Societe daosociete = new DAO_Societe();		
		return daosociete.ListeDesEmployeesDuneSociete(id) ;		
	}
	

}
