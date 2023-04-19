/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionrh.gui;

import gestionrh.entities.Contrat;
import gestionrh.entities.Employees;
import gestionrh.services.ContratService;
import gestionrh.services.EmployeesService;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author derou
 */
public class ModifiercontratController implements Initializable {
    EmployeesService es=new EmployeesService();
    ContratService rec =new ContratService();

    @FXML
    private ComboBox<Contrat> Empcombo;
    @FXML
    private ComboBox<String> Typecombo;
    @FXML
    private TextField salairebtn;
    @FXML
    private DatePicker datedebut;
    @FXML
    private DatePicker datefin;
    @FXML
    private Button modifierbtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void modifier(ActionEvent event) {
     
    
    }
    
}
