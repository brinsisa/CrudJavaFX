package Services;

import Entites.Commentaire;
import Entites.Voyage;
import Util.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author a
 */
public class ServiceCommentaire implements Icommentaire {
    
    Statement st = null;
    Connection cnx = DataSource.getInstance().getConnection();
//    ServiceVoyage sv = new ServiceVoyage();
//    ServiceMembre sm = new ServiceMembre();
    Commentaire comment = new Commentaire();
    
    @Override
    public List afficherCommentaires() {
//        Voyage v = new Voyage();
     
        
        List<Commentaire> list = new ArrayList<>();
        try {
            String req = "SELECT c.id_commentaire, c.membre, v.nom, c.contenu, c.date, c.id_voyage, v.id_voyage FROM commentaire c, voyage v where v.id_voyage=c.id_voyage";
            st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Commentaire c = new Commentaire(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5), rs.getInt(6));
                
                    list.add(c);
                
            }
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return list;
    }

//    @Override
//    public List TriCommentaires() {
//        List<Commentaire> commentaires = new ArrayList<>();
//        String req = "SELECT * FROM commentaire ORDER BY date DESC";
//
//        try {
//            st = cnx.createStatement();
//            ResultSet rs = st.executeQuery(req);
//
//            while (rs.next()) {
//                commentaires.add(new Commentaire(rs.getInt(1), sm.RetrieveMembre(rs.getInt(2)), sv.RetrieveVoyage(rs.getInt(3)), rs.getString(4), rs.getString(5)));
//            }
//        } catch (SQLException e) {
//            System.out.println(e);
//        }
//        return commentaires;
//    }
    @Override
    public boolean modifierCommentaire(Commentaire c) {
        boolean rowUpdated = false;
        try {
            Statement stat = cnx.createStatement();
            
            String query = "UPDATE  commentaire  set contenu = '" + c.getContenu() + "'  WHERE id_commentaire = " + c.getId_commentaire() + " ;";
            
            int rowsUpdated = stat.executeUpdate(query);
            if (rowsUpdated > 0) {
                System.out.println("data updated successfully");
                rowUpdated = true;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return rowUpdated;
    }
    
    @Override
    public boolean supprimerCommentaire(Commentaire c) {
        
        String sql = "DELETE FROM commentaire WHERE id_commentaire=?";
        boolean rowDeleted = false;
        
        try {
            PreparedStatement statement = cnx.prepareStatement(sql);
            statement.setInt(1, c.getId_commentaire());
            
            int rowsDeleted = statement.executeUpdate();
            
            if (rowsDeleted > 0) {
                System.out.println("A user was deleted successfully!");
                rowDeleted = true;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
        return rowDeleted;
    }
    
    @Override
    public void ajouterCommentaire(Commentaire c) {
        
        String query = "INSERT INTO `commentaire` (`membre`,`voyage`,`contenu`,`id_membre`,`id_voyage`) VALUES (?,?,?,?,?)";
        try {
            st = cnx.createStatement();
            PreparedStatement pst = cnx.prepareStatement(query);
            pst.setString(1, c.getNom_m());
            pst.setString(2, c.getNom_v());
            pst.setString(3, c.getContenu());
            pst.setInt(4, c.getId_m());
            pst.setInt(5, c.getId_v());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
    }
    
    @Override
    public String afficherCommentairesMembres(Commentaire c) {
        String contenu = null;
        try {
            String req = "SELECT contenu FROM commentaire where id_commentaire=" + c.getId_commentaire();
            st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                contenu = rs.getString(1);
                
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return contenu;
    }
    
    @Override
    public int CalculerCommentaires() {
        int nb = 0;
        String req = "SELECT COUNT(*) FROM commentaire";
        
        try {
            st = cnx.createStatement();
            
            ResultSet rs = st.executeQuery(req);
            if (rs.next()) {
                String chaine = String.valueOf(rs.getString(1));
                nb = Integer.parseInt(chaine);
                System.out.println(nb);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return nb;
    }
    
    @Override
    public List<Integer> ReturnerVoyages() {
        List v = new ArrayList<>();
        String req = "SELECT id_voyage FROM voyage";
        try {
            st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                v.add(1, rs.getInt(1));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return v;
    }

 
}
