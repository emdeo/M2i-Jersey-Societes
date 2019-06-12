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

import adelium.dao.DAO_Personne;
import adelium.dao.DAO_Societe;
import adelium.metier.Personne;
import adelium.metier.Societe;

@Path("/societes")
public class SocieteService {

	DAO_Societe daos = new DAO_Societe();
	DAO_Personne daop = new DAO_Personne();

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Societe LireUneSociete(@PathParam("id") int id) {

		// Renvoie les infos d'une société au format JSON
		return daos.Read(id);

	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Societe> LireToutesSocietes() {

		ArrayList<Societe> lstSoc = daos.ReadAll();
		return lstSoc;

	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
//	@Consumes(MediaType.APPLICATION_JSON)
	public Societe AjoutSociete(Societe s) {

		daos.Create(s);
		System.out.println("Ajout de la société " + s);
		return s;

	}

	@PUT
	@Path("{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public String ModifieSociete(@PathParam("id") int id) {

		// message renvoyé à l'exécution d'une méthode PUT
		return "Vous modifiez la société n°" + id;

	}

	@DELETE
	@Path("{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public String SupprimeSociete(@PathParam("id") int id) {

		// message renvoyé à l'exécution d'une méthode DELETE
		return "Vous supprimez la société n°" + id;

	}
}
