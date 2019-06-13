package adelium.cours.java.metier;

public class Personne 
{
	private int _ID_Personne ;
	private String _Nom;
	private String _Prenom;
	private float _Poids ;
	private float _Taille ;
	private Genre _Sexe ;
	
	
	
	
	
	public Personne() {
		super();
		// TODO Auto-generated constructor stub
	}


	private int _ID_Societe ;
	
	public int get_ID_Societe() {
		return _ID_Societe;
	}

	public void set_ID_Societe(int _ID_Societe) {
		this._ID_Societe = _ID_Societe;
	}
	public Personne(int _ID_Personne, String _Nom, String _Prenom, float _Poids, float _Taille, Genre _Sexe ) {
		super();
		this._ID_Personne = _ID_Personne;
		this._Nom = _Nom;
		this._Prenom = _Prenom;
		this._Poids = _Poids;
		this._Taille = _Taille;
		this._Sexe = _Sexe;
		
		
	}
	

	public Personne(int _ID_Personne, String _Nom, String _Prenom, float _Poids, float _Taille, Genre _Sexe , int id_societe) {
		super();
		this._ID_Personne = _ID_Personne;
		this._Nom = _Nom;
		this._Prenom = _Prenom;
		this._Poids = _Poids;
		this._Taille = _Taille;
		this._Sexe = _Sexe;
		
		this._ID_Societe = id_societe ;
		
	}

	public int get_ID_Personne() {
		return _ID_Personne;
	}

	public void set_ID_Personne(int _ID_Personne) {
		this._ID_Personne = _ID_Personne;
	}

	public String get_Nom() {
		return _Nom;
	}

	public void set_Nom(String _Nom) {
		this._Nom = _Nom;
	}

	public String get_Prenom() {
		return _Prenom;
	}

	public void set_Prenom(String _Prenom) {
		this._Prenom = _Prenom;
	}

	public float get_Poids() {
		return _Poids;
	}

	public void set_Poids(float _Poids) {
		this._Poids = _Poids;
	}

	public float get_Taille() {
		return _Taille;
	}

	public void set_Taille(float _Taille) {
		this._Taille = _Taille;
	}

	public Genre get_Sexe() {
		return _Sexe;
	}

	public void set_Sexe(Genre _Sexe) {
		this._Sexe = _Sexe;
	}
	
	public float IMC()
	{
		return this._Poids / (this._Taille * this._Taille);
	}
	
	public float PoidsMin()
	{
		return  (19 * this._Taille * this._Taille);
	}
	
	public float PoidsMax()
	{
		return  (25 * this._Taille * this._Taille);
	}
	
	public float PoidsIdeal()
	{
		if (this._Sexe == Genre.MASCULIN)
			return  (22 * this._Taille * this._Taille);
		else
			return  (21 * this._Taille * this._Taille);
	}

	public String Diagnostic()
	   {
		   float imc = this.IMC();
		   
		   
		   if (imc < 17 )
			   return "Anorexique" ;
		   
		   if (imc < 19 )
			   return "Maîgre" ;
		   if (imc < 25 )
			   return "Super forme" ;
		   if (imc < 30 )
			   return "Kilos +" ;
		   if (imc < 40 )
			   return "Obèse léger" ;
		  
			   return "Obèse morbide" ;
		   
	   }
	
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("\tPersonne \n_ID_Personne : ");
		builder.append(_ID_Personne);
		builder.append("\n\tNom : ");
		builder.append(_Nom);
		builder.append("\n\tPrenom : ");
		builder.append(_Prenom);
		builder.append("\n\tPoids : ");
		builder.append(_Poids);
		builder.append("\n\tTaille : ");
		builder.append(_Taille);
		builder.append("\n\tSexe: ");
		builder.append(_Sexe);
		builder.append("\n\tIMC : ");
		builder.append(IMC());
		builder.append("\n\tPoids min : ");
		builder.append(PoidsMin());
		builder.append("\n\tPoids max : ");
		builder.append(PoidsMax());
		builder.append("\n\tPoids Ideal : ");
		builder.append(PoidsIdeal());
		
		return builder.toString();
	}
	
    
	
	
	
}
