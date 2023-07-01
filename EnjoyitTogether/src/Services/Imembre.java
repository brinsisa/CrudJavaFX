/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entites.Membre;
import java.util.List;

/**
 * 
 */
public interface Imembre {

    public Membre getMembre(Membre membre);

    public List<Membre> getMembres(int id);

    public Membre retrieveMembre(int id);

}
