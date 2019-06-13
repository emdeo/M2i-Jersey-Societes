package adelium.cours.java.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexion
{

	private Connexion()
	{		
	}
	
	private static Connection _instance = null ;
	
	public static Connection getInstance(String url , String user, String pwd)
	{
		if (_instance == null)
		{
			synchronized(Connexion.class)
			{			
					if (_instance == null)
					{
						try {
							Class.forName("com.mysql.cj.jdbc.Driver");
							_instance = DriverManager.getConnection(url, user, pwd);
						} catch (SQLException e) {				
							System.out.println(e.getMessage());;
						} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
			}		
		}		
		
		return _instance;
		
	}
	
}
