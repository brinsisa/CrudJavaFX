/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Models.Invitation;
import Entites.Membre;
import Services.ServiceInvitation;
import Services.ServiceMembre;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class DisplayMembreController implements Initializable {

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
    private Button btnSend;

    @FXML
    private Button btnAccept;

    @FXML
    private Button btnRemove;

    ServiceMembre sm = new ServiceMembre();
    ServiceInvitation si = new ServiceInvitation();

    Invitation invitation = new Invitation();

    Alert a = new Alert(AlertType.NONE);
Membre membre;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        System.out.println(membre.toString());

        lblLogin.setText(membre.getLogin());
        lblNom.setText(membre.getNom());
        lblPrenom.setText(membre.getPrenom());
        lblAge.setText(String.valueOf(membre.getAge()));
        lblAdresse.setText(membre.getAdresse());
        lblCin.setText(String.valueOf(membre.getCin()));

        updateScreen();

    }

    public void updateScreen() {
        invitation = si.getInvitation(1, membre.getId_membre());

        btnSend.setVisible(true);
        btnAccept.setVisible(false);
        btnRemove.setVisible(false);

        System.out.println(invitation.toString());

        if (invitation.getId_invitation() != 0) {
            int etat = invitation.getEtat();
            if (etat == 0) { // invitation en attente
                // cacher button envoyer invitation
                btnSend.setVisible(false);

                if (invitation.getId_user() == 1) {
                    btnRemove.setVisible(true); // afficher button annuler invitation
                }

                if (invitation.getId_user() == membre.getId_membre() && invitation.getId_membre() == 1) {
                    btnAccept.setVisible(true); // afficher button accepter invitation
                    btnRemove.setVisible(true); // afficher button annuler invitation
                }

            }

            if (etat == 1) { // invitation déja accepter
                btnSend.setVisible(false);
                btnAccept.setVisible(false);
                btnRemove.setVisible(false);
            }

            if (etat == 2) { // invitation déja supprimer
                btnSend.setVisible(false);
                btnAccept.setVisible(false);
                btnRemove.setVisible(false);
            }

        } else {
            btnSend.setVisible(true);
        }
    }

    public void sendInvitation() {

        int response = si.sendInvitation(1, membre.getId_membre());

        updateScreen();
        if (response == 0) {
            a.setAlertType(AlertType.ERROR);
            a.setContentText("Erreur");
            a.show();
        } else if (response == 1) {
            a.setAlertType(AlertType.INFORMATION);
            a.setContentText("Invitation déja envoyé");
            a.show();
        } else if (response == 2) {
            a.setAlertType(AlertType.INFORMATION);
            a.setContentText("Invitation envoyé");
            a.show();
        }

    }

    public void acceptInvitation() {
        invitation = si.getInvitation(1, membre.getId_membre());
        si.accepterInvitation(invitation.getId_invitation(), invitation.getId_user(), invitation.getId_membre());

        a.setAlertType(AlertType.INFORMATION);
        a.setContentText("Invitation acceptée");
        a.show();

        updateScreen();
    }

    public void deleteInvitation() {
        invitation = si.getInvitation(1, membre.getId_membre());

        System.out.println(invitation.toString());

        si.supprimerInvitation(invitation.getId_invitation(), invitation.getId_user(), invitation.getId_membre());

        a.setAlertType(AlertType.INFORMATION);
        a.setContentText("Invitation supprimée");
        a.show();

        updateScreen();
    }

}
