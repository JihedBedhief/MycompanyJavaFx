/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionrh.gui;

import gestionrh.entities.Employees;
import gestionrh.services.EmployeesService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author derou
 */
public class EmployeefxmlController implements Initializable {

    @FXML
    private TextField cinbtn;
    @FXML
    private TextField nimbtn;
    @FXML
    private TextField prenombtn;
    @FXML
    private TextField emailbtn;
    @FXML
    private TextField phonenumbtn;
    @FXML
    private Button ajoutebtn;
    @FXML
    private Button retourbtn;

    /**
     * Initializes the controller class.
     * 
     */
    EmployeesService rec= new EmployeesService();
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajoute(ActionEvent event) throws IOException {
        
        
         if (cinbtn.getText().equals("") || nimbtn.getText().equals("") || prenombtn.getText().equals("") || emailbtn.getText().equals("") || phonenumbtn.getText().equals("")){
       
           Alert alert = new Alert(Alert.AlertType.ERROR, "vérifier votre champs", ButtonType.OK);
           alert.showAndWait();
           
       
       }
                else if (rec.validerEmail(emailbtn.getText())) {
                               Alert alert = new Alert(Alert.AlertType.ERROR, "valider Email", ButtonType.OK);
                               alert.showAndWait();
             }
                else if (rec.validerNom(nimbtn.getText())) {
                               Alert alert = new Alert(Alert.AlertType.ERROR, "valider Nom", ButtonType.OK);
                               alert.showAndWait();
             }
                else if (rec.validerPrenom(prenombtn.getText())) {
                               Alert alert = new Alert(Alert.AlertType.ERROR, "valider Prenom", ButtonType.OK);
                               alert.showAndWait();
             }
                else if (!rec.validerCin(cinbtn.getText())) {
                               Alert alert = new Alert(Alert.AlertType.ERROR, "valider Cin", ButtonType.OK);
                               alert.showAndWait();
             }
                else if (!rec.validerPhonenum(Integer.parseInt(phonenumbtn.getText()))) {
                               Alert alert = new Alert(Alert.AlertType.ERROR, "valider le numero de telephone", ButtonType.OK);
                               alert.showAndWait();
             }
                
                
       else{
        
         
         String cin= cinbtn.getText();
         String nom= nimbtn.getText();
         String prenom= prenombtn.getText();
         String email= emailbtn.getText();         
         Integer phonenum=Integer.parseInt(phonenumbtn.getText());
         
         
         Employees R;
           R = new Employees(cin,nom,prenom,email,phonenum);
           rec.ajouter_Employees(R);
           Alert alert = new Alert(Alert.AlertType.INFORMATION, "Ajouter Avec Succés", ButtonType.OK);
            alert.showAndWait();
                
     
                 Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow(); 
                    //stage.close();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("ContratFXML.fxml")));       
                    stage.setScene(scene);
                    stage.setTitle("Ajouter un Contrat");
                    
                    
                }   
                    
                     }

    @FXML
    private void retour(ActionEvent event) throws IOException {
   
                    
        
                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow(); 
                    stage.close();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("ListeEmployeeFXML.fxml")));       
                    stage.setScene(scene);
                    stage.setTitle("Affichage");
                    
                    stage.show();  
    }
    
   
    
}
