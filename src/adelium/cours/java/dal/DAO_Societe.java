package adelium.cours.java.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import adelium.cours.java.metier.Genre;
import adelium.cours.java.metier.Personne;
import adelium.cours.java.metier.Societe;

public class DAO_Societe implements IDAO<Societe> {
	
	final static String url = "jdbc:mysql://localhost:3306/dp_formation?serverTimezone=UTC" ;
	final static String user = "root" ;
	final static String pwd = "" ;	
	
	private static Connection _Cnn = Connexion.getInstance(url, user, pwd);

	@Override
	public int Create(Societe s) {

		int rep = -1 ;		
		String chSql = "Insert into Societe VALUES (?,?,?,?)" ;		
		
		try {
			PreparedStatement ps = _Cnn.prepareStatement(chSql);			
			ps.setInt(1, s.get_ID_Societe());
			ps.setString(2,s.get_Nom());
			ps.setString(3,s.get_Activite());
			ps.setFloat(4,s.get_CA());			
			rep = ps.executeUpdate();	
			
		    DAO_Personne daop = new DAO_Personne();
		    for(Personne p : s.getEmployees())
		    {
		    	daop.Create(p);
		    }			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}		
		return rep;
	}

	@Override
	public Societe Retreive(int id)
	{
		
		Societe rep = null;
		
		String chSql = "Select * from Societe Where ID_Societe = ?" ;		
		
		try {
			PreparedStatement ps = _Cnn.prepareStatement(chSql);			
			ps.setInt(1, id);		
			
			ResultSet rs =  ps.executeQuery();			
			if (rs.next())
			{
				String nom = rs.getString("Nom");
				String activite = rs.getString("Activite");
				float ca = rs.getFloat("CA");
				
				rep = new Societe(id,nom,activite,ca);
				//rep.setEmployees(this.ListeDesEmployeesDuneSociete(id));
				
			}			
		} catch (SQLException e) {			
			e.printStackTrace();
		}		
				return rep;
		
		
		
		
		
	}

	@Override
	public List<Societe> RetreiveAll() {
		List<Societe> rep = new ArrayList<Societe>();
		
		String chSql = "Select * from Societe" ;		
		
		try {
			PreparedStatement ps = _Cnn.prepareStatement(chSql);			
			
			
			ResultSet rs =  ps.executeQuery();
						
			while (rs.next())
			{
				int id = rs.getInt("ID_Societe");
				String nom = rs.getString("Nom");
				String activite = rs.getString("Activite");
				float ca = rs.getFloat("CA");
				
				Societe s = new Societe(id,nom,activite,ca) ;
				//s.setEmployees(this.ListeDesEmployeesDuneSociete(id));
				
				rep.add(s );			
			}			
		} catch (SQLException e) {			
			e.printStackTrace();
		}		
				return rep;
	}

	@Override
	public int Update(Societe s) {
	int rep = -1 ;	
	if (this.Retreive(s.get_ID_Societe()) == null)
	{
		return this.Create(s);
	}
		
		String chSql = "Update Societe Set Nom = ?, Activite = ? , CA = ? where ID_Societe = ?" ;		
		
		try {
			PreparedStatement ps = _Cnn.prepareStatement(chSql);			
			ps.setString(1,s.get_Nom());
			ps.setString(2,s.get_Activite());
			ps.setFloat(3,s.get_CA());	
			ps.setInt(4, s.get_ID_Societe());
			
//			DAO_Personne daop = new DAO_Personne();
//			
//			this.supprimeTousLesEmployesDuneSociete(s.get_ID_Societe());			
//			
//			for(Personne p : s.getEmployees())
//			{
//				daop.Create(p);
//			}
			
			rep = ps.executeUpdate();	
			
		 		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return rep;
	}

	@Override
	public int Delete(int id) {
		int rep = -1 ;
		
		String chSql = "Delete from Societe where ID_Societe = ?" ;		
		
		try {
			PreparedStatement ps = _Cnn.prepareStatement(chSql);
			
			
				
			ps.setInt(1, id);
			
			Societe s = this.Retreive(id);
			
			DAO_Personne daop = new DAO_Personne();
			for(Personne p : s.getEmployees())
			{
				daop.Delete(p.get_ID_Personne());
			}
			
			rep = ps.executeUpdate();	
			
		 		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return rep;
	}

	public List<Personne> ListeDesEmployeesDuneSociete(int idsociete) {
		List<Personne> rep = new ArrayList<Personne>();
		
		String chSql = "Select * from Personne Where ID_Societe = ?" ;		
		
		try {
			PreparedStatement ps = _Cnn.prepareStatement(chSql);			
			ps.setInt(1, idsociete);		
			
			ResultSet rs =  ps.executeQuery();			
			while (rs.next())
			{
				int id_personne = rs.getInt("ID_Personne");
				String nom = rs.getString("Nom");
				String prenom = rs.getString("Prenom");
				float poids = rs.getFloat("Poids");
				float taille = rs.getFloat("Taille");
				Genre sexe = Genre.valueOf(rs.getString("Sexe"));
				int id_societe = rs.getInt("ID_SOCIETE");
				
				rep.add( new Personne(id_personne,nom,prenom,poids,taille,sexe , id_societe));
			}			
		} catch (SQLException e) {			
			e.printStackTrace();
		}		
				return rep;
	}
	

	public int supprimeTousLesEmployesDuneSociete(int id_societe) 
	{
		int rep = -1 ;
		
		String chSql = "Delete from  Personne Where ID_Societe = ?" ;		
		
		try {
			PreparedStatement ps = _Cnn.prepareStatement(chSql);
			
			ps.setInt(1,id_societe);			
			rep = ps.executeUpdate();		
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
				return rep;
		
	}



}
