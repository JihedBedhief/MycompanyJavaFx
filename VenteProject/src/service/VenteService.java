/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Vente;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.DataSource;

/**
 *
 * @author Abderrazekbenhamouda
 */
public class VenteService {
    
    private final Connection cnx;

    private static VenteService instance;
    
        public VenteService() {
        cnx = DataSource.getInstance().getCnx();
    }
    
    public static VenteService getInstance()
    {
        if (instance == null) {
            instance = new VenteService();
        }
        return instance; 
    }
    
   public void addVente(Vente q) throws SQLDataException, SQLException {
    String query = "INSERT INTO `vente`(`point_vente_id`, `produit`, `quantite`, `prix_unite`, `taxe`, `prix_totale`, `date_vente`, `client`) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    PreparedStatement st;
    try {
        double prixTotale = (q.getPrix_unite() * q.getQuantite()) + q.getTaxe(); // calcul du prix total
        st = cnx.prepareStatement(query);
        st.setInt(1, q.getPtvente());
        st.setString(2, q.getProduit());
        st.setInt(3, q.getQuantite());
        st.setDouble(4, q.getPrix_unite());
        st.setDouble(5, q.getTaxe());
        st.setDouble(6, prixTotale); // utilisation du prix total calculé
        st.setDate(7, (Date) q.getDate());
        st.setString(8, q.getClient());
        st.executeUpdate();
    } catch (SQLException ex) {
        Logger.getLogger(VenteService.class.getName()).log(Level.SEVERE, null, ex);
    }
}
    public boolean ModifierVente(Vente q) throws SQLDataException {

               
                String query = "UPDATE `vente` SET `point_vente_id`=?,`produit`=?,`quantite`=?,`prix_unite`=?,`taxe`=?,`prix_totale`=?,`date_vente`=?,`client`=? WHERE `id` = ?";
		PreparedStatement st;
        try {
            double prixTotale = (q.getPrix_unite() * q.getQuantite()) + q.getTaxe();
                st = cnx.prepareStatement(query);
                st.setInt(1,q.getPtvente());
                st.setString(2,q.getProduit());
                st.setInt(3,q.getQuantite());
                st.setDouble(4, q.getPrix_unite());
                st.setDouble(5, q.getTaxe());
                st.setDouble(6, prixTotale);
                st.setDate(7, (Date) q.getDate());
                st.setString(8,q.getClient());
                st.setInt(9,q.getId());
                st.executeUpdate();
                return true;
                
        } catch (SQLException ex) {
            Logger.getLogger(VenteService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
                
               
    }

    public ObservableList<Vente> getAllVente() throws SQLDataException {

        
        List<Vente> list =new ArrayList<Vente>();
        int count =0;
        
        String requete="select * from vente";
         try{
             Statement st = cnx.createStatement();
             ResultSet rs = st.executeQuery(requete);
            
            while (rs.next()){
                
                Vente e = new Vente();
                e.setId(rs.getInt("id"));
                e.setPtvente(rs.getInt("point_vente_id"));
                e.setProduit(rs.getString("produit"));
                e.setQuantite(rs.getInt("quantite"));
                e.setPrix_unite(rs.getDouble("prix_unite"));
                e.setTaxe(rs.getDouble("taxe"));
                e.setPrix_totale(rs.getDouble("prix_totale"));
                e.setDate(rs.getDate("date_vente"));
                e.setClient(rs.getString("client"));
                list.add(e);
                
                count++;
            }
            if(count == 0){
                return null;
           }else{
             ObservableList lc_final = FXCollections.observableArrayList(list);

               return lc_final;
            
           
        }
         }
        catch (SQLException ex) {
            Logger.getLogger(VenteService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
   
           
}
    
    
    
        public ObservableList<Vente> getAllVenteByPointVente(int id ) throws SQLDataException {

        
        List<Vente> list =new ArrayList<Vente>();
        int count =0;
        
        String requete="select * from vente where point_vente_id="+id;
         try{
             Statement st = cnx.createStatement();
             ResultSet rs = st.executeQuery(requete);
            
            while (rs.next()){
                
                Vente e = new Vente();
                e.setId(rs.getInt("id"));
                e.setPtvente(rs.getInt("point_vente_id"));
                e.setProduit(rs.getString("produit"));
                e.setQuantite(rs.getInt("quantite"));
                e.setPrix_unite(rs.getDouble("prix_unite"));
                e.setTaxe(rs.getDouble("taxe"));
                e.setPrix_totale(rs.getDouble("prix_totale"));
                e.setDate(rs.getDate("date_vente"));
                e.setClient(rs.getString("client"));
                list.add(e);
                
                count++;
            }
            if(count == 0){
                return null;
           }else{
             ObservableList lc_final = FXCollections.observableArrayList(list);

               return lc_final;
            
           
        }
         }
        catch (SQLException ex) {
            Logger.getLogger(VenteService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
   
           
}
       public Vente get_Vente(int i) {
        Vente e = new Vente();
        String requete = "select * from vente where id="+i;
        try {
            PreparedStatement ps = cnx.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                e.setId(rs.getInt("id"));
                e.setPtvente(rs.getInt("point_vente_id"));
                e.setProduit(rs.getString("produit"));
                e.setQuantite(rs.getInt("quantite"));
                e.setPrix_unite(rs.getDouble("prix_unite"));
                e.setTaxe(rs.getDouble("taxe"));
                e.setPrix_totale(rs.getDouble("prix_totale"));
                e.setDate(rs.getDate("date_vente"));
                e.setClient(rs.getString("client"));
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return e;

    }
    
    


    public boolean deleteVente(int idVente) throws SQLDataException {

        
        
        try {
            
            Statement st=cnx.createStatement();
            String req= "DELETE FROM `vente` WHERE `id` ="+idVente;
            st.executeUpdate(req);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(VenteService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }
   public static boolean estDouble(String valeur) {
    try {
        Double.parseDouble(valeur);
        return true;
    } catch (NumberFormatException e) {
        return false;
    }
}
   
       public int NombreVote (int p) throws SQLException{
      
                    int c =0 ;
                    String query = "SELECT COUNT(*) FROM vente WHERE point_vente_id="+p;
                    Statement st  = cnx.createStatement();
                    ResultSet rs = st.executeQuery(query);
                     while(rs.next()){
                         
                         
                    c=c+ rs.getInt(1);
                     
                     }
                     return c ;


 }
       
       
         

}