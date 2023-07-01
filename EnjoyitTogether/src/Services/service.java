/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.io.IOException;
import java.util.ArrayList;
import javafx.collections.ObservableList;

/**
 * @param <T>
 */
public interface service <T>{
//        void add(T t);
//        void delete(T t);
//        T readById(int id);
//        ArrayList<T>readAll();
//        void update(T t);
    
    void adduser(T u);
     void adduserAdmin(T u);

    boolean forgetpass(T t);

    void delete(T t);

    boolean readById(T t);
boolean readByIdAdmin(T t);
    ObservableList<T> readAll();

    void login(T t);
    void loginAdmin(T t );
    void updatepass(T t);

    void upuser(T t);

}
