
package Entites;

import java.sql.Date;

/**
 *
 * @author a
 */
public class Avis {
    private int id_avis;
    private double valeur_avis;
    private int id_membre;
    private Date date;
    private String nom_membre;

    public Avis(int id_avis, double type_avis, int id_membre) {
        this.id_avis = id_avis;
        this.valeur_avis = type_avis;
        this.id_membre = id_membre;
    }

    public Avis(int id_avis, String nom_membre, double valeur_avis ,Date date) {
        this.id_avis = id_avis;
        this.valeur_avis = valeur_avis;
        this.nom_membre = nom_membre;
        this.date=date;
    }

    public Avis(String nom_membre, double valeur_avis,int id_membre) {
      
        this.valeur_avis = valeur_avis;
        this.id_membre=id_membre;
        this.nom_membre = nom_membre;
    }

    public Avis(int id_avis, String nom_membre, double valeur_avis, int id_membre) {
        this.id_avis = id_avis;
        this.valeur_avis = valeur_avis;
        this.id_membre = id_membre;
        this.nom_membre = nom_membre;
    }
    

    public Avis(double type_avis, int id_membre) {
        this.valeur_avis = type_avis;
        this.id_membre = id_membre;
    }
     public Avis( int id_membre,double type_avis) {
        this.valeur_avis = type_avis;
        this.id_membre = id_membre;
    }

    public Avis() {
    }

    public int getId_avis() {
        return id_avis;
    }

    public void setId_avis(int id_avis) {
        this.id_avis = id_avis;
    }

    public double getValeur_avis() {
        return valeur_avis;
    }

    public void setValeur_avis(double type_avis) {
        this.valeur_avis = type_avis;
    }

    public int getId_membre() {
        return id_membre;
    }

    public void setId_membre(int id_membre) {
        this.id_membre = id_membre;
    }

    public String getNom_membre() {
        return nom_membre;
    }

    public void setNom_membre(String nom_membre) {
        this.nom_membre = nom_membre;
    }

    public Avis(double valeur_avis) {
        this.valeur_avis = valeur_avis;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
}
