/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entites.Membre;
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
 * 
 */
public class DisplayMembresController implements Initializable {

    @FXML
    private TableView<Membre> tablec;
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
    public static Membre membre;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadMembers();
    }

    @FXML
    public void friendsList() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/Gui/ListAmis.fxml"));
            loader.load();
            ListAmisController listAmisController = loader.getController();
            Parent parent = loader.getRoot(); 
            Stage stage = new Stage();
            stage.setScene(new Scene(parent));
            stage.show();
          
        } catch (IOException ex) {
            Logger.getLogger(DisplayMembresController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void displayMembres() {
        System.out.println("REFRESH");
        ObservableList<Membre> data = FXCollections.observableArrayList(sm.getMembres(1));

        login.setCellValueFactory(new PropertyValueFactory<>("login"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        email.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        tablec.setItems(data);
    }

    private void loadMembers() {

        ObservableList<Membre> data = FXCollections.observableArrayList(sm.getMembres(1));

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

                        FontAwesomeIconView infoIcon = new FontAwesomeIconView(FontAwesomeIcon.EYE);
                        infoIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#1bdaad;"
                        );

                        infoIcon.setOnMouseClicked((event) -> {

                            membre = tablec.getSelectionModel().getSelectedItem();

                            try {
                                FXMLLoader loader = new FXMLLoader();
                                loader.setLocation(getClass().getResource("/Gui/DisplayMembre.fxml"));
                                loader.load();
                                DisplayMembreController AjouterCommentaire = loader.getController();

                                Parent parent = loader.getRoot();

                                Stage stage = new Stage();
                                stage.setScene(new Scene(parent));
                                stage.show();

                            } catch (IOException ex) {
                                Logger.getLogger(DisplayMembresController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        });

                        HBox managebtn = new HBox(infoIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setHgrow(this, Priority.ALWAYS);
                        HBox.setMargin(infoIcon, new Insets(2, 2, 0, 3));

                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };

        profilCol.setCellFactory(cellFoctory);
        tablec.setItems(data);
    }

}
