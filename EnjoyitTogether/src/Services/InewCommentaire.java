package Services;

import Entites.Commentaire;
import Entites.NewCommentaire;
import java.util.List;

/**
 *
 * @author a
 */
public interface InewCommentaire {

    public void ajouterNewCommentaire(NewCommentaire c);

    public List<NewCommentaire> afficherNewCommentaires();

    public boolean modifierNewCommentaire(NewCommentaire c);
    public int CalculerNewCommentaires();
    public boolean supprimerNewCommentaire(NewCommentaire c);
    public List<NewCommentaire> afficherNewCommentairesCommentaires();
    public int FindById();
}
