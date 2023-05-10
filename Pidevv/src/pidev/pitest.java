/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

import Entities.Achat;
import Services.ServiceAchat;
import java.sql.Date;
  

import Entities.Fournisseur;
import Services.ServiceFournisseur;
/**
 *
 * @author Hammouda
 */
public class pitest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       ServiceAchat Sa=new ServiceAchat();
       Achat a=new Achat("",150,50,"20-20-2000",40);
      System.out.println(Sa.afficherAchat());
      Sa.ajouterAchat(a);
       //Sa.modifierAchat(40 ,a);
       //Sa.supprimerAchat(40);
      //ServiceFournisseur Sf=new ServiceFournisseur();
     //Fournisseur f=new Fournisseur("iheb","manager",258,"ariana","iheb@gmail.com");
      //System.out.println(Sf.afficherFournisseur());
        //Sf.ajouterFournisseur(f);
       //Sf.modifierFournisseur(1,f);
      //Sf.supprimerFournisseur(8);
    }
    
}
