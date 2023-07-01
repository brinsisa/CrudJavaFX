package Gui;

import Entites.TypeVoyage;
import Entites.Voyage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.Window;
import Services.CrudVoyage;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.util.Callback;

public class ListeVoyageController implements Initializable {
    @FXML
    private Label lablist;
    @FXML
    private TableView<Voyage> table;
    @FXML
    private TableColumn<Voyage, String> col_Nom;
    @FXML
    private TableColumn<Voyage, Date> col_Date;
    @FXML
    private TableColumn<Voyage, String> col_Description;
    @FXML
    private TableColumn<Voyage, ComboBox<TypeVoyage>> col_Type;
    @FXML
    private Button btnReturnHome;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private TableColumn<Voyage, String> col_comment;
Voyage v;
//int id_v;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        remplirTab();
    }

    public void setLablist(String lablist) {
        this.lablist.setText(lablist);
    }

    public void remplirTab() {
        CrudVoyage ps = new CrudVoyage();
        ArrayList<Voyage> list = ps.readAll();

        ObservableList<Voyage> obs = FXCollections.observableArrayList(list);

        col_Nom.setCellValueFactory(new PropertyValueFactory<Voyage, String>("nom"));
        col_Date.setCellValueFactory(new PropertyValueFactory<>("date"));
        col_Description.setCellValueFactory(new PropertyValueFactory<>("description"));
        col_Type.setCellValueFactory(new PropertyValueFactory<>("type"));

//        table.setItems(obs);
        
        
        Callback<TableColumn<Voyage, String>, TableCell<Voyage, String>> cellFoctory;
        cellFoctory = (TableColumn<Voyage, String> param) -> {
            // make cell containing buttons
            final TableCell<Voyage, String> cell = new TableCell<Voyage, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        FontAwesomeIconView addIcon = new FontAwesomeIconView(FontAwesomeIcon.COMMENTING);
                        
                        addIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#e479e0;"
                        );
                        
                        addIcon.setOnMouseClicked((event) -> {

                            v = table.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader();
                            loader.setLocation(getClass().getResource("DisplayCommentaire.fxml"));

                            try {
                                loader.load();
                            } catch (IOException ex) {
                                System.out.println(ex);
                            }
                            DisplayCommentaireController dc = loader.getController();
                            dc.initdata(v);
                            System.out.println(v.getId());
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.show();

                        });


                        HBox managebtn = new HBox(addIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setHgrow(this, Priority.ALWAYS);
                        HBox.setMargin(addIcon, new Insets(2, 2, 0, 1));

                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };
        col_comment.setCellFactory(cellFoctory);
        table.setItems(obs);
    }
        
    
   
    public void AddVoyage(ActionEvent event) throws IOException {
Scene scene;
        Object root = FXMLLoader.load(getClass().getResource("AddVoyage.fxml"));
        Window stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene((Parent) root);
        ((Stage) stage).setScene(scene);
        ((Stage) stage).show();
    }
    public void UpdateVoyage(ActionEvent event) throws IOException {
Scene scene;
        Object root = FXMLLoader.load(getClass().getResource("UpdateVoyage.fxml"));
        Window stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene((Parent) root);
        ((Stage) stage).setScene(scene);
        ((Stage) stage).show();
    }
    public void DeleteVoyage(ActionEvent event) throws IOException {

    }

    public void returnHome(ActionEvent event) throws IOException {
/*
        Object root = FXMLLoader.load(getClass().getResource(""));
        Window stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene((Parent) root);
        ((Stage) stage).setScene(scene);
        ((Stage) stage).show();
  */
    }

}

