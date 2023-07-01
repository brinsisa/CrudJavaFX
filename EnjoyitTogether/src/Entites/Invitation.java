/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

public class Invitation {

    int id_invitation;
    int id_user;
    int id_membre;
    int etat;

    public int getId_invitation() {
        return id_invitation;
    }

    public void setId_invitation(int id_invitation) {
        this.id_invitation = id_invitation;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_membre() {
        return id_membre;
    }

    public void setId_membre(int id_membre) {
        this.id_membre = id_membre;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "Invitation{" + "id_invitation=" + id_invitation + ", id_user=" + id_user + ", id_membre=" + id_membre + ", etat=" + etat + '}';
    }

}
