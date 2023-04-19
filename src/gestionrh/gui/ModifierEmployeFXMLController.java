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
import java.util.function.UnaryOperator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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
    EmployeesService rec =new EmployeesService();

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
            phonenumbtn.setTextFormatter(new TextFormatter<Integer>(new IntegerStringConverter(), 0, integerFilter));
          
            Employees e = rec.get_employees(ListeEmployeeFXMLController.ide);
            
            cinbtn.setText(e.getCin());
            nombtn.setText(e.getNom());
            prenombtn.setText(e.getPrenom());
            emailbtn.setText(e.getEmail());
            phonenumbtn.setText(String.valueOf(e.getPhoneNum()));
           
    }    

    @FXML
    private void Modifier(ActionEvent event) throws IOException {
        
         String cin= cinbtn.getText();
         String nom= nombtn.getText();
         String prenom= prenombtn.getText();
         String email= emailbtn.getText();         
         Integer phonenum=Integer.parseInt(phonenumbtn.getText());
         Employees R;
         Employees rr = rec.listerEmployeesparNom(nom);
         int id = rr .getId();
         
           R = new Employees(id,cin,nom,prenom,email,phonenum);
           
           rec.modifier_Employee(R, cin, nom, prenom, email, phonenum);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("My  Company :: Success Message");
                alert.setHeaderText(null);
                alert.setContentText("Employe modifié");
                alert.showAndWait();   
                
                
                Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow(); 
                    stage.close();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("ListeEmployeeFXML.fxml")));       
                    stage.setScene(scene);
                    stage.setTitle("modifier");
                    
                    stage.show(); 
    }
    
}
