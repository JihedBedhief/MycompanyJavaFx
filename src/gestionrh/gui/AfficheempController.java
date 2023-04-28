 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionrh.gui;

import gestionrh.entities.Employees;
import gestionrh.services.EmployeesService;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author derou
 */
public class AfficheempController implements Initializable {
    EmployeesService rec= new EmployeesService();

    @FXML
    private ComboBox<String> comboname;
    @FXML
    private TextField cinbtn;
    @FXML
    private TextField nombtn;
    @FXML
    private Button selectbtn;
    @FXML
    private TextField prenombtn;
    @FXML
    private TextField emailbtn;
    @FXML
    private TextField phonenumbtn;
    @FXML
    private Button supprimerbtn;
    @FXML
    private Button modifierbtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
      
       comboname.setItems(rec.RecupCombo());
       
        
                
    }    

    @FXML
    private Employees seclect(ActionEvent event) {
        Employees emp = new Employees();
        emp = rec.listerEmployeesparNom(comboname.getValue());
        cinbtn.setText(emp.getCin());
        nombtn.setText(emp.getNom());
        prenombtn.setText(emp.getPrenom());
        emailbtn.setText(emp.getEmail());
       
        int phoneNum = emp.getPhoneNum();
phonenumbtn.setText(String.valueOf(phoneNum));
        
    return emp;    
    }

    @FXML
    private void supprimer(ActionEvent event) {
        rec.supprimer_Employee(rec.listerEmployeesparNom(comboname.getValue()));
      
    }

    @FXML
    private void modifier(ActionEvent event) {
         String cin= cinbtn.getText();
         String nom= nombtn.getText();
         String prenom= prenombtn.getText();
         String email= emailbtn.getText();         
         Integer phonenum=Integer.parseInt(phonenumbtn.getText());
       //  rec.modifier_Employee(rec.listerEmployeesparNom(comboname.getValue()),cin,nom,prenom,email,phonenum);
        
    }
    
}
