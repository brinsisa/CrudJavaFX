/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entites.Membre;
import Services.ServiceAmis;
import Services.ServiceMembre;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 */
public class DisplayMembre2Controller implements Initializable {

    @FXML
    private Label lblLogin;

    @FXML
    private Label lblNom;

    @FXML
    private Label lblPrenom;

    @FXML
    private Label lblAge;

    @FXML
    private Label lblAdresse;

    @FXML
    private Label lblCin;

    @FXML
    private Button btnRemove;

    ServiceMembre sm = new ServiceMembre();
    ServiceAmis sa = new ServiceAmis();
Membre membre;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println(membre.toString());

        lblLogin.setText(membre.getLogin());
        lblNom.setText(membre.getNom());
        lblPrenom.setText(membre.getPrenom());
        lblAge.setText(String.valueOf(membre.getAge()));
        lblAdresse.setText(membre.getAdresse());
        lblCin.setText(String.valueOf(membre.getCin()));

    }

    public void removeInvitation() {

        System.out.println(membre.getId_membre());

        boolean res = sa.removeAmi(1, membre.getId_membre());

        System.out.println(res);

        Alert a = new Alert(Alert.AlertType.NONE);

        if (!res) {
            a.setAlertType(Alert.AlertType.ERROR);
            a.setContentText("Erreur");
            a.show();
        } else {
            a.setAlertType(Alert.AlertType.INFORMATION);
            a.setContentText("Ami supprimé");
            a.show();
        }
    }

}
