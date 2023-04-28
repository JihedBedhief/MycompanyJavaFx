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
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
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

    private ComboBox<String> Empcombo;
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
    @FXML
    private Button retourbtn;
    @FXML
    private Label nomempbtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
         Contrat e = rec.get_contrat(ListeContratController.ide);
         
            nomempbtn.setText(e.getEmp().getNom());
            Typecombo.setValue(e.getType());
            salairebtn.setText(String.valueOf(e.getSalaire()));
//            datedebut.setd(String.valueOf(e.getDatedebut()));
//            datefin.setText(String.valueOf(e.getDatefin()));

        btncombo();
    }    

    @FXML
    private void modifier(ActionEvent event) {
        Contrat e = rec.get_contrat(ListeContratController.ide);
        int idemp=e.getEmp().getId();
        Employees emplo=es.getEmployeeById(idemp);
        int id = ListeContratController.ide;
        String nom = nomempbtn.getText();
        String type = Typecombo.getValue();
        Double salaire = Double.parseDouble(salairebtn.getText());
        java.sql.Date localDateDebut = Date.valueOf(datedebut.valueProperty().getValue());
        java.sql.Date localDateFin = Date.valueOf(datefin.valueProperty().getValue());       
        

        Contrat R = new Contrat(id, type, salaire, localDateDebut, localDateFin,emplo);
        rec.modifier_Contrat( R , type, salaire, localDateDebut, localDateFin,emplo);

        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Modification effectuée avec succès", ButtonType.OK);
        alert.showAndWait();

        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ListeEmployeeFXML.fxml"));
        //Parent root = loader.load();
       // Scene scene = new Scene(root);
       // stage.setScene(scene);
        stage.setTitle("Liste des employés");
        stage.show();
     
    
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
        
        
                Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow(); 
                    stage.close();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("ListeContrat.fxml")));       
                    stage.setScene(scene);
                    stage.setTitle("Mofication");
                    
                    stage.show();  
    }
    
    public void btncombo(){
         List<String> cont = Arrays.asList("CDI", "CIVP");       
        Typecombo.setItems(FXCollections.observableArrayList(cont));
    }
    
}
