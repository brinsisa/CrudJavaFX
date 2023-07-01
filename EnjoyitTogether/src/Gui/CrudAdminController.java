package Gui;

import Entites.Membre;
import Services.userservice;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import Util.DataSource;

public class CrudAdminController implements Initializable{
    Connection cnx = DataSource.getInstance().getConnection();
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;
    private Parent root;
    private Stage stage;
    private Scene scene;
    int myIndex;
    int id_membre;


      
     @FXML
    private TableColumn<Membre, String> NomColmn;
     
    @FXML
    private TableColumn<Membre, String> AdresseColmn;

    @FXML
    private TableColumn<Membre , Integer> AgeColmn;

    @FXML
    private TableColumn<Membre , Integer> CinColmn;

    @FXML
    private TableColumn<Membre, String> EmailColmn;

    @FXML
    private TableColumn<Membre, String> GenreColmn;

    @FXML
    private TableColumn<Membre , Integer> IDColmn;

    @FXML
    private TableColumn<Membre, String> LoginColmn;

    @FXML
    private TableColumn<Membre, String> PasswordColmn;

    @FXML
    private TableColumn<Membre, String> PrenomColmn;

    @FXML
    private TableColumn<Membre , Integer> TelColmn;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableView<Membre> table;

    @FXML
    private TextField txtAdresse;

    @FXML
    private TextField txtAge;

    @FXML
    private TextField txtCin;

    @FXML
    private TextField txtEmail;

     @FXML
    private ComboBox txtGenre;

    @FXML
    private TextField txtLogin;

    @FXML
    private TextField txtNom;

    @FXML
    private TextField txtPrenom;

    @FXML
    private TextField txtPwd;

    @FXML
    private TextField txtTel;
    userservice us =new userservice();
//    Ajouter  Ajouter  Ajouter  Ajouter  Ajouter  Ajouter  Ajouter  Ajouter  Ajouter 
    @FXML
    void Add(ActionEvent event) throws SQLException {
        String Nom = txtNom.getText();
        String Prenom = txtPrenom.getText();
        String Adresse = txtAdresse.getText();
        int Age = Integer.parseInt(txtAge.getText());
        String Login = txtLogin.getText();
        String Password = txtPwd.getText();
        int Cin = Integer.parseInt(txtCin.getText());
        int Telephone = Integer.parseInt(txtTel.getText());
        String Mail = txtEmail.getText();
        userservice us = new userservice();
        String Genre = txtGenre.getSelectionModel().getSelectedItem().toString();
        
        
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
              
              
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("user Registation");
                        alert.setHeaderText("user Ajouter avec succèes");
                        alert.setContentText("user Ajouter avec succèes");
                        alert.showAndWait();
            table();
            txtNom.setText("");
            txtPrenom.setText("");
            txtAdresse.setText("");
            txtTel.setText("");
            txtGenre.setValue("");
            txtAge.setText("");
            txtCin.setText("");
            txtEmail.setText("");
            txtLogin.setText("");
            txtPwd.setText("");
            txtNom.requestFocus();
            pst.executeUpdate();
        }
        
 }
 }
}

//    tableVieuw tableVieuw tableVieuw tableVieuw tableVieuw tableVieuw tableVieuw tableVieuw tableVieuw tableVieuw tableVieuw tableVieuw 
    public void table() throws SQLException
      {
          Connection cnx = DataSource.getInstance().getConnection();
          ObservableList<Membre> Membres = FXCollections.observableArrayList();

       //{
           
           
//
//           pst = cnx.prepareStatement("select id_membre,nom,prenom,adresse,age,login,password,cin,telephone,mail,genre from membre");  
//           ResultSet rs = pst.executeQuery();
//      {
//        while (rs.next())
//        {
          //  Membre st = new Membre();
            
//            st.setId_membre(rs.getInt("id_membre"));
//            st.setNom(rs.getString("nom"));
//            st.setPrenom(rs.getString("prenom"));
//            st.setAdresse(rs.getString("adresse"));
//            st.setAge(rs.getInt("age"));
//            st.setLogin(rs.getString("login"));
//            st.setPassword(rs.getString("password"));
//            st.setCin(rs.getInt("cin"));
//            st.setTelephone(rs.getInt("telephone"));
//            st.setMail(rs.getString("mail"));
//            st.setGenre(rs.getString("genre"));
//             Membres.add(st);
           
      // }
    //}
                table.setItems(us.readAll());
                IDColmn.setCellValueFactory(new PropertyValueFactory("id_membre"));
                NomColmn.setCellValueFactory(new PropertyValueFactory<>("nom"));
                PrenomColmn.setCellValueFactory(new PropertyValueFactory<>("prenom"));
                AdresseColmn.setCellValueFactory(new PropertyValueFactory<>("adresse"));
                AgeColmn.setCellValueFactory(new PropertyValueFactory<>("age"));
                LoginColmn.setCellValueFactory(new PropertyValueFactory<>("login"));
                PasswordColmn.setCellValueFactory(new PropertyValueFactory<>("password"));
                CinColmn.setCellValueFactory(new PropertyValueFactory<>("cin"));
                TelColmn.setCellValueFactory(new PropertyValueFactory<>("telephone"));   
                EmailColmn.setCellValueFactory(new PropertyValueFactory<>("mail"));
                GenreColmn.setCellValueFactory(new PropertyValueFactory<>("genre"));
     
    //   }}

   table.setRowFactory( (TableView<Membre> tv) -> {
     TableRow<Membre> myRow = new TableRow<>();
     myRow.setOnMouseClicked ((MouseEvent event) ->
     {
        if (event.getClickCount() == 1 && (!myRow.isEmpty()))
        {
           myIndex =  table.getSelectionModel().getSelectedIndex();
          id_membre = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getId_membre()));
           txtNom.setText(table.getItems().get(myIndex).getNom());
           txtPrenom.setText(table.getItems().get(myIndex).getPrenom());
           txtAdresse.setText(table.getItems().get(myIndex).getAdresse());
           txtAge.setText(table.getItems().get(myIndex).getAge() + "");
           txtLogin.setText(table.getItems().get(myIndex).getLogin());
           txtPwd.setText(table.getItems().get(myIndex).getPassword());
           txtCin.setText(table.getItems().get(myIndex).getCin() + "");
           txtTel.setText(table.getItems().get(myIndex).getTelephone() + "");
           txtEmail.setText(table.getItems().get(myIndex).getMail());
           txtGenre.setValue(table.getItems().get(myIndex).getGenre()+ "");
          
                    
        }
     });
        return myRow;
                   });
    
    
      }
