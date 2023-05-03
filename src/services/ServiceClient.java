/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Entity.Client;
import Entity.Division;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mycompany.database.Database;

/**
 *
 * @author hassen
 */
public class ServiceClient implements IServiceClient<Client>{
    
      private Connection cnx;

    public ServiceClient() {
        cnx = Database.getInstance().getCnx();
    }

           private ObservableList data;

    
    @Override
    public void ajouter(Client d) throws SQLException {
        
         Statement ste =  cnx.createStatement();
        
        ServiceDivision r = new ServiceDivision();
        
        Division rr = new Division();
        rr = r.SelectDivision(d.getDivision_id().getId());

//       int id = t.getId_role().getId_role();
//        System.out.println(id);
         String requeteInsert = "INSERT INTO client (id, division_id,name,telephone,email,ville,code_postal,cin,date_ajout) VALUES (NULL,'" + rr + "','" + d.getName() + "', '" + d.getTelephone() + "', '" + d.getEmail()+"', '" + d.getVille()+"', '" + d.getCode_postal()+"', '" + d.getCin()+"', '" + d.getDate_ajout()+"');";
//           String j = "INSERT INTO user (id_user, id_role, nom, prenom, mail, nomd, mdp) SELECT NULL,id_role,nom,prenom, mail,nomd, mdp FROM user r JOIN role u ON r.id_role = u.id_role";
    
//Role role = r.SelectRole(t.getId_role().getId_role());
//int id=role.getId_role();
//t.setId_role(r.SelectRole(id));
ste.executeUpdate(requeteInsert);
//t.setId_role(r.SelectRole(id));
    }

    @Override
    public boolean delete(Client d) throws SQLException {
  if (search(d)==true){
         Statement ste =(Statement) cnx.createStatement();
         String requeteDelete ="DELETE FROM client WHERE id="+ d.getId();
         ste.executeUpdate(requeteDelete);}
         else{
           System.out.println("L'utulisateur n'existe pas");
        }
         return true;    }

    @Override
    public boolean update(Client d) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean search(Client d) throws SQLException {
 Statement ste= cnx.createStatement();
    ResultSet rs=ste.executeQuery("select * from client");
    boolean ok=false; 
    while (rs.next()&&(ok==false)) {         
         if (rs.getInt(1)==d.getId())
             ok=true;
     }
     return ok;     }

    @Override
    public List<Client> readAll() throws SQLException {
 ServiceDivision r = new ServiceDivision();
   List<Client> arr=new ArrayList<>();
    Statement ste= cnx.createStatement();
    ResultSet rs=ste.executeQuery("select * from client");
     while (rs.next()) {                
               int id=rs.getInt(1);
               Division division=r.SelectDivision(rs.getInt(2));
               String name=rs.getString(3);
               int telephone=rs.getInt(4);
               String email=rs.getString(5);
               String ville=rs.getString(6);
               String code_postal=rs.getString(7);
               String cin=rs.getString(8);
               String date_ajout =rs.getString(9);
               
               Client p=new Client(id,division,name,telephone,email,ville,code_postal,cin,date_ajout);
     arr.add(p);
     }
    return arr; 
    }
    
      public static ObservableList<Client> RecupBase2(){
             
    ObservableList<Client> list = FXCollections.observableArrayList();
    
       java.sql.Connection cnx;
     cnx = Database.getInstance().getCnx();
          String sql = "select *from client ";
    try {
       
        PreparedStatement st = (PreparedStatement) cnx.prepareStatement(sql);

    ResultSet R = st.executeQuery();
    while (R.next()){
        ServiceDivision r = new ServiceDivision();
        
//       r.SelectRole(R.getInt(2));
        
        Client u = new Client();
        u.setId(R.getInt(1));
     u.setDivision_id(r.SelectDivision(R.getInt(2)));
     u.setName(R.getString(3));
     u.setTelephone(R.getInt(4));
     u.setEmail(R.getString(5));
     u.setVille(R.getString(6));
     u.setCode_postal(R.getString(7));
     u.setCin(R.getString(8));
     u.setDate_ajout(R.getString(9));

 

    
     
      list.add(u);
    }
    }catch (SQLException ex){
    ex.getMessage(); 
    } 
    return list;
    }
    
      
  
    
      
      public static ObservableList<String> RecupCombo(){
             
             
    ObservableList<String> list = FXCollections.observableArrayList();
    
       java.sql.Connection cnx;
     cnx = Database.getInstance().getCnx();
          String sql = "SELECT type FROM division";
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
      
      
         public boolean UpdateGrade(Client d ) throws SQLException {
        if (search(d)==true){
            PreparedStatement pre=cnx.prepareStatement("UPDATE client SET division_id ='"+d.getDivision_id().getId()+"'  WHERE `id`='"+ d.getId() +"' ");
            
           
            pre.executeUpdate();
            return true;
        }
        else{
           System.out.println("La division n'existe pas");
           return true;
        } 
        
        
    }
      
      
      
        public boolean Updateclient(Client d ) throws SQLException {
        if (search(d)==true){
            PreparedStatement pre=cnx.prepareStatement("UPDATE client SET name ='"+d.getName()+"',telephone ='"+d.getTelephone()+"',email ='"+d.getEmail()+"',ville ='"+d.getVille()+"',code_postal ='"+d.getCode_postal()+"',cin ='"+d.getCin()+"'  WHERE `id`='"+ d.getId() +"' ");
            
           
            pre.executeUpdate();
            return true;
        }
        else{
           System.out.println("La division n'existe pas");
           return true;
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
      
      
       public int RecupTotal(){
             
             int total = 0;
    
       java.sql.Connection cnx;
     cnx = Database.getInstance().getCnx();
          String sql = "SELECT COUNT(*) AS total_clent FROM client";
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
