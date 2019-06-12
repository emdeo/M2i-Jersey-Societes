package adelium.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexion {
	/*
	 * Design pattern single temps -> Tout attribut doit être de classe "static", il
	 * contiendra la seule instance de classe Connection (permet de vérifier s'il y
	 * a déjà un objet créé, sinon il le crée lui-même)
	 */
	private static Connection _instance = null;

	// Constructeur (private, ne prend pas d'arguments)
	private Connexion() {
		// VIDE
	}

	public static Connection get_instance(String url, String user, String pwd) {
		if (_instance == null) {
			/*
			 * Verrou : on empêche le système de passer à une autre tâche tant que le bloc
			 * d'instructions ci-dessous n'est pas entièrement exécuté.
			 * 
			 * Permet de vérifier que _instance est complètement instancié. Il est conseillé
			 * de garder ce protoype de code à chaque fois qu'on utilise un singleton.
			 */
			synchronized (Connexion.class) {

				if (_instance == null) { // on crée l'instance si ce n'est pas déjà fait

					try {
						Class.forName("com.mysql.cj.jdbc.Driver");
//						Class.forName("com.mysql.jdbc.Connection");
						_instance = DriverManager.getConnection(url, user, pwd); // on crée la connexion
					} catch (SQLException e) {
						System.out.println(e.getMessage()); // afficher message d'erreur
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
				}
			}
		}

		return _instance;
	}

}