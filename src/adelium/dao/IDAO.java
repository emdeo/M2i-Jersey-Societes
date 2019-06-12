package adelium.dao;

import java.util.ArrayList;

//Agit sur des objets de type T (Data Access Object)
public interface IDAO<T> {

	// Les 4 opérations de base des BD (CRUD) :

	int Create(T obj); // extrait les données de l'objet T et renvoie le nombre de modifications dans
						// la table
	
	T Read(int id); // renvoie un objet de type T, celui dont l'ID est passé en paramètre
	ArrayList<T> ReadAll(); // renvoie une toute la collection d'objets T

	int Update(T obj); // extrait les données de l'objet T et renvoie le nombre de modifications

	int Delete(int id); // supprime la donnée dont on passe l'ID en prm
}