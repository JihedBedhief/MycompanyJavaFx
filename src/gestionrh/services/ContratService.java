/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionrh.services;

import gestionrh.entities.Contrat;
import gestionrh.entities.Employees;
import gestionrh.utils.MyConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author derou
 */
public class ContratService {
     Connection cnx2;
    public ContratService(){
        cnx2 = MyConnection.getinstance().getCnx();
    }
     public void ajouter_contrat(Contrat contrat) {
    try {
        String requete1 = "INSERT INTO contrat (type, salaire, datedebut, datefin, emp) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement pst = MyConnection.getinstance().getCnx().prepareStatement(requete1);
        pst.setString(1, contrat.getType());
        pst.setDouble(2, contrat.getSalaire());
        pst.setDate(3, new java.sql.Date(contrat.getDatedebut().getTime()));
        pst.setDate(4, new java.sql.Date(contrat.getDatefin().getTime()));
        pst.setInt(5, contrat.getEmp().getId());
        pst.executeUpdate();
        System.out.println("Contrat ajouté !");

    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}

     
    
     
     
     public void modifier_Contrat(Contrat r, String type, double salaire, Date datedebut, Date datefin, Employees emp) {
    try {
        String requete = "UPDATE contrat SET type = ?, salaire = ?, datedebut = ?, datefin = ?, emp = ? WHERE id = ?";
        PreparedStatement pst = MyConnection.getinstance().getCnx().prepareStatement(requete);
        pst.setString(1, type);
        pst.setDouble(2, salaire);
        pst.setDate(3, datedebut);
        pst.setDate(4, datefin);
        pst.setInt(5, emp.getId());
        pst.executeUpdate();
        System.out.println("Contrat modifié !");
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
}

public void supprimer_Contrat(Contrat r) {
    try {
        String requete = "DELETE FROM contrat WHERE id = ?";
        PreparedStatement pst = MyConnection.getinstance().getCnx().prepareStatement(requete);
        pst.setInt(1, r.getId());
        pst.executeUpdate();
        System.out.println("Contrat supprimé !");
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
}

public List<Contrat> listerContrats() {
    List<Contrat> myList = new ArrayList<>();
    try {
        String requete = "SELECT * FROM contrat";
        Statement st = MyConnection.getinstance().getCnx().createStatement();
        ResultSet rs = st.executeQuery(requete);
        while (rs.next()) {
            Contrat rec = new Contrat();
            rec.setId(rs.getInt("id"));
            rec.setType(rs.getString("type"));
            rec.setSalaire(rs.getDouble("salaire"));
            rec.setDatedebut(rs.getDate("datedebut"));
            rec.setDatefin(rs.getDate("datefin"));
            int empId = rs.getInt("emp");
            Employees emp = getEmployeeById(empId);
            rec.setEmp(emp);
            myList.add(rec);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return myList;
}

private Employees getEmployeeById(int empId) {
    Employees emp = null;
    try {
        String requete = "SELECT * FROM employees WHERE id = ?";
        PreparedStatement pst = MyConnection.getinstance().getCnx().prepareStatement(requete);
        pst.setInt(1, empId);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            emp = new Employees();
            emp.setId(rs.getInt("id"));
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


public List<Contrat> listerContratsparType(String type) {
    List<Contrat> myList = new ArrayList<>();
    try (PreparedStatement ps = MyConnection.getinstance().getCnx().prepareStatement(
            "SELECT * FROM contrat WHERE type = ?")) {
        ps.setString(1, type);
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Contrat rec = new Contrat();
                rec.setSalaire(rs.getDouble("salaire"));
                rec.setDatedebut(rs.getDate("datedebut"));
                rec.setDatefin(rs.getDate("datefin"));
                int empId = rs.getInt("emp");
                Employees emp = getEmployeeById(empId);
                rec.setEmp(emp);
                myList.add(rec);
            }
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return myList;
}


public List<Contrat> listerContratsparEmp(String nom) {
    List<Contrat> myList = new ArrayList<>();
    try (PreparedStatement ps = MyConnection.getinstance().getCnx().prepareStatement(
            "SELECT * FROM contrat c INNER JOIN employees e ON c.emp = e.id WHERE e.nom = ?")) {
        ps.setString(1, nom);
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Contrat rec = new Contrat();
                rec.setType(rs.getString("type"));
                rec.setSalaire(rs.getDouble("salaire"));
                rec.setDatedebut(rs.getDate("datedebut"));
                rec.setDatefin(rs.getDate("datefin"));
               // rec.setEmp(rs.getInt("emp_id"));
                myList.add(rec);
            }
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return myList;
}


     
  //******************************************************************************************************
     
  public boolean employeeExiste(int id) {
    boolean existe = false;
    try {
        String requeteSelectionEmploye = "SELECT * FROM employees WHERE id = ?";
        PreparedStatement preparedStatement = MyConnection.getinstance().getCnx().prepareStatement(requeteSelectionEmploye);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            existe = true;
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return existe;
}   
 
  //******************************************************************************************************
  

  //******************************************************************************************************
      public static ObservableList<String> RecupCombo(){
             
             
    ObservableList<String> list = FXCollections.observableArrayList();
    
       java.sql.Connection cnx;
     cnx = MyConnection.getinstance().getCnx();
          String sql = "SELECT emp FROM contrat";
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
 
     
   //**********************************************************************************
       public  ObservableList<Contrat> RecupBase(){
             
    ObservableList<Contrat> list = FXCollections.observableArrayList();
    
       java.sql.Connection cnx;
     cnx = MyConnection.getinstance().getCnx();
          String sql = "select * from contrat";
    try {
       
        PreparedStatement st = (PreparedStatement) cnx.prepareStatement(sql);

    ResultSet rs = st.executeQuery();
    Contrat rec=new Contrat();
    while (rs.next()){
      rec.setId(rs.getInt(1));
                rec.setSalaire(rs.getDouble("salaire"));
                rec.setDatedebut(rs.getDate("datedebut"));
                rec.setDatefin(rs.getDate("datefin"));
                int empId = rs.getInt("emp");
                Employees emp = getEmployeeById(empId);
                rec.setEmp(emp);
               
    
     
      list.add(rec);
    }
    }catch (SQLException ex){
    ex.getMessage(); 
    } 
    return list;
    }
       
       public ObservableList<Contrat> listerContrat() {
    ObservableList<Contrat> myList = FXCollections.observableArrayList();
    try {
        String requete = "SELECT * FROM contrat";
        Statement st = MyConnection.getinstance().getCnx().createStatement();
        ResultSet rs = st.executeQuery(requete);
        while (rs.next()) {
            Contrat rec = new Contrat();
            rec.setId(rs.getInt("id"));
            rec.setType(rs.getString("type"));
            rec.setSalaire(rs.getDouble("salaire"));
            rec.setDatedebut(rs.getDate("datedebut"));
            rec.setDatefin(rs.getDate("datefin"));
            int empId = rs.getInt("emp");
            Employees emp = getEmployeeById(empId);
            rec.setEmp(emp);
            myList.add(rec);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return myList;
}
       
       //******************************************************
       
       
      public boolean validerDate(LocalDate dateDebut, LocalDate dateFin) {
    if (dateDebut.isBefore(dateFin)) {
        return true; 
    } else {
        return false; 
    }
}
       
       
    public boolean validerSalaire(double salaire) {
        
       if (salaire >= 400 && salaire <= 12000) {
        return true;
    } else {
        return false;
    }
}


       
     
    
}
