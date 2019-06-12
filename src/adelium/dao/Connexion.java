package adelium.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexion {
	/*
	 * Design pattern single temps -> Tout attribut doit �tre de classe "static", il
	 * contiendra la seule instance de classe Connection (permet de v�rifier s'il y
	 * a d�j� un objet cr��, sinon il le cr�e lui-m�me)
	 */
	private static Connection _instance = null;

	// Constructeur (private, ne prend pas d'arguments)
	private Connexion() {
		// VIDE
	}

	public static Connection get_instance(String url, String user, String pwd) {
		if (_instance == null) {
			/*
			 * Verrou : on emp�che le syst�me de passer � une autre t�che tant que le bloc
			 * d'instructions ci-dessous n'est pas enti�rement ex�cut�.
			 * 
			 * Permet de v�rifier que _instance est compl�tement instanci�. Il est conseill�
			 * de garder ce protoype de code � chaque fois qu'on utilise un singleton.
			 */
			synchronized (Connexion.class) {

				if (_instance == null) { // on cr�e l'instance si ce n'est pas d�j� fait

					try {
						Class.forName("com.mysql.cj.jdbc.Driver");
//						Class.forName("com.mysql.jdbc.Connection");
						_instance = DriverManager.getConnection(url, user, pwd); // on cr�e la connexion
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