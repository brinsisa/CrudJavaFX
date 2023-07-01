package Gui;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author a
 */
public class AccueilController implements Initializable {

    @FXML
    private FontAwesomeIconView Exit;

    @FXML
    private Label Menu;

    @FXML
    private Label MenuBack;

    @FXML
    private AnchorPane slider;
    @FXML
    private BorderPane mainCenter;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        HomePage();
//        Exit.setOnMouseClicked(event ->{
//          System.exit(0);
//        });
        slider.setTranslateX(-176);
        Menu.setOnMouseClicked(event -> {

            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(slider);
            slide.setToX(0);
            slide.play();
            slider.setTranslateX(-176);
            slide.setOnFinished((ActionEvent e) -> {
                Menu.setVisible(false);
                MenuBack.setVisible(true);
            });
        });

        MenuBack.setOnMouseClicked(event -> {

            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(slider);
            slide.setToX(-176);
            slide.play();
            slider.setTranslateX(0);
            slide.setOnFinished((ActionEvent e) -> {
                Menu.setVisible(true);
                MenuBack.setVisible(false);
            });
        });
    }
//     @FXML
//    private void HomePage(ActionEvent event){
//        LoadPage("DisplayCommentaire");
//
//    }

    @FXML
    private void HomePage() {
        LoadPage("Home");
    }

    @FXML
    private void LoginPage(ActionEvent event) {
        LoadPage("logininterface");

    }

    @FXML
    private void SinupPage(ActionEvent event) {
        LoadPage("inscription");

    }

    @FXML
    private void AvisPage(ActionEvent event) {
        LoadPage("DisplayAvis");

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
}
