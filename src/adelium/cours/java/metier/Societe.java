package adelium.cours.java.metier;

import java.util.ArrayList;
import java.util.List;

public class Societe
{
    private int _ID_Societe ;
    private String _Nom;
    private String _Activite;
    private float _CA;
    
    
    
    //private List<Personne> Employees = new ArrayList<Personne> ();
    private List<Personne> Employees ;
    
    public void setEmployees(List<Personne> employees) {
		Employees = employees;
	}

	public List<Personne> getEmployees() {
		return Employees;
	}

	public Societe() {
		this.Employees = new ArrayList<Personne> ();
	}
    
	public Societe(int _ID_Societe, String _Nom, String _Activite, float _CA) {
		super();
		this._ID_Societe = _ID_Societe;
		this._Nom = _Nom;
		this._Activite = _Activite;
		this._CA = _CA;
		this.Employees = new ArrayList<Personne> ();
	}

	public int get_ID_Societe() {
		return _ID_Societe;
	}

	public void set_ID_Societe(int _ID_Societe) {
		this._ID_Societe = _ID_Societe;
	}

	public String get_Nom() {
		return _Nom;
	}

	public void set_Nom(String _Nom) {
		this._Nom = _Nom;
	}

	public String get_Activite() {
		return _Activite;
	}

	public void set_Activite(String _Activite) {
		this._Activite = _Activite;
	}

	public float get_CA() {
		return _CA;
	}

	public void set_CA(float _CA) {
		this._CA = _CA;
	}

	public void AjoutEmploye(Personne p)
	{
		p.set_ID_Societe(this._ID_Societe);
		this.Employees.add(p);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Societe :\n\tID_Societe : ");
		builder.append(_ID_Societe);
		builder.append("\n\tNom=");
		builder.append(_Nom);
		builder.append("\n\tActivite : ");
		builder.append(_Activite);
		builder.append("\n\tCA : ");
		builder.append(_CA);
		builder.append("\nListe des Employees :\n ");
//		for (Personne p : this.Employees)
//		builder.append(p.toString());
	
		return builder.toString();
	}
    
    
    
    
    
}
