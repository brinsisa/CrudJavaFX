/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Models.Msg;
import java.util.List;

public interface iMsg {

    public List<Msg> getMessages(int id_user);
    
    public Msg getMessage(int id_message);
    
    public void addMessage(int id_to, int id_from, String message);
    
    public void deleteMessage(int id_message);
    
}
