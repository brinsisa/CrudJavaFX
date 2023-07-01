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
public class ServiceAmis implements Iamis {

    Connection cnx = DataSource.getInstance().getConnection();
    Statement st = null;

    @Override
    public List<Membre> getAmis(int id) {
        List<Membre> membres = new ArrayList<>();

        String sql = "SELECT membre.* FROM membre, liste_amis WHERE membre.id_membre=liste_amis.id_membre AND liste_amis.id_user=" + id + " ";

        try {
            st = cnx.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                //int id, String nom, String prenom, String adresse, String age, String login, String pwd, String cin
                membres.add(
                        new Membre(
                                rs.getInt("id_membre"), rs.getString("nom"), rs.getString("prenom"), rs.getString("adresse"), rs.getString("age"), rs.getString("login"), rs.getString("pwd"), rs.getString("cin")
                        )
                );
            }

        } catch (SQLException e) {
            System.out.println(e);
        }

        return membres;
    }

    @Override
    public boolean findAmi(int user_id, int membre_id) {
        boolean res = false;

        String sql = "SELECT * FROM liste_amis WHERE id_user =" + user_id + " AND id_membre =  " + membre_id;

        try {
            st = cnx.createStatement();
            ResultSet rs = st.executeQuery(sql);

            if (rs.next()) {
                res = true;
            }

        } catch (SQLException e) {
            System.out.println(e);
        }

        return res;
    }

    @Override
    public boolean removeAmi(int user_id, int membre_id) {
        ServiceMembre sm = new ServiceMembre();

        boolean response = false;

        Membre user = sm.retrieveMembre(user_id);
        Membre membre = sm.retrieveMembre(membre_id);

        if ((user != null) && (membre != null)) {
            if (this.findAmi(user_id, membre_id)) {
                String sql = "DELETE FROM liste_amis WHERE id_user ='" + user_id + "' AND id_membre =  '" + membre_id + "'";

                try {
                    st = cnx.createStatement();
                    st.execute(sql);;
                    response = true;

                } catch (SQLException e) {
                    System.out.println(e);
                    response = false;
                }
            } else {
                response = false;
            }
        }

        return response;
    }

    @Override
    public boolean addAmi(int user_id, int membre_id) {
        boolean response = false;

        String sql = "INSERT INTO liste_amis(id_user, id_membre) VALUES(" + user_id + "," + membre_id + ")";
        try {
            st = cnx.createStatement();
            st.execute(sql);;
            response = true;

        } catch (SQLException e) {
            System.out.println(e);
        }

        return response;
    }

}
