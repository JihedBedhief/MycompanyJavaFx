/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Database.Database;
import Entity.Category;
import Entity.Produit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author MediaCenter Zaghouan
 */
public class ServiceProduit implements IserviceProduit<Produit>{
    
      private Connection cnx;

    public ServiceProduit() {
        cnx = Database.getInstance().getCnx();
    }

    


  


    
      public static ObservableList<Produit> RecupBase2(){
             
    ObservableList<Produit> list = FXCollections.observableArrayList();
    
       java.sql.Connection cnx;
     cnx = Database.getInstance().getCnx();
          String sql = "select *from produit ";
    try {
       
        PreparedStatement st = (PreparedStatement) cnx.prepareStatement(sql);

    ResultSet R = st.executeQuery();
    while (R.next()){
        ServiceCategory r = new ServiceCategory();
        
//       r.SelectRole(R.getInt(2));
        
        Produit u = new Produit();
        u.setId(R.getInt(1));
     u.setCategory_id(r.SelectCategory(R.getInt(2)));
     u.setName(R.getString(3));
     u.setDescription(R.getString(4));
     u.setBuyprice(R.getInt(5));
     u.setSellprice(R.getInt(6));
     u.setQuantity(R.getInt(7));
     u.setImage(R.getString(8));
     u.setRate(R.getDouble(9));

  

 

      list.add(u);
    }
    }catch (SQLException ex){
    ex.getMessage(); 
    } 
    return list;
    }

    @Override
    public void ajouter(Produit p) throws SQLException {
  Statement ste =  cnx.createStatement();
        
        Category cr = new Category();
        ServiceCategory r = new ServiceCategory();
        cr = r.SelectCategory(p.getCategory_id().getId());

        

         String requeteInsert = "INSERT INTO client (id, name,description,buyprice,sellprice,quantity,image,category_id) VALUES (NULL,'" + cr + "','" + p.getName() + "', '" + p.getDescription() + "', '" + p.getBuyprice()+"', '" + p.getSellprice()+"', '" + p.getQuantity()+"', '" + p.getImage()+"','"+p.getCategory_id()+"');";
    

ste.executeUpdate(requeteInsert);
    }

    @Override
    public boolean delete(Produit p) throws SQLException {
 if (search(p)==true){
         Statement ste =(Statement) cnx.createStatement();
         String requeteDelete ="DELETE FROM produit WHERE id="+ p.getId();
         ste.executeUpdate(requeteDelete);}
         else{
           System.out.println("L'utulisateur n'existe pas");
        }
         return true;      }

    @Override
    public boolean update(Produit p) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean search(Produit p) throws SQLException {
Statement ste= cnx.createStatement();
    ResultSet rs=ste.executeQuery("select * from produit");
    boolean ok=false; 
    while (rs.next()&&(ok==false)) {         
         if (rs.getInt(1)==p.getId())
             ok=true;
     }
     return ok;         }

    
    public boolean updaterating(Produit p) throws SQLException {
        if (search(p)==true){
        PreparedStatement pre=cnx.prepareStatement("UPDATE produit SET Rate = ? WHERE `id`= ? ");
        
                pre.setDouble(1,p.getRate());

         pre.setInt(2,p.getId());

        //pre.setInt(3,p.getId());

        
        pre.executeUpdate();
        
      
        return true;
 }
          else{
           System.out.println("Le rating  n'existe pas");
           return true;
        }
    }
    
     public boolean updateprix(Produit p,int prix) throws SQLException {
         
         prix = prix-2;
         
         
        if (search(p)==true){
        PreparedStatement pre=cnx.prepareStatement("UPDATE produit SET sellprice = '"+prix+"' WHERE `id`= ? ");
        
             //   pre.setInt(1,p.getSellprice());

         pre.setInt(1,p.getId());

        //pre.setInt(3,p.getId());

        
        pre.executeUpdate();
        
      
        return true;
 }
          else{
           System.out.println("Le rating  n'existe pas");
           return true;
        }
    }
    
    
    @Override
    public List<Produit> read_all() throws SQLException {
 ServiceCategory r = new ServiceCategory();
   List<Produit> arr=new ArrayList<>();
    Statement ste= cnx.createStatement();
    ResultSet rs=ste.executeQuery("select * from produit");
     while (rs.next()) {                
               int id=rs.getInt(1);
               Category category=r.SelectCategory(rs.getInt(2));
               String name=rs.getString(3);
               String description=rs.getString(4);
               int buyprice=rs.getInt(5);
               int sellprice=rs.getInt(6);
               int quantity=rs.getInt(7);
               String image=rs.getString(8);
               
               Produit p=new Produit(id,category,name,description,buyprice,sellprice,quantity,image);
     arr.add(p);
     }
    return arr;     }
    
       public static ObservableList<Integer> RecupCombo(){
             
             
    ObservableList<Integer> list = FXCollections.observableArrayList();
    
       java.sql.Connection cnx;
     cnx = Database.getInstance().getCnx();
          String sql = "SELECT id FROM category";
    try {
       
        PreparedStatement st = (PreparedStatement) cnx.prepareStatement(sql);

    ResultSet R = st.executeQuery();
    while (R.next()){
      
     
   int r = R.getInt(1);
        System.out.println(r);
    
     
      list.add(r);
    }
    }catch (SQLException ex){
    ex.getMessage(); 
    } 
    return list;
    }

  
    
}
