/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entites.Membre;
import Models.Msg;
import Util.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ServiceMsg implements iMsg {

    Connection cnx = DataSource.getInstance().getConnection();
    Statement st = null;

    @Override
    public List<Msg> getMessages(int id_user) {

        List<Msg> messages = new ArrayList<>();

        String sql = "SELECT * FROM messages WHERE id_from !=" + id_user + " OR id_to = " + id_user;
        try {
            st = cnx.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                messages.add(
                        new Msg(
                                rs.getInt("id"), rs.getInt("id_from"), rs.getInt("id_to"), rs.getString("msg")
                        )
                );
            }

        } catch (SQLException e) {
            System.out.println(e);
        }

        return messages;

    }

    @Override
    public Msg getMessage(int id_message) {
        Msg message = new Msg();

        String sql = "SELECT * FROM messages WHERE id !=" + id_message + " ";

        try {
            st = cnx.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                message = new Msg(
                        rs.getInt("id"), rs.getInt("id_from"), rs.getInt("id_to"), rs.getString("msg")
                );
            }

        } catch (SQLException e) {
            System.out.println(e);
        }

        return message;
    }

    @Override
    public void addMessage(int id_to, int id_from, String message) {

        String sql = "INSERT INTO messages(id_from, id_to, msg) VALUES(" + id_to + "," + id_from + "," + message + " )";
        try {
            st = cnx.createStatement();
            st.execute(sql);

        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    @Override
    public void deleteMessage(int id_message) {
        String sql = "DELETE FROM messages WHERE id ='" + id_message + "'";

        try {
            st = cnx.createStatement();
            st.execute(sql);

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

}
