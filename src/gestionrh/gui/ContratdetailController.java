/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionrh.gui;

import gestionrh.entities.Contrat;
import gestionrh.entities.Employees;
import gestionrh.services.ContratService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

/**
 * FXML Controller class
 *
 * @author derou
 */
public class ContratdetailController implements Initializable {
      ContratService rec= new ContratService();
    
    private ListView<Integer> listebtn;
    @FXML
    private Label empbtn;
    @FXML
    private Label typebtn;
    @FXML
    private Label salairebtn;
    @FXML
    private Label datedbtn;
    @FXML
    private Label datefbtn;
    @FXML
    private Button imprimerbtn;
    @FXML
    private Button retourbtn;
    @FXML
    private AnchorPane pane;
    @FXML
    private HBox boxing;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
  
    
            Contrat e = rec.get_contrat(ListeContratController.ide);
            
            empbtn.setText(e.getEmp().getNom());
            typebtn.setText(e.getType());
            salairebtn.setText(String.valueOf(e.getSalaire()));
            datedbtn.setText(String.valueOf(e.getDatedebut()));
            datefbtn.setText(String.valueOf(e.getDatefin()));
         
    }  

    @FXML
    private void Imprimer(ActionEvent event) {
        
         PrinterJob job = PrinterJob.createPrinterJob();
       
        Node root= this.boxing;
        
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
    private void Retour(ActionEvent event) throws IOException {
        
         Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow(); 
                    stage.close();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("ListeContrat.fxml")));       
                    stage.setScene(scene);
                    stage.setTitle("Mofication");
                    
                    stage.show();  
    }
}
