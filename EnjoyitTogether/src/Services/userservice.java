package Services;

import Entites.Membre;
import Gui.InterfaceUserController;
import Util.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javax.swing.JOptionPane;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author JamelSSD
 */
public class userservice implements service<Membre> {

    private Parent root;
    private Stage stage;
    private Scene scene;
    private Connection cnx;
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;

    public userservice() {
        cnx = DataSource.getInstance().getConnection();
    }

    @Override
    public ObservableList<Membre> readAll() {
        ObservableList<Membre> Membres = FXCollections.observableArrayList();
        try {
            String requete = "select * from membre";
            ste = cnx.createStatement();
            rs = ste.executeQuery(requete);
            while (rs.next()) {
                Membre st = new Membre();
                st.setId_membre(rs.getInt("id_membre"));
                st.setNom(rs.getString("nom"));
                st.setPrenom(rs.getString("prenom"));
                st.setCin(rs.getInt("cin"));
                st.setAdresse(rs.getString("adresse"));
                st.setAge(rs.getInt("age"));
                st.setTelephone(rs.getInt("telephone"));
                st.setMail(rs.getString("mail"));
                st.setGenre(rs.getString("genre"));
                st.setLogin(rs.getString("login"));
                st.setPassword(rs.getString("Password"));
                Membres.add(st);
            }
        } catch (SQLException ex) {
            Logger.getLogger(userservice.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Membres;
    }

    @Override
    public void adduser(Membre m) {
        try {
            pst = cnx.prepareStatement("insert into membre(nom, prenom,cin,adresse, age,telephone,mail,genre,login,password) values(?,?,?,?,?,?,?,?,?,?)");
            pst.setString(1, m.getNom());
            pst.setString(2, m.getPrenom());
            pst.setInt(3, m.getCin());
            pst.setString(4, m.getAdresse());
            pst.setInt(5, m.getAge());
            pst.setInt(6, m.getTelephone());
            pst.setString(7, m.getMail());
            pst.setString(8, m.getGenre());
            pst.setString(9, m.getLogin());
            pst.setString(10, m.getPassword());
            pst.executeUpdate();

            pst.execute();
        } catch (SQLException ex) {
            Logger.getLogger(userservice.class.getName()).log(Level.SEVERE, null, ex);
        }

        JOptionPane.showMessageDialog(null, "Account successfully registered");
    }

    @Override
    public void adduserAdmin(Membre m) {
        try {
            pst = cnx.prepareStatement("insert into administrateur(nom, prenom,login,pwd) values(?,?,?,?)");
            pst.setString(1, m.getNom());
            pst.setString(2, m.getPrenom());
            pst.setString(3, m.getLogin());
            pst.setString(4, m.getPassword());
            pst.executeUpdate();

            pst.execute();
        } catch (SQLException ex) {
            Logger.getLogger(userservice.class.getName()).log(Level.SEVERE, null, ex);
        }

        JOptionPane.showMessageDialog(null, "Account successfully registered");
    }

    @Override
    public boolean forgetpass(Membre t) {
        try {
            pst = cnx.prepareStatement("select login from membre where login=?");
            ste = cnx.createStatement();
            pst.setString(1, t.getLogin());

            rs = pst.executeQuery();

            if (rs.next()) {
                System.out.println("nom existe");
                return true;

            } else {
                JOptionPane.showMessageDialog(null, "Error: nom  incorrect");
            }
        } catch (SQLException ex) {
            Logger.getLogger(userservice.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public void delete(Membre t) {
        try {
            pst = cnx.prepareStatement("Delete from membre where id_membre=?");
            pst.setInt(1, t.getId_membre());
            pst.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);

            alert.setContentText("Deleted!");

            alert.showAndWait();
            // table();
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(userservice.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public boolean readById(Membre t) {
        try {

            pst = cnx.prepareStatement("select * from membre where login=? ");
            ste = cnx.createStatement();
            pst.setString(1, t.getLogin());
            rs = pst.executeQuery();
            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "le nom deja existe");
                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(userservice.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean readByIdAdmin(Membre t) {
        try {

            pst = cnx.prepareStatement("select * from administrateur where login=? ");
            ste = cnx.createStatement();
            pst.setString(1, t.getLogin());
            rs = pst.executeQuery();
            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "le nom deja existe");
                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(userservice.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public void login(Membre t) {
        try {
            pst = cnx.prepareStatement("select * from membre where login=? and password=?");
            ste = cnx.createStatement();
            pst.setString(1, t.getLogin());
            pst.setString(2, t.getPassword());
            rs = pst.executeQuery();
            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "login success");
       
            } else {

                JOptionPane.showMessageDialog(null, "login Failed");
                root = FXMLLoader.load(getClass().getResource("/Gui/Accueil.fxml"));

                scene = new Scene(root);
                stage = new Stage();
                stage.setScene(scene);
                stage.show();
            }

        } catch (SQLException ex) {
            Logger.getLogger(userservice.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(userservice.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }

    @Override
    public void loginAdmin(Membre t) {
        try {
            pst = cnx.prepareStatement("select * from administrateur where login=? and pwd=?");
            ste = cnx.createStatement();
            pst.setString(1, t.getLogin());
            pst.setString(2, t.getPassword());
            rs = pst.executeQuery();
            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "login success");

                Parent root = FXMLLoader.load(getClass().getResource("/Gui/CrudAdmin.fxml"));

                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();

            } else {

                JOptionPane.showMessageDialog(null, "login Failed");
            }

        } catch (SQLException ex) {
            Logger.getLogger(userservice.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(userservice.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void updatepass(Membre t) {
        try {
            ste = cnx.createStatement();
            String req = "UPDATE membre set `password` = " + "'" + t.getPassword() + "'" + " WHERE login = '" + t.getLogin() + "'";
            ste.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(userservice.class.getName()).log(Level.SEVERE, null, ex);
        }

        JOptionPane.showMessageDialog(null, "la nouveau mot de passe a changé avec succée");
    }

    @Override
    public void upuser(Membre u) {
        try {
            pst = cnx.prepareStatement("UPDATE membre set `nom` = " + "'" + u.getNom() + "'" + ",`prenom` = " + "'" + u.getPrenom() + "'" + ",`cin` = " + "'" + u.getCin() + "'" + ",`adresse` = " + "'" + u.getAdresse() + "'" + ",`age` = " + "'" + u.getAge() + "'" + ",`telephone` = " + "'" + u.getTelephone() + "'" + ",`mail` = " + "'" + u.getMail() + "'" + ",`genre` = " + "'" + u.getGenre() + "'" + ",`login` = " + "'" + u.getLogin() + "'" + ",`password` = " + "'" + u.getPassword() + "'" + ", WHERE id = " + u.getId_membre());
//             pst.setString(1, u.getNom());
//                        pst.setString(2, u.getPrenom());
//                        pst.setString(3, u.getAdresse());
//                        pst.setInt(4, u.getAge());
//                        pst.setString(5, u.getLogin());
//                        pst.setString(6, u.getPassword());
//                        pst.setInt(7, u.getCin());
//                        pst.setInt(8, u.getTelephone());
//                        pst.setString(9, u.getMail());
//                        pst.setString(10, u.getGenre());
            pst.executeUpdate();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("User update");
            alert.setHeaderText("User update");
            alert.setContentText("Updateddd!");
            alert.showAndWait();
        } catch (SQLException ex) {
            Logger.getLogger(userservice.class.getName()).log(Level.SEVERE, null, ex);
        }
        // JOptionPane.showMessageDialog(null, "Account successfully updated");
    }
}
