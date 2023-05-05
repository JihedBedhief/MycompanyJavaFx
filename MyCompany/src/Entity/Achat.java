
package Entity;

import java.util.Date;


public class Achat {
  private int Ida;
  private String Produit,DateAchat;
 private float Quantite;
 private float Prix;
 
 private float Taxe;
 public Achat () {
     
    }

   public Achat(int Ida, String Produit, float Quantite, float Prix, String DateAchat, float Taxe) {
   
     
     this.Ida = Ida;
    this.Produit = Produit;
    this.Quantite = Quantite;
    this.Prix = Prix;
    this.DateAchat = DateAchat;
    this.Taxe = Taxe;
}

    public Achat(String Produit, float Quantite, float Prix,String DateAchat, float Taxe) {
        this.Produit = Produit;
        this.Quantite = Quantite;
        this.Prix = Prix;
        this.DateAchat=DateAchat;
        this.Taxe = Taxe;
    }

    public int getIda() {
        return Ida;
    }

    public String getProduit() {
        return Produit;
    }

    public float getQuantite() {
        return Quantite;
    }

    public float getPrix() {
        return Prix;
    }

    public String getDateAchat() {
        return DateAchat;
    }

    public float getTaxe() {
        return Taxe;
    }

    public void setIda(int Ida) {
        this.Ida = Ida;
    }

    public void setProduit(String Produit) {
   
        this.Produit = Produit;
    
}

    public void setQuantite(float Quantite) {
   
        this.Quantite = Quantite;
   
}

    public void setPrix(float Prix) {
   
        this.Prix = Prix;
   
}

    public void setDateAchat(String DateAchat) {
        this.DateAchat = DateAchat;
    }

    public void setTaxe(float Taxe) {
   
        this.Taxe = Taxe;
    
}

    @Override
    public String toString() {
        return "Achat{" + "Ida=" + Ida + ", Produit=" + Produit + ", Quantite=" + Quantite + ", Prix=" + Prix + ", DateAchat=" + DateAchat + ", Taxe=" + Taxe + '}';
    }

 
 
}
