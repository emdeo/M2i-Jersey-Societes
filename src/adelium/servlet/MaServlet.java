package adelium.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import adelium.dao.DAO_Personne;
import adelium.dao.DAO_Societe;
import adelium.metier.Activites;
import adelium.html.HTMLDynamique;
import adelium.metier.Personne;
import adelium.metier.Societe;

/**
 * Servlet implementation class MaServlet
 */
@WebServlet("/MaServlet")
public class MaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MaServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String btnAction = request.getParameter("btnAction");

		System.out.println("\n\nBTN ACTION (get) - " + btnAction);

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

//		1 - CREER UN FORMULAIRE DANS L'ELEMENT "collapseAjouterSociete"
		if (btnAction.contentEquals("AjouterSociete")) {
			out.append(HTMLDynamique.formulaireAjouterSociete());
		}

//		2 - AFFICHER LA TABLE DES EMPLOYES QUAND ON CLIQUE SUR LE BOUTON "Select"
		if (btnAction.equals("Select")) {

			int idSociete = Integer.parseInt(request.getParameter("IdSociete"));
			out.append(HTMLDynamique.TableauEmployes(idSociete));

		}

//		3 - CREER UN FORMULAIRE DANS L'ELEMENT "collapseModifierSociete"
		if (btnAction.contentEquals("Update")) {

			int idSociete = Integer.parseInt(request.getParameter("IdSociete"));
			out.append(HTMLDynamique.formulaireUpdate(idSociete));

		}

//		4 - SUPPRIMER L'ENTREE SOCIETE + LES ENTREES EMPLOYES CORRESPONDANTES QUAND ON CLIQUE SUR "Delete"
		if (btnAction.equals("Delete")) {

			int idSociete = Integer.parseInt(request.getParameter("IdSociete"));

			DAO_Societe daos = new DAO_Societe();
			daos.Delete(idSociete);

//			Récupérer les employés des sociétés enregistrées
			DAO_Personne daop = new DAO_Personne();
			ArrayList<Personne> lstEmployes = daop.ListeEmployesSociete(idSociete);

			out.append(HTMLDynamique.TableauSocietes());
		}

//		5 - CONFIRMER L'AJOUT D'UNE ENTREE A LA TABLE 'SOCIETE'
		if (btnAction.contentEquals("ConfirmerAjoutSociete")) {

			int idSociete = Integer.parseInt(request.getParameter("IdSociete"));

//			Récupérer les valeurs de la nouvelle société
			String nom = request.getParameter("newNom");
			float CA = Float.parseFloat(request.getParameter("newCA"));
			Activites act = Activites.valueOf(request.getParameter("newActivite"));

			System.out.println("#####\nMaServlet.doGet : AJOUTER la société " + idSociete + " : " + nom + ", " + CA
					+ ", " + act + "\n#####");

			DAO_Societe daos = new DAO_Societe();
			daos.Create(new Societe(idSociete, nom, CA, act, 0));

//			Modifier le tableau de sociétés dans la page HTML
			out.append(HTMLDynamique.TableauSocietes());
		}

//		6 - MODIFIER UNE ENTREE DU TABLEAU DE SOCIETES
		if (btnAction.contentEquals("ModifierSociete")) {

			int idSociete = Integer.parseInt(request.getParameter("IdSociete"));

//			Récupérer les nouvelles valeurs de la société
			String nom = request.getParameter("newNom");
			float CA = Float.parseFloat(request.getParameter("newCA"));
			Activites act = Activites.valueOf(request.getParameter("newActivite"));

			System.out.println("#####\nMaServlet.doGet : MODIFIER la société " + idSociete + " : " + nom + ", " + CA
					+ ", " + act + "\n#####");

//			Modifier l'entrée de la table 'Societe'
			DAO_Societe daos = new DAO_Societe();

			Societe s = daos.Read(idSociete);
			s.set_Nom(nom);
			s.set_CA(CA);
			s.set_Activite(act);
			s.set_Nb_Employes(0);

			daos.Update(s);

//			Supprimer tous les employés dont l'ID Société est celui passé en paramètre
			DAO_Personne daop = new DAO_Personne();
			daop.DeleteAll(idSociete);

//			Modifier le tableau de sociétés dans la page HTML
			out.append(HTMLDynamique.TableauSocietes());
		}

//		7 - AJOUTER UN EMPLOYE A LA TABLE
		if (btnAction.contentEquals("AjouterEmploye")) {

			System.out.println("###\nAjout d'un employé\n###");
			
			

		}

//		8 - MODIFIER UN EMPLOYE DANS LA TABLE
		if (btnAction.contentEquals("ModifierEmploye")) {
			
			System.out.println("###\nModification d'un employé\n###");

			
			
		}
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// super.doDelete(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// super.doPost(req, resp);
	}
}
