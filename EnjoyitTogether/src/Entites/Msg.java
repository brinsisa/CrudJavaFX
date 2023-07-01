/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

public class Msg {

    public int id;

    public int id_from;

    public int id_to;

    public String message;

    public Msg() {
    }

    public Msg(int id, int id_from, int id_to, String message) {
        this.id = id;
        this.id_from = id_from;
        this.id_to = id_to;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_from() {
        return id_from;
    }

    public void setId_from(int id_from) {
        this.id_from = id_from;
    }

    public int getId_to() {
        return id_to;
    }

    public void setId_to(int id_to) {
        this.id_to = id_to;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Msg{" + "id=" + id + ", id_from=" + id_from + ", id_to=" + id_to + ", message=" + message + '}';
    }

}
