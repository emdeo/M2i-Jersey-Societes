package adelium.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import adelium.metier.Societe;
import adelium.metier.Personne;
import adelium.metier.Sexe;

public class DAO_Personne implements IDAO<Personne> {

	// Constantes statiques qui n'existent qu'en un seul exemplaire
	final static String url = "jdbc:mysql://localhost:3306/dp_formation?serverTimezone=UTC";
	final static String user = "root";
	final static String pwd = "";

	// On se connecte à la BD "dp_formation"
	private static Connection _Cnn = Connexion.get_instance(url, user, pwd);

	/**
	 * Instancier la table avec 4 lignes de personnes.
	 * 
	 * @return
	 */
	public int Instanciate() {
		if (ReadAll().size() != 0) {
			System.out.println("La table 'personne' n'est pas vide, Instanciate() impossible.");
			return -1;
		}

		int output = -1;

		ArrayList<Personne> listePersonnes = new ArrayList<Personne>();
		listePersonnes.add(new Personne(1, "Alpha", "Alice", 1.8f, 73, Sexe.FEMININ, 1));
		listePersonnes.add(new Personne(2, "Bravo", "Bernard", 1.5f, 64f, Sexe.MASCULIN, 1));
		listePersonnes.add(new Personne(3, "Charly", "Carole", 1.68f, 92f, Sexe.FEMININ, 2));
		listePersonnes.add(new Personne(4, "Delta", "Denis", 1.91f, 130f, Sexe.MASCULIN, 2));
		listePersonnes.add(new Personne(5, "Echo", "Elise", 1.8f, 73, Sexe.FEMININ, 3));
		listePersonnes.add(new Personne(6, "Foxtrot", "Fanchon", 1.5f, 64f, Sexe.FEMININ, 3));
		listePersonnes.add(new Personne(7, "Golf", "Gilbert", 1.68f, 92f, Sexe.MASCULIN, 4));

		DAO_Personne daop = new DAO_Personne();

		for (Personne p : listePersonnes) {
			daop.Create(p);
		}

		return output;
	}

	// INSERER UNE LIGNE DANS LA TABLE (= nouvelle personne)
	@Override
	public int Create(Personne p) {

		int output = -1;
		String ma_requete = "INSERT INTO Personne VALUES (?,?,?,?,?,?,?)";

//		Message d'info
		System.out.println("DAO_Personne Create");

		try {
			PreparedStatement ps = _Cnn.prepareStatement(ma_requete);

			// Complète la requête SQL
			ps.setInt(1, p.get_ID_Personne());
			ps.setString(2, p.get_Nom());
			ps.setString(3, p.get_Prenom());
			ps.setFloat(4, p.get_Poids());
			ps.setFloat(5, p.get_Taille());
			ps.setString(6, p.get_Genre().name());
			ps.setInt(7, p.get_ID_Societe());

//			Incrémenter le nombre d'employés de 1 pour la société correspondante
			DAO_Societe daos = new DAO_Societe();
			int nbEmployes = daos.Read(p.get_ID_Societe()).get_Nb_Employes();
			daos.UpdateNbEmployes(p.get_ID_Societe(), nbEmployes + 1);

			// Enregistre le nombre de modifs exécutées
			output = ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println("DAO_Personne Create() error: " + e.getMessage());
		}

		return output;
	}

	// RENVOYER L'OBJET DONT ON PASSE L'ID EN PARAMETRE
	@Override
	public Personne Read(int id) {

		Personne output = null;
		String ma_requete = "select * from personne where ID_Personne = ?";

//		Message d'info
		System.out.println("DAO_Personne Read " + id);

		try {
			PreparedStatement ps = _Cnn.prepareStatement(ma_requete);

			ps.setInt(1, id); // Complète la requête SQL

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				String nom = rs.getString("Nom");
				String prenom = rs.getString("Prenom");
				float poids = rs.getFloat("Poids");
				float taille = rs.getFloat("Taille");
				Sexe sexe = Sexe.valueOf(rs.getString("Sexe"));
				int id_Societe = rs.getInt("ID_Societe");

				output = new Personne(id, nom, prenom, poids, taille, sexe, id_Societe);
//				output.toString();
			}

		} catch (SQLException e) {
			System.out.println("DAO_Personne Read() error: " + e.getMessage());
		}

