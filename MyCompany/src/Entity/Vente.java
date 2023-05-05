/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import com.itextpdf.text.pdf.PdfPCell;
import java.util.Date;

/**
 *
 * @author Abderrazekbenhamouda
 */
public class Vente {
    
    private int id ,ptvente,quantite ;
    
    private double prix_totale,taxe,prix_unite ;
    private String client ,produit ;
    private Date date ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPtvente() {
        return ptvente;
    }

    public void setPtvente(int ptvente) {
        this.ptvente = ptvente;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public double getPrix_totale() {
        return prix_totale;
    }

    public void setPrix_totale(double prix_totale) {
        this.prix_totale = prix_totale;
    }

    public double getTaxe() {
        return taxe;
    }

    public void setTaxe(double taxe) {
        this.taxe = taxe;
    }

    public double getPrix_unite() {
        return prix_unite;
    }

    public void setPrix_unite(double prix_unite) {
        this.prix_unite = prix_unite;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getProduit() {
        return produit;
    }

    public void setProduit(String produit) {
        this.produit = produit;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Vente{" + "id=" + id + ", ptvente=" + ptvente + ", quantite=" + quantite + ", prix_totale=" + prix_totale + ", taxe=" + taxe + ", prix_unite=" + prix_unite + ", client=" + client + ", produit=" + produit + ", date=" + date + '}';
    }

    public PdfPCell getdate() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
}
