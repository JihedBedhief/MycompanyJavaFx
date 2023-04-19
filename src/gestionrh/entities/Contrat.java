/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionrh.entities;

import java.util.Date;

/**
 *
 * @author derou
 */
public class Contrat implements Comparable<Contrat> {
    private int id;
    private String type;
    private double salaire;
    private Date datedebut;
    private Date datefin;
    private Employees emp;

    public Contrat() {
    }

    public Contrat(String type, double salaire, Date datedebut, Date datefin, Employees emp) {
        this.type = type;
        this.salaire = salaire;
        this.datedebut = datedebut;
        this.datefin = datefin;
        this.emp = emp;
    }

    public Contrat(int id, String type, double salaire, Date datedebut, Date datefin, Employees emp) {
        this.id = id;
        this.type = type;
        this.salaire = salaire;
        this.datedebut = datedebut;
        this.datefin = datefin;
        this.emp = emp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getSalaire() {
        return salaire;
    }

    public void setSalaire(double salaire) {
        this.salaire = salaire;
    }

    public Date getDatedebut() {
        return datedebut;
    }

    public void setDatedebut(Date datedebut) {
        this.datedebut = datedebut;
    }

    public Date getDatefin() {
        return datefin;
    }

    public void setDatefin(Date datefin) {
        this.datefin = datefin;
    }

    public Employees getEmp() {
        return emp;
    }

    public void setEmp(Employees emp) {
        this.emp = emp;
    }
    
    
    

    @Override
    public String toString() {
        return "Contrat{" + "type=" + type + ", salaire=" + salaire + ", datedebut=" + datedebut + ", datefin=" + datefin + ", emp=" + emp + '}';
    }

    @Override
    public int compareTo(Contrat o) {
       return this.emp.getId()-o.emp.getId();
    }
    
    
    
    
}
