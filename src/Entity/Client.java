/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author MediaCenter Zaghouan
 */
public class Client {
    private int id;
    private Division division_id;
    private String name;
    private int telephone;
    private String email;
    private String ville;
    private String code_postal;
    private String cin;
    private String date_ajout;

    public Client() {
    }

    public Client(int id, Division division_id, String name, int telephone, String email, String ville, String code_postal, String cin, String date_ajout) {
        this.id = id;
        this.division_id = division_id;
        this.name = name;
        this.telephone = telephone;
        this.email = email;
        this.ville = ville;
        this.code_postal = code_postal;
        this.cin = cin;
        this.date_ajout = date_ajout;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Division getDivision_id() {
        return division_id;
    }

    public void setDivision_id(Division division_id) {
        this.division_id = division_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getCode_postal() {
        return code_postal;
    }

    public void setCode_postal(String code_postal) {
        this.code_postal = code_postal;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getDate_ajout() {
        return date_ajout;
    }

    public void setDate_ajout(String date_ajout) {
        this.date_ajout = date_ajout;
    }

    @Override
    public String toString() {
        return "Client{" + "id=" + id + ", division_id=" + division_id + ", name=" + name + ", telephone=" + telephone + ", email=" + email + ", ville=" + ville + ", code_postal=" + code_postal + ", cin=" + cin + ", date_ajout=" + date_ajout + '}';
    }
    
    
    
}
