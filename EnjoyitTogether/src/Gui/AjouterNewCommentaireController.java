package Gui;

import Entites.NewCommentaire;
import Services.InewCommentaire;
import Services.ServiceNewCommentaire;
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
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author a
 */
public class AjouterNewCommentaireController implements Initializable {

//    Imembre sm = new ServiceMembre();
    InewCommentaire Ic = new ServiceNewCommentaire();
    private NewCommentaire c;
//    private Membre m;

    @FXML
    private TextArea textcontenu;
//    @FXML
//    private TextField datetext;
DisplayNewCommentaireController dnc= new DisplayNewCommentaireController();
    @FXML
    private ComboBox commentairecombo;
    @FXML
    private ComboBox membrecombo;
    private boolean update;
    private int idnc;
    private int commentaireId[];
    private int membreId[];
    Statement st = null;
    Connection cnx = DataSource.getInstance().getConnection();

    @FXML
    private void ajoutNewComment(ActionEvent event) throws Exception {
//
//        String contenu = textcontenu.getText();
//        String date = datetext.getText();
//        int id_membre = Integer.parseInt(idmembretext.getText());
//        int id_voyage = Integer.parseInt(idvoyagetext.getText());
        if (update == false) {
            if (textcontenu.getText()==null || membrecombo.getSelectionModel().getSelectedItem()==null || commentairecombo.getSelectionModel().getSelectedItem()==null) {
                Alert alerts = new Alert(Alert.AlertType.WARNING);
                alerts.setTitle("Warning");
                alerts.setHeaderText(null);
                alerts.setContentText("Veuillez remplir les champs!");
                alerts.show();
            } else {
                c = new NewCommentaire(commentairecombo.getSelectionModel().getSelectedItem().toString(), membrecombo.getSelectionModel().getSelectedItem().toString(), textcontenu.getText(), commentaireId[commentairecombo.getSelectionModel().getSelectedIndex()], membreId[membrecombo.getSelectionModel().getSelectedIndex()]);
                Ic.ajouterNewCommentaire(c);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Info");
                alert.setHeaderText("Insertion");
                alert.setContentText("Data Inserted Successfully ... ");
                alert.show();
                System.out.println("You've successfully created a new Commment ");

            }
        } else {

            c = new NewCommentaire();
            c.setId_Newcommentaire(idnc);
            c.setContenu(textcontenu.getText());
            Ic.modifierNewCommentaire(c);
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
        membrecombo.setValue(null);
        commentairecombo.setValue(null);
    }

    @FXML
    public void fermer(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        LoadMembre();
        LoadComment();
    }

    void setTextField(int id, String membre, String commentaire, String contenu) {
        idnc = id;
        this.membrecombo.setValue(membre);
        this.commentairecombo.setValue(commentaire);
        textcontenu.setText(contenu);
//        this.datetext.setText(date);

    }

    void setUpdate(boolean b) {
        this.update = b;

    }

    public void LoadMembre() {
        ObservableList names = FXCollections.observableArrayList();
        int index = 0;
        membreId = new int[10];
        try {
            st = cnx.createStatement();

            ResultSet rs = st.executeQuery("SELECT id_membre, prenom FROM membre");
            while (rs.next()) {
                membreId[index] = rs.getInt(1);
                index++;
                names.add(rs.getString(2));
                membrecombo.setItems(names);
            }
        } catch (SQLException ex) {
            System.out.println(ex);

        }
    }

    public void LoadComment() {
        ObservableList contenu = FXCollections.observableArrayList();
        int index = 0;
        commentaireId = new int[10];
        try {
            st = cnx.createStatement();

            ResultSet rs = st.executeQuery("SELECT id_commentaire, contenu FROM commentaire");
            while (rs.next()) {
                commentaireId[index] = rs.getInt(1);
                index++;
                contenu.add(rs.getString(2));
                commentairecombo.setItems(contenu);
            }
        } catch (SQLException ex) {
            System.out.println(ex);

        }
    }

}
