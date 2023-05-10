/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Achat;
import Entities.Pdf;
import Services.ServiceAchat;
import Utils.Maconnexion;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterAttributes;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.transform.Scale;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class ListeController implements Initializable {

    private TextField filterfield;
    @FXML
    private TableView<Achat> table;
  
    
    private boolean okClicked = false;
    
    private Set<String> possibleWordSet = new HashSet<>();
    private AutoCompletionBinding<String> autocompletionBinding;
    private String filePath = "src/GUI/Freelance.txt";
    
      ObservableList<Achat> list = FXCollections.observableArrayList();
   
    @FXML
    private Label chercher;
    @FXML
    private ComboBox<String> ExporterListe;
    @FXML
    private TableColumn<Achat, String> cproduit;
    @FXML
   private TableColumn<Achat, String> cquantite;
    @FXML
   private TableColumn<Achat, String> cprix;
    @FXML
    private TableColumn<Achat, String> cdate;
    @FXML
     private TableColumn<Achat, String> ctaxe;
    @FXML
    private Button actualiser;
    @FXML
    private Button supprimer;
    @FXML
    private Button Modifier;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         readWordsFromFile();
        autocompletionBinding = TextFields.bindAutoCompletion(filterfield, possibleWordSet);
        filterfield.setOnKeyReleased(event -> {
            if (!event.getCode().isArrowKey()) {
                learnWord(filterfield.getText());
            }
        });
        
        
         ObservableList<String> list1 = FXCollections.observableArrayList("PDF",  "Imprimer");
        ExporterListe.setItems(list1);
        
        
        //Affichage a partire du base de donnes done:
        ServiceAchat Sf = new ServiceAchat();
        List<Achat> listeFr;
        listeFr = Sf.afficherAchat();
        ObservableList<Achat> list = FXCollections.observableList(listeFr);
        
        cproduit.setCellValueFactory(new PropertyValueFactory<Achat,String>("Produit"));
        cquantite.setCellValueFactory(new PropertyValueFactory<Achat,String>("Quantite"));
        cprix.setCellValueFactory(new PropertyValueFactory<Achat,String>("Prix"));
        cdate.setCellValueFactory(new PropertyValueFactory<Achat,String>("DateAchat"));
        ctaxe.setCellValueFactory(new PropertyValueFactory<Achat,String>("Taxe"));
        // TODO
    }  
    
       public boolean isOkClicked() {
        return okClicked;
    }
       
         public void filter() {  
        ServiceAchat sf= new ServiceAchat();
        //remplire lobservablelist
        list.addAll(sf.afficherAchat());
        //liste filtrer
        FilteredList<Achat> filtreddata=new FilteredList<>(list,u->true);
        //creation du listenere a partire du textfield
        filterfield.textProperty().addListener((observable,oldValue,newValue)->{
            filtreddata.setPredicate(Achat->{
                if(newValue==null||newValue.isEmpty()){
                    return true;
                }
                if(Achat.getProduit().indexOf(newValue)!=-1){
                    return true;
                }
                else if (Float.toString(Achat.getQuantite()).contains(newValue)){
                    return true;
                }
                else if(Achat.getDateAchat().indexOf(newValue)!=-1){
                    return true;
                }
              
                if (Float.toString(Achat.getTaxe()).contains(newValue)){
                    return true;
                }
               
               
                else{
                    return false;
                }
            });
            table.setItems(filtreddata);
        });
    }

    private void search(KeyEvent event) {
        filter();
    }

    @FXML
    private void Actualiser(ActionEvent event) {
        refresh();
        load();
    }
    
      private void refresh() {
        ServiceAchat Sf = new ServiceAchat();
        List<Achat> listeFr;
        listeFr = Sf.afficherAchat();
        ObservableList<Achat> list = FXCollections.observableList(listeFr);
        

        try {
            listeFr.clear();
            String query = "SELECT * FROM `Achat`";
             Connection cnx = Maconnexion.getInstance().getConnection();
            PreparedStatement ps = cnx.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                listeFr.add(new Achat(
                        rs.getString("Produit"),
                        rs.getFloat("Quantite"),
                        rs.getFloat("Prix"),
                        rs.getString("DateAchat"),
                        rs.getFloat("Taxe"))     
                );
                table.setItems(list);

            }
        } catch (Exception e) {
            System.err.print(e.getMessage());
        }

    }
           private void load() {
        Achat f = new Achat();
        Connection cnx = Maconnexion.getInstance().getConnection();
         cproduit.setCellValueFactory(new PropertyValueFactory<>("Matricule"));
        cquantite.setCellValueFactory(new PropertyValueFactory<>("Quantite"));
        cprix.setCellValueFactory(new PropertyValueFactory<>("Prix"));
        cdate.setCellValueFactory(new PropertyValueFactory<>("DateAchat"));
        ctaxe.setCellValueFactory(new PropertyValueFactory<>("Taxe"));
    }

    @FXML
    private void supprimer(ActionEvent event) {
              ServiceAchat Sf = new ServiceAchat();
        List<Achat> listeFr;
        if(table.getSelectionModel().getSelectedItem()!=null){
        int Ida=table.getSelectionModel().getSelectedItem().getIda();//slecter l id 
        Sf.supprimerAchat(Ida);
        table.refresh();
        
        }
    }
    
    
    private void learnWord(String text) {
       possibleWordSet.add(text);
        autocompletionBinding.dispose();
        autocompletionBinding = TextFields.bindAutoCompletion(filterfield, possibleWordSet);
        writeWordsToFile();

    }
     private void readWordsFromFile() {
    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                possibleWordSet.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void writeWordsToFile() {
   try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (String word : possibleWordSet) {
                bw.write(word);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    
    }
    
             private void PDF(MouseEvent event) {
        Achat f = table.getSelectionModel().getSelectedItem();

        Pdf pd=new Pdf();
        try{
           pd.GeneratePdf("Mes Achats",f,f.getIda());
            System.out.println("impression done");
        } catch (Exception ex) {
            Logger.getLogger(ServiceAchat.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void ExporterListe(ActionEvent event) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
                 String N = (String) ExporterListe.getValue();

        if (N == "PDF") {
            
           ExporterListe.setValue("Exporter");
          Achat f = table.getSelectionModel().getSelectedItem();
           Pdf pd=new Pdf();
        try{
              pd.GeneratePdf(""+f.getProduit()+"",f,f.getIda());
              Alert alert= new Alert(Alert.AlertType.INFORMATION);
              alert.setTitle("PDF");
              alert.setHeaderText(null);
              alert.setContentText("!!!PDF exported!!!");
              alert.showAndWait();
            System.out.println("impression done");
        } catch (Exception ex) {
            Logger.getLogger(ServiceAchat.class.getName()).log(Level.SEVERE, null, ex);
            Alert alert= new Alert(Alert.AlertType.WARNING);
                  alert.setTitle("Alert");
                  alert.setHeaderText(null);
                  alert.setContentText("!!!Selectioner une formation!!!");
                  alert.showAndWait();
            }
        }
                if (N == "Imprimer") {
             ExporterListe.setValue("Exporter");
             printNode(table);
        }
    }
       public static void printNode(final Node node) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Printer printer = Printer.getDefaultPrinter();
        javafx.print.PageLayout pageLayout = printer.createPageLayout(Paper.A4, PageOrientation.LANDSCAPE, Printer.MarginType.DEFAULT);
        PrinterAttributes attr = printer.getPrinterAttributes();
        PrinterJob job = PrinterJob.createPrinterJob();
        double scaleX = pageLayout.getPrintableWidth() / node.getBoundsInParent().getWidth();
        double scaleY = pageLayout.getPrintableHeight() / node.getBoundsInParent().getHeight();
        Scale scale = new Scale(scaleX, scaleY);
        node.getTransforms().add(scale);
        
        if (job != null && job.showPrintDialog(node.getScene().getWindow())) {
            boolean success = job.printPage(pageLayout, node);
            if (success) {
                job.endJob();
                
            }
        }
        node.getTransforms().remove(scale);
        
    }


    private void ImprimerAction(ActionEvent event) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
                printNode(table);
    }

    @FXML
    private void modifier(ActionEvent event) {
    }
    
}
