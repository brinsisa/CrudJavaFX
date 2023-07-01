package Entites;

import java.util.Objects;


public class Membre {
    private int id_membre;
    private  String Nom;
    private  String Prenom;

    public Membre(String Nom, String Prenom, String Login, String Password) {
        this.Nom = Nom;
        this.Prenom = Prenom;
        this.Login = Login;
        this.Password = Password;
    }
    private  int Cin;
    private  String Adresse;
    private  int Age;
    private  int Telephone;
    private  String Mail;
    private  String Genre;
    private  String Login;
    private  String Password;
    
    

    public Membre () {}

    public Membre(String Nom, String Prenom, int Cin, String Adresse, int Age, int Telephone, String Mail, String Genre, String Login, String Password) {
        this.Nom = Nom;
        this.Prenom = Prenom;
        this.Cin = Cin;
        this.Adresse = Adresse;
        this.Age = Age;
        this.Telephone = Telephone;
        this.Mail = Mail;
        this.Genre = Genre;
        this.Login = Login;
        this.Password = Password;
    }

    public Membre(String Login, String Password) {
        this.Login = Login;
        this.Password = Password;
    }

    public Membre(String login) {
     this.Login=login;    }

    public Membre(int aInt, String string, String string0, String string1, String string2, String string3, String string4, String string5) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

 

    public int getId_membre() {
        return id_membre;
    }

    public void setId_membre(int id_membre) {
        this.id_membre = id_membre;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    public void setPrenom(String Prenom) {
        this.Prenom = Prenom;
    }

    public int getCin() {
        return Cin;
    }

    public void setCin(int Cin) {
        this.Cin = Cin;
    }

    public String getAdresse() {
        return Adresse;
    }

    public void setAdresse(String Adresse) {
        this.Adresse = Adresse;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int Age) {
        this.Age = Age;
    }

    public int getTelephone() {
        return Telephone;
    }

    public void setTelephone(int Telephone) {
        this.Telephone = Telephone;
    }

    public String getMail() {
        return Mail;
    }

    public void setMail(String Mail) {
        this.Mail = Mail;
    }

    public String getGenre() {
        return Genre;
    }

    public void setGenre(String Genre) {
        this.Genre = Genre;
    }

    public String getLogin() {
        return Login;
    }

    public void setLogin(String Login) {
        this.Login = Login;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    @Override
    public String toString() {
        return "Membre{" + "id_membre=" + id_membre + ", Nom=" + Nom + ", Prenom=" + Prenom + ", Cin=" + Cin + ", Adresse=" + Adresse + ", Age=" + Age + ", Telephone=" + Telephone + ", Mail=" + Mail + ", Genre=" + Genre + ", Login=" + Login + ", Password=" + Password + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Membre other = (Membre) obj;
        if (this.id_membre != other.id_membre) {
            return false;
        }
        if (this.Cin != other.Cin) {
            return false;
        }
        if (this.Age != other.Age) {
            return false;
        }
        if (this.Telephone != other.Telephone) {
            return false;
        }
        if (!Objects.equals(this.Nom, other.Nom)) {
            return false;
        }
        if (!Objects.equals(this.Prenom, other.Prenom)) {
            return false;
        }
        if (!Objects.equals(this.Adresse, other.Adresse)) {
            return false;
        }
        if (!Objects.equals(this.Mail, other.Mail)) {
            return false;
        }
        if (!Objects.equals(this.Genre, other.Genre)) {
            return false;
        }
        if (!Objects.equals(this.Login, other.Login)) {
            return false;
        }
        if (!Objects.equals(this.Password, other.Password)) {
            return false;
        }
        return true;
    }
   
}
