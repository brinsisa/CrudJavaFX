package Gui;

import Entites.Avis;
import Services.Iavis;
import Services.ServiceAvis;
import Util.DataSource;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author a
 */
public class AjouterAvisController implements Initializable {

    @FXML
    private Rating ratSTAR;

    @FXML
    private ComboBox membrecombo;
    Iavis sa = new ServiceAvis();
//    private Membre m;
     
    Avis a;
    private int idAvis;
    private boolean update;
    private int idm[];
    Statement st = null;
    Connection cnx = DataSource.getInstance().getConnection();

    @FXML
    private void AjouterRate(ActionEvent event) throws SQLException {

//       int userid=Integer.parseInt(membretext.getText());
        double rate = ratSTAR.getRating();
//        m=new Membre();
        if (update == false) {
            if (membrecombo.getSelectionModel().getSelectedItem()==null || ratSTAR.getRating() == 0.0) {
                Alert alerts = new Alert(Alert.AlertType.WARNING);
                alerts.setTitle("Warning");
                alerts.setHeaderText(null);
                alerts.setContentText("Veuillez remplir les champs!");
                alerts.show();
            } else {
                a = new Avis(membrecombo.getSelectionModel().getSelectedItem().toString(), ratSTAR.getRating(),idm[membrecombo.getSelectionModel().getSelectedIndex()]);
                sa.ajouterAvis(a);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Info");
                alert.setHeaderText("Insertion");
                alert.setContentText("Data Inserted Successfully ... ");
                alert.show();
                System.out.println("You've successfully created a new rating ");

//            System.out.println("user1 rating by user avec l'id "+ userid+ ",le rating est : "+rate);
            }
        } else {

            a = new Avis();
            a.setId_avis(idAvis);
            a.setValeur_avis(ratSTAR.getRating());
            sa.modifierAvis(a);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Info");
            alert.setHeaderText("modification");
            alert.setContentText("Data updated Successfully ... ");
            alert.show();
        }
        annuler();
    }

    @FXML
    public void annuler() {
//        membrecombo.setItems(null);
        ratSTAR.setRating(0);
    }
 

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        LoadNamesM();
        // TODO
    }

    void setTextField(int id, double rating) {
        idAvis = id;
        ratSTAR.setRating(rating);

    }

    void setUpdate(boolean b) {
        this.update = b;

    }

    public void LoadNamesM() {
        ObservableList names = FXCollections.observableArrayList();
        int index = 0;
        idm = new int[10];
        try {
            st = cnx.createStatement();

            ResultSet rs = st.executeQuery("SELECT id_membre, prenom FROM membre");
            while (rs.next()) {

                idm[index] = rs.getInt(1);
                index++;
                names.add(rs.getString(2));
                membrecombo.setItems(names);
            }
        } catch (SQLException ex) {
            System.out.println(ex);

        }
    }
}
