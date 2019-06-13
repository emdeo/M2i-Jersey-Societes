package adelium.cours.java;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="contact")
public class Contact2 
{

	@XmlElement public int id ;
	@XmlElement
	public int nom ;
	@XmlElement
	public int prenom ;
	@XmlElement
	public int email ;
	@Override
	public String toString() {
		return "Contact2 [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + "]";
	}
	
	
	
	
}
