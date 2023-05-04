/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionrh.gui;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import gestionrh.entities.Contrat;
import gestionrh.services.ContratService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.SQLDataException;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;


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

//    @FXML
//    private void Imprimer(ActionEvent event) {
//        
//         PrinterJob job = PrinterJob.createPrinterJob();
//       
//        Node root= this.boxing;
//        
//     if(job != null){
//     job.showPrintDialog(root.getScene().getWindow()); 
//     Printer printer = job.getPrinter();
//     PageLayout pageLayout = printer.createPageLayout(Paper.A3, PageOrientation.LANDSCAPE, Printer.MarginType.HARDWARE_MINIMUM);
//     boolean success = job.printPage(pageLayout, root);
//     if(success){
//        job.endJob();
//     }
//   }
//   
//    }
@FXML
private void Imprimer(ActionEvent event) throws FileNotFoundException, DocumentException, MalformedURLException, BadElementException, IOException {
   String file_name="C:\\Users\\derou\\Desktop\\Pdf\\document.pdf";
    Document document = new Document();

    PdfWriter.getInstance(document, new FileOutputStream(file_name));
    document.open();
    List<Contrat> liste = new ArrayList<>();
    liste = rec.listerContrats();

    // Define table with 5 columns
PdfPTable table = new PdfPTable(4);
table.setWidthPercentage(100); // définir la largeur de la table à 100% de la page

// Add table headers
table.addCell("Nom");
table.addCell("Type");
table.addCell("Salaire");
table.addCell("Date debut");
table.addCell("Date fin");

// Add data to table
for (Contrat v : liste) {
    table.addCell(v.getEmp().getNom());
    table.addCell(String.valueOf(v.getType()));
    table.addCell(String.valueOf(v.getSalaire()));
    table.addCell(String.valueOf(v.getDatedebut()));
    table.addCell(String.valueOf(v.getDatefin()));
}






// Load the logo image from URL
Image logo = Image.getInstance(new URL("https://scontent.xx.fbcdn.net/v/t1.15752-9/324943107_727844712198640_5952788068570103838_n.png?_nc_cat=101&ccb=1-7&_nc_sid=aee45a&_nc_ohc=L9r2DUjzChgAX9h2-di&_nc_ad=z-m&_nc_cid=0&_nc_ht=scontent.xx&oh=03_AdQx9hfEknVIywvznUHzX-TMK_bMzU_NHPwzQhB1a_lbUQ&oe=647A5B3D"));
logo.scaleToFit(150, 150); // Resize the image to fit within a 100x100 rectangle

// Add the logo to the document at the top left corner
logo.setAbsolutePosition(document.left(), document.top() - 50);
document.add(logo);

// Add some space before the table
table.setSpacingBefore(70f);


// Add table to document
document.add(table);


document.close();
   
 

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
