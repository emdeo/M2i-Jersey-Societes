package adelium.cours.java.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import adelium.cours.java.metier.Genre;
import adelium.cours.java.metier.Personne;

public class DAO_Personne implements IDAO<Personne> 
{
	
	final static String url = "jdbc:mysql://localhost:3306/dp_formation?serverTimezone=UTC" ;
	final static String user = "root" ;
	final static String pwd = "" ;
	
	
	private static Connection _Cnn = Connexion.getInstance(url, user, pwd);
	

	@Override
	public int Create(Personne p) 	
	{
		System.out.println(p);
		int rep = -1 ;
		
		String chSql = "Insert into Personne VALUES (?,?,?,?,?,?,?)" ;		
		
		try {
			PreparedStatement ps = _Cnn.prepareStatement(chSql);
			
			ps.setInt(1, p.get_ID_Personne());
			ps.setString(2, p.get_Nom());
			ps.setString(3, p.get_Prenom());
			ps.setFloat(4, p.get_Poids());
			ps.setFloat(5, p.get_Taille());
			ps.setString(6, p.get_Sexe().name());
			
			ps.setInt(7, p.get_ID_Societe());			
			
			rep = ps.executeUpdate();			
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
				return rep;
	}

	
	
	public int getIDPersonneMax()
	{
		int id_max = -100 ;
		
		String chSql = "Select Max(ID_Personne) as maxid from Personne" ;		
		
		try {
			PreparedStatement ps = _Cnn.prepareStatement(chSql);					
			
			ResultSet rs =  ps.executeQuery();			
			if (rs.next())
			{
				
				 id_max = rs.getInt("maxid");
				
			
			}			
		} catch (SQLException e) {			
			e.printStackTrace();
		}		
		
		return id_max + 1;
		
	}
	
	
	@Override
	public Personne Retreive(int id) {
		Personne rep = null;
		
		String chSql = "Select * from Personne Where ID_Personne = ?" ;		
		
		try {
			PreparedStatement ps = _Cnn.prepareStatement(chSql);			
			ps.setInt(1, id);		
			
			ResultSet rs =  ps.executeQuery();			
			if (rs.next())
			{
				String nom = rs.getString("Nom");
				String prenom = rs.getString("Prenom");
				float poids = rs.getFloat("Poids");
				float taille = rs.getFloat("Taille");
				Genre sexe = Genre.valueOf(rs.getString("Sexe"));
				int id_societe = rs.getInt("ID_SOCIETE");
				
				rep = new Personne(id,nom,prenom,poids,taille,sexe , id_societe);
			}			
		} catch (SQLException e) {			
			e.printStackTrace();
		}		
				return rep;
	}

	@Override
	public List<Personne> RetreiveAll() {
		
		List<Personne> rep = new ArrayList<Personne>();		
		String chSql = "Select * from Personne " ;		
		
		try {
			PreparedStatement ps = _Cnn.prepareStatement(chSql);			
						
			ResultSet rs =  ps.executeQuery();		
			
			while (rs.next())
			{
				int id  = rs.getInt("ID_Personne");
				String nom = rs.getString("Nom");
				String prenom = rs.getString("Prenom");
				float poids = rs.getFloat("Poids");
				float taille = rs.getFloat("Taille");
				Genre sexe = Genre.valueOf(rs.getString("Sexe"));
				int id_societe = rs.getInt("ID_SOCIETE");
				
				rep.add(new Personne(id,nom,prenom,poids,taille,sexe,id_societe) );
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
				return rep;
	}
	

	@Override
	public int Update(Personne p) {
	int rep = -1 ;
		
		String chSql = "Update Personne Set Nom = ?,Prenom = ?,Poids = ?, Taille = ?,Sexe = ? Where ID_Personne = ?" ;		
		
		try {
			PreparedStatement ps = _Cnn.prepareStatement(chSql);
			
		
			ps.setString(1, p.get_Nom());
			ps.setString(2, p.get_Prenom());
			ps.setFloat(3, p.get_Poids());
			ps.setFloat(4, p.get_Taille());
			ps.setString(5, p.get_Sexe().name());
			ps.setInt(6, p.get_ID_Personne());
			
			rep = ps.executeUpdate();			
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}		
				return rep;
	}

	@Override
	public int Delete(int id) {
		int rep = -1 ;
		
		String chSql = "Delete from  Personne Where ID_Personne = ?" ;		
		
		try {
			PreparedStatement ps = _Cnn.prepareStatement(chSql);
			
			ps.setInt(1,id);
			
			rep = ps.executeUpdate();			
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
				return rep;
	}


}
