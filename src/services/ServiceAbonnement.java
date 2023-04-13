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
 * @author MediaCenter Zaghouan
 */
public class ServiceAbonnement implements IServiceAbonnement<Abonnement> {
    
       private Connection cnx;

    public ServiceAbonnement() {
        cnx = Database.getInstance().getCnx();
    }

    @Override
    public void ajouter(Abonnement a) throws SQLException {
  Statement ste = cnx.createStatement();

        String requeteInsert = "INSERT INTO abonnement (id, type,duree,frais) VALUES (NULL, '" + a.getType()+ "','" + a.getDuree()+ "','" + a.getFrais()+ "');";
        ste.executeUpdate(requeteInsert);    }

    @Override
    public boolean delete(Abonnement a) throws SQLException {
 Statement ste = cnx.createStatement();
        if (search(a) == true) {
            ste = cnx.createStatement();
            String requeteDelete = "DELETE FROM abonnement WHERE id=" + a.getId() + "";
            ste.executeUpdate(requeteDelete);
        } else {
            System.out.println("Le Role n'existe pas");
        }
        return true;    }

    @Override
    public boolean update(Abonnement a) throws SQLException {
    if (search(a)==true){
        PreparedStatement pre=cnx.prepareStatement("UPDATE abonnement SET id ='"+ a.getId() +"' , type = '"+ a.getType()+"', duree = '"+ a.getDuree()+"', frais = '"+ a.getFrais()+"' WHERE `id`= '" + a.getId() );
        pre.setInt(1,a.getId());
        pre.setString(2,a.getType());
        pre.setString(3,a.getDuree());
        pre.setString(4,a.getFrais());

        pre.executeUpdate();
        return true;
 }
          else{
           System.out.println("La division  n'existe pas");
           return true;
        }    }

    @Override
    public boolean search(Abonnement a) throws SQLException {
      Statement ste = cnx.createStatement();
        ResultSet rs = ste.executeQuery("select * from abonnement");
        boolean ok = false;
        while (rs.next() && (ok == false)) {
            if (rs.getInt(1) == a.getId()) {
                ok = true;
            }
        }
        return ok;    }

    @Override
    public List<Abonnement> readAll() throws SQLException {
  List<Abonnement> arr = new ArrayList<>();
        Statement ste = cnx.createStatement();
        ResultSet rs = ste.executeQuery("select * from abonnement");
        while (rs.next()) {
            int id = rs.getInt(1);
            String type = rs.getString(2);
            String duree = rs.getString(3);
            String frais = rs.getString(4);


            Abonnement d = new Abonnement(id, type, duree,frais);
            arr.add(d);
        }
        return arr;    }
    
    
     public static ObservableList<Abonnement> RecupBase(){
             
    ObservableList<Abonnement> list = FXCollections.observableArrayList();
    
       java.sql.Connection cnx;
     cnx = Database.getInstance().getCnx();
          String sql = "select *from abonnement";
    try {
       
        PreparedStatement st = (PreparedStatement) cnx.prepareStatement(sql);

    ResultSet R = st.executeQuery();
    while (R.next()){
      Abonnement r =new Abonnement();
     r.setId((R.getInt(1)));
     r.setType(R.getString(2));
     r.setDuree(R.getString(3));
          r.setFrais(R.getString(4));


     
    
     
      list.add(r);
    }
    }catch (SQLException ex){
    ex.getMessage(); 
    } 
    return list;
    }
     
       public boolean UNBAN(Abonnement t) throws SQLException {
        if (search(t)==true){
            PreparedStatement pre=cnx.prepareStatement("UPDATE abonnement SET type ='"+t.getType()+"',duree ='"+t.getDuree()+"',frais ='"+t.getFrais()+"'  WHERE `id`='"+ t.getId() +"' ");
            
           
            pre.executeUpdate();
            return true;
        }
        else{
           System.out.println("L'ABRARS n'existe pas");
           return true;
        } 
        
        
    }
     
        public Abonnement SelectAbonnement(int id){
        Abonnement r = new Abonnement();
        String req = "SELECT * FROM abonnement where id ="+id+"";
        
        try {
            PreparedStatement ps = cnx.prepareStatement(req);

        ResultSet rs = ps.executeQuery(req);
            
            while(rs.next()){           
                 
                r = new Abonnement(rs.getInt("id"), rs.getString("type"),rs.getString("duree"),rs.getString("frais"));

            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceDivision .class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }
     
}
