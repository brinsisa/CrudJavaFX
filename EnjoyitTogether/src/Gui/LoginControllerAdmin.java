/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entites.Membre;
import Services.userservice;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author panda
 */
public class LoginControllerAdmin implements Initializable {

    @FXML
    private TextField txtusername;
    @FXML
    private PasswordField txtpassword;
    @FXML
    private Button btnlogin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void loginAdmin(ActionEvent event) {
         String login=txtusername.getText();
        String pwd=txtpassword.getText();
        
        if (login.equals("") && pwd.equals("")) {
  
               JOptionPane.showMessageDialog(null, "champ vide !");
            }
        else
         {
//           try{
//           pst = cnx.prepareStatement("select login,pwd from administrateur where login=? and pwd=?");
//           ste = cnx.createStatement();
//           pst.setString(1, login );
//           pst.setString(2, pwd);
//           rs = pst.executeQuery();
//           if (rs.next()) {
//           JOptionPane.showMessageDialog(null, "login success");   
//           Stage stage=new Stage();
//           Parent root =FXMLLoader.load(getClass().getResource("CrudAdmin.fxml"));
//           Scene scene = new Scene(root);
//           stage.setScene(scene);
//           stage.show();
//           
//          
             userservice us = new userservice();
             Membre m=new Membre(login,pwd);
             us.loginAdmin(m);

             
             txtusername.setText("");
             txtpassword.setText("");
             txtusername.requestFocus();  
            
            }
    }
    
}
