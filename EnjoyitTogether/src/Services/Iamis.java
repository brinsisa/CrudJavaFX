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
public interface Iamis {
    
    public List<Membre> getAmis(int id);
    
    public boolean findAmi(int user_id, int membre_id);
    
    public boolean removeAmi(int user_id, int membre_id);
    
    public boolean addAmi(int user_id, int membre_id);
    
}
