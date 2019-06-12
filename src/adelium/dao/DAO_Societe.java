package adelium.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import adelium.metier.Activites;
import adelium.metier.Personne;
import adelium.metier.Societe;

public class DAO_Societe implements IDAO<Societe> {

	final static String url = "jdbc:mysql://localhost:3306/dp_formation?serverTimezone=UTC";
	final static String user = "root";
	final static String pwd = "";

	// Connexion à la BD "dp_formation"
	private static Connection _Cnn = Connexion.get_instance(url, user, pwd);

	public int Instanciate() {

		int output = -1;

		DAO_Societe daos = new DAO_Societe();
		daos.Create(new Societe(1, "Alcibiad", 45f, Activites.ENERGIE, 0));
		daos.Create(new Societe(2, "Barthom", 789.5f, Activites.COMMERCE, 0));
		daos.Create(new Societe(3, "Calipyge", 24f, Activites.NUMERIQUE, 0));
		daos.Create(new Societe(4, "Durotron", 666f, Activites.SCIENCES, 0));

		return output;
	}

	public int Create(Societe s) {

		int output = -1;
		String request1 = "INSERT INTO Societe VALUES (?,?,?,?,?)";

//		Message d'info
		System.out.println("DAO_Societe  Create");

		try {
			PreparedStatement ps = _Cnn.prepareStatement(request1);

			// Complète la requête SQL
			ps.setInt(1, s.get_ID_Societe());
			ps.setString(2, s.get_Nom());
			ps.setFloat(3, s.get_CA());
			ps.setString(4, s.get_Activite().name());
			ps.setInt(5, s.get_Nb_Employes());

			// Exécute la reqûete et enregistre le nombre de modifs
			output = ps.executeUpdate();

			// Ajouter les employés enregistrés dans l'objet Societe dans la table
			// "Personne"
			DAO_Personne daop = new DAO_Personne();
			for (Personne p : s.get_lstEmployes()) {
				daop.Create(p);
			}

		} catch (SQLException e) {
			System.out.println("DAO_Societe  Create() error: " + e.getMessage());
		}

		return output;
	}

	public Societe Read(int id) {

		Societe output = null;
		String ma_requete = "SELECT * FROM Societe WHERE ID_Societe = ?";

//		Message d'info
		System.out.println("DAO_Societe  Read");

		try {
			PreparedStatement ps = _Cnn.prepareStatement(ma_requete);

			ps.setInt(1, id); // Complète la requête SQL

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				String nom = rs.getString("Nom");
				float ca = rs.getFloat("CA");
				Activites activite = Activites.valueOf(rs.getString("Activite"));
				int nbEmployes = rs.getInt("Employe");

				output = new Societe(id, nom, ca, activite, nbEmployes);
			}

		} catch (SQLException e) {
			System.out.println("DAO_Societe  Read() error: " + e.getMessage());
		}

		return output;
	}

	public ArrayList<Societe> ReadAll() {

		ArrayList<Societe> output = new ArrayList<Societe>();
		String request = "SELECT * FROM Societe";

//		Message d'info
		System.out.println("DAO_Societe  ReadAll");

		try {

			PreparedStatement ps = _Cnn.prepareStatement(request);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("ID_Societe");
				String nom = rs.getString("Nom");
				float ca = rs.getFloat("CA");
				Activites act = Activites.valueOf(rs.getString("Activite"));
				int nbEmployes = rs.getInt("Employe");

				Societe s = new Societe(id, nom, ca, act, nbEmployes);
				output.add(s);

			}

		} catch (SQLException e) {
			System.out.println("DAO_Societe  ReadAll() error: " + e.getMessage());
		}

		return output;
	}

	/**
	 * Mettre à jour le nombre d'employés de la société dont on passe l'ID en
	 * paramètre.
	 * 
	 * @param IdSociete
	 * @return
	 */
	public int UpdateNbEmployes(int IdSociete, int nb) {

		int output = -1;

		String request = "UPDATE Societe SET Employe = ? WHERE ID_Societe = ?";

//		Message d'info
		System.out.println("DAO_Societe  UpdateNbEmployes");

		try {
			// Charger la requête SQL
			PreparedStatement ps = _Cnn.prepareStatement(request);

			// Compléter la requête
			ps.setInt(1, nb);
			ps.setInt(2, IdSociete);

			// Exécuter la requête et enregistrer son résultat
			output = ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println("DAO_Societe  UpdateNbEmployes() error: " + e.getMessage());
		}

		return output;
	}

	public int Update(Societe s) {

		int output = -1;

		// Si la société qu'on veut modifier n'existe pas, on la crée et la méthode
		// s'arrête
		if (this.Read(s.get_ID_Societe()) == null) {
			return this.Create(s);
		}

		String request = "UPDATE Societe SET Nom = ?, CA = ?, Activite = ?, Employe = ? WHERE ID_Societe = ?";

//		Message d'info
		System.out.println("DAO_Societe  Update " + s.get_ID_Societe());

		try {
			// Charger la requête SQL
			PreparedStatement ps = _Cnn.prepareStatement(request);

			// Compléter la requête
			ps.setString(1, s.get_Nom());
			ps.setFloat(2, s.get_CA());
			ps.setString(3, s.get_Activite().name());
			ps.setInt(4, s.get_Nb_Employes());
			ps.setInt(5, s.get_ID_Societe());

			// Supprimer tous les les employés de la société et les remplacer par les
			// nouveaux
			DAO_Personne daop = new DAO_Personne();
			daop.DeleteAll(s.get_ID_Societe());

			for (Personne p : s.get_lstEmployes()) {
				daop.Create(p);
			}

			// Exécuter la requête et enregistrer son résultat
			output = ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println("DAO_Societe  Update() error: " + e.getMessage());
		}
		return output;
	}

	/**
	 * Supprimer l'entrée dont on passe l'ID en paramètre.
	 */
	public int Delete(int id) {

		int output = 0;
		String request = "DELETE FROM Societe WHERE ID_Societe = ?";
		String request2 = "DELETE FROM Personne WHERE ID_Societe = ?";

//		Message d'info
		System.out.println("DAO_Societe  Delete " + id);

		try {

//			Supprimer les employés de la société (table personne)
			PreparedStatement ps = _Cnn.prepareStatement(request2);
			ps.setInt(1, id);
			output = ps.executeUpdate();

//			Supprimer la société (table societe)
			ps = _Cnn.prepareStatement(request);
			ps.setInt(1, id);
			output = ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println("DAO_Societe  Delete() error: " + e.getMessage() + "\n");
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
		String request = "TRUNCATE Societe";

//		Message d'info
		System.out.println("###\nDAO_Societe Truncate\n###");

		try {
			PreparedStatement ps = _Cnn.prepareStatement(request);
			output = ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println(("DAO_Societe Truncate() error: " + e.getMessage() + "\n"));
		}

		return output;
	}

}