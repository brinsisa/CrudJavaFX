/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Models.Invitation;

/**
 * 
 */
public interface Iinvitation {

    public int sendInvitation(int user_id, int membre_id);

    public boolean findInvitation(int user_id, int membre_id);

    public Invitation getInvitation(int user_id, int membre_id);

    public void accepterInvitation(int invitation_id, int user_id, int membre_id);

    public void supprimerInvitation(int invitation_id, int user_id, int membre_id);
}
