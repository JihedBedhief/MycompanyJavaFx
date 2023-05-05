/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esprit;

import Database.Database;
import Entity.Category;
import Entity.Produit;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.util.Callback;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.Rating;

import services.ServiceCategory;
import services.ServiceProduit;

/**
 * FXML Controller class
 *
 * @author MediaCenter Zaghouan
 */
public class ProduitController implements Initializable {

    @FXML
    private TableColumn<Produit, Integer> id;
    @FXML
    private TableColumn<Produit, String> cat;
    @FXML
    private TableColumn<Produit, String> name;
    @FXML
    private TableColumn<Produit, String> desc;
    @FXML
    private TableColumn<Produit, Integer> prix;
    @FXML
    private TableColumn<Produit, Integer> prix2;
    @FXML
    private TableColumn<Produit, Integer> quant;
    @FXML
    private TableColumn<Produit, Integer> rate;
    @FXML
    private TableView<Produit> tab;
    @FXML
    private TextField idx;
    @FXML
    private TextField n;
    @FXML
    private TextField d;
    @FXML
    private TextField p;
    @FXML
    private TextField p2;
    @FXML
    private TextField q;
    @FXML
    private TextField urlx;
    @FXML
    private ComboBox<Integer> comm;
    @FXML
    private TextField recherche;
    
    @FXML
    private Rating stars;

    
                        ObservableList<Produit> Chercheprod;
    @FXML
    private ImageView img;
  

    /**
     * Initializes the controller class.
     */
     public ProduitController() {
        Connection cnx = Database.getInstance().getCnx();
    }
    private Connection cnx;

    ServiceProduit prod = new ServiceProduit();
    
     public void table() {

//        r.setCellValueFactory( new PropertyValueFactory<>("Role"));
        id.setCellValueFactory(new PropertyValueFactory<>("id"));

        cat.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Produit, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Produit, String> param) {
                return new SimpleStringProperty(param.getValue().getCategory_id().getName());
            }
        });
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        desc.setCellValueFactory(new PropertyValueFactory<>("description"));
        prix.setCellValueFactory(new PropertyValueFactory<>("buyprice"));
        prix2.setCellValueFactory(new PropertyValueFactory<>("sellprice"));
        quant.setCellValueFactory(new PropertyValueFactory<>("quantity"));
                rate.setCellValueFactory(new PropertyValueFactory<>("Rate"));


      

        tab.setItems(prod.RecupBase2());
        System.out.println(prod.RecupBase2());

    }
    
    
     @FXML
    private void Ajoutefichier(ActionEvent event) {
             FileChooser f = new FileChooser();
            
        File S = f.showOpenDialog(null);
         if(S!=null){
       
        
         String n = S.getAbsolutePath();
        urlx.setText(S.getAbsolutePath());
        
         }
    }
    
     public void onEdit() {
               
               java.sql.Connection cnx;
     cnx = Database.getInstance().getCnx();
     
    if (tab.getSelectionModel().getSelectedItem() != null) {
          Produit f = tab.getSelectionModel().getSelectedItem();
      String id=String.valueOf(f.getId());
            String prix=String.valueOf(f.getBuyprice());
      String prix2=String.valueOf(f.getSellprice());
      String quntite=String.valueOf(f.getQuantity());

      idx.setText(id);
          n.setText(f.getName());
          d.setText(f.getDescription());
          p.setText(prix);
          p2.setText(prix2);
          q.setText(quntite);
          urlx.setText(f.getImage());
          System.out.println(f.getImage());
  
     
    }
}
  public void change() throws MalformedURLException{
  

            // TODO
         
  
  }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        urlx.setVisible(false);
        table();
        ChercheFichier();
        comm.setItems(prod.RecupCombo());
        
