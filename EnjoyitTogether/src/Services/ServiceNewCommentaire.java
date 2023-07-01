package Services;

import Entites.Commentaire;
import Entites.NewCommentaire;
import Util.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author a
 */
public class ServiceNewCommentaire implements InewCommentaire {

    Statement st = null;
    Connection cnx = DataSource.getInstance().getConnection();
//    ServiceVoyage sv = new ServiceVoyage();
//    ServiceMembre sm = new ServiceMembre();
    Commentaire cc=new Commentaire();
//    ServiceCommentaire sc = new ServiceCommentaire();
//String commentaire=sc.afficherCommentairesMembres(c);

    @Override
    public void ajouterNewCommentaire(NewCommentaire c) {
        String query = "INSERT INTO `newcommentaire` (`commentaire`,`membre`,`contenu`,`id_commentaire`,`id_membre`) VALUES (?,?,?,?,?)";
        try {
            st = cnx.createStatement();
            PreparedStatement pst = cnx.prepareStatement(query);
            pst.setString(1, c.getNom_c());
            pst.setString(2, c.getNom_m());
            pst.setString(3, c.getContenu());
            pst.setInt(4, c.getId_c());
            pst.setInt(5, c.getId_m());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

    @Override
    public List<NewCommentaire> afficherNewCommentaires() {
    
        List<NewCommentaire> list = new ArrayList<>();
        try {

            String req = ("SELECT nc.id_newcommentaire, nc.commentaire, nc.membre, nc.contenu, nc.date, c.id_commentaire FROM newcommentaire AS nc JOIN commentaire as c ON nc.id_commentaire=c.id_commentaire");
            st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                NewCommentaire commentr = new NewCommentaire(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(4),rs.getDate(5),rs.getInt(6));

                list.add(commentr);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return list;
    }

    @Override
    public boolean modifierNewCommentaire(NewCommentaire c) {
        boolean rowUpdated = false;
        try {
            Statement stat = cnx.createStatement();

            String query = "UPDATE  `newcommentaire`  set `contenu` = '" + c.getContenu() + "'  WHERE `id_newcommentaire` = " + c.getId_Newcommentaire() + " ;";

            int rowsUpdated = stat.executeUpdate(query);
            if (rowsUpdated > 0) {
                System.out.println("a comment updated successfully");
                rowUpdated = true;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return rowUpdated;
    }

    @Override
    public boolean supprimerNewCommentaire(NewCommentaire c) {

        boolean rowDeleted = false;
        try {
            st = cnx.createStatement();

            String query = "DELETE FROM `newcommentaire` WHERE id_newcommentaire=" + c.getId_Newcommentaire() + "";
            int rowsDeleted = st.executeUpdate(query);
            if (rowsDeleted > 0) {
                System.out.println("A comment was deleted successfully!");
                rowDeleted = true;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
//        
//              String sql = "DELETE FROM newcommentaire WHERE id_newcommentaire=?";
//        boolean rowDeleted = false;
//
//        try {
//            PreparedStatement statement = cnx.prepareStatement(sql);
//            statement.setInt(1, c.getId_Newcommentaire());
//
//            int rowsDeleted = statement.executeUpdate();
//
//            if (rowsDeleted > 0) {
//                System.out.println("A comment was deleted successfully!");
//                rowDeleted = true;
//            }
//        } catch (SQLException ex) {
//            System.out.println(ex);
//        }

        return rowDeleted;
    }

    @Override
    public List<NewCommentaire> afficherNewCommentairesCommentaires() {
        

        List<NewCommentaire> list = new ArrayList<>();

        try {
            String req = "SELECT newcommentaire.id_commentaire,newcommentaire.id_newcommentaire, newcommentaire.date, newcommentaire.contenu FROM newcommentaire,commentaire where newcommentaire.id_commentaire=commentaire.id_commentaire ";
            st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                NewCommentaire c = new NewCommentaire(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getDate(4));

                list.add(c);

            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return list;

    }

    @Override
    public int FindById() {
        
        int id=0;
        String req="select id_commentaire from commentaire";
        try {
            st=cnx.createStatement();
        
        ResultSet rs=st.executeQuery(req);
        while (rs.next()) { 
            id=rs.getInt(1);   
        }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceNewCommentaire.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id; 
    }

    @Override
    public int CalculerNewCommentaires() {
        int nb = 0 ;
         String req = "SELECT COUNT(*) FROM newcommentaire" ;
          
        try {
            st = cnx.createStatement();
        
           ResultSet rs=st.executeQuery(req);
           if (rs.next()){
          String chaine = String.valueOf(rs.getString(1));
           nb=Integer.parseInt(chaine);
            System.out.println(nb);}
    } catch (SQLException ex) {
            System.out.println(ex);
        }
return nb;
}
}
