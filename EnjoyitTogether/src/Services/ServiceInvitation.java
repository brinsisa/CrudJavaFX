/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Models.Invitation;
import Entites.Membre;
import Util.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 
 */
public class ServiceInvitation implements Iinvitation {

    Connection cnx = DataSource.getInstance().getConnection();
    Statement st = null;

    @Override
    public int sendInvitation(int user_id, int membre_id) {

        ServiceMembre sm = new ServiceMembre();

        int response = 0;

        Membre user = sm.retrieveMembre(user_id);
        Membre membre = sm.retrieveMembre(membre_id);

        if ((user != null) && (membre != null)) {
            if (this.findInvitation(user_id, membre_id)) {
                response = 1;
            } else {
                String sql = "INSERT INTO invitation(id_user, id_membre) VALUES(" + user_id + "," + membre_id + ")";

                try {
                    st = cnx.createStatement();
                    st.execute(sql);;
                    response = 2;

                } catch (SQLException e) {
                    System.out.println(e);
                    response = 0;
                }
            }
        }

        return response;
    }

    @Override
    public boolean findInvitation(int user_id, int membre_id) {
        boolean res = false;

        String sql = "SELECT * FROM invitation WHERE id_user =" + user_id + " AND id_membre =  " + membre_id;

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
    public Invitation getInvitation(int user_id, int membre_id) {
        Invitation invitation = new Invitation();

        String sql = "SELECT * FROM invitation WHERE id_user ="
                + user_id + " AND id_membre =  " + membre_id
                + " OR id_user =" + membre_id + " AND id_membre= " + user_id;

        try {
            st = cnx.createStatement();
            ResultSet rs = st.executeQuery(sql);

            if (rs.next()) {
                invitation.setId_invitation(rs.getInt("id"));
                invitation.setId_user(rs.getInt("id_user"));
                invitation.setId_membre(rs.getInt("id_membre"));
                invitation.setEtat(rs.getInt("etat"));
            }

        } catch (SQLException e) {
            System.out.println(e);
        }

        return invitation;
    }

    @Override
    public void accepterInvitation(int invitation_id, int user_id, int membre_id) {
        try {
            String sql = "UPDATE invitation SET etat =" + 1 + " WHERE id =  " + invitation_id;

            st = cnx.createStatement();
            st.execute(sql);

            ServiceAmis sa = new ServiceAmis();
            sa.addAmi(user_id, membre_id);
            sa.addAmi(membre_id, user_id);

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public void supprimerInvitation(int invitation_id, int user_id, int membre_id) {
        try {
            String sql = "DELETE FROM invitation  WHERE id =  " + invitation_id;

            st = cnx.createStatement();
            st.execute(sql);

            ServiceAmis sa = new ServiceAmis();
            sa.removeAmi(user_id, membre_id);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

}
