/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionrh.entities;

/**
 *
 * @author derou
 */
public class Employees {
    private int id;
    private String cin;
    private String nom;
    private String prenom;
    private String email;
    private int phoneNum;

   

    public Employees() {
    }

    public Employees(String cin, String nom, String prenom, String email, int phoneNum) {
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.phoneNum = phoneNum;
    }

    public Employees(int id, String cin, String nom, String prenom, String email, int phoneNum) {
        this.id = id;
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.phoneNum = phoneNum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(int phoneNum) {
        this.phoneNum = phoneNum;
    }
    

    @Override
    public String toString() {
        return "Employees{" +" cin=" + cin + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", phoneNum=" + phoneNum + '}';
    }
    
    
}
