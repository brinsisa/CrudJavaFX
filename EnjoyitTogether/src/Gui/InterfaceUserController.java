
package Gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author a
 */
public class InterfaceUserController implements Initializable {

    @FXML
    private BorderPane mainCenter;
private Parent root;
    private Scene scene;
    private Stage stage;
    private Stage primarystage;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ProfilPage();
        // TODO
    }

    @FXML
    private void ProfilPage() {
//        LoadPage("");
    }

    @FXML
    private void InvitationsPage(ActionEvent event) {
        LoadPage("DisplayMembre");

    }

    @FXML
    private void AmisPage(ActionEvent event) {
        LoadPage("ListAmis");

    }

    @FXML
    private void VoyagesPage(ActionEvent event) {
        LoadPage("ListVoyage");

    }

    private void LoadPage(String page) {
        Parent root = null;

        try {
            root = FXMLLoader.load(getClass().getResource(page + ".fxml"));
        } catch (IOException ex) {
            Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
        }
        mainCenter.setCenter(root);
    }

    @FXML
    private void LogOut(ActionEvent Event) throws IOException {

        Parent rootRec2 = FXMLLoader.load(getClass().getResource("logininterface.fxml"));;
        Scene rec2 = new Scene(rootRec2);
        Stage app = (Stage) ((Node) Event.getSource()).getScene().getWindow();
        app.setScene(rec2);
        app.show();
    
        
    }
}
