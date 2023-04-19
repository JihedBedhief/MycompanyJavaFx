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
import java.sql.SQLDataException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author derou
 */
public class ListeEmployeeFXMLController implements Initializable {
    EmployeesService Es=new  EmployeesService();
    ObservableList<Employees> data;
    
    public static int ide ;

    @FXML
    private TableView<Employees> tableEmp;
    @FXML
    private TableColumn<Employees, String> nombtn;
    @FXML
    private TableColumn<Employees, String> prenombtn;
    @FXML
    private TableColumn<Employees, String> emailbtn;
    @FXML
    private TableColumn<Employees, Integer> phonenumbtn;
    @FXML
    private TableColumn<Employees, String> cinbtn;
    @FXML
    private Button ajouterbtn;
    @FXML
    private Button Modiferbtn;
    @FXML
    private Button Supprimerbtn;
    private TextField N;
    private TextField P;
    private TextField E;
    private TextField PH;
    private TextField C;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       table();
       
  //     tableEmp.setOnMouseClicked((MouseEvent event) -> {
  //  if (event.getClickCount() > 0) {
  //      onEdit2();
        
  //  }
  // });
       
       
        
}
    
 //   public void onEdit2() {
               
       
     
 //   if (tableEmp.getSelectionModel().getSelectedItem() != null) {
 //         Employees f = tableEmp.getSelectionModel().getSelectedItem();
 //         int i = f.getPhoneNum();
 //       String n = String.valueOf(i);
         
  //        N.setText(f.getNom());
  //        P.setText(f.getPrenom());
  //        E.setText(f.getEmail());
  //        PH.setText(n);   
  //        C.setText(f.getCin());
  //  }
// }
    
    
    
     public void table(){
        cinbtn.setCellValueFactory( new PropertyValueFactory<>("cin"));
        nombtn.setCellValueFactory( new PropertyValueFactory<>("nom"));
        prenombtn.setCellValueFactory(new PropertyValueFactory <>("prenom"));
        emailbtn.setCellValueFactory( new PropertyValueFactory<>("email"));
        phonenumbtn.setCellValueFactory(new PropertyValueFactory <>("phoneNum"));
        tableEmp.setItems( EmployeesService.RecupBase()); 
        System.out.println(EmployeesService.RecupBase());
        
     }

    @FXML
    private void Ajouter(ActionEvent event) throws IOException {
        
        
Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow(); 
                    stage.close();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Employeefxml.fxml")));       
                    stage.setScene(scene);
                    stage.setTitle("Ajouter");
                    
                    stage.show();  
                    
                    
           
        
    
    
}
    
    

    @FXML
    private void Modifier(ActionEvent event) throws IOException {
       ide =  tableEmp.getSelectionModel().getSelectedItem().getId();     

        
                  Parent root;
               try {
              root = FXMLLoader.load(getClass().getResource("ModifierEmployeFXML.fxml"));
               Stage myWindow = (Stage) tableEmp.getScene().getWindow();
               Scene sc = new Scene(root);
               myWindow.setScene(sc);
               myWindow.setTitle("page name");
                            //myWindow.setFullScreen(true);
               myWindow.show();
               } catch (IOException ex) {
               Logger.getLogger(ListeEmployeeFXMLController.class.getName()).log(Level.SEVERE, null, ex);
               }
        
        
    }

    @FXML
    private void Supprimer(ActionEvent event) throws SQLDataException {
         
        Employees R =  tableEmp.getSelectionModel().getSelectedItem();     
        Es.supprimer_Employee(R);
        resetTableData();
        
      
    }
    
    
    public void resetTableData() throws SQLDataException
    {
        List<Employees> lisre = new ArrayList<>();
        lisre = Es.listerEmployees();
        ObservableList<Employees> data = FXCollections.observableArrayList(lisre);
        tableEmp.setItems(data);
    }
    
    
   
    }    
    

