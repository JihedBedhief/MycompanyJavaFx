/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import Entity.Vente;
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
import services.VenteService;

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
    
private void Pdf(ActionEvent event) throws FileNotFoundException, DocumentException, SQLDataException, BadElementException, IOException {

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



// Add total sum of prices
Font font = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD, new BaseColor(0, 128, 0)); // définir la police en gras, taille 16 et couleur verte
Chunk chunk = new Chunk("Totale des ventes: " + total + " DT", font); // appliquer les propriétés de la police à la variable
Paragraph paragraph = new Paragraph(chunk);
paragraph.setAlignment(Element.ALIGN_RIGHT); // aligner le texte à droite
paragraph.setSpacingBefore(10f); // ajouter 10 unités d'espace avant le texte
paragraph.setIndentationLeft(50f); // ajouter une indentation gauche de 50 unités
document.add(paragraph);

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

}
