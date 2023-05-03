/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Entity.Abonnement;
import Entity.Division;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mycompany.database.Database;

/**
 *
 * @author user
 */
public class ServiceDivision implements IServiceDivision<Division> {

    private Connection cnx;

    public ServiceDivision() {
        cnx = Database.getInstance().getCnx();
    }

    @Override
    public void ajouter(Division t) throws SQLException {
        Statement ste = cnx.createStatement();

        String requeteInsert = "INSERT INTO division (id, type,taux_remise) VALUES (NULL, '" + t.getType() + "','" + t.getTaux_remise() + "');";
        ste.executeUpdate(requeteInsert);
    }

    @Override
    public boolean delete(Division d) throws SQLException {
        Statement ste = cnx.createStatement();
        if (search(d) == true) {
            ste = cnx.createStatement();
            String requeteDelete = "DELETE FROM division WHERE id=" + d.getId() + "";
            ste.executeUpdate(requeteDelete);
        } else {
            System.out.println("Le Role n'existe pas");
        }
        return true;
    }

    @Override
    public boolean update(Division d) throws SQLException {
        if (search(d)==true){
        PreparedStatement pre=cnx.prepareStatement("UPDATE division SET id ='"+ d.getId() +"' , type = '"+ d.getType()+"', taux_remise = '"+ d.getTaux_remise()+"' WHERE `id`= '" + d.getId() );
        pre.setInt(1,d.getId());
        pre.setString(2,d.getType());
        pre.setDouble(3,d.getTaux_remise());
        pre.executeUpdate();
        return true;
 }
          else{
           System.out.println("La division  n'existe pas");
           return true;
        }
    }

    @Override
    public boolean search(Division d) throws SQLException {
        Statement ste = cnx.createStatement();
        ResultSet rs = ste.executeQuery("select * from division");
        boolean ok = false;
        while (rs.next() && (ok == false)) {
            if (rs.getInt(1) == d.getId()) {
                ok = true;
            }
        }
        return ok;
    }

    @Override
    public List<Division> readAll() throws SQLException {
        List<Division> arr = new ArrayList<>();
        Statement ste = cnx.createStatement();
        ResultSet rs = ste.executeQuery("select * from division");
        while (rs.next()) {
            int id = rs.getInt(1);
            String type = rs.getString(2);
            double taux_remise = rs.getDouble(3);

            Division d = new Division(id, type, taux_remise);
            arr.add(d);
        }
        return arr;
    }
     public static ObservableList<Division> RecupBase(){
             
    ObservableList<Division> list = FXCollections.observableArrayList();
    
       java.sql.Connection cnx;
     cnx = Database.getInstance().getCnx();
          String sql = "select *from division";
    try {
       
        PreparedStatement st = (PreparedStatement) cnx.prepareStatement(sql);

    ResultSet R = st.executeQuery();
    while (R.next()){
      Division r =new Division();
     r.setId((R.getInt(1)));
     r.setType(R.getString(2));
          r.setTaux_remise(R.getDouble(3));

     
    
     
      list.add(r);
    }
    }catch (SQLException ex){
    ex.getMessage(); 
    } 
    return list;
    }

       public Division SelectDivision(int id){
        Division r = new Division();
        String req = "SELECT * FROM division where id ="+id+"";
        
        try {
            PreparedStatement ps = cnx.prepareStatement(req);

        ResultSet rs = ps.executeQuery(req);
            
            while(rs.next()){           
                 
                r = new Division(rs.getInt("id"), rs.getString("type"),rs.getDouble("taux_remise"));

            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceDivision .class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }
       
       
       public Division listerEmployeesparNom(String nom) {
        Division rec = new Division();
    try (PreparedStatement ps = Database.getInstance().getCnx().prepareStatement(
            "SELECT * FROM division WHERE type = ?")) {
        ps.setString(1, nom);
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                
                              rec = new Division(rs.getInt("id"), rs.getString("type"),rs.getDouble("taux_remise"));

              
            }
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return rec;
}

       
           public Division SelectDivisionbyname(String s){
        Division r = new Division();
        
        try {
                    String req = "SELECT * FROM division where type =?" ;

            PreparedStatement ps = cnx.prepareStatement(req);
ps.setString(1, s);
        ResultSet rs = ps.executeQuery(req);
            
            while(rs.next()){           
                 
                r = new Division(rs.getInt("id"), rs.getString("type"),rs.getDouble("taux_remise"));

            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceDivision .class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }
       
       
     
            public boolean UpdateDiv(Division d ) throws SQLException {
        if (search(d)==true){
            PreparedStatement pre=cnx.prepareStatement("UPDATE division SET type ='"+d.getType()+"',taux_remise ='"+d.getTaux_remise()+"'  WHERE `id`='"+ d.getId() +"' ");
            
           
            pre.executeUpdate();
            return true;
        }
        else{
           System.out.println("La division n'existe pas");
           return true;
        } 
        
        
    }
            
             public int RecupTotal(){
             
             int total = 0;
    
       java.sql.Connection cnx;
     cnx = Database.getInstance().getCnx();
          String sql = "SELECT COUNT(*) AS total_division FROM division";
    try {
       
        PreparedStatement st = (PreparedStatement) cnx.prepareStatement(sql);

    ResultSet R = st.executeQuery();
    while (R.next()){
      
     
    total = R.getInt(1);
    
     
      
    }
    }catch (SQLException ex){
    ex.getMessage(); 
    } 
    return total;
    }
     
     
}
