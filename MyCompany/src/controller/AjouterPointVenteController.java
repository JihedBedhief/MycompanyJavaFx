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
import java.sql.SQLException;
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
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import services.PointVenteService;

/**
 * FXML Controller class
 *
 * @author Abderrazekbenhamouda
 */
public class AjouterPointVenteController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private TextField region;
    @FXML
    private TextField ville;
    @FXML
    private TextField email;
    @FXML
    private TextField code;
    @FXML
    private TextField tel;
    
    PointVenteService pv = new PointVenteService();

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
            tel.setTextFormatter(new TextFormatter<Integer>(new IntegerStringConverter(), 0, integerFilter));
            code.setTextFormatter(new TextFormatter<Integer>(new IntegerStringConverter(), 0, integerFilter));

        
    }    

    @FXML
    private void Ajouter(ActionEvent event) throws SQLException {
        
        if (nom.getText().equals("") || region.getText().equals("") || email.getText().equals("") || ville.getText().equals("")|| tel.getText().equals("0") || code.getText().equals("0")){
       
           Alert alert = new Alert(Alert.AlertType.ERROR, "vérifier votre champs", ButtonType.OK);
           alert.showAndWait();
        
       
       }
                else if (pv.validerEmail(email.getText())) {
                               Alert alert = new Alert(Alert.AlertType.ERROR, "valider Email", ButtonType.OK);
                               alert.showAndWait();
             }
       else{
        
            
            PointVente p = new PointVente();
            p.setCodePostal(Integer.parseInt(code.getText()));
            p.setTel(Integer.parseInt(tel.getText()));
            p.setEmail(email.getText());
            p.setRegion(region.getText());
            p.setVille(ville.getText());
            p.setNom(nom.getText());
            
            pv.addPointVente(p);
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Ajouter Avec Succés", ButtonType.OK);
            alert.showAndWait();
            
            Parent root;
               try {
              root = FXMLLoader.load(getClass().getResource("/gui/AfficherPointVente.fxml"));
               Stage myWindow = (Stage) nom.getScene().getWindow();
               Scene sc = new Scene(root);
               myWindow.setScene(sc);
               myWindow.setTitle("page name");
                            //myWindow.setFullScreen(true);
               myWindow.show();
               } catch (IOException ex) {
               Logger.getLogger(AjouterPointVenteController.class.getName()).log(Level.SEVERE, null, ex);
               }
            
        
        
        
        
        
        
        }
        
        
        
    }
    
}
