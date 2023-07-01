
package Entites;

import java.sql.Date;



public class Commentaire {
 
    private int id_commentaire;
//    private Membre membre;
//    private Voyage voyage;
    private String contenu;
    private Date date;
        private String nom_m;
        private String nom_v;
    private int id_v;
    private int id_m;

    public Commentaire() {
    }
    
//     public Commentaire(Object id_membre, Object date, Object contenu, Object id_voyage) {
//        this.id_voyage = (int) id_voyage;
//        this.id_membre = (int) id_membre;
//        //this.date = (String) date;
//        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
//        this.date = (Date) date;
//        this.contenu = (String) contenu;
//    }
//


    public Commentaire(int id_commentaire, int id_m, String contenu) {
        this.id_commentaire = id_commentaire;
        this.id_m = id_m;
        this.contenu = contenu;
    }

    public Commentaire(int id_commentaire) {
        this.id_commentaire = id_commentaire;
    }

   

    public Commentaire(int id_commentaire,  String contenu, Date date) {
        this.id_commentaire = id_commentaire;
//        this.membre = membre;
//        this.voyage = voyage;
        this.contenu = contenu;
        this.date = date;
    }
     public Commentaire(String contenu, Date date) {
      
//        this.membre = membre;
//        this.voyage = voyage;
        this.contenu = contenu;
        this.date = date;
    }

    public Commentaire(int id, String contenu) {

       this.id_commentaire = id;
         this.contenu = contenu;
//        this.voyage = voyage;
    }

 

    public Commentaire(String contenu) {
        this.contenu = contenu;
        
    }


    public int getId_commentaire() {
        return id_commentaire;
    }

    public void setId_commentaire(int id) {
        this.id_commentaire = id;
    }
//
//    public Membre getMembre() {
//        return membre;
//    }
//
//    public void setMembre(Membre membre) {
//        this.membre = membre;
//    }

//    public Voyage getVoyage() {
//        return voyage;
//    }
//
//    
//    public void setVoyage(Voyage voyage) {
//        this.voyage = voyage;
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

    public Commentaire(int id_commentaire, String contenu, Date date, int id_v, int id_m) {
        this.id_commentaire = id_commentaire;
        this.contenu = contenu;
        this.date = date;
        this.id_v = id_v;
        this.id_m = id_m;
    }

    public Commentaire(String nom_m, String nom_v, String contenu, int id_m, int id_v) {
        this.contenu = contenu;
        this.nom_m = nom_m;
        this.nom_v = nom_v;
        this.id_v = id_v;
        this.id_m = id_m;
    }

    public Commentaire(int id_v, int id_m ,String contenu, Date date) {
        this.contenu = contenu;
        this.date = date;
        this.id_v = id_v;
        this.id_m = id_m;
    }

    public int getId_v() {
        return id_v;
    }

    public void setId_v(int id_v) {
        this.id_v = id_v;
    }

    public int getId_m() {
        return id_m;
    }

    public void setId_m(int id_m) {
        this.id_m = id_m;
    }

    public String getNom_m() {
        return nom_m;
    }

    public void setNom_m(String nom_m) {
        this.nom_m = nom_m;
    }

    public Commentaire(int id_commentaire, String nom_m , String nom_v ,String contenu, Date date,int id_v) {
        this.id_commentaire = id_commentaire;
        this.nom_m = nom_m;
        this.contenu = contenu;
        this.nom_v = nom_v;
        this.date=date;
        this.id_v=id_v;
        
    }
  public Commentaire(String nom_m , String nom_v ,String contenu) {
        
        this.nom_m = nom_m;
        this.contenu = contenu;
        this.nom_v = nom_v;
        
    }
    public String getNom_v() {
        return nom_v;
    }

    public void setNom_v(String nom_v) {
        this.nom_v = nom_v;
    }
    

}
