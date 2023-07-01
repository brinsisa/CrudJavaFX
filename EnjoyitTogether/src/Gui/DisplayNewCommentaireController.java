package Gui;

import Entites.Commentaire;
import Entites.NewCommentaire;
import Services.ServiceCommentaire;
import Services.ServiceNewCommentaire;
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
import javafx.scene.control.Label;
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
public class DisplayNewCommentaireController implements Initializable {

    @FXML
    private TableView<NewCommentaire> tableNewc;
//    @FXML
//    private TableColumn<NewCommentaire, Integer> idcol;
    @FXML
    private TableColumn<NewCommentaire, String> idNCcol;
    @FXML
    private TableColumn<NewCommentaire, String> nomcol;
    @FXML
    private TableColumn<NewCommentaire, String> descriptioncol;
    @FXML
    private TableColumn<NewCommentaire, String> editCol;
    @FXML
    private TableColumn<NewCommentaire, Date> datecol;
    @FXML
    private Label labelCount;
//    Membre m = new Membre();
    NewCommentaire c = new NewCommentaire();
    ServiceNewCommentaire sc = new ServiceNewCommentaire();
    Commentaire ct;
   private int idc;

    @FXML
    public void DisplayNBComments() {
        labelCount.setText(String.valueOf(sc.CalculerNewCommentaires()));
    }

   

    

    @FXML
    public void DisplayNewComments() {
        ct = new Commentaire();
        ct.setId_commentaire (idc);
        ObservableList<NewCommentaire> data = FXCollections.observableArrayList(sc.afficherNewCommentaires());
//       data.clear();

        idNCcol.setCellValueFactory(new PropertyValueFactory<>("nom_c"));
        nomcol.setCellValueFactory(new PropertyValueFactory<>("nom_m"));
        descriptioncol.setCellValueFactory(new PropertyValueFactory<>("contenu"));
        datecol.setCellValueFactory(new PropertyValueFactory<>("date"));
        tableNewc.setItems(data);

    }

    @FXML
    public void getAddNewComment() {

        try {
            Parent root1 = FXMLLoader.load(getClass().getResource("/Gui/AjouterNewCommentaire.fxml"));
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
        DisplayNBComments();
        loadDate();
    }

    @FXML
    private void loadDate() {
                ct = new Commentaire();
        ct.setId_commentaire (idc);
        ObservableList<NewCommentaire> data = FXCollections.observableArrayList(sc.afficherNewCommentaires());
//        idcol.setCellValueFactory(new PropertyValueFactory<>("id_c"));
        idNCcol.setCellValueFactory(new PropertyValueFactory<>("nom_c"));
        nomcol.setCellValueFactory(new PropertyValueFactory<>("nom_m"));
        descriptioncol.setCellValueFactory(new PropertyValueFactory<>("contenu"));
        datecol.setCellValueFactory(new PropertyValueFactory<>("date"));

        DisplayNewComments();

        //add cell of button edit 
        Callback<TableColumn<NewCommentaire, String>, TableCell<NewCommentaire, String>> cellFoctory;
        cellFoctory = (TableColumn<NewCommentaire, String> param) -> {
            // make cell containing buttons
            final TableCell<NewCommentaire, String> cell = new TableCell<NewCommentaire, String>() {
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

                            c = tableNewc.getSelectionModel().getSelectedItem();
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//                           alert.setTitle("Info");
                            alert.setHeaderText("Confirmation");
                            // set content text
                            alert.setContentText("Are you sure to delete ");
                            Optional<ButtonType> action = alert.showAndWait();
                            if (action.get() == ButtonType.OK) {
                                sc.supprimerNewCommentaire(c);
                                DisplayNewComments();
                                DisplayNBComments();
                            }
                        });
                        editIcon.setOnMouseClicked((event) -> {

                            c = tableNewc.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader();
                            loader.setLocation(getClass().getResource("AjouterNewCommentaire.fxml"));

                            try {
                                loader.load();
//                                sc.modifierCommentaire(c);
                            } catch (IOException ex) {
                                System.out.println(ex);
                            }

                            AjouterNewCommentaireController AjouterNewCommentaire = loader.getController();
                            AjouterNewCommentaire.setUpdate(true);
                            AjouterNewCommentaire.setTextField(c.getId_Newcommentaire(), c.getNom_m(), c.getNom_c(), c.getContenu());
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

        editCol.setCellFactory(cellFoctory);
        tableNewc.setItems(data);
    }
   void  initcommentaire(int id_c, String nom){
       idc=id_c;
       nomcol.setText(nom);
   }
}
