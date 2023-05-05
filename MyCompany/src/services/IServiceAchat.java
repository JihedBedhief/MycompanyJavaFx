
package services;
import Entity.Achat;
import java.util.List;

 
public interface IServiceAchat {
    public void ajouterAchat (Achat a);
    public List<Achat> afficherAchat();
    public void modifierAchat(Achat a) ;
    public void supprimerAchat(int Ida);
   
    
}



