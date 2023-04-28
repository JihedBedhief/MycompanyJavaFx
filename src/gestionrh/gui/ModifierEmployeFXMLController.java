  /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionrh.gui;

import gestionrh.entities.Employees;
import static gestionrh.gui.ListeEmployeeFXMLController.ide;
import gestionrh.services.EmployeesService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLDataException;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

/**
 * FXML Controller class
 *
 * @author derou
 */
public class ModifierEmployeFXMLController implements Initializable {
    EmployeesService pv =new EmployeesService();

    @FXML
    private TextField cinbtn;
    @FXML
    private TextField nombtn;
    @FXML
    private TextField emailbtn;
    @FXML
    private Button modifierbtn;
    @FXML
    private TextField prenombtn;
    @FXML
    private TextField phonenumbtn;
    @FXML
    private Button retourbtn;
    @FXML
    private ComboBox<String> formationabtn;
    @FXML
    private ComboBox<String> formationpbtn;
    @FXML
    private ComboBox<String> experiencebtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          
        // TODO
         UnaryOperator<TextFormatter.Change> integerFilter = change -> {
            String newText = change.getControlNewText();
            if (newText.matches("-?([1-9][0-9]*)?")) { 
                return change;
            }
            return null;
        };
            phonenumbtn.setTextFormatter(new TextFormatter<Integer>(new IntegerStringConverter(),0, integerFilter));
          
            Employees e = pv.get_employees(ListeEmployeeFXMLController.ide);
            
            cinbtn.setText(e.getCin());
            nombtn.setText(e.getNom());
            prenombtn.setText(e.getPrenom());
            emailbtn.setText(e.getEmail());
            formationabtn.setValue(e.getFormationA());
            formationpbtn.setValue(e.getFormationP());
            experiencebtn.setValue(e.getExperience());
            phonenumbtn.setText(String.valueOf(e.getPhoneNum()));
         
            
        //**************************************************************    
         listeform();   
    }    

  @FXML
  private void Modifier(ActionEvent event) throws IOException {
       EmployeesService rec = new EmployeesService();
//      if (cinbtn.getText().equals("") || nombtn.getText().equals("") || prenombtn.getText().equals("") || emailbtn.getText().equals("")|| formationabtn.getSelectionModel().isEmpty() || formationpbtn.getSelectionModel().isEmpty()  || experiencebtn.getSelectionModel().isEmpty() || phonenumbtn.getText().equals("")){
//       
//           Alert alert = new Alert(Alert.AlertType.ERROR, "vérifier votre champs", ButtonType.OK);
//           alert.showAndWait();
//           
//       
//       }
//                else if (rec.validerEmail(emailbtn.getText())) {
//                               Alert alert = new Alert(Alert.AlertType.ERROR, "valider Email", ButtonType.OK);
//                               alert.showAndWait();
//             }
//                else if (rec.validerNom(nombtn.getText())) {
//                               Alert alert = new Alert(Alert.AlertType.ERROR, "valider Nom", ButtonType.OK);
//                               alert.showAndWait();
//             }
//                else if (rec.validerPrenom(prenombtn.getText())) {
//                               Alert alert = new Alert(Alert.AlertType.ERROR, "valider Prenom", ButtonType.OK);
//                               alert.showAndWait();
//             }
//                else if (!rec.validerCin(cinbtn.getText())) {
//                               Alert alert = new Alert(Alert.AlertType.ERROR, "valider Cin", ButtonType.OK);
//                               alert.showAndWait();
//             }
//                else if (!rec.validerPhonenum(Integer.parseInt(phonenumbtn.getText()))) {
//                               Alert alert = new Alert(Alert.AlertType.ERROR, "valider le numero de telephone", ButtonType.OK);
//                               alert.showAndWait();
//             }
//                  
//     else {
       
        int id = ListeEmployeeFXMLController.ide;
        String cin = cinbtn.getText();
        String nom = nombtn.getText();
        String prenom = prenombtn.getText();
        String email = emailbtn.getText();
        String formationa = formationabtn.getValue().toString();
        String formationp = formationpbtn.getValue().toString();
        String experience = experiencebtn.getValue().toString();       
        int phone = Integer.parseInt(phonenumbtn.getText());

        Employees R = new Employees(id, cin, nom, prenom, email, formationa, formationp, experience, phone);
        rec.modifier_Employee(R, cin, nom, prenom, email, formationa, formationp, experience, phone);

        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Modification effectuée avec succès", ButtonType.OK);
        alert.showAndWait();

        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ListeEmployeeFXML.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Liste des employés");
        stage.show();
  //  }
}


        
        
        

    

    @FXML
    private void retour(ActionEvent event) throws IOException {
        
        Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow(); 
                    stage.close();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("ListeEmployeeFXML.fxml")));       
                    stage.setScene(scene);
                    stage.setTitle("modifier");
                    
                    stage.show(); 
    }
    
    
    public void listeform(){
         List<String> formA = Arrays.asList(
            "Licence", "Master", "Ingénierie", "Doctorat");
            
        formationabtn.setItems(FXCollections.observableArrayList(formA));
        
       List<String> formP = Arrays.asList(
           "Professionnel", "Superieur");      
            formationpbtn.setItems(FXCollections.observableArrayList(formP));
       
          
         List<String> exper = Arrays.asList(
     
            "Il n'a aucune experience", "Il a moins d'un an d'experience","Il a plus d'un an d'experience");
            
        experiencebtn.setItems(FXCollections.observableArrayList(exper));
        
    }
    
}
