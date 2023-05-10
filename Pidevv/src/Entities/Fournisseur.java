
package Entities;


public class Fournisseur {
  private int Id;
  private String Nom;
 private String Type;
 private int Num;
 private String Adresse ;
 private String Email;
 public Fournisseur () {
    }

    public Fournisseur(int Id, String Nom, String Type, int Num,String Adresse ,String Email) {
        this.Id = Id;
        this.Nom = Nom;
        this.Type = Type;
        this.Num = Num;
        this.Adresse=Adresse;
        this.Email = Email;
        
    }

   public Fournisseur( String Nom, String Type, int Num,String Adresse ,String Email) {
       
        this.Nom = Nom;
        this.Type = Type;
        this.Num = Num;
        this.Adresse=Adresse;
        this.Email = Email;
    }

    public int getId() {
        return Id;
    }

    public String getNom() {
        return Nom;
    }

    public String getType() {
        return Type;
    }

    public int getNum() {
        return Num;
    }

    public String getAdresse() {
        return Adresse;
    }

    public String getEmail() {
        return Email;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public void setNum(int Num) {
        this.Num = Num;
    }

    public void setAdresse(String Adresse) {
        this.Adresse = Adresse;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    @Override
    public String toString() {
        return "Fournisseur{" + "Id=" + Id + ", Nom=" + Nom + ", Type=" + Type + ", Num=" + Num + ", Adresse=" + Adresse + ", Email=" + Email + '}';
    }
    
    
}
