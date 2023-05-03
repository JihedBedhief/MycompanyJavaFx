/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author MediaCenter Zaghouan
 */
public class Abonnement {
    
    private int id;
    private String type;
    private String duree;
    private String frais;

    public Abonnement() {
    }

    public Abonnement(int id, String type, String duree, String frais) {
        this.id = id;
        this.type = type;
        this.duree = duree;
        this.frais = frais;
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

    public String getDuree() {
        return duree;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    public String getFrais() {
        return frais;
    }

    public void setFrais(String frais) {
        this.frais = frais;
    }

    @Override
    public String toString() {
        return "Abonnement{" + "id=" + id + ", type=" + type + ", duree=" + duree + ", frais=" + frais + '}';
    }
    
    
    
    
}
