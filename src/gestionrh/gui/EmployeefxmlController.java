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
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
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

    
     List<String> formA;
     List<String> formP;
     List<String> exper;
    /**
     * Initializes the controller class.
     * 
     */
    EmployeesService rec= new EmployeesService();
    @FXML
    private ComboBox<String> formationabtn;
    @FXML
    private ComboBox<String> formationpbtn;
    @FXML
    private ComboBox<String> experiencebtn;
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
       // List<String> formA = Arrays.asList(
       formA = Arrays.asList(
            "Licence", "Master", "Ingénierie", "Doctorat");
            
        formationabtn.setItems(FXCollections.observableArrayList(formA));
        
      // List<String> formP = Arrays.asList(
            formP = Arrays.asList("Professionnel", "Superieur");      
            formationpbtn.setItems(FXCollections.observableArrayList(formP));
       
          
       //  List<String> exper = Arrays.asList(
      exper = Arrays.asList(
            "Il n'a aucune experience", "Il a moins d'un an d'experience","Il a plus d'un an d'experience");
            
        experiencebtn.setItems(FXCollections.observableArrayList(exper)); 
        
         
    }    

    @FXML
    private void ajoute(ActionEvent event) throws IOException {
        
        
         if (cinbtn.getText().equals("") || nimbtn.getText().equals("") || prenombtn.getText().equals("") || emailbtn.getText().equals("")|| formationabtn.getSelectionModel().isEmpty() || formationpbtn.getSelectionModel().isEmpty()  || experiencebtn.getSelectionModel().isEmpty() || phonenumbtn.getText().equals("")){
       
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
         String formationa= formationabtn.getValue();
         String formationp= formationpbtn.getValue();
         String experience= experiencebtn.getValue();         
         Integer phonenum=Integer.parseInt(phonenumbtn.getText());
         
         
         Employees R;
           R = new Employees(cin,nom,prenom,email,formationa,formationp,experience,phonenum);
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
    
  //  public void afiche(){
  //      if (formA.equals("Licence")){
  //          formP = Arrays.asList("Fondamentale", "Appliqué");      
  //          formationpbtn.setItems(FXCollections.observableArrayList(formP));
  //      
  //      }else if(formA.equals("Master")){
  //          formP = Arrays.asList("Professionnel", "Recherche");      
  //         formationpbtn.setItems(FXCollections.observableArrayList(formP));
  //    
  //      }else if(formA.equals("Ingénierie")){
  //          formP = Arrays.asList("Professionnel", "Superieur");      
  //          formationpbtn.setItems(FXCollections.observableArrayList(formP));
  //          }else {
  //          formP = Arrays.asList("Professionnel", "Superieur");      
  //          formationpbtn.setItems(FXCollections.observableArrayList(formP));
  //      }
    // List<String> formP = Arrays.asList(
  //  }
   
    
}
