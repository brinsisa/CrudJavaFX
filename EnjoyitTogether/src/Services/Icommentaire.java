
package Services;


import Entites.Commentaire;
import Entites.NewCommentaire;
import Entites.Voyage;
import java.util.List;


public interface Icommentaire{
      //ajouter
    public void ajouterCommentaire(Commentaire c);
    
    //lister
    public List<Commentaire> afficherCommentaires();
    public boolean modifierCommentaire(Commentaire c);
    public int CalculerCommentaires();
    public boolean supprimerCommentaire(Commentaire c);
//    public List<Commentaire> TriCommentaires();
    public String afficherCommentairesMembres(Commentaire c);
    public List<Integer> ReturnerVoyages();
}
