
package services;

import Entity.Achat;
import services.IServiceAchat;
import utils.Maconnexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public  class ServiceAchat implements IServiceAchat {

    Connection cnx;
    private String Produit;
    private int Ida;

    public ServiceAchat() {
        cnx = Maconnexion.getInstance().getConnection();
    }

   
    @Override
    public void ajouterAchat(Achat a) {

        String sql = " insert into Achat(Produit,Quantite,Prix,DateAchat,Taxe)"
                + " values (?,?,?,?,?)";
        

        try {
            PreparedStatement ps = cnx.prepareStatement(sql);
            
            
            ps.setString(1, a.getProduit());
            ps.setFloat(2, a.getQuantite());
            ps.setFloat(3, a.getPrix());
            ps.setString(4, a.getDateAchat());
            ps.setFloat(5,  a.getTaxe());
            ps.executeUpdate();
            System.out.println("Ajout avec succés !");

        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    @Override
    public List<Achat> afficherAchat() {
        String sql = "SELECT * FROM Achat";
        List<Achat> listeAchat = new ArrayList<>(); 

        try {
            Statement statement = cnx.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
              Achat a = new Achat( result.getInt("Ida"),
                result.getString("Produit"),
                result.getFloat("Quantite"),
                result.getFloat("Prix"),
                result.getString("DateAchat"),
                result.getFloat("Taxe"));
                
                listeAchat.add(a);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return listeAchat;
    }
    @Override
     public void modifierAchat(Achat a) {
        String sql = " Update Achat set Produit=?, Quantite=?, Prix=?, DateAchat=?, Taxe=?,  where Ida=?";
        try {
            PreparedStatement ps = cnx.prepareStatement(sql);
            
            
            
            ps.setString(1, a.getProduit());
            ps.setFloat(2, a.getQuantite());
            ps.setFloat(3, a.getPrix());
            ps.setString(4, a.getDateAchat());
            ps.setFloat(5, a.getTaxe());
            ps.setInt(6, a.getIda());
           
            ps.executeUpdate();
            System.out.println("Achat modifié avec succés !");

        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    @Override
     public void supprimerAchat(int Ida) {
        
        try {
            Statement st = cnx.createStatement();
            String sql = "DELETE FROM Achat WHERE Ida="+Ida+"";
         st.executeUpdate(sql);      
      System.out.println("Achat supprimee avec succès...");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
}
       /* public ObservableList<Achat> chercherAchat(String chaine){
          String sql="SELECT * FROM Achat WHERE (Produit LIKE ? or Quantite LIKE ? or Prix LIKE ? or DateAchat LIKE ? or Taxe LIKE ?)";
            
            Connection cnx = Maconnexion.getInstance().getConnection();
            String ch=""+chaine+"%";
         System.out.println(sql);
            ObservableList<Achat> myList= FXCollections.observableArrayList();
        try {
           
            Statement ste= cnx.createStatement();
           // PreparedStatement pst = myCNX.getCnx().prepareStatement(requete6);
            PreparedStatement stee =cnx.prepareStatement(sql);  
            stee.setString(1, ch);
            stee.setString(2, ch);
            stee.setString(3, ch);
            stee.setString(4, ch);
            stee.setString(5, ch);
           
         System.out.println(stee);

            ResultSet rs = stee.executeQuery();
            while (rs.next()){
                Achat f = new Achat ();
                f.setProduit(rs.getString(2));
                f.setQuantite(rs.getString(3));
                f.setPrix(rs.getString(4));
                f.setDateAchat(rs.getString(5));
                 f.setTaxe(rs.getString(6));
                myList.add(f);
                System.out.println("Achat trouvé! ");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
      }*/
     
        public int calculnb(String Type) {
        PreparedStatement pre;
        ResultSet result;
        String query = "SELECT COUNT(*) FROM Achat WHERE Produit='"+Produit+"'";
        int i = 0;
        Achat a = new Achat();
        try {
             pre = cnx.prepareStatement(query);
             result = pre.executeQuery();
             
                   if (result.next()) {
                i = result.getInt(1);
            }
        } catch (SQLException e) {

        }
        return i++;
    }
     
    
    
}