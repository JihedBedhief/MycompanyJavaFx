/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.PointVente;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.DataSource;

/**
 *
 * @author Abderrazekbenhamouda
 */
public class PointVenteService {
    
    private final Connection cnx;

    private static PointVenteService instance;
    
        public PointVenteService() {
        cnx = DataSource.getInstance().getCnx();
    }
    
    public static PointVenteService getInstance()
    {
        if (instance == null) {
            instance = new PointVenteService();
        }
        return instance; 
    }
    
   public void addPointVente(PointVente q)throws SQLDataException, SQLException{
        
         
 String query ="INSERT INTO `point_vente`( `name`, `region`, `ville`,`code_postal`,`telephone`,`email`) VALUES (?,?,?,?,?,?)";
 
         PreparedStatement st;
        
        try {
            st = cnx.prepareStatement(query);
                st.setString(1,q.getNom());
                st.setString(2,q.getRegion());
                st.setString(3,q.getVille());
                st.setInt(4, q.getCodePostal());
                st.setInt(5, q.getTel());                
                st.setString(6, q.getEmail());
                st.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(PointVenteService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
                

    }

    public boolean ModifierPointVente(PointVente e) throws SQLDataException {

               
                String query = "UPDATE `point_vente` SET `name`=?,`region`=?,`code_postal`=?,`telephone`=?,`email`=?,`ville`=? WHERE `id` = ?";
		PreparedStatement st;
        try {
                st = cnx.prepareStatement(query);
                st.setString(1,e.getNom());
                st.setString(2,e.getRegion());
                st.setInt(3,e.getCodePostal());
                st.setInt(4,e.getTel());
                st.setString(5,e.getEmail());
                st.setString(6,e.getVille());
                st.setInt(7,e.getId());

                st.executeUpdate();
                return true;
                
        } catch (SQLException ex) {
            Logger.getLogger(PointVenteService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
                
               
    }

    public ObservableList<PointVente> getAllPointVente() throws SQLDataException {

        
        List<PointVente> list =new ArrayList<PointVente>();
        int count =0;
        
        String requete="select * from point_vente";
         try{
             Statement st = cnx.createStatement();
             ResultSet rs = st.executeQuery(requete);
            
            while (rs.next()){
                
                PointVente e = new PointVente();
                e.setId(rs.getInt("id"));
                e.setNom(rs.getString("name"));
                e.setRegion(rs.getString("region"));
                e.setVille(rs.getString("ville"));
                e.setEmail(rs.getString("email"));
                e.setCodePostal(rs.getInt("code_postal"));
                e.setTel(rs.getInt("telephone"));
                e.setBadge(rs.getString("badge"));
                
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
            Logger.getLogger(PointVenteService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
   
           
}
       public PointVente get_PointVente(int i) {
        PointVente e = new PointVente();
        String requete = "select * from point_vente where id="+i;
        try {
            PreparedStatement ps = cnx.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {


                e.setId(rs.getInt("id"));
                e.setNom(rs.getString("name"));
                e.setRegion(rs.getString("region"));
                e.setVille(rs.getString("ville"));
                e.setEmail(rs.getString("email"));
                e.setCodePostal(rs.getInt("code_postal"));
                e.setTel(rs.getInt("telephone"));
                e.setBadge(rs.getString("badge"));
                

            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return e;

    }
    
    
    
    
    
   public boolean validerEmail(String s){
    Pattern p = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");
    Matcher m = p.matcher(s);
    if (m.find() && m.group().equals(s)){
        return false;
    }
    else 
    {
      
        return true;
    
        
}
   }
    
    
    

    public boolean deletePointVente(int idEvenement) throws SQLDataException {

        
        
        try {
            
            Statement st=cnx.createStatement();
            String req= "DELETE FROM `point_vente` WHERE `id` ="+idEvenement;
            st.executeUpdate(req);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(PointVenteService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

       
     public PointVente getPointVenteByNom(String i) {
        PointVente e = new PointVente();
        int nombre = 0;
        String requete =  "SELECT * FROM `point_vente` WHERE name =\""+i+"\"";
        try {
            PreparedStatement ps = cnx.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                e.setId(rs.getInt("id"));
                e.setNom(rs.getString("name"));
                e.setRegion(rs.getString("region"));
                e.setVille(rs.getString("ville"));
                e.setEmail(rs.getString("email"));
                e.setCodePostal(rs.getInt("code_postal"));
                e.setTel(rs.getInt("telephone"));
                e.setBadge(rs.getString("badge"));
                nombre++;

            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return e;

    }

 public boolean ModifierBadge(PointVente q, String badge) throws SQLDataException {

               
                String query = "UPDATE `point_vente` SET `badge`=? WHERE `id` = ?";
		PreparedStatement st;
        try {
                st = cnx.prepareStatement(query);
                st.setString(1,badge);
                st.setInt(2,q.getId());
                st.executeUpdate();
                return true;
                
        } catch (SQLException ex) {
            Logger.getLogger(VenteService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
                
               
    }
    


}