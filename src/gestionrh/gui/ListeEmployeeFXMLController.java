/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionrh.gui;

import gestionrh.entities.Employees;
import gestionrh.services.EmployeesService;
import gestionrh.utils.MyConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Pagination;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
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
    
     
    
    
    private final static int NumPage = 3;
    
    
//      @FXML
//    private TextField cherch;

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
    @FXML
    private TableColumn<Employees, String> formationabtn;
    @FXML
    private TableColumn<Employees, String> formationpbtn;
    @FXML
    private TableColumn<Employees, String> experiencebtn;
    @FXML
    private AnchorPane pagination;
    @FXML
    private Button statbtn;
    @FXML
    private Pagination pag;
    
    
    //ObservableList<Employees> chercherecla ;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
        table();
//         Cherchemp();
           pag.setPageFactory(this::createPage);
//                   

            

             
        
//            
//                 tableEmp.setOnMouseClicked((MouseEvent event) -> {
//              if (event.getClickCount() > 0) {
//                  onEdit2();
//            
//              }
//             });
//        
//       
//       
//        
//}
//    
//    public void onEdit2() {
//               
//       
//     
//    if (tableEmp.getSelectionModel().getSelectedItem() != null) {
//          Employees f = tableEmp.getSelectionModel().getSelectedItem();
//          int i = f.getPhoneNum();
//        String n = String.valueOf(i);
//         
//          N.setText(f.getNom());
//          P.setText(f.getPrenom());
//          E.setText(f.getEmail());
//          PH.setText(n);   
//          C.setText(f.getCin());
//    }
 }   
    
     public void table(){
        cinbtn.setCellValueFactory( new PropertyValueFactory<>("cin"));
        nombtn.setCellValueFactory( new PropertyValueFactory<>("nom"));
        prenombtn.setCellValueFactory(new PropertyValueFactory <>("prenom"));
        emailbtn.setCellValueFactory( new PropertyValueFactory<>("email"));
        formationabtn.setCellValueFactory( new PropertyValueFactory<>("formationA"));
        formationpbtn.setCellValueFactory(new PropertyValueFactory <>("formationP"));
        experiencebtn.setCellValueFactory( new PropertyValueFactory<>("experience"));
        phonenumbtn.setCellValueFactory(new PropertyValueFactory <>("phoneNum"));
       // tableEmp.setItems( EmployeesService.RecupBase()); 
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
    
    
//    @FXML
//    public void Cherchemp(){
//    
//       cinbtn.setCellValueFactory( new PropertyValueFactory<>("cin"));
//        nombtn.setCellValueFactory( new PropertyValueFactory<>("nom"));
//        
//    chercherecla = EmployeesService.RecupBase();
//    tableEmp.setItems(EmployeesService.RecupBase());
//    FilteredList<Employees> filtreddata;
//     filtreddata = new FilteredList<>(chercherecla ,b ->true);
//    cherch.textProperty().addListener((observable,oldValue,newValue)->{
//      filtreddata.setPredicate((u  ->  {
//          
//          if((newValue ==null) || newValue.isEmpty())
//          { return true;}
//      
//      String lowerCaseFilter = newValue.toLowerCase();
//  
//         if (u.getNom().toLowerCase().contains(lowerCaseFilter))
//          {return true;}
//        
//          else if (u.getCin().toLowerCase().contains(lowerCaseFilter))
//          {return true;}
//        
//     
//        
//      return false;
//      })); 
//    });
//    
//    SortedList<Employees> srt = new SortedList<>(filtreddata);
//    srt.comparatorProperty().bind(tableEmp.comparatorProperty());
//    tableEmp.setItems(srt);
//    
//    }

    
    
    

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
    
    
    
    
    private  ObservableList<Employees> dataa =  EmployeesService.RecupBase();
     private Node createPage(int pageIndex){
      int fromIndex = pageIndex * NumPage ;
      int toIndex = Math.min(fromIndex + NumPage , dataa.size());
      tableEmp.setItems(FXCollections.observableArrayList(dataa.subList(fromIndex, toIndex)));
      
      return tableEmp;
      
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

    @FXML
    private void Statique(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow(); 
                    stage.close();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Statemp.fxml")));       
                    stage.setScene(scene);
                    stage.setTitle("Ajouter");
                  
                    
                    stage.show();  

    }
    
    
   
    }    
    

