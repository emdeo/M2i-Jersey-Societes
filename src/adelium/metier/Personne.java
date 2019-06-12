package adelium.metier;

public class Personne {

	private int _ID_Personne;
	private String _Nom;
	private String _Prenom;
	private float _Taille;
	private float _Poids;
	private Sexe _Genre;
	private int _ID_Societe;

	/**
	 * Constructeur n°1. Préférable car il est indépendant du servlet (ie. on peut
	 * l'instancier sans recourir à un fichier JSP).
	 * 
	 * @param _ID_Personne
	 * @param _Nom
	 * @param _Prenom
	 * @param _Taille
	 * @param _Poids
	 * @param _Genre
	 * @param _ID_Societe
	 */
	public Personne(int _ID_Personne, String _Nom, String _Prenom, float _Taille, float _Poids, Sexe _Genre,
			int _ID_Societe) {
		super();
		this._ID_Personne = _ID_Personne;
		this._Nom = _Nom;
		this._Prenom = _Prenom;
		this._Taille = _Taille;
		this._Poids = _Poids;
		this._Genre = _Genre;
		this._ID_Societe = _ID_Societe;
	}

	/**
	 * Constructeur n°2.
	 * 
	 * @param req
	 */
	public Personne(javax.servlet.ServletRequest req) {
		super();
		this._ID_Personne = Integer.parseInt(req.getParameter("txtIDPersonne"));
		this._Nom = req.getParameter("txt_Nom");
		this._Prenom = req.getParameter("txt_Prenom");
		this._Taille = Float.parseFloat(req.getParameter("txt_Taille"));
		this._Poids = Float.parseFloat(req.getParameter("txt_Poids"));
		this._Genre = Sexe.valueOf(req.getParameter("lstSexe"));
		this._ID_Societe = Integer.parseInt(req.getParameter("txtIDSociete"));
	}

	/**
	 * Renvoyer une valeur arrondie au centième.
	 * 
	 * @param valeur
	 * @return
	 */
	public float arrondi(float valeur) {
		return Math.round(valeur * 100f) / 100f;
	}

	/**
	 * Calculer l'IMC.
	 * 
	 * @return
	 */
	public float calculIMC() {
		return arrondi(this._Poids / this._Taille * this._Taille);
	}

	/**
	 * Calculer le _Poids minimum.
	 * 
	 * @return
	 */
	public float _PoidsMin() {
		return arrondi(19 * (this._Taille * this._Taille));
	}

	/**
	 * Calculer le _Poids maximum.
	 * 
	 * @return
	 */
	public float _PoidsMax() {
		return arrondi(25 * (this._Taille * this._Taille));
	}

	/**
	 * Calculer le _Poids idéal.
	 * 
	 * @return
	 */
	public float _PoidsIdeal() {

		float _Taille = this._Taille * 100f;
		float output = _Taille - 100f;

		if (this._Genre == Sexe.FEMININ)
			output -= ((_Taille - 150f) / 2.5f);
		else
			output -= ((_Taille - 150f) / 4f);

		return arrondi(output);
	}

	/**
	 * Déterminer le diagnostic à partir de l'IMC.
	 * 
	 * @return
	 */
	public String diagnostic() {
		float imc = calculIMC();

		if (imc < 19)
			return "Sous-_Poids";
		else if (imc < 25)
			return "_Poids sain";
		else if (imc < 30)
			return "Sur_Poids";
		else if (imc < 40)
			return "Obésité";
		return "Obésité morbide";
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

	public float get_Taille() {
		return _Taille;
	}

	public void set_Taille(float _Taille) {
		this._Taille = _Taille;
	}

	public float get_Poids() {
		return _Poids;
	}

	public void set_Poids(float _Poids) {
		this._Poids = _Poids;
	}

	public Sexe get_Genre() {
		return _Genre;
	}

	public void set_Genre(Sexe _Genre) {
		this._Genre = _Genre;
	}

	public int get_ID_Personne() {
		return _ID_Personne;
	}

	public void set_ID_Personne(int _ID_Personne) {
		this._ID_Personne = _ID_Personne;
	}

	public int get_ID_Societe() {
		return _ID_Societe;
	}

	public void set_ID_Societe(int _ID_Societe) {
		this._ID_Societe = _ID_Societe;
	}

	@Override
	public String toString() {
		return "Personne [ID_Personne=" + _ID_Personne + ", Nom=" + _Nom + ", Prenom=" + _Prenom + ", Taille=" + _Taille
				+ ", Poids=" + _Poids + ", Genre=" + _Genre + ", ID_Societe=" + _ID_Societe + "]";
	}
}
