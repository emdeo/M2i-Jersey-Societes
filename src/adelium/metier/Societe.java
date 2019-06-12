package adelium.metier;

import java.util.ArrayList;

//DAO_Societe

public class Societe {

	private int _ID_Societe;
	private String _Nom;
	private float _CA;
	private Activites _Activite;
	private int _Nb_Employes;

	// Liste d'employés (objets de classe Personne)
	private ArrayList<Personne> _lstEmployes = new ArrayList<Personne>();

	public Societe(int ID_Societe, String _Nom, float _CA, Activites _Activite, int nbEmployes) {
		super();
		this._ID_Societe = ID_Societe;
		this._Nom = _Nom;
		this._CA = _CA;
		this._Activite = _Activite;
		this._Nb_Employes = nbEmployes;

		// Insérer une société dans la table "Société" chaque fois qu'un nouvel objet
		// Societe est instancié
//		DAO_Societe daos = new DAO_Societe();
//		daos.Create(this);
	}

	public void CreerListe(ArrayList<Personne> liste) {
		if (this._lstEmployes.size() == 0)
			this._lstEmployes = liste;
		else
			System.out.println("La liste existe déjà\n");
	}

	public void AjoutEmploye(Personne p) {
		this._lstEmployes.add(p);
	}

	@Override
	public String toString() {
		return "Societe [_ID_Societe=" + _ID_Societe + ", _Nom=" + _Nom + ", _CA=" + _CA + ", _Activite=" + _Activite
				+ ", _Nb_Employes=" + _Nb_Employes + ", _lstEmployes=" + _lstEmployes + "]";
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

	public float get_CA() {
		return _CA;
	}

	public void set_CA(float _CA) {
		this._CA = _CA;
	}

	public Activites get_Activite() {
		return _Activite;
	}

	public void set_Activite(Activites _Activite) {
		this._Activite = _Activite;
	}

	public ArrayList<Personne> get_lstEmployes() {
		return _lstEmployes;
	}

	public void set_lstEmployes(ArrayList<Personne> _lstEmployes) {
		this._lstEmployes = _lstEmployes;
	}

	public int get_Nb_Employes() {
		return _Nb_Employes;
	}

	public void set_Nb_Employes(int _Nb_Employes) {
		this._Nb_Employes = _Nb_Employes;
	}

}