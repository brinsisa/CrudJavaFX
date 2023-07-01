package Gui;

import Entites.Commentaire;
import Services.Icommentaire;
import Services.ServiceCommentaire;
import Util.DataSource;
import java.awt.event.MouseEvent;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class AjouterCommentaireController implements Initializable {

//    Imembre sm = new ServiceMembre();
//    Ivoyage sv = new ServiceVoyage();
    Icommentaire Ic = new ServiceCommentaire();
    private Commentaire c;
//    private Voyage v;
//    private Membre m;
    private Parent root;
    private Stage stage;
    private Scene scene;
    @FXML
    private TextArea textcontenu;

    @FXML
    private ComboBox idvoyagecombo;

    @FXML
    private ComboBox idmembrecombo;
    private boolean update;

    private int idc;
    Statement st = null;
    Connection cnx = DataSource.getInstance().getConnection();
    private int idv[];
    private int idm[];

    @FXML
    private void ajoutComment(ActionEvent event) throws Exception {

        if (update == false) {
            if (textcontenu.getText()==null || idmembrecombo.getSelectionModel().getSelectedItem()==null || idvoyagecombo.getSelectionModel().getSelectedItem()==null) {
                Alert alerts = new Alert(Alert.AlertType.WARNING);
                alerts.setTitle("Warning");
                alerts.setHeaderText(null);
                alerts.setContentText("Veuillez remplir les champs!");
                alerts.show();
            } else {
                c = new Commentaire(idmembrecombo.getSelectionModel().getSelectedItem().toString(), idvoyagecombo.getSelectionModel().getSelectedItem().toString(), textcontenu.getText(), idm[idmembrecombo.getSelectionModel().getSelectedIndex()], idv[idvoyagecombo.getSelectionModel().getSelectedIndex()]);
                Ic.ajouterCommentaire(c);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Info");
                alert.setHeaderText("Insertion");
                alert.setContentText("Data Inserted Successfully ... ");
                alert.show();
                System.out.println("You've successfully created a new Commment ");

            }
        } else {

            c = new Commentaire();
            c.setId_commentaire(idc);
            c.setContenu(textcontenu.getText());
            Ic.modifierCommentaire(c);
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
        textcontenu.setText(null);
//        idmembrecombo.setItems(null);
//        idvoyagecombo.setItems(null);
//        datetext.setValue(null);
    }

    @FXML
    public void fermer(MouseEvent event) {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        LoadNamesM();
        LoadNamesV();
    }

 

    void setTextField(int id, String membre, String voyage, String contenu) {
        idc = id;
        this.idmembrecombo.setValue(membre);
        this.idvoyagecombo.setValue(voyage);
        textcontenu.setText(contenu);
    }

    void setUpdate(boolean b) {
        this.update = b;

    }

    public void LoadNamesM() {
        ObservableList names = FXCollections.observableArrayList();
        idm = new int[10];
        int index = 0;
        try {
            st = cnx.createStatement();

            ResultSet rs = st.executeQuery("SELECT id_membre, prenom FROM membre");
            while (rs.next()) {
                idm[index] = rs.getInt(1);
                index++;
                names.add(rs.getString(2));
                idmembrecombo.setItems(names);
            }
        } catch (SQLException ex) {
            System.out.println(ex);

        }
    }

    public void LoadNamesV() {
        ObservableList namesv = FXCollections.observableArrayList();
        idv = new int[10];
        int index = 0;
        try {
            st = cnx.createStatement();

            ResultSet rs = st.executeQuery("SELECT id_voyage, nom FROM voyage");
            while (rs.next()) {
                idv[index] = rs.getInt(1);
                index++;
                namesv.add(rs.getString(2));
                idvoyagecombo.setItems(namesv);
            }
        } catch (SQLException ex) {
            System.out.println(ex);

        }
    }

}
