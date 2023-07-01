package Services;

import Entites.Avis;
import Util.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author a
 */
public class ServiceAvis implements Iavis {

    Connection cnx = DataSource.getInstance().getConnection();
    Statement stm;
    ResultSet rst;
//Membre m;
    @Override
    public void ajouterAvis(Avis a) {
//m=new Membre();
        try {
            stm = cnx.createStatement();

            String query = "INSERT INTO `avis`(`membre`,`valeur_avis`,`id_membre`) VALUES ('" + a.getNom_membre() + "'," + a.getValeur_avis() + "," + a.getId_membre() + ")";
            stm.executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void supprimerAvis(Avis a) {

        try {
            stm = cnx.createStatement();

            String query = "DELETE FROM `avis` WHERE id_avis=" + a.getId_avis() + "";
            stm.executeUpdate(query);

        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public List<Avis> afficherAvis() {

        List<Avis> list = new ArrayList<>();
        try {
            String req = "SELECT  id_avis, membre, valeur_avis, date FROM avis ";
             stm = cnx.createStatement();
             rst = stm.executeQuery(req);
            while (rst.next()) {
                Avis a = new Avis(rst.getInt(1), rst.getString(2), rst.getInt(3),rst.getDate(4));

                list.add(a);

            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return list;

    }

    @Override
    public void modifierAvis(Avis a) {
        try {
            stm = cnx.createStatement();
            String query = "UPDATE  avis  set valeur_avis = '" + a.getValeur_avis() + "' WHERE id_avis = " + a.getId_avis() + " ;";
            stm.executeUpdate(query);
            stm.executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

}
