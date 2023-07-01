/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import static Gui.DisplayMembresController.membre;
import Entites.Membre;
import Services.ServiceAmis;
import Services.ServiceMembre;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 */
public class ListAmisController implements Initializable {

    @FXML
    private TableView<Membre> tablec2;
    @FXML
    private TableColumn<Membre, Integer> id;

    @FXML
    private TableColumn<Membre, String> login;

    @FXML
    private TableColumn<Membre, String> nom;

    @FXML
    private TableColumn<Membre, String> prenom;

    @FXML
    private TableColumn<Membre, String> email;

    @FXML
    private TableColumn<Membre, String> profilCol;

    ServiceMembre sm = new ServiceMembre();
    ServiceAmis sa = new ServiceAmis();
    public static Membre membre;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadAmis();
    }

    public void envoyerMessage() {

    }

    @FXML
    public void chercherAmis() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/Gui/DisplayMembres.fxml"));
            loader.load();
            DisplayMembresController displayMembresController = loader.getController();
            Parent parent = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(parent));
            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(DisplayMembresController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void getAddAvis() {
//        membre = tablec2.getSelectionModel().getSelectedItem();
//        FXMLLoader loader = new FXMLLoader();
//        DisplayAvisController dac = loader.getController();
//        dac.initAvis(membre.getId_membre(), membre.getPrenom());
        try {
            Parent root1 = FXMLLoader.load(getClass().getResource("/Gui/DisplayAvis.fxml"));
            Scene scene1 = new Scene(root1);
            Stage stage = new Stage();
            stage.setScene(scene1);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex);
        }

    }

    @FXML
    private void displayMembres() {
        ObservableList<Membre> data = FXCollections.observableArrayList(sa.getAmis(1));

        login.setCellValueFactory(new PropertyValueFactory<>("login"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        email.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        tablec2.setItems(data);
    }

    private void loadAmis() {

        ObservableList<Membre> data = FXCollections.observableArrayList(sa.getAmis(1));

        login.setCellValueFactory(new PropertyValueFactory<>("login"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        email.setCellValueFactory(new PropertyValueFactory<>("adresse"));

        displayMembres();

        Callback<TableColumn<Membre, String>, TableCell<Membre, String>> cellFoctory;

        cellFoctory = (TableColumn<Membre, String> param) -> {
            // make cell containing buttons
            final TableCell<Membre, String> cell = new TableCell<Membre, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        FontAwesomeIconView infoIcon2 = new FontAwesomeIconView(FontAwesomeIcon.EYE);
//                        FontAwesomeIconView RateIcon = new FontAwesomeIconView(FontAwesomeIcon.STAR);
                        infoIcon2.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#1bdaad;"
                        );
//                        RateIcon.setStyle(
//                                " -fx-cursor: hand ;"
//                                + "-glyph-size:28px;"
//                                + "-fx-fill:#ebe715;"
//                        );
                        infoIcon2.setOnMouseClicked((event) -> {

                            membre = tablec2.getSelectionModel().getSelectedItem();

                            System.out.println(membre.toString());
                            try {
                                FXMLLoader loader = new FXMLLoader();
                                loader.setLocation(getClass().getResource("/Gui/DisplayMembre2.fxml"));
                                loader.load();
                                DisplayMembre2Controller displayMembre2Controller = loader.getController();

                                Parent parent = loader.getRoot();

                                Stage stage = new Stage();
                                stage.setScene(new Scene(parent));
                                stage.show();

                            } catch (IOException ex) {
                                Logger.getLogger(DisplayMembresController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        });
//                        RateIcon.setOnMouseClicked((event) -> {
//
//                            membre = tablec2.getSelectionModel().getSelectedItem();
//                            FXMLLoader loader = new FXMLLoader();
//                            loader.setLocation(getClass().getResource("DisplayAvis.fxml"));
//
//                            try {
//                                loader.load();
//                            } catch (IOException ex) {
//                                System.out.println(ex);
//                            }
//                            DisplayAvisController dac = loader.getController();
//                            dac.initAvis(membre.getId_membre(), membre.getPrenom());
//                            Parent parent = loader.getRoot();
//                            Stage stage = new Stage();
//                            stage.setScene(new Scene(parent));
//                            stage.show();
//
//                        });
                        HBox managebtn = new HBox(infoIcon2);   //, RateIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setHgrow(this, Priority.ALWAYS);
                        HBox.setMargin(infoIcon2, new Insets(2, 2, 0, 3));
//                        HBox.setMargin(RateIcon, new Insets(2, 3, 0, 2));

                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };

        profilCol.setCellFactory(cellFoctory);
        tablec2.setItems(data);
    }

}
