package Entites;

import java.util.Date;

public class Voyage {

    private int id;
    private String nom;
    private Date date;
    private String description;
    private String type;

    public Voyage() {
    }

    public Voyage(int id, String nom, Date date, String description, String type) {
        this.id = id;
        this.nom = nom;
        this.date = date;
        this.description = description;
        this.type = type;
    }

    public Voyage(String nom, Date date, String description, String type) {
        this.nom = nom;
        this.date = date;
        this.description = description;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Voyage(String nom, String description, String type) {
        this.nom = nom;
        this.description = description;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Voyage{" + "nom=" + nom + ", date=" + date + "description=" + description + "type=" + type + '}';

    }

}
