
package services;
import Entity.Fournisseur;
import java.util.List;

 
public interface IservicesFournisseur {
    public void ajouterAchat (Fournisseur f);
    public List<Fournisseur> afficherFournisseur();
    public void modifierFournisseur(int Id,Fournisseur f) ;
    public void supprimerFournisseur(int Id);
    
}
