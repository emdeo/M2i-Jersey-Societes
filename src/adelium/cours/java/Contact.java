package adelium.cours.java;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Contact")
public class Contact 
{
	
  
	 //@XmlElement
	 private int idcontact; 
	 //@XmlElement
	 private String Nom; 	
	 //@XmlElement
	 private String Prenom; 	
	 //@XmlElement
	 private String Email ;

	public Contact() {
	
}
	


	public int getIdcontact() {
		return idcontact;
	}



	public void setIdcontact(int idcontact) {
		this.idcontact = idcontact;
	}



	public Contact(int id, String nom, String prenom, String email) {
	super();
	this.idcontact = id;
	Nom = nom;
	Prenom = prenom;
	Email = email;
}
	
	public String getNom() {
		return Nom;
	}

	public void setNom(String nom) {
		Nom = nom;
	}
	
	public String getPrenom() {
		return Prenom;
	}

	public void setPrenom(String prenom) {
		Prenom = prenom;
	}
	
	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}
	@Override
	public String toString() {
		return "Contact [id=" + idcontact + ", Nom=" + Nom + ", Prenom=" + Prenom + ", Email=" + Email + "]";
	} ;
   
   
   
   
   
}