//    delete delete delete delete delete delete delete delete delete delete delete delete delete delete delete 
 @FXML
    void Delete(ActionEvent event) {
         Membre  myIndex = table.getSelectionModel().getSelectedItem();
    //    = table.getSelectionModel().getSelectedIndex();
      //  id_membre  = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getId_membre()));
                    
 
        try
        {
//            pst = cnx.prepareStatement("delete from membre where id_membre = " + id_membre + ";");
//           // pst.setInt(1, id);
//            pst.executeUpdate();
            
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//
//           alert.setContentText("Deleted!");
// 
//           alert.showAndWait();
            userservice us = new userservice();
                           // User selectedItem = table.getSelectionModel().getSelectedItem();
                           

                            us.delete(myIndex);
                  table();

        }
        catch (SQLException ex)
        {
          
        }
    }
//update update update update update update update update update update update update update update update 
   @FXML
    void Update(ActionEvent event) throws SQLException {
      
       // String nom,prenom,adresse,login,password,mail;
       // int cin,telephone,age;
       // String Genre = txtGenre.getSelectionModel().getSelectedItem().toString();
        
         Membre  m = table.getSelectionModel().getSelectedItem();
          //  id_membre = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getId_membre()));
//           
       String Nom = txtNom.getText();
        String Prenom = txtPrenom.getText();
        String Adresse = txtAdresse.getText();
        int Age = Integer.parseInt(txtAge.getText());
        String Login = txtLogin.getText();
        String Password = txtPwd.getText();
        int Cin = Integer.parseInt(txtCin.getText());
        int Telephone = Integer.parseInt(txtTel.getText());
        String Mail = txtEmail.getText();
        userservice us = new userservice();
        String Genre = txtGenre.getSelectionModel().getSelectedItem().toString();
        
            
//
//        try
//        {
//            pst = cnx.prepareStatement("update membre set nom = ?,prenom = ? ,adresse = ?,age=?, login=?,password=?,cin=?,telephone=?,mail=?,Genre=? where id_membre  = " + id_membre + "; ");
//                        pst.setString(1, nom);
//                        pst.setString(2, prenom);
//                        pst.setString(3, adresse);
//                        pst.setInt(4, age);
//                        pst.setString(5, login);
//                        pst.setString(6, password);
//                        pst.setInt(7, cin);
//                        pst.setInt(8, telephone);
//                        pst.setString(9, mail);
//                        pst.setString(10, Genre);
//            pst.executeUpdate();
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setTitle("User update");
//            alert.setHeaderText("User update");
//            alert.setContentText("Updateddd!");
//            alert.showAndWait();
              Membre m1 = new Membre(m.getNom(), m.getPrenom(), m.getCin() , m.getAdresse(), m.getAge() ,m.getTelephone() ,m.getMail(), m.getGenre(),m.getLogin(),m.getPassword());

               us.upuser(m1);
                table();

        //}
//        catch (SQLException ex)
//        {
//           Logger.getLogger(CrudAdminController.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
  

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Connection cnx = DataSource.getInstance().getConnection();
        LoadGenre();
        try {
            table();
        } catch (SQLException ex) {
            Logger.getLogger(CrudAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
 

    public void LoadGenre() {
        ObservableList options = FXCollections.observableArrayList();
        options.add("Femme");
        options.add("Homme");
        txtGenre.setItems(options);

    }
   
}