//        try {
//            read();
//        } catch (SQLException ex) {
//            Logger.getLogger(ProduitController.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
             tab.setOnMouseClicked((MouseEvent event) -> {
    if (event.getClickCount() > 0) {
        try {
            onEdit();
            String s = urlx.getText();
            
            File file = new File(s);
            System.out.println(s);
            Image image = new Image(file.toURI().toURL().toExternalForm());
            img.setImage(image);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
  
        
    }
});
    }    
    
    
     public void read() throws SQLException {
         
               java.sql.Connection cnx;
     cnx = Database.getInstance().getCnx();
         
    Statement ste= cnx.createStatement();
    ResultSet rs=ste.executeQuery("select * from produit");
     while (rs.next()) {                
               int id=rs.getInt(1);
               int sellprice=rs.getInt(6);
               double rate = rs.getDouble(9);
               Produit p=new Produit(id,sellprice,rate);
               if(p.getRate()==1.0){
              
               prod.updateprix(p, p.getSellprice());
               
               }
               
        }}
    
    
     private void EnregistrerVersBase() {
           java.sql.Connection cnx;
     cnx = Database.getInstance().getCnx();
             
           try {
            String sql = "INSERT INTO produit (category_id,name,description,buyprice,sellprice,quantity,image) VALUES (?,?,?,?,?,?,?)";
            
        PreparedStatement st = (PreparedStatement) cnx.prepareStatement(sql);
              
                int prix1 = Integer.valueOf(p.getText());
                                int prix2 = Integer.valueOf(p2.getText());

         int quantitée = Integer.valueOf(q.getText());


              


        
        
             Integer  i = comm.getValue();
               System.out.println(i);
            st.setInt(1, i);
            st.setString(2, n.getText());
            st.setString(3, d.getText());
              st.setInt(4,prix1);
            st.setInt(5, prix2);
              st.setInt(6,quantitée);
            st.setString(7, urlx.getText());
          //  st.setString(8, date.getText());

        
            st.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error");
        }
    } 


    @FXML
    private void Add(ActionEvent event) {
             if (name.getText().isEmpty() || n.getText().isEmpty() || p.getText().isEmpty() || p2.getText().isEmpty() ||  q.getText().isEmpty())
         {
             Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Erreur");
            
            alert.setContentText("Please fill all input");
            Optional<ButtonType> result = alert.showAndWait();}
           
           
           
             else if(urlx.getText().isEmpty()){
                 Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Erreur");
            
            alert.setContentText("ajouter une image");
            Optional<ButtonType> result = alert.showAndWait();
             }
        
            
           
           
         else {
           
           EnregistrerVersBase();
           table();
//           JOptionPane.showMessageDialog(null,"Account created successfully");
       notif1("Produit", "Ajouter avec succes");
          
           
          
//           
//           
//         
           }
       
        
    }
    public void notif1(String title, String text) {
        
        
        Image img = new Image("/esprit/logo1.png");
        Notifications notificationBuilder = Notifications.create()
                .title(title)
                .text(text)
                .graphic(new ImageView(img))
                .hideAfter(Duration.seconds(10))
                .position(Pos.BOTTOM_RIGHT);
        notificationBuilder.show();
    }
    
       public void ChercheFichier(){
      Produit f = new Produit();
      
         name.setCellValueFactory(new PropertyValueFactory<>("name"));
        desc.setCellValueFactory(new PropertyValueFactory<>("description"));
       
    Chercheprod = prod.RecupBase2();
    tab.setItems(prod.RecupBase2());
    FilteredList<Produit> filtreddata;
     filtreddata = new FilteredList<>(Chercheprod ,b ->true);
    recherche.textProperty().addListener((observable,oldValue,newValue)->{
      filtreddata.setPredicate((u  ->  {
          
          if((newValue ==null) || newValue.isEmpty())
          { return true;}
      
      String lowerCaseFilter = newValue.toLowerCase();
      if (u.getName().toLowerCase().contains(lowerCaseFilter)){
      return true;
      } else if (u.getDescription().toLowerCase().contains(lowerCaseFilter))
          {return true;}
        
     
        
      return false;
      })); 
    });
    
    SortedList<Produit> srt = new SortedList<>(filtreddata);
    srt.comparatorProperty().bind(tab.comparatorProperty());
    tab.setItems(srt);
    }
    
    private void print(ActionEvent event) {
        
        
          PrinterJob job = PrinterJob.createPrinterJob();
       
        Node root= this.tab;
        
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
    private void Supp(ActionEvent event) throws SQLException {
           String idf=idx.getText();
        int i=Integer.valueOf(idf);
        Produit r = new Produit();
        
        r.setId(i);
         
              System.out.println(r);
              prod.delete(r);
        
                         table();

//        SuppRole(i);
     
       
        
                JOptionPane.showMessageDialog(null,"Le client a été supprimer avec succés");
    }
    
     @FXML
    private void Rate(ActionEvent event) throws SQLException{
        
        
        Produit p = new Produit();
        
        String n = idx.getText();
        int id = Integer.valueOf(n);
         String nn = p2.getText();
        int prix = Integer.valueOf(nn);
        p.setSellprice(prix);
        p.setId(id);
        p.setRate(stars.getRating());
         System.out.println(p);
        
            prod.updaterating(p);
                   
           table();
           if(p.getRate()==1.0){
              
               prod.updateprix(p, p.getSellprice());
                          table();

               }
               

            
           

                  

       
        
         System.out.println(stars.getRating());
    }

//    @FXML
//    private void stars(MouseEvent event) {
//        stars.setRating(stars.getRating());
//        System.out.println(stars);
//    }

    @FXML
    private void Rate(MouseEvent event) {
    }

    @FXML
    private void retour(ActionEvent event) {
    }

    @FXML
    private void chercher(ActionEvent event) {
        Produit f = new Produit();
      
         name.setCellValueFactory(new PropertyValueFactory<>("name"));
        desc.setCellValueFactory(new PropertyValueFactory<>("description"));
       
    Chercheprod = prod.RecupBase2();
    tab.setItems(prod.RecupBase2());
    FilteredList<Produit> filtreddata;
     filtreddata = new FilteredList<>(Chercheprod ,b ->true);
    recherche.textProperty().addListener((observable,oldValue,newValue)->{
      filtreddata.setPredicate((u  ->  {
          
          if((newValue ==null) || newValue.isEmpty())
          { return true;}
      
      String lowerCaseFilter = newValue.toLowerCase();
      if (u.getName().toLowerCase().contains(lowerCaseFilter)){
      return true;
      } else if (u.getDescription().toLowerCase().contains(lowerCaseFilter))
          {return true;}
        
     
        
      return false;
      })); 
    });
    
    SortedList<Produit> srt = new SortedList<>(filtreddata);
    srt.comparatorProperty().bind(tab.comparatorProperty());
    tab.setItems(srt);
    }
    
//    User user;
//    public void setUser(User user) {
//
//        this.user = user;}

    @FXML
    void btnGenPDF(ActionEvent event) throws DocumentException, FileNotFoundException, IOException {
ServiceProduit sp = new ServiceProduit();

  long millis = System.currentTimeMillis();
        java.sql.Date DateRapport = new java.sql.Date(millis);

        String DateLyoum = new SimpleDateFormat("yyyyMMddHHmmss", Locale.ENGLISH).format(DateRapport);//yyyyMMddHHmmss
        System.out.println("Date d'aujourdhui : " );

        com.itextpdf.text.Document document = new com.itextpdf.text.Document();
        
        

        try {
              com.itextpdf.text.Image img = com.itextpdf.text.Image.getInstance("C:\\Users\\wassi\\OneDrive\\Pictures\\332842809_856558328771419_1402304466079459982_n.png");
               img.scaleAbsoluteWidth(200);
               img.scaleAbsoluteHeight(50);
               img.setAlignment(com.itextpdf.text.Image.ALIGN_CENTER);
              
            PdfWriter.getInstance(document, new FileOutputStream(String.valueOf(DateLyoum + ".pdf")));//yyyy-MM-dd
            document.open();
            Paragraph ph1 = new Paragraph("les produits :" );
            Paragraph ph2 = new Paragraph(".");
            PdfPTable table = new PdfPTable(3);

            //On créer l'objet cellule.
            PdfPCell cell;

            //contenu du tableau.
            table.addCell("nom");
            table.addCell("description");
            table.addCell("prix de vente");
            Produit r = new Produit();
            sp.read_all().forEach(e-> {
                //  table.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(String.valueOf(e.getName()));
                table.addCell(String.valueOf(e.getDescription()));
                table.addCell(String.valueOf(e.getSellprice()));
                
            }
            );
            document.add(img);
            document.add(ph1);
            document.add(ph2);
            document.add(table);
            //  document.addAuthor("Bike");
            // AlertDialog.showNotification("Creation PDF ", "Votre fichier PDF a ete cree avec success", AlertDialog.image_checked);
        } catch (Exception e) {
            System.out.println(e);
        }
        document.close();

        ///Open FilePdf
        File file = new File(DateLyoum + ".pdf");
        Desktop desktop = Desktop.getDesktop();
        if (file.exists()) //checks file exists or not  
        {
            desktop.open(file); //opens the specified file   
        }

    
    }}
