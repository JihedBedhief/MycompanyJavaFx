/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import entity.Vente;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLDataException;
import java.util.ArrayList;
import java.util.Date;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import service.PointVenteService;
import service.VenteService;

/**
 * FXML Controller class
 *
 * @author Abderrazekbenhamouda
 */
public class AfficherVenteController implements Initializable {

    @FXML
    private TableView<Vente> table;
    @FXML
    private TableColumn<Vente , String> produit;
    @FXML
    private TableColumn<Vente , String> client;
    @FXML
    private TableColumn<Vente , Integer> quanatite;
    @FXML
    private TableColumn<Vente, Double> prix;
    @FXML
    private TableColumn<Vente , Double> taxe;
    @FXML
    private TableColumn<Vente , Double> prixtotal;
    @FXML
    private TableColumn<Vente, Date> date;
    
    
        
    private ObservableList<Vente> UserData = FXCollections.observableArrayList();
    
    VenteService pv = new VenteService();
    
    public static int idVente ;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                     try {
            List<Vente> listb= new ArrayList<Vente>();
            
            listb = pv.getAllVente();
     
            UserData.clear();
            UserData.addAll(listb);
            table.setItems(UserData);
            
            produit.setCellValueFactory
                      (
                              new PropertyValueFactory<>("produit")
                      );
            client.setCellValueFactory
                      (
                              new PropertyValueFactory<>("client")
                      );
            prix.setCellValueFactory
                      (
                              new PropertyValueFactory<>("prix_unite")
                      );
            prixtotal.setCellValueFactory
                      (
                              new PropertyValueFactory<>("prix_totale")
                      );
            taxe.setCellValueFactory
                      (
                              new PropertyValueFactory<>("taxe")
                      );
            quanatite.setCellValueFactory
                      (
                              new PropertyValueFactory<>("quantite")
                      );
                        date.setCellValueFactory
                      (
                              new PropertyValueFactory<>("date")
                      );
            
        } catch (SQLDataException ex) {
            Logger.getLogger(AfficherVenteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void Ajouter(ActionEvent event) {
        
                             Parent root;
               try {
              root = FXMLLoader.load(getClass().getResource("/gui/AjouterVente.fxml"));
               Stage myWindow = (Stage) table.getScene().getWindow();
               Scene sc = new Scene(root);
               myWindow.setScene(sc);
               myWindow.setTitle("page name");
                            //myWindow.setFullScreen(true);
               myWindow.show();
               } catch (IOException ex) {
               Logger.getLogger(AfficherPointVenteController.class.getName()).log(Level.SEVERE, null, ex);
               }
        
    }

    @FXML
    private void Supprimer(ActionEvent event) throws SQLDataException {
        
                int id =  table.getSelectionModel().getSelectedItem().getId();     
        pv.deleteVente(id);
        resetTableData();
        
    }

    @FXML
    private void Modifer(ActionEvent event) {
        
                    idVente =  table.getSelectionModel().getSelectedItem().getId();     

        
                  Parent root;
               try {
              root = FXMLLoader.load(getClass().getResource("/gui/ModifierVente.fxml"));
               Stage myWindow = (Stage) table.getScene().getWindow();
               Scene sc = new Scene(root);
               myWindow.setScene(sc);
               myWindow.setTitle("page name");
                            //myWindow.setFullScreen(true);
               myWindow.show();
               } catch (IOException ex) {
               Logger.getLogger(AfficherPointVenteController.class.getName()).log(Level.SEVERE, null, ex);
               }
        
    }
    
    
      public void resetTableData() throws SQLDataException
    {
        List<Vente> lisre = new ArrayList<>();
        lisre = pv.getAllVente();
        ObservableList<Vente> data = FXCollections.observableArrayList(lisre);
        table.setItems(data);
    }

    @FXML
    
private void Pdf(ActionEvent event) throws FileNotFoundException, DocumentException, SQLDataException {

    String file_name="C:\\Users\\MSI\\Desktop\\pdf\\pdfpayment.pdf";
    Document document = new Document();

    PdfWriter.getInstance(document, new FileOutputStream(file_name));
    document.open();
    List<Vente> lisre = new ArrayList<>();
    lisre = pv.getAllVente();

    // Define table with 5 columns
PdfPTable table = new PdfPTable(4);
table.setWidthPercentage(100); // définir la largeur de la table à 100% de la page

// Add table headers
table.addCell("Client");
table.addCell("Produit");
table.addCell("Prix Total");
table.addCell("Date");

// Add data to table
for (Vente v : lisre) {
    table.addCell(v.getClient());
    table.addCell(v.getProduit());
    table.addCell(String.valueOf(v.getPrix_totale()));
    table.addCell(String.valueOf(v.getDate()));
}

// Calculate total sum of prices
double total = 0.0;
for (Vente v : lisre) {
    total += v.getPrix_totale();
}

// Add table to document
document.add(table);

// Add total sum of prices
Paragraph paragraph = new Paragraph("Somme des Totales: " + total);
paragraph.setAlignment(Element.ALIGN_RIGHT); // aligner le texte à droite
paragraph.setSpacingBefore(10f); // ajouter 10 unités d'espace avant le texte
paragraph.setIndentationLeft(50f); // ajouter une indentation gauche de 50 unités
document.add(paragraph);

document.close();
   
 }

}
