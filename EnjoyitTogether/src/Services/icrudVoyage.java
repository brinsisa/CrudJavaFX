package Services;

import Entites.TypeVoyage;

import java.util.ArrayList;

public interface icrudVoyage<T> {

    void add(T t);
    void update(T t);
    void delete(T t);
    T getVoyageById(int id);
    ArrayList<T> getListVoyageByName(String nom);
    ArrayList<T> readAll();
    ArrayList<TypeVoyage> getTypeVoyage();

}
