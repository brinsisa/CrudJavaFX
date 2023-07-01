package Gui;

import Entites.TypeVoyage;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import Services.CrudVoyage;
import Util.DataSource;

import javax.swing.*;


public class UpdateVoyageController implements Initializable {
    private Connection cnx;
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;
    private Parent root;
    private Stage stage;
    private Scene scene;

    public UpdateVoyageController () {
        cnx = DataSource.getInstance().getConnection();
    }

    @FXML
    private TextField txtNom;
    @FXML
    private TextField txtDate;
    @FXML
    private TextField txtDescription;
    @FXML
    private ComboBox<TypeVoyage> txtType;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnReturn;

    @FXML
    private void valider(ActionEvent event) throws IOException {
        try {
            String Nom = txtNom.getText();
            String Date = txtDate.getText();
            String Description = txtDescription.getText();
            ListCell<TypeVoyage> Type = txtType.getButtonCell();

            PreparedStatement ste;

                ste = cnx.prepareStatement("update set voyage (nom, date, description, type) values(?,?,?,?)");

                ste.setCursorName(txtNom.getText().trim());
                ste.setCursorName(txtDate.getText().trim());
                ste.setCursorName(txtDescription.getText().trim());
                ste.setCursorName(txtType.getPromptText());


           int  i=ste.executeUpdate();

                JOptionPane.showMessageDialog(null, "Enregistré avec succès");

 //           }
        } catch (SQLException ex) {
            Logger.getLogger(CrudVoyage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void returnToList(ActionEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("ListVoyage.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    /**
     * Initializes the controller class.
     */

    @Override
    public void initialize(URL url, ResourceBundle rb) {


        CrudVoyage crudVoyage=new CrudVoyage();
        ArrayList<TypeVoyage> list = crudVoyage.getTypeVoyage();
        ObservableList<TypeVoyage> observableList = FXCollections.observableList(list);
        txtType = new ComboBox<TypeVoyage>();
        txtType.setItems(observableList);
        txtType.getSelectionModel().select(1);

    }
}
