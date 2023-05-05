/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Entity.PointVente;
import Entity.Vente;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLDataException;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import org.controlsfx.control.Notifications;
import services.PointVenteService;
import services.VenteService;

/**
 * FXML Controller class
 *
 * @author Abderrazekbenhamouda
 */
public class ModifierVenteController implements Initializable {

    @FXML
    private TextField produit;
    @FXML
    private TextField client;
    @FXML
    private TextField quantite;
    @FXML
    private TextField prix;
    @FXML
    private TextField taxe;
    @FXML
    private ChoiceBox<String> checBox;
    

    PointVenteService pv = new PointVenteService();
    VenteService vs = new VenteService();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                 UnaryOperator<TextFormatter.Change> integerFilter = change -> {
            String newText = change.getControlNewText();
            if (newText.matches("-?([1-9][0-9]*)?")) { 
                return change;
            }
            return null;
        };
            quantite.setTextFormatter(new TextFormatter<Integer>(new IntegerStringConverter(), 0, integerFilter));
            List<PointVente> l;

        try {
            l = pv.getAllPointVente();
            l.forEach((c) -> {
                checBox.getItems().addAll(c.getNom());
              });
        } catch (SQLDataException ex) {
            Logger.getLogger(AjouterVenteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Vente vmodifier = vs.get_Vente(AfficherVenteController.idVente);
        
        prix.setText(String.valueOf(vmodifier.getPrix_unite()));
        
        taxe.setText(String.valueOf(vmodifier.getTaxe()));
        client.setText(vmodifier.getClient());
        produit.setText(vmodifier.getProduit());
        quantite.setText(String.valueOf(vmodifier.getQuantite()));
        
    }    

    @FXML
    private void Ajouter(ActionEvent event) throws SQLDataException {
          if (produit.getText().equals("") || client.getText().equals("")|| checBox.getValue().equals("") || prix.getText().equals("") || taxe.getText().equals("") || quantite.getText().equals("0")){
       
           Alert alert = new Alert(Alert.AlertType.ERROR, "vérifier votre champs", ButtonType.OK);
           alert.showAndWait();
        
       
       }
                else if (!vs.estDouble(prix.getText())) {
                               Alert alert = new Alert(Alert.AlertType.ERROR, "valider Prix", ButtonType.OK);
                               alert.showAndWait();
             }
                                
             
                                                else if (!vs.estDouble(taxe.getText())) {
                               Alert alert = new Alert(Alert.AlertType.ERROR, "valider Taxe", ButtonType.OK);
                               alert.showAndWait();
             }
       else{
        
            
            Vente v = new Vente();
            v.setClient(client.getText());
            v.setProduit(produit.getText());
            v.setPrix_unite(Double.parseDouble(prix.getText()));
            
            v.setTaxe(Double.parseDouble(taxe.getText()));
            v.setPtvente((pv.getPointVenteByNom(checBox.getValue())).getId());
            v.setQuantite(Integer.parseInt(quantite.getText()));
            v.setDate(Date.valueOf(LocalDate.now()));
            v.setId(AfficherVenteController.idVente);
            vs.ModifierVente(v);
            
            Notifications.create().title("Sucess").text("Modifier Avec Succés").position(Pos.BOTTOM_RIGHT).showInformation();
            
            Parent root;
               try {
              root = FXMLLoader.load(getClass().getResource("/gui/AfficherVente.fxml"));
               Stage myWindow = (Stage) prix.getScene().getWindow();
               Scene sc = new Scene(root);
               myWindow.setScene(sc);
               myWindow.setTitle("page name");
                            //myWindow.setFullScreen(true);
               myWindow.show();
               } catch (IOException ex) {
               Logger.getLogger(ModifierPointVenteController.class.getName()).log(Level.SEVERE, null, ex);
               }
            
        
        
        
        
        
        
        }
        
        
    
}
    
}