		return output;
	}

	/**
	 * Lire toutes les entrées de la table.
	 */
	@Override
	public ArrayList<Personne> ReadAll() {

		ArrayList<Personne> output = new ArrayList<Personne>();
		String ma_requete = "SELECT * FROM Personne";

//		Message d'info
		System.out.println("DAO_Personne ReadAll");

		try {
			PreparedStatement ps = _Cnn.prepareStatement(ma_requete);
			ResultSet rs = ps.executeQuery();

			// WHILE : on ajoute un nouvel objet tant qu'il y a encore un ligne de données
			while (rs.next()) {
				int id = rs.getInt("ID_Personne");
				String nom = rs.getString("Nom");
				String prenom = rs.getString("Prenom");
				float poids = rs.getFloat("Poids");
				float taille = rs.getFloat("Taille");
				Sexe sexe = Sexe.valueOf(rs.getString("Sexe"));
				int id_Societe = rs.getInt("ID_Societe");

				output.add(new Personne(id, nom, prenom, poids, taille, sexe, id_Societe));
			}

		} catch (SQLException e) {
			System.out.println("DAO_Personne ReadAll() error: " + e.getMessage());
		}

		return output;
	}

	// Renvoie une liste d'employés dont l'ID_Société correspond au paramètre
	public ArrayList<Personne> ListeEmployesSociete(int id_soc) {

		ArrayList<Personne> output = new ArrayList<Personne>();
		String ma_requete = "SELECT * FROM Personne WHERE ID_Societe = ?";

//		Message d'info
		System.out.println("DAO_Personne ListeEmployesSociete");

		try {
			PreparedStatement ps = _Cnn.prepareStatement(ma_requete);
			ps.setInt(1, id_soc); // Complète la requête SQL
			ResultSet rs = ps.executeQuery();

			// WHILE : on ajoute un nouvel objet tant qu'il y a encore un ligne de données
			while (rs.next()) {
				int id = rs.getInt("ID_Personne");
				String nom = rs.getString("Nom");
				String prenom = rs.getString("Prenom");
				float poids = rs.getFloat("Poids");
				float taille = rs.getFloat("Taille");
				Sexe sexe = Sexe.valueOf(rs.getString("Sexe"));
				int id_Societe = rs.getInt("ID_Societe");

				output.add(new Personne(id, nom, prenom, poids, taille, sexe, id_Societe));
			}

		} catch (SQLException e) {
			System.out.println("DAO_Personne ListeEmployesSociete() error: " + e.getMessage());
		}

		return output;
	}

	/**
	 * Met à jour une ligne de la table à partir d'un objet passé en paramètre.
	 * 
	 */
	@Override
	public int Update(Personne p) {

		int output = -1;
		String ma_requete = "UPDATE Personne SET Nom = ?, Prenom = ?, Poids = ?,"
				+ "Taille = ?, Sexe = ?, ID_Societe = ? WHERE ID_Personne = ?";

//		Message d'info
		System.out.println("DAO_Personne Update " + p.get_ID_Personne());

		try {
			PreparedStatement ps = _Cnn.prepareStatement(ma_requete);

			// Complète la requête SQL
			ps.setString(1, p.get_Nom());
			ps.setString(2, p.get_Prenom());
			ps.setFloat(3, p.get_Poids());
			ps.setFloat(4, p.get_Taille());
			ps.setString(5, p.get_Genre().name());
			ps.setInt(6, p.get_ID_Societe());
			ps.setInt(7, p.get_ID_Personne());

			// Enregistre le nombre de modifs exécutées
			output = ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println("DAO_Personne Update() error: " + e.getMessage());
		}

		return output;
	}

	@Override
	public int Delete(int id) {

		int output = -1;
		String ma_requete = "DELETE FROM Personne WHERE ID_Personne = ?";

//		Message d'info
		System.out.println("DAO_Personne Delete " + id);

		try {
			PreparedStatement ps = _Cnn.prepareStatement(ma_requete);

			ps.setInt(1, id); // Complète la requête SQL

			// Enregistre le nombre de modifs exécutées
			output = ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println("DAO_Personne Delete() error: " + e.getMessage());
		}

		return output;
	}

	/**
	 * Supprimer tous les employés d'une société dont on passe l'ID en paramètre.
	 * 
	 * @param id_soc
	 * @return
	 */
	public int DeleteAll(int id_soc) {

		int output = -1;

		String ma_requete = "DELETE FROM Personne WHERE ID_Societe = ?";

//		Message d'info
		System.out.println("DAO_Personne DeleAll (societe " + id_soc + ")");

		try {
			PreparedStatement ps = _Cnn.prepareStatement(ma_requete);

			ps.setInt(1, id_soc); // Complète la requête SQL

			// Enregistre le nombre de modifs exécutées
			output = ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println("DAO_Personne DeleteAll() error: " + e.getMessage());
		}

		return output;
	}

	/**
	 * Vider la table.
	 * 
	 * @return
	 */
	public int Truncate() {

		int output = -1;
		String request = "TRUNCATE Personne";

//		Message d'info
		System.out.println("###\nDAO_Personne Truncate\n###");

		try {
			PreparedStatement ps = _Cnn.prepareStatement(request);
			output = ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println(("DAO_Personne Truncate() error: " + e.getMessage() + "\n"));
		}

		return output;
	}

}