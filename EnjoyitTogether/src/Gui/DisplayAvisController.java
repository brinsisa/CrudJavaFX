package Gui;

import Entites.Avis;
import Entites.Membre;
import Services.ServiceAvis;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
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
 * @author a
 */
public class DisplayAvisController implements Initializable {

    @FXML
    private TableView<Avis> table_a;
    @FXML
    private TableColumn<Avis, Date> date_acol;
    @FXML
    private TableColumn<Avis, String> nom_mcol;
    @FXML
    private TableColumn<Avis, Integer> rating_acol;
    @FXML
    private TableColumn<Avis, Integer> id_acol;
    @FXML
    private TableColumn<Avis, String> editcol;
//    Membre m = new Membre();
    Avis a = new Avis();
    ServiceAvis sa = new ServiceAvis();
    Membre membre;
private int id;
    public void DisplayAvis() {
        ObservableList<Avis> data = FXCollections.observableArrayList(sa.afficherAvis());
//       data.clear();

        nom_mcol.setCellValueFactory(new PropertyValueFactory<>("nom_membre"));
        rating_acol.setCellValueFactory(new PropertyValueFactory<>("valeur_avis"));
        date_acol.setCellValueFactory(new PropertyValueFactory<>("date"));
        table_a.setItems(data);

    }

    @FXML
    public void getAddAvis() {

        try {
            Parent root1 = FXMLLoader.load(getClass().getResource("/Gui/AjouterAvis.fxml"));
            Scene scene1 = new Scene(root1);
            Stage stage = new Stage();
            stage.setScene(scene1);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadDate();
    }

    @FXML
    private void loadDate() {
        ObservableList<Avis> data = FXCollections.observableArrayList(sa.afficherAvis());
//       data.clear();

        nom_mcol.setCellValueFactory(new PropertyValueFactory<>("nom_membre"));
        rating_acol.setCellValueFactory(new PropertyValueFactory<>("valeur_avis"));
        date_acol.setCellValueFactory(new PropertyValueFactory<>("date"));
        table_a.setItems(data);

        DisplayAvis();
        //add cell of button edit 
        Callback<TableColumn<Avis, String>, TableCell<Avis, String>> cellFoctory;
        cellFoctory = (TableColumn<Avis, String> param) -> {
            // make cell containing buttons
            final TableCell<Avis, String> cell = new TableCell<Avis, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                        FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL);
                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#ff1744;"
                        );
                        editIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#1bdaad;"
                        );

                        deleteIcon.setOnMouseClicked((event) -> {

                            a = table_a.getSelectionModel().getSelectedItem();
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//                           alert.setTitle("Info");
                            alert.setHeaderText("Confirmation");
                            // set content text
                            alert.setContentText("Are you sure to delete ");
                            Optional<ButtonType> action = alert.showAndWait();
                            if (action.get() == ButtonType.OK) {
                                sa.supprimerAvis(a);
                                DisplayAvis();
                            }
                        });
                        editIcon.setOnMouseClicked((event) -> {

                            a = table_a.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader();
                            loader.setLocation(getClass().getResource("AjouterAvis.fxml"));

                            try {
                                loader.load();
//                                sa.modifierAvis(a);
                            } catch (IOException ex) {
                                System.out.println(ex);
                            }
                            AjouterAvisController ajouteravis = loader.getController();
                            ajouteravis.setUpdate(true);
                            ajouteravis.setTextField(a.getId_avis(), a.getValeur_avis());
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.show();

                        });

                        HBox managebtn = new HBox(editIcon, deleteIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setHgrow(this, Priority.ALWAYS);
                        HBox.setMargin(editIcon, new Insets(2, 2, 0, 3));
                        HBox.setMargin(deleteIcon, new Insets(2, 3, 0, 2));

                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };

        editcol.setCellFactory(cellFoctory);
        table_a.setItems(data);
    }

    void initAvis(int id_membre, String prenom) {
    id=id_membre;
    membre.setPrenom(prenom);
    }
}
