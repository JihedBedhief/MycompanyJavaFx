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
public class User {
    
    private int id ; 
    private Abonnement abonnement_id;
    private String email;
    private String roles;

    private String password;
        private String matricule; 
            private String adresse ; 
                private String domaine;
                    private String pays ;



    private int telephone; 
        private String nom;

    private String statuts ; 
    private String image;


    
    public static User connecte;

    public User() {
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public User(int id, String nom, String image) {
        this.id = id;
        this.nom = nom;
        this.image = image;
    }

    public User(int id, Abonnement abonnement_id, String email, String roles, String password, String matricule, String adresse, String domaine, String pays, int telephone, String nom, String statuts, String image) {
        this.id = id;
        this.abonnement_id = abonnement_id;
        this.email = email;
        this.roles = roles;
        this.password = password;
        this.matricule = matricule;
        this.adresse = adresse;
        this.domaine = domaine;
        this.pays = pays;
        this.telephone = telephone;
        this.nom = nom;
        this.statuts = statuts;
        this.image = image;
    }

    
    
    
    public User(int id, Abonnement abonnement_id, String email, String roles, String password, String matricule, String adresse, String domaine, String pays, int telephone, String nom, String statuts) {
        this.id = id;
        this.abonnement_id = abonnement_id;
        this.email = email;
        this.roles = roles;
        this.password = password;
        this.matricule = matricule;
        this.adresse = adresse;
        this.domaine = domaine;
        this.pays = pays;
        this.telephone = telephone;
        this.nom = nom;
        this.statuts = statuts;
    }

    
 

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Abonnement getAbonnement_id() {
        return abonnement_id;
    }

    public void setAbonnement_id(Abonnement abonnement_id) {
        this.abonnement_id = abonnement_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public String getStatuts() {
        return statuts;
    }

    public void setStatuts(String statuts) {
        this.statuts = statuts;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getDomaine() {
        return domaine;
    }

    public void setDomaine(String domaine) {
        this.domaine = domaine;
    }

    public static User getConnecte() {
        return connecte;
    }

    public static void setConnecte(User connecte) {
        User.connecte = connecte;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", abonnement_id=" + abonnement_id + ", email=" + email + ", roles=" + roles + ", password=" + password + ", matricule=" + matricule + ", adresse=" + adresse + ", domaine=" + domaine + ", pays=" + pays + ", telephone=" + telephone + ", nom=" + nom + ", statuts=" + statuts + ", image=" + image + '}';
    }

   
    
    
}
