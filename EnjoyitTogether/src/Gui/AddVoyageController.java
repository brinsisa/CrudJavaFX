package Gui;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import Entites.Voyage;
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


public class AddVoyageController implements Initializable {
    private Connection cnx;
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;
    private Parent root;
    private Stage stage;
    private Scene scene;

    public AddVoyageController () {
        cnx = DataSource.getInstance().getConnection();
    }

    @FXML
    private TextField txtNom;
//    @FXML
//    private TextField txtDate;
    @FXML
    private TextField txtDescription;
    @FXML
    private ComboBox txtType;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnReturn;
Voyage v;
CrudVoyage cv=new CrudVoyage();
    @FXML
    private void valider(ActionEvent event) throws IOException {
        
            String Nom = txtNom.getText();
//            String Date = txtDate.getText();
            String Description =txtDescription.getText();
//            ListCell<TypeVoyage> Type= txtType.getButtonCell();

            if (Nom.equals("")||Description.equals(""))

                JOptionPane.showMessageDialog(null,"Remplir tous les champs!");

//            else {
//
//                    ste = cnx.createStatement();
//                    ste.setText(Nom);
//                    rs = ste.executeQuery("select * from voyage where nom=?");
//
//                    if(rs.next()){
//                        JOptionPane.showMessageDialog(null,"voyage déjà ajoutee ");
//                    }

                    else{
                        v = new Voyage(txtNom.getText(),txtDescription.getText(),txtType.getSelectionModel().getSelectedItem().toString());
                cv.add(v);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Info");
                alert.setHeaderText("Insertion");
                alert.setContentText("Data Inserted Successfully ... ");
                alert.show();
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

        ObservableList type = FXCollections.observableArrayList();
      
        try {
            ste = cnx.createStatement();

            ResultSet rs = ste.executeQuery("SELECT nom_type FROM type_voyage");
            while (rs.next()) {
                type.add(rs.getString(1));
                txtType.setItems(type);
            }
        } catch (SQLException ex) {
            System.out.println(ex);

        }

    }
}
