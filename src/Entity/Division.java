/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author user
 */
public class Division {

    private int id;
    private String type;
    private double taux_remise;

    public Division() {
    }

    public Division(int id, String type, double taux_remise) {
        this.id = id;
        this.type = type;
        this.taux_remise = taux_remise;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getTaux_remise() {
        return taux_remise;
    }

    public void setTaux_remise(double taux_remise) {
        this.taux_remise = taux_remise;
    }

    @Override
    public String toString() {
        return "Division{" + "id=" + id + ", type=" + type + ", taux_remise=" + taux_remise + '}';
    }

    
}
