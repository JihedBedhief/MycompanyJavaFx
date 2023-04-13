/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Entity.Abonnement;
import Entity.Client;
import Entity.User;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mycompany.database.Database;

/**
 *
 * @author MediaCenter Zaghouan
 */
public class ServiceUser implements IServiceUser<User> {
    
    
     private Connection cnx;

    public ServiceUser(){
        cnx = Database.getInstance().getCnx();
    }


    @Override
    public void ajouter(User t) throws SQLException {
  
        Statement ste =  cnx.createStatement();
        
        ServiceAbonnement r = new ServiceAbonnement();
        
        Abonnement rr = new Abonnement();
        rr = r.SelectAbonnement(t.getAbonnement_id().getId());

//       int id = t.getId_role().getId_role();
//        System.out.println(id);
         String requeteInsert = "INSERT INTO user (id, abonnement_id,email,roles,password,matricule,adresse,telephone,nom,statuts) VALUES (NULL,'" + rr + "','" + t.getEmail()+ "', 'ROLE_USER', '" + t.getPassword()+"', '" + t.getMatricule()+"', '" + t.getAdresse()+"', '" + t.getTelephone()+"', '" + t.getNom()+"', 'desabled');";
//           String j = "INSERT INTO user (id_user, id_role, nom, prenom, mail, nomd, mdp) SELECT NULL,id_role,nom,prenom, mail,nomd, mdp FROM user r JOIN role u ON r.id_role = u.id_role";
    
//Role role = r.SelectRole(t.getId_role().getId_role());
//int id=role.getId_role();
//t.setId_role(r.SelectRole(id));
ste.executeUpdate(requeteInsert);
//t.setId_role(r.SelectRole(id));
    }

    @Override
    public boolean delete(User t) throws SQLException {
  if (search(t)==true){
         Statement ste =(Statement) cnx.createStatement();
         String requeteDelete ="DELETE FROM user WHERE id="+ t.getId();
         ste.executeUpdate(requeteDelete);}
         else{
           System.out.println("L'utulisateur n'existe pas");
        }
         return true;    }

    @Override
    public boolean update(User t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean search(User t) throws SQLException {
Statement ste= cnx.createStatement();
    ResultSet rs=ste.executeQuery("select * from user");
    boolean ok=false; 
    while (rs.next()&&(ok==false)) {         
         if (rs.getInt(1)==t.getId())
             ok=true;
     }
     return ok;       }

    @Override
    public List<User> readAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User login(String mail, String mdpasse) throws SQLException {
  ServiceAbonnement ro =new ServiceAbonnement();
         
      User p=new User();
      Abonnement r=new Abonnement();
        String req=("select * from user WHERE email=? and password=? ");
        PreparedStatement pre = cnx.prepareStatement(req);
         pre.setString(1, mail);
         pre.setString(2, encryptThisString(mdpasse));
        ResultSet rs = pre.executeQuery();
        while (rs.next()) {
            int id = rs.getInt(1);
            Abonnement abonnement = ro.SelectAbonnement(rs.getInt(2));
            String email = rs.getString(3);
            String roles = rs.getString(4);
            String password = rs.getString(5);
            String matricule = rs.getString(6);
            String adresse = rs.getString(7);
            String domaine = rs.getString(8);
            String pays = rs.getString(9);
            int telephone=rs.getInt(10);
            String nom = rs.getString(11);
            String statuts = rs.getString(12);

            
            
            
            p = new User(id, abonnement, email, roles, password, matricule, adresse,domaine ,pays ,telephone,nom,statuts);
            User.connecte=new User(id, abonnement, email, roles, password, matricule, adresse,domaine ,pays ,telephone,nom,statuts);
        }
        return p;    }
    
    
       public String encryptThisString(String input) 
    { 
        try { 
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] messageDigest = md.digest(input.getBytes()); 
            BigInteger no = new BigInteger(1, messageDigest); 
            String hashtext = no.toString(16); 
            while (hashtext.length() < 32) { 
                hashtext = "0" + hashtext; 
            } 
            return hashtext; 
        } 
        catch (NoSuchAlgorithmException e) { 
            throw new RuntimeException(e); 
        } 
    }
      public  boolean VerifEmail(String email) 
    { 
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+ 
                            "[a-zA-Z0-9_+&*-]+)*@" + 
                            "(?:[a-zA-Z0-9-]+\\.)+[a-z" + 
                            "A-Z]{2,7}$"; 
                              
        Pattern pat = Pattern.compile(emailRegex); 
        if (email == null) 
            return false; 
        return pat.matcher(email).matches(); 
    } 
       
        public static ObservableList<String> RecupCombo(){
             
             
    ObservableList<String> list = FXCollections.observableArrayList();
    
       java.sql.Connection cnx;
     cnx = Database.getInstance().getCnx();
          String sql = "SELECT type FROM abonnement";
    try {
       
        PreparedStatement st = (PreparedStatement) cnx.prepareStatement(sql);

    ResultSet R = st.executeQuery();
    while (R.next()){
      
     
   String r = R.getString(1);
        System.out.println(r);
    
     
      list.add(r);
    }
    }catch (SQLException ex){
    ex.getMessage(); 
    } 
    return list;
    }
    
         public static ObservableList<User> RecupBase2(){
             
    ObservableList<User> list = FXCollections.observableArrayList();
    
       java.sql.Connection cnx;
     cnx = Database.getInstance().getCnx();
          String sql = "select *from user ";
    try {
       
        PreparedStatement st = (PreparedStatement) cnx.prepareStatement(sql);

    ResultSet R = st.executeQuery();
    while (R.next()){
        ServiceAbonnement r = new ServiceAbonnement();
        
//       r.SelectRole(R.getInt(2));
        
        User u = new User();
        u.setId(R.getInt(1));
     u.setAbonnement_id(r.SelectAbonnement(R.getInt(2)));
     u.setEmail(R.getString(3));
     u.setMatricule(R.getString(6));
     u.setAdresse(R.getString(7));
     u.setDomaine(R.getString(8));
     u.setPays(R.getString(9));
     u.setTelephone(R.getInt(10));
     u.setNom(R.getString(11));
     u.setStatuts(R.getString(12));


 

    
     
      list.add(u);
    }
    }catch (SQLException ex){
    ex.getMessage(); 
    } 
    return list;
    }
         
          public boolean UNBAN(User t) throws SQLException {
           String s ="enable" ;
        if (search(t)==true){
            PreparedStatement pre=cnx.prepareStatement("UPDATE user SET statuts ='"+s+"'  WHERE `id`='"+ t.getId() +"' ");
            
           
            pre.executeUpdate();
            return true;
        }
        else{
           System.out.println("L'ABRARS n'existe pas");
           return true;
        } 
        
        
    }
       
       public boolean BAN(User t) throws SQLException {
           String s ="desable" ;
        if (search(t)==true){
            PreparedStatement pre=cnx.prepareStatement("UPDATE user SET statuts ='"+s+"'  WHERE `id`='"+ t.getId() +"' ");
            
           
            pre.executeUpdate();
            return true;
        }
        else{
           System.out.println("L'ABRARS n'existe pas");
           return true;
        } 
        
        
    }
     
         
         
}
