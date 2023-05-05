/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Entity.Achat;
import Entity.Pdf;
import services.ServiceAchat;
import utils.Maconnexion;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterAttributes;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;
import tray.animations.AnimationType;
import tray.notification.NotificationType;


/**
 * FXML Controller class
 *
 * @author HP
 */
public class ListeachatController implements Initializable {

    @FXML
    private TableColumn<Achat, String> cprix;
    @FXML
    private TextField filterfield;
    @FXML
    private TableColumn<Achat, String> cproduit;
    @FXML
    private TableColumn<Achat, String> cquantite;
    @FXML
    private TableColumn<Achat, String> cdate;
    @FXML
    private TableColumn<Achat, String> ctaxe;
    @FXML
    private Button Modifier;
    private TextField TexType;
    @FXML
    private Button supprimer;
    @FXML
    private ComboBox<String> ExporterListe;
    @FXML
    private Button actualiser;
    @FXML
    private TableView<Achat> table;
     private boolean okClicked = false;
      ObservableList<Achat> list = FXCollections.observableArrayList();
      
       private Set<String> possibleWordSet = new HashSet<>();
    private AutoCompletionBinding<String> autocompletionBinding;
    private String filePath = "src/GUI/Freelance.txt";
    @FXML
    private Button btn_stat;

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
        
        ObservableList<String> list1 = FXCollections.observableArrayList("PDF", "Imprimer");
        ExporterListe.setItems(list1);
        //Affichage a partire du base de donnes done:
        ServiceAchat Sa = new ServiceAchat();
        List<Achat> listeAc;
        listeAc = Sa.afficherAchat();
        ObservableList<Achat> list = FXCollections.observableList(listeAc);
        
        cproduit.setCellValueFactory(new PropertyValueFactory<Achat,String>("Produit"));
        cquantite.setCellValueFactory(new PropertyValueFactory<Achat,String>("Quantite"));
        cprix.setCellValueFactory(new PropertyValueFactory<Achat,String>("Prix"));
        cdate.setCellValueFactory(new PropertyValueFactory<Achat,String>("DateAchat"));
        ctaxe.setCellValueFactory(new PropertyValueFactory<Achat,String>("Taxe"));
       
        
        
        table.setItems(list);
        // TODO
    } 
       public boolean isOkClicked() {
        return okClicked;
    }
public void filter() {
    ServiceAchat Sa = new ServiceAchat();
    // remplir l'observablelist
    list.addAll(Sa.afficherAchat());
    // liste filtrée
    FilteredList<Achat> filteredData = new FilteredList<>(list, u -> true);
    // création du listener à partir du textfield
    filterfield.textProperty().addListener((observable, oldValue, newValue) -> {
        filteredData.setPredicate(Achat -> {
            if (newValue == null || newValue.isEmpty()) {
                return true;
            }
            String lowerCaseFilter = newValue.toLowerCase();
            if (Achat.getProduit().toLowerCase().contains(lowerCaseFilter)) {
                return true;
            } else if (String.valueOf(Achat.getQuantite()).contains(lowerCaseFilter)) {
                return true;
            } else if (String.valueOf(Achat.getPrix()).contains(lowerCaseFilter)) {
                return true;
            } else if (Achat.getDateAchat().toLowerCase().contains(lowerCaseFilter)) {
                return true;
            } else if (String.valueOf(Achat.getTaxe()).contains(lowerCaseFilter)) {
                return true;
            } else {
                return false;
            }
        });
        table.setItems(filteredData);
    });
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

    @FXML
    private void search(KeyEvent event) {
        filter();
    }
    
        private void refresh() {
        ServiceAchat Sa = new ServiceAchat();
        List<Achat> listeAc;
        listeAc = Sa.afficherAchat();
        ObservableList<Achat> list = FXCollections.observableList(listeAc);
        

        try {
            listeAc.clear();
            String query = "SELECT * FROM `Achat`";
              Connection cnx = Maconnexion.getInstance().getConnection();
            PreparedStatement ps = cnx.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                listeAc.add(new Achat(
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
        Achat a = new Achat();
       Connection cnx = Maconnexion.getInstance().getConnection();
         cproduit.setCellValueFactory(new PropertyValueFactory<>("Produit"));
        cquantite.setCellValueFactory(new PropertyValueFactory<>("Quantite"));
          cprix.setCellValueFactory(new PropertyValueFactory<>("Prix"));
        cdate.setCellValueFactory(new PropertyValueFactory<>("DateAchat"));
        ctaxe.setCellValueFactory(new PropertyValueFactory<>("Taxe"));
    }

    @FXML
    private void Actualiser(ActionEvent event) {
         refresh();
        load();
    }

    @FXML
    private void modifier(ActionEvent event) {
        Connection cnx = Maconnexion.getInstance().getConnection();
       Achat NewAchat = table.getSelectionModel().getSelectedItem();
         FXMLLoader loader = new FXMLLoader ();
             loader.setLocation(getClass().getResource("/GUI/achat.fxml"));
                try {
                      loader.load();
                    } catch (Exception ex) {
                          System.err.println(ex.getMessage());
                       }
         AchatController fnc = loader.getController();
                         //  fnc.setUpdate(true);
         fnc.setTextFields(NewAchat);
          Parent parent = loader.getRoot();
          Stage stage = new Stage();
           stage.setScene(new Scene(parent));
            stage.initStyle(StageStyle.UTILITY);
          stage.show();
    }
  

    @FXML
    private void supprimer(ActionEvent event) {
                      ServiceAchat Sa = new ServiceAchat();
        List<Achat> listeAc;
        if(table.getSelectionModel().getSelectedItem()!=null){
        int Ida=table.getSelectionModel().getSelectedItem().getIda();//slecter l id 
        Sa.supprimerAchat(Ida);
        table.refresh();
        
        }
    }
    
     private void PDF(MouseEvent event) {
        Achat a = table.getSelectionModel().getSelectedItem();

        Pdf pd=new Pdf();
        try{
           pd.GeneratePdf("Mes achats",a,a.getIda());
            System.out.println("impression done");
        } catch (Exception ex) {
            Logger.getLogger(ServiceAchat.class.getName()).log(Level.SEVERE, null, ex);
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
    private void ExporterListe(ActionEvent event) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        String N = (String) ExporterListe.getValue();

        if (N == "PDF") {
            
           ExporterListe.setValue("Exporter");
          Achat a = table.getSelectionModel().getSelectedItem();
           Pdf pd=new Pdf();
        try{
              pd.GeneratePdf(""+a.getProduit()+"",a,a.getIda());
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
                  alert.setContentText("!!!Selectioner une achat!!!");
                  alert.showAndWait();
            }
        }
         if (N == "Imprimer") {
             ExporterListe.setValue("Exporter");
             printNode(table);
        }
    }

    @FXML
    private void stati(ActionEvent event) throws IOException {
             
                 try {
            Parent page1 = FXMLLoader.load(getClass().getResource("AchatStat.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    
}
}
