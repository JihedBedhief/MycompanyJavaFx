/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionrh.gui;

import gestionrh.entities.Contrat;
import gestionrh.entities.Employees;
import static gestionrh.gui.ListeEmployeeFXMLController.ide;
import gestionrh.services.ContratService;
import gestionrh.services.EmployeesService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLDataException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author derou
 */
public class ListeContratController implements Initializable {
    ContratService sv =new ContratService();
    ObservableList<Employees> data;

    @FXML
    private TableView<Contrat> contrattab;
    @FXML
    private TableColumn<Contrat, String> typebtn;
    @FXML
    private TableColumn<Contrat, Double> salairebtn;
    @FXML
    private TableColumn<Contrat, Date> datedbtn;
    @FXML
    private TableColumn<Contrat, Date> datefbtn;
    @FXML
    private TableColumn<Contrat, String> empbtn;
    @FXML
    private Button supprimerbtn;
    @FXML
    private Button modifierbtn;
    @FXML
    private CheckBox cdi;
    @FXML
    private CheckBox civp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        table();
        
    } 
    
    
    public void table(){
        typebtn.setCellValueFactory( new PropertyValueFactory<>("type"));
        salairebtn.setCellValueFactory( new PropertyValueFactory<>("salaire"));
        datedbtn.setCellValueFactory(new PropertyValueFactory <>("datedebut"));
        datefbtn.setCellValueFactory( new PropertyValueFactory<>("datefin"));
        empbtn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Contrat,String>,ObservableValue<String>>(){
    @Override
    public ObservableValue<String> call(TableColumn.CellDataFeatures<Contrat, String> param) {
                            return new SimpleStringProperty(param.getValue().getEmp().getNom());
    }
            });
        contrattab.setItems(sv.listerContrat()); 
        System.out.println(EmployeesService.RecupBase());
        
     }

    @FXML
    private void suprimer(ActionEvent event) throws SQLDataException {
         Contrat r =  contrattab.getSelectionModel().getSelectedItem();     
        sv.supprimer_Contrat(r);
         resetTableData();
    }
    
    public void resetTableData() throws SQLDataException
    {
        List<Contrat> lisre = new ArrayList<>();
        lisre = sv.listerContrat();
        ObservableList<Contrat> data = FXCollections.observableArrayList(lisre);
        contrattab.setItems(data);
    }

    @FXML
    private void Modifier(ActionEvent event) {
        
         ide =  contrattab.getSelectionModel().getSelectedItem().getId();     

        
                  Parent root;
               try {
              root = FXMLLoader.load(getClass().getResource("Modifiercontrat.fxml"));
               Stage myWindow = (Stage) contrattab.getScene().getWindow();
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
    private void print(ActionEvent event) {
        
        
          PrinterJob job = PrinterJob.createPrinterJob();
       
        Node root= this.contrattab;
        
     if(job != null){
     job.showPrintDialog(root.getScene().getWindow()); 
     Printer printer = job.getPrinter();
     PageLayout pageLayout = printer.createPageLayout(Paper.A3, PageOrientation.LANDSCAPE, Printer.MarginType.HARDWARE_MINIMUM);
     boolean success = job.printPage(pageLayout, root);
     if(success){
        job.endJob();
     }
   }
   
   
}
    
    
   @FXML
public void bien(ActionEvent event) {
    if (cdi.isSelected()) {
        civp.setSelected(false);
         ObservableList<Contrat> list = FXCollections.observableArrayList();

        list = sv.listerContrat();
        if (!list.isEmpty()) {
            ObservableList<Contrat> list2 = FXCollections.observableArrayList();
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getType().equals("CDI")) {
                    list2.add(list.get(i));
                }
            }
            if (!list2.isEmpty()) {
                contrattab.setItems(list2);
            } else {
                Alert alert = new Alert(AlertType.INFORMATION, "Aucune contrat CDI.");
                alert.setHeaderText(null);
                alert.showAndWait();
                civp.setSelected(false);
            }
        } else {
            Alert alert = new Alert(AlertType.INFORMATION, "Vous n'avez aucune contrat avec cette résolution.");
            alert.setHeaderText(null);
            alert.showAndWait();
            cdi.setSelected(false);
        }
    } else {
        table(); // Assuming this method is defined elsewhere
    }
}

@FXML
public void resolu(javafx.event.ActionEvent event)  {
    if (civp.isSelected()) {
        cdi.setSelected(false);
        ObservableList<Contrat> list = FXCollections.observableArrayList();
        list = sv.listerContrat();
        if (!list.isEmpty()) {
            ObservableList<Contrat> list2 = FXCollections.observableArrayList();
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getType().equals("CIVP")) {
                    list2.add(list.get(i));
                }
            }
            if (!list2.isEmpty()) {
                contrattab.setItems(list2);
            } else {
                Alert alert = new Alert(AlertType.INFORMATION, "Aucune contrat CIVP.");
                alert.setHeaderText(null);
                alert.showAndWait();
                cdi.setSelected(false);
            }
        } else {
            Alert alert = new Alert(AlertType.INFORMATION, "Vous n'avez aucune contrat avec cette resolution");
            alert.setHeaderText(null);
            alert.showAndWait();
            civp.setSelected(false);
        }
    } else {
        table(); 
    }
}
    
}
