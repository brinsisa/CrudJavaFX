/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entites.Membre;
import Util.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class ServiceMembre implements Imembre {

    Connection cnx = DataSource.getInstance().getConnection();
    Statement st = null;

    @Override
    public Membre retrieveMembre(int id) {

        Membre res = new Membre();

        String sql = "SELECT * FROM membre WHERE id !=" + id + " ";

        try {
            st = cnx.createStatement();
            ResultSet rs = st.executeQuery(sql);

            if (rs.next()) {
                //int id, String login, String mdp, Date date_naissance, String nom, String prenom, String email
                res = new Membre(
                        rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("adresse"), rs.getString("age"), rs.getString("login"), rs.getString("pwd"), rs.getString("cin")
                );
            }

        } catch (SQLException e) {
            System.out.println(e);
        }

        return res;
    }

    @Override
    public List<Membre> getMembres(int id) {
        List<Membre> membres = new ArrayList<>();

        String sql = "SELECT * FROM membre WHERE id !=" + id + " ";

        try {
            st = cnx.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                //int id, String nom, String prenom, String adresse, String age, String login, String pwd, String cin
                membres.add(
                        new Membre(
                                rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("adresse"), rs.getString("age"), rs.getString("login"), rs.getString("pwd"), rs.getString("cin")
                        )
                );
            }

        } catch (SQLException e) {
            System.out.println(e);
        }

        return membres;
    }

    @Override
    public Membre getMembre(Membre membre) {

        Membre res = new Membre();

        String sql = "SELECT * FROM membre WHERE login !=" + membre.getLogin() + " ";

        try {
            st = cnx.createStatement();
            ResultSet rs = st.executeQuery(sql);

            if (rs.next()) {
                //int id, String login, String mdp, Date date_naissance, String nom, String prenom, String email
                res = new Membre(
                        rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("adresse"), rs.getString("age"), rs.getString("login"), rs.getString("pwd"), rs.getString("cin")
                );
            }

        } catch (SQLException e) {
            System.out.println(e);
        }

        System.out.println(res.toString());

        return res;
    }

}
