package Gui;

import Entites.Membre;
import Services.userservice;
import Util.DataSource;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;


public class InscriptionController implements Initializable {
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;
    private Parent root;
    private Stage stage;
    private Scene scene;

   Connection cnx = DataSource.getInstance().getConnection();
    
    @FXML
    private Button btnSave;

    @FXML
    private TextField txtNom;
    @FXML
    private TextField txtPrenom;
    @FXML
    private TextField txtAge;
    @FXML
    private TextField txtAdresse;
    @FXML
    private TextField txtCin;
    @FXML
    private TextField txtTel;
    @FXML
    private TextField txtEmail;
    @FXML
    private ComboBox txtGenre;
    @FXML
    private TextField txtLogin;
    @FXML
    private TextField txtPwd;

   @FXML
    void select(ActionEvent event) throws SQLException {
        String Nom = txtNom.getText();
        String Prenom = txtPrenom.getText();
        int Cin = Integer.parseInt(txtCin.getText());
        String Adresse = txtAdresse.getText();
        int Age = Integer.parseInt(txtAge.getText());
        int Telephone = Integer.parseInt(txtTel.getText());
        String Mail = txtEmail.getText();
        String Genre = txtGenre.getSelectionModel().getSelectedItem().toString();
        String Login = txtLogin.getText();
        String Password = txtPwd.getText();
        userservice us = new userservice();
        
        
        
        if (Nom.equals("") || Prenom.equals("") || Login.equals("")||Password.equals("") ||Cin == 0  || Telephone == 0 || Mail.equals("") ) {
            JOptionPane.showMessageDialog(null, "Remplir tous les champs!");
        } else {
            if (Telephone <= 8) {

                JOptionPane.showMessageDialog(null, "veuillez choisir 8 caractères !");
            } else {
              
                   
                   Membre m = new Membre(txtLogin.getText());
                if ((us.readById(m) == true)) {
                    System.out.println(m);
                } else {
                    Membre m1 = new Membre(txtNom.getText(), txtPrenom.getText(), Integer.parseInt(txtCin.getText()) , txtAdresse.getText(), Integer.parseInt(txtAge.getText()) ,Integer.parseInt(txtTel.getText()) ,txtEmail.getText(), txtGenre.getSelectionModel().getSelectedItem().toString(),txtLogin.getText(),txtPwd.getText());
                    us.adduser(m1);
                
                     
               
                   

                }

            }
        }

    }

    @FXML

    public void singin(ActionEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("logininterface.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        LoadGenre();
    }

    public void LoadGenre() {
        ObservableList options = FXCollections.observableArrayList();
        options.add("Femme");
        options.add("Homme");
        txtGenre.setItems(options);

    }
}
