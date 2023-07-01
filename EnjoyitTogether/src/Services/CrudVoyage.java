package Services;

import Entites.TypeVoyage;
import Entites.Voyage;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import Util.DataSource;

public class CrudVoyage implements icrudVoyage<Voyage> {

    private Connection cnx;
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;

    public CrudVoyage() {

        cnx = DataSource.getInstance().getConnection();
    }

    @Override
    public void add(Voyage voyage) {
        try {
            ste = cnx.createStatement();
            String requete = "INSERT INTO voyage(nom,description,type)  VALUES ('" + voyage.getNom() + "','" + voyage.getDescription() + "','" + voyage.getType() + "')";

            ste.executeUpdate(requete);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void update(Voyage voyage) {

    }

    @Override
    public void delete(Voyage voyage) {

    }

    @Override
    public Voyage getVoyageById(int id) {
        return null;
    }

    @Override
    public ArrayList<Voyage> getListVoyageByName(String nom) {
        return null;
    }

    @Override
    public ArrayList<Voyage> readAll() {
        ArrayList<Voyage> list = new ArrayList<>();
        try {
            String query = "select * from voyage";

            ste = cnx.createStatement();
            rs = ste.executeQuery(query);
            while (rs.next()) {
                Voyage V = new Voyage(rs.getInt(1),rs.getString(2), rs.getDate(3), rs.getString(4), rs.getString(5));
                list.add(V);
            }

        } catch (SQLException ex) {
            Logger.getLogger(CrudVoyage.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public ArrayList<TypeVoyage> getTypeVoyage() {
        ArrayList<TypeVoyage> list = new ArrayList<>();
        try {
            String query = "select * from type_voyage";

            ste = cnx.createStatement();
            rs = ste.executeQuery(query);
            while (rs.next()) {
                TypeVoyage TV = new TypeVoyage();
                TV.setIdType(rs.getInt(1));
                TV.setNomType(rs.getString(2));
                list.add(TV);
            }

        } catch (SQLException ex) {
            Logger.getLogger(CrudVoyage.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
