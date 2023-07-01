
package Gui;

import Entites.Membre;
import Services.userservice;
import Util.DataSource;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;

import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;


public class LoginControllerUser implements Initializable {
    private Connection cnx;
//    private Statement ste;
//    private PreparedStatement pst;
//    private ResultSet rs;
    private Stage stage;
     private Scene scene;
    private Parent root;
    @FXML
    private Label btnForgot;
    @FXML
    private Button btnSignup;
    @FXML
    private Label lblErrors;
    private Stage primarystage;
    public LoginControllerUser() {
         cnx = DataSource.getInstance().getConnection();
    }  

    
      @FXML
    private Button btnlogin;

    @FXML
    private PasswordField txtpassword;

    @FXML
    private TextField txtusername;

       @FXML

   public void singup(ActionEvent event) throws IOException {
       
  root = FXMLLoader.load(getClass().getResource("inscription.fxml"));
  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  scene = new Scene(root);
  stage.setScene(scene);
  stage.show();
 }
    
    @FXML
    void login(ActionEvent event) throws IOException {
        
        String login=txtusername.getText();
        String password=txtpassword.getText();
        
        if (login.equals("") && password.equals("")) {
  
               JOptionPane.showMessageDialog(null, "champ vide !");
            }
        else
         {
//           try{
//           pst = cnx.prepareStatement("select * from membre where login=? and password=?");
//           ste = cnx.createStatement();
//           pst.setString(1, login );
//           pst.setString(2, password);
//           rs = pst.executeQuery();
//            if (rs.next()) {
//             JOptionPane.showMessageDialog(null, "login success");          
//            }
//            else{     
//             
//             JOptionPane.showMessageDialog(null, "login Failed");
//             txtusername.setText("");
//             txtpassword.setText("");
//             txtusername.requestFocus();  
//            
//            }
//  } catch (SQLException ex) {
//            
//        }
             Membre m=new Membre(login,password);
             userservice us = new userservice();
             us.login(m);

        Parent root2 = FXMLLoader.load(getClass().getResource("InterfaceUser.fxml"));;
        Scene scene2 = new Scene(root2);
        Stage app = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app.setScene(scene2);
        app.show();
           
            }
    }
    
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
//    @FXML
//   void loginAdmin(ActionEvent event) throws  IOException {
//        
//        String login=txtusername.getText();
//        String pwd=txtpassword.getText();
//        
//        if (login.equals("") && pwd.equals("")) {
//  
//               JOptionPane.showMessageDialog(null, "champ vide !");
//            }
//        else
//         {
////           try{
////           pst = cnx.prepareStatement("select login,pwd from administrateur where login=? and pwd=?");
////           ste = cnx.createStatement();
////           pst.setString(1, login );
////           pst.setString(2, pwd);
////           rs = pst.executeQuery();
////           if (rs.next()) {
////           JOptionPane.showMessageDialog(null, "login success");   
////           Stage stage=new Stage();
////           Parent root =FXMLLoader.load(getClass().getResource("CrudAdmin.fxml"));
////           Scene scene = new Scene(root);
////           stage.setScene(scene);
////           stage.show();
////           
////          
//             userservice us = new userservice();
//             Membre m=new Membre(login,pwd);
//             us.loginAdmin(m);
//
//             
//             txtusername.setText("");
//             txtpassword.setText("");
//             txtusername.requestFocus();  
//            
//            }
//
//            
//    }
    
}
