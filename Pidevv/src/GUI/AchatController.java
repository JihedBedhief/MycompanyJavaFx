/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Achat;
import Services.ServiceAchat;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import static java.time.zone.ZoneRulesProvider.refresh;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class AchatController implements Initializable {
     Alert alert = new Alert(Alert.AlertType.NONE); 
           public Achat f;
          public List<Achat> listeFr;
    private ServiceAchat Sf= new ServiceAchat();

    @FXML
    private Button ajouter1;
    @FXML
    private Button clear;
    @FXML
    private Button modifier;
    @FXML
    private Button ajouter;
    @FXML
    private TextField tfproduit;
    @FXML
    private TextField tfquantite;
    @FXML
    private TextField tfprix;
    @FXML
    private TextField tfdate;
    @FXML
    private TextField tftaxe;
    
     private Statement st;
    private ResultSet rs;
    private Connection cnx;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         refresh();
    }  
        // TODO
    
          public String controleDeSaisie(){
        //pattern titre
        Pattern pattern = Pattern.compile("^[A-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[A-Z0-9_!#$%&'*+/=?`{|}~^-]+↵\n" +
				")*@[A-Z0-9-]+(?:\\.[A-Z0-9-]+)*$", Pattern.CASE_INSENSITIVE);
        
        String erreur="";//liste des erreurs vide
        if(tfproduit.getText().trim().isEmpty()){
            erreur+="-Produit vide\n"; //message 
        }
        if(tfquantite.getText().trim().isEmpty()){
            erreur+="-Quantite vide\n";//message 
        }
        if(tfprix.getText().trim().isEmpty()){
            erreur+="-Prix vide\n";
        }
        if(tfdate.getText().trim().isEmpty()){
            erreur+="-Date vide\n";
        }
        if(tftaxe.getText().trim().isEmpty()){
            erreur+="-Taxe vide\n";
        }

        return erreur;
    }

    @FXML
    private void ajouter(ActionEvent event) {
           String erreur=controleDeSaisie();
        if(erreur.length()>0){
            Alert alert=new Alert(Alert.AlertType.WARNING); //alert
            alert.setTitle("invalide");
            alert.setContentText(erreur);
            alert.showAndWait();
        }
        else{
            Achat f=new Achat();
            
    
           f.setProduit(tfproduit.getText());
    f.setQuantite(Float.valueOf(tfquantite.getText())); // Ajout de la parenthèse fermante
    f.setPrix(Float.valueOf(tfprix.getText())); // Ajout de la parenthèse fermante
    f.setDateAchat(tfdate.getText());
    f.setTaxe(Float.valueOf(tftaxe.getText())); // Ajout de la parenthèse fermante
    Sf.ajouterAchat(f);

    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Succès");
    alert.setContentText("Nouvel achat ajouté !");
    alert.showAndWait();
}
    }
         private void reset() {

        tfproduit.setText(null);
        tfquantite.setText(null);
        tfprix.setText(null);
        tfdate.setText(null);
        tftaxe.setText(null);
       

    }
         
         public void setTextFields(Achat NewAchat) {
        tfproduit.setText(NewAchat.getProduit());
        tfquantite.setText(Float.toString(NewAchat.getQuantite()));
        tfprix.setText(Float.toString(NewAchat.getPrix()));
        tfdate.setText(NewAchat.getDateAchat());
        tftaxe.setText(Float.toString(NewAchat.getTaxe()));        
}
       
       

    @FXML
    private void clear(ActionEvent event) {
         reset();
    }
    

    @FXML
    private void modifier(ActionEvent event) {
  if (tfproduit.getText().isEmpty() || tfquantite.getText().isEmpty() ||tfprix.getText().isEmpty() ||tfdate.getText().isEmpty() ||tftaxe.getText().isEmpty()){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs  !");
            alert.showAndWait();
        } else {

            alert.setAlertType(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText(null);
            alert.setContentText("Voulez-vous vraiment modifier cet achat ?");
            Optional<ButtonType> action = alert.showAndWait();
             if (action.get() == ButtonType.OK) {
                Achat ab = new Achat();
                ab.setProduit(tfproduit.getText());
                 ab.setQuantite(Float.valueOf(tfquantite.getText())); // Ajout de la parenthèse fermante
    ab.setPrix(Float.valueOf(tfprix.getText())); // Ajout de la parenthèse fermante
    ab.setDateAchat(tfdate.getText());
    ab.setTaxe(Float.valueOf(tftaxe.getText())); // Ajout de la parenthèse fermante
                Sf.modifierAchat(ab);
                ObservableList<Achat> items = FXCollections.observableArrayList();
                List<Achat> listabb = Sf.afficherAchat();
           
        }
                
    }
    }

    @FXML
    private void back(ActionEvent event) {
               try {
            Parent page1 = FXMLLoader.load(getClass().getResource("Liste_1.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
}
