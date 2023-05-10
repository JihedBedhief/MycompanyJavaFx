
package Services;

import Entities.Fournisseur;
import Iservices.IservicesFournisseur;
import Utils.Maconnexion;
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

public  class ServiceFournisseur implements IservicesFournisseur{
    Connection cnx;

    public ServiceFournisseur() {
        cnx = Maconnexion.getInstance().getConnection();
    }
    
    
    
    
    
    
    public void ajouterFournisseur(Fournisseur f) {

        String sql = " insert into Fournisseur(Nom,Type,Num,Adresse,Email)"
                + " values (?,?,?,?,?)";
        

        try {
            PreparedStatement ps = cnx.prepareStatement(sql);
            
            
            ps.setString(1, f.getNom());
            ps.setString(2, f.getType());
            ps.setInt(3, f.getNum());
            ps.setString(4,  f.getAdresse());
            ps.setString(5,  f.getEmail());
            ps.executeUpdate();
            System.out.println("Ajout avec succés !");

        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    
    
    
    
    
    
    
    
     @Override
    public List<Fournisseur> afficherFournisseur() {
        String sql = "SELECT * FROM Fournisseur";
        List<Fournisseur> listeFournisseur = new ArrayList<>(); 

        try {
            Statement statement = cnx.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
              Fournisseur f = new Fournisseur( result.getInt("Idf"),
                result.getString("Nom"),
                result.getString("Type"),
                result.getInt("Num"),
                result.getString("Adresse"),
                result.getString("Email"));
                
                listeFournisseur.add(f);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return listeFournisseur;
    }

    




@Override


     public void modifierFournisseur(int Idf,Fournisseur f) {


        String sql = " Update Fournisseur set Nom=?, Type=?, Num=?, Adresse=?, Email=?,  where Idf=?";
        try {



            PreparedStatement ps = cnx.prepareStatement(sql);
            
            
            
            ps.setString(1, f.getNom());
            ps.setString(2, f.getType());
            ps.setInt(3, f.getNum());
            ps.setString(4,  f.getAdresse());
            ps.setString(5, f.getEmail());
            ps.setInt(6, f.getId());
           
            ps.executeUpdate();
            System.out.println("Fournisseur modifié avec succés !");

        } catch (SQLException ex) {
            System.out.println(ex);
        }
     }

    @Override
     public void supprimerFournisseur(int Id) {
        
        try {
            Statement st = cnx.createStatement();
            String sql = "DELETE FROM Fournisseur WHERE Id="+Id+"";
         st.executeUpdate(sql);      
      System.out.println("Fournisseur supprimee avec succès...");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
}

    @Override
    public void ajouterAchat(Fournisseur f) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
   
   
    }