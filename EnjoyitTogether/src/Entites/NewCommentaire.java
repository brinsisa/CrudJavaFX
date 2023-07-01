
package Entites;

import java.sql.Date;

/**
 *
 * @author a
 */
public class NewCommentaire {
    private int id_Newcommentaire;
//    private Membre membre;
    private String contenu;
    private Date date;
        private String nom_m;
        private String nom_c;
    private int id_m;
    private int id_c;

    public NewCommentaire() {
    }

    public NewCommentaire( String nom_c, String nom_m, String contenu, Date date) {
        this.contenu = contenu;
        this.date = date;
        this.nom_m = nom_m;
        this.nom_c = nom_c;
    }

    public NewCommentaire(int id_m, int id_c, String contenu, Date date) {
        this.contenu = contenu;
        this.date = date;
        this.id_m = id_m;
        this.id_c = id_c;
    }

//    public NewCommentaire(int id_c, int id_Newcommentaire, String nom_m, String contenu ) {
//        this.id_c = id_c;
//        this.id_Newcommentaire = id_Newcommentaire;
//        this.nom_m = nom_m;
//        this.contenu = contenu;
//        
//        
//    }

    public NewCommentaire( int id_Newcommentaire,String nom_c , String nom_m,String contenu,Date date, int id_c) {
        this.contenu = contenu;
        this.nom_c = nom_c;
        this.nom_m = nom_m;
        this.id_Newcommentaire = id_Newcommentaire;
        this.date = date;
        this.id_c=id_c;
    }

   

    public NewCommentaire(int id_Newcommentaire, String contenu, Date date, String nom_m,  int id_c) {
        this.id_Newcommentaire = id_Newcommentaire;
//        this.membre = membre;
        this.contenu = contenu;
        this.date = date;
        this.nom_m = nom_m;
        this.id_c = id_c;
    }

    public int getId_Newcommentaire() {
        return id_Newcommentaire;
    }

    public void setId_Newcommentaire(int id_Newcommentaire) {
        this.id_Newcommentaire = id_Newcommentaire;
    }

//    public Membre getMembre() {
//        return membre;
//    }
//
//    public void setMembre(Membre membre) {
//        this.membre = membre;
//    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getNom_m() {
        return nom_m;
    }

    public void setNom_m(String nom_m) {
        this.nom_m = nom_m;
    }

    public int getId_m() {
        return id_m;
    }

    public void setId_m(int id_m) {
        this.id_m = id_m;
    }

    public int getId_c() {
        return id_c;
    }

    public void setId_c(int id_c) {
        this.id_c = id_c;
    }

    public String getNom_c() {
        return nom_c;
    }

    public void setNom_c(String nom_c) {
        this.nom_c = nom_c;
    }
    

    public NewCommentaire(int id_Newcommentaire,String nom_c, String nom_m, String contenu) {
        this.id_Newcommentaire=id_Newcommentaire;
        this.nom_c = nom_c;
        this.nom_m = nom_m;
        this.contenu = contenu;
      
        
    }
    public NewCommentaire(String nom_c, String nom_m, String contenu,int id_c,int id_m) {
        this.nom_c = nom_c;
        this.nom_m = nom_m;
        this.contenu = contenu;
        this.id_c=id_c;
        this.id_m=id_m;
    }
    
}
