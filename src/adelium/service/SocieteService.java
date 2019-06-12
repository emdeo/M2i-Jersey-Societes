package adelium.service;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import adelium.dao.DAO_Societe;
import adelium.metier.Societe;

@Path("/societes")
public class SocieteService {

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Societe LireUneSociete(@PathParam("id") int id) {

		DAO_Societe daos = new DAO_Societe();
		Societe Soc = daos.Read(id);
		
		System.out.println(Soc);
		
		return Soc;

	}

	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Societe> LireToutesSocietes() {

		DAO_Societe daos = new DAO_Societe();
		ArrayList<Societe> lstSoc = daos.ReadAll();
		
		return lstSoc;

	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Societe AjoutSociete(Societe s) {

		// message renvoy� � l'ex�cution d'une m�thode POST
		System.out.println("Ajout de la soci�t� " + s);
		s.set_ID_Societe(120);
		return s;

	}

	@PUT
	@Path("{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public String ModifieSociete(@PathParam("id") int id) {

		// message renvoy� � l'ex�cution d'une m�thode PUT
		return "Vous modifiez la soci�t� n�" + id;

	}

	@DELETE
	@Path("{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public String SupprimeSociete(@PathParam("id") int id) {

		// message renvoy� � l'ex�cution d'une m�thode DELETE
		return "Vous supprimez la soci�t� n�" + id;

	}
}
