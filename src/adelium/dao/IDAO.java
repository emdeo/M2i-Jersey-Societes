package adelium.dao;

import java.util.ArrayList;

//Agit sur des objets de type T (Data Access Object)
public interface IDAO<T> {

	// Les 4 op�rations de base des BD (CRUD) :

	int Create(T obj); // extrait les donn�es de l'objet T et renvoie le nombre de modifications dans
						// la table
	
	T Read(int id); // renvoie un objet de type T, celui dont l'ID est pass� en param�tre
	ArrayList<T> ReadAll(); // renvoie une toute la collection d'objets T

	int Update(T obj); // extrait les donn�es de l'objet T et renvoie le nombre de modifications

	int Delete(int id); // supprime la donn�e dont on passe l'ID en prm
}