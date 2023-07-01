package Gui;

import Entites.Commentaire;
import Entites.Voyage;
import Services.ServiceCommentaire;
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

public class DisplayCommentaireController implements Initializable {

    @FXML
    private TableView<Commentaire> tablec;
    @FXML
    private TableColumn<Commentaire, Integer> idcol;
    @FXML
    private TableColumn<Commentaire, String> nomcol;
    @FXML
    private TableColumn<Commentaire, String> descriptioncol;
    @FXML
    private TableColumn<Commentaire, String> editCol;
    @FXML
    private TableColumn<Commentaire, Date> datecol;

    @FXML
    private Label labelcol;
//    Membre m = new Membre();
    Commentaire c = new Commentaire();
    Voyage v;
    private int id_v;
    ServiceCommentaire sc = new ServiceCommentaire();

    public void DisplayComments() {
        v=new Voyage();
        v.setId(id_v);
        
        ObservableList<Commentaire> data = FXCollections.observableArrayList(sc.afficherCommentaires());
//       data.clear();

        // nomcol.setCellValueFactory(new PropertyValueFactory<>(c.getMembre().getNom()));
        nomcol.setCellValueFactory(new PropertyValueFactory<>("nom_m"));
        descriptioncol.setCellValueFactory(new PropertyValueFactory<>("contenu"));
        datecol.setCellValueFactory(new PropertyValueFactory<>("date"));
        tablec.setItems(data);
         
    }

    @FXML
    public void DisplayNBComments() {
        labelcol.setText(String.valueOf(sc.CalculerCommentaires()));
    }

    @FXML
    public void getAddComment() {

        try {
            Parent root1 = FXMLLoader.load(getClass().getResource("/Gui/AjouterCommentaire.fxml"));
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
        DisplayNBComments();
    }
     
    @FXML
    private void loadDate() {
        v=new Voyage();
        v.setId(id_v);
        ObservableList<Commentaire> data = FXCollections.observableArrayList(sc.afficherCommentaires());
//        idcol.setCellValueFactory(new PropertyValueFactory<>("id_commentaire"));
        // nomcol.setCellValueFactory(new PropertyValueFactory<>(c.getMembre().getNom()));
        nomcol.setCellValueFactory(new PropertyValueFactory<>("nom_m"));
        descriptioncol.setCellValueFactory(new PropertyValueFactory<>("description"));

        datecol.setCellValueFactory(new PropertyValueFactory<>("date"));

        DisplayComments();

        //add cell of button edit 
        Callback<TableColumn<Commentaire, String>, TableCell<Commentaire, String>> cellFoctory;
        cellFoctory = (TableColumn<Commentaire, String> param) -> {
            // make cell containing buttons
            final TableCell<Commentaire, String> cell = new TableCell<Commentaire, String>() {
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
                        FontAwesomeIconView addIcon = new FontAwesomeIconView(FontAwesomeIcon.COMMENTING);
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
                        addIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#e479e0;"
                        );
                        deleteIcon.setOnMouseClicked((event) -> {

                            c = tablec.getSelectionModel().getSelectedItem();
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//                           alert.setTitle("Info");
                            alert.setHeaderText("Confirmation");
                            // set content text
                            alert.setContentText("Are you sure to delete ");
                            Optional<ButtonType> action = alert.showAndWait();
                            if (action.get() == ButtonType.OK) {
                                sc.supprimerCommentaire(c);
                                DisplayComments();
                                DisplayNBComments();
                            }
                        });
                        addIcon.setOnMouseClicked((event) -> {

                            c = tablec.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader();
                            loader.setLocation(getClass().getResource("DisplayNewCommentaire.fxml"));

                            try {
                                loader.load();
                            } catch (IOException ex) {
                                System.out.println(ex);
                            }
                             DisplayNewCommentaireController dnc = loader.getController();
                            dnc.initcommentaire(c.getId_commentaire(),c.getContenu());
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.show();

                        });

                        editIcon.setOnMouseClicked((event) -> {

                            c = tablec.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader();
                            loader.setLocation(getClass().getResource("AjouterCommentaire.fxml"));

                            try {
                                loader.load();
                            } catch (IOException ex) {
                                System.out.println(ex);
                            }

                            AjouterCommentaireController AjouterCommentaire = loader.getController();
                            AjouterCommentaire.setUpdate(true);
                            AjouterCommentaire.setTextField(c.getId_commentaire(), c.getNom_m(), c.getNom_v(), c.getContenu());
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.show();

                        });

                        HBox managebtn = new HBox(addIcon, editIcon, deleteIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setHgrow(this, Priority.ALWAYS);
                        HBox.setMargin(editIcon, new Insets(2, 2, 0, 3));
                        HBox.setMargin(deleteIcon, new Insets(2, 3, 0, 2));
                        HBox.setMargin(addIcon, new Insets(2, 1, 0, 1));

                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };

        editCol.setCellFactory(cellFoctory);
        tablec.setItems(data);
    }
    
 void initdata(Voyage voyage){
    v=voyage;
   
}
  
}
