
package Services;

import Entites.Avis;
import java.util.List;

/**
 *
 * @author a
 */
public interface Iavis {
  public void ajouterAvis(Avis a);
public void supprimerAvis(Avis a);
public List<Avis> afficherAvis();
public void modifierAvis(Avis a);  
}
