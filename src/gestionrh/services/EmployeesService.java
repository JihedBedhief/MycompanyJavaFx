/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionrh.services;

import gestionrh.utils.MyConnection;
import gestionrh.entities.Employees;
import java.sql.Connection;
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

/**
 *
 * @author derou
 */
public class EmployeesService {
     Connection cnx2;
    public EmployeesService(){
        cnx2 = MyConnection.getinstance().getCnx();
    }
    
     public void ajouter_Employees(Employees r) {
        try {
            String requete1 = "INSERT INTO employees (cin,nom,prenom,email,phoneNum) VALUES(?,?,?,?,?)";
            PreparedStatement pst = MyConnection.getinstance().getCnx().prepareStatement(requete1);
            pst.setString(1, r.getCin());
            pst.setString(2, r.getNom());
            pst.setString(3, r.getPrenom());
            pst.setString(4, r.getEmail());
            pst.setInt(5, r.getPhoneNum());
            pst.executeUpdate();
            System.out.println("Employees ajouté !");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
     
     
     
     public void modifier_Employee(Employees r, String cin, String nom,String prenom,String email,int phoneNum) {
        try {
            String requete4 = " UPDATE employees SET " + "  cin= ?, nom = ? ,prenom = ? , email  = ?,phoneNum = ? WHERE id= " + r.getId();
            PreparedStatement pst = MyConnection.getinstance().getCnx().prepareStatement(requete4);
            pst.setString(1, r.getCin());
            pst.setString(2, r.getNom());
            pst.setString(3, r.getPrenom());
            pst.setString(4, r.getEmail());
            pst.setInt(5, r.getPhoneNum());
            pst.executeUpdate();
            System.out.println("Employee modifié !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
     }
    //*****************************************************
     
     public void modifierEmp(Employees emp) {
        try {
            String requete2="update employees set cin=?,nom=?,prenom=?,email=?,phoneNum=? where id=?";
            PreparedStatement pst = cnx2.prepareStatement(requete2);
            pst.setString(1, emp.getCin());
            pst.setString(2, emp.getNom());
            pst.setString(3, emp.getPrenom());
            pst.setString(4, emp.getEmail());
            pst.setInt(5,    emp.getPhoneNum());
            
            pst.executeUpdate();
           
            System.out.println("Employee est modifié");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
    }}
     //****************************************************
    
     
      public void supprimer_Employee(Employees R) {

        try {
            String requete3 = "DELETE FROM employees WHERE id=" + R.getId();
            Statement st = MyConnection.getinstance().getCnx().createStatement();

            st.executeUpdate(requete3);
            System.out.println("Employee supprimé !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }
      
      
       public ArrayList<Employees> listerEmployees() {
         ArrayList<Employees> myList = new ArrayList();
        try {

            String requete2 = "Select * FROM employees";
            Statement st = MyConnection.getinstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete2);
            while (rs.next()) {
                Employees rec = new Employees();
                rec.setId(rs.getInt(1));
                rec.setCin(rs.getString("cin"));
                rec.setNom(rs.getString("nom"));
                rec.setPrenom(rs.getString("prenom"));
                rec.setEmail(rs.getString("email"));
                rec.setPhoneNum(rs.getInt("phoneNum"));
                myList.add(rec);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return myList;
    }
       
    public Employees listerEmployeesparNom(String nom) {
        Employees rec = new Employees();
    try (PreparedStatement ps = MyConnection.getinstance().getCnx().prepareStatement(
            "SELECT * FROM employees WHERE nom = ?")) {
        ps.setString(1, nom);
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                
                rec.setId(rs.getInt("id"));
                rec.setCin(rs.getString("cin"));
                rec.setNom(rs.getString("nom"));
                rec.setPrenom(rs.getString("prenom"));
                rec.setEmail(rs.getString("email"));
                rec.setPhoneNum(rs.getInt("phoneNum"));
              
            }
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return rec;
}
       
    public List<Employees> listerEmployeesparNomPrenom(String nom, String prenom) {
    List<Employees> myList = new ArrayList<>();
    try (
        PreparedStatement ps = MyConnection.getinstance().getCnx().prepareStatement(
        "SELECT * FROM employees WHERE nom = ? AND prenom = ?")) {
        ps.setString(1, nom);
        ps.setString(2, prenom);
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Employees rec = new Employees();
                rec.setId(rs.getInt("id"));
                rec.setCin(rs.getString("cin"));
                rec.setNom(rs.getString("nom"));
                rec.setPrenom(rs.getString("prenom"));
                rec.setEmail(rs.getString("email"));
                rec.setPhoneNum(rs.getInt("phoneNum"));
                myList.add(rec);
            }
        }
    } catch (SQLException ex) {
       System.out.println(ex.getMessage());
    }
    return myList;
}
    public static ObservableList<String> RecupCombo(){
             
             
    ObservableList<String> list = FXCollections.observableArrayList();
    
       java.sql.Connection cnx;
     cnx = MyConnection.getinstance().getCnx();
          String sql = "SELECT id FROM employees";
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
    
    
    //*********************************************************************************
    public static ObservableList<Employees> RecupBase(){
             
    ObservableList<Employees> list = FXCollections.observableArrayList();
    
       java.sql.Connection cnx;
     cnx = MyConnection.getinstance().getCnx();
          String sql = "select * from employees";
    try {
       
        PreparedStatement st = (PreparedStatement) cnx.prepareStatement(sql);

    ResultSet rs = st.executeQuery();
    Employees rec=new Employees();
    while (rs.next()){
      rec.setId(rs.getInt(1));
                rec.setCin(rs.getString("cin"));
                rec.setNom(rs.getString("nom"));
                rec.setPrenom(rs.getString("prenom"));
                rec.setEmail(rs.getString("email"));
                rec.setPhoneNum(rs.getInt("phoneNum"));
               
    
     
      list.add(rec);
    }
    }catch (SQLException ex){
    ex.getMessage(); 
    } 
    return list;
    }

//*******************************************************************************************
    public Employees getEmployeeById(int id) {
    Employees emp = null;
    try {
        String query = "SELECT * FROM employees WHERE cin = ?";
        PreparedStatement ps = MyConnection.getinstance().getCnx().prepareStatement(query);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            emp = new Employees();
            emp.setId(rs.getInt(1));
            emp.setCin(rs.getString("cin"));
            emp.setNom(rs.getString("nom"));
            emp.setPrenom(rs.getString("prenom"));
            emp.setEmail(rs.getString("email"));
            emp.setPhoneNum(rs.getInt("phoneNum"));
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return emp;
}
    //*********************************************************************
  public boolean validerCin(String cin) {
    
    if (cin == null || cin.length() != 8) {
        return false;
    }
    for (int i = 0; i < cin.length(); i++) {
        if (!Character.isDigit(cin.charAt(i))) {
            return false;
        }
    }
    
    return true;
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
    
     public boolean validerNom(String s){
    Pattern p = Pattern.compile("[a-zA-Z]+");
    Matcher m = p.matcher(s);
    if (m.find() && !m.group().equals(s)){
        return true;
    }
    else {
        return false;
    }
}

       
      public boolean validerPrenom(String s){
    Pattern p = Pattern.compile("[a-zA-Z]+");
    Matcher m = p.matcher(s);
    if (m.find() && !m.group().equals(s)){
        return true;
    }
    else {
        return false;
    }
}


  //     public boolean validerPhonenum(int s) {
    //     String num = String.valueOf(s); 
  //  if (num.length() != 8) { 
   //     return false; 
  //  } else {
    //    
      //  return true;
 //   }
 // }
      
      
      public boolean validerPhonenum(int s) {
       if (s >= 20000000 && s <= 99999999){
            return true;
    } else {
        return false;
    }
      
      }

      
      
      //****************************************
      
      public Employees get_employees(int i) {
        Employees e = new Employees();
        String requete = "select * from employees where id="+i;
        try {
            PreparedStatement ps = cnx2.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {


                e.setId(rs.getInt("id"));
                e.setCin(rs.getString("cin"));
                e.setNom(rs.getString("nom"));
                e.setPrenom(rs.getString("prenom"));
                e.setEmail(rs.getString("email"));
                e.setPhoneNum(rs.getInt("phoneNum"));
               
                

            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return e;

    }
    
}
