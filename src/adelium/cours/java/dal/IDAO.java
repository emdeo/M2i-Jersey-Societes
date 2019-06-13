package adelium.cours.java.dal;

import java.util.List;

public interface IDAO <T>
{
    int Create(T obj);
    T Retreive(int id);
    List<T> RetreiveAll();
    int Update(T obj);
    int Delete(int id);    
}
