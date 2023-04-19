/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionrh.gui;

import static com.sun.javafx.animation.TickCalculation.sub;
import gestionrh.entities.Contrat;
import gestionrh.entities.Employees;
import gestionrh.services.ContratService;
import gestionrh.services.EmployeesService;
import static java.awt.PageAttributes.MediaType.C;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Date ; 
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.mail.Message.RecipientType;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author derou
 */
public class ContratFxmlController implements Initializable {
    ContratService rec = new ContratService();
    
    
    
    EmployeesService es = new EmployeesService() ; 

    @FXML
    private AnchorPane datedbtn;
    @FXML
    private ComboBox<String> typebtn;
    @FXML
    private DatePicker datefbtn;
    @FXML
    private TextField salairebtn;
    @FXML
    private ComboBox<String> empcombo;
    @FXML
    private Button ajoutbtn;
    @FXML
    private DatePicker datedhbtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       // salairebtn.setDisable(true);
        List <Contrat> listecontrat = rec.listerContrats();
         ArrayList<Employees> listeEmpl = es.listerEmployees() ; 
    //    for( Contrat c: listecontrat){
            for(Employees em : listeEmpl){
          // if(!c.compareTo(em.getId())) 
        
            empcombo.getItems().add(em.getNom());
           
        }
        //   }
        
        List<String> cont = Arrays.asList(
            "CDI", "CIVP"
           
        );
            
        typebtn.setItems(FXCollections.observableArrayList(cont));
         
    


    }    
    
    
    public void sendmail(String email , Contrat name , String prenom){
       
           Properties props=new Properties();
        props.put("mail.smtp.host","smtp.gmail.com");
        props.put("mail.smtp.port",465);
        props.put("mail.smtp.user","derouiche568@gmail.com");
        props.put("mail.smtp.auth",true);
        props.put("mail.smtp.starttls.enable",true);
        props.put("mail.smtp.debug",true);
        props.put("mail.smtp.socketFactory.port",465);
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback",false); 
        
        try {             
                Session session = Session.getDefaultInstance(props, null);
                session.setDebug(true);
                MimeMessage message = new MimeMessage(session);
                message.setText("Your contract is : " + name);
                message.setSubject("Contrat");
                message.setFrom(new InternetAddress("derouiche568@gmail.com"));
                message.addRecipient(RecipientType.TO, new InternetAddress(email.trim()));
                message.saveChanges();
                try
                {
                Transport transport = session.getTransport("smtp");
                transport.connect("smtp.gmail.com","derouiche568@gmail.com","rayxxvzckpdqvrvs");
                transport.sendMessage(message, message.getAllRecipients());
                transport.close();
                
            
                
                JOptionPane.showMessageDialog(null,"le contrat a envoyé avec succée"); 
                }catch(Exception e)
                {
                    JOptionPane.showMessageDialog(null,"Please check your internet connection");
                }              
            
        } catch (Exception e) {
            e.printStackTrace();  
            JOptionPane.showMessageDialog(null,e);
        }  
       }
    
    

    @FXML
    private void ajouter(ActionEvent event) throws IOException {
        
         LocalDate dateDebut = datefbtn.getValue();
        LocalDate dateFin = datedhbtn.getValue();
        
        if (typebtn.getSelectionModel().isEmpty()  || empcombo.getSelectionModel().isEmpty() || salairebtn.getText().equals("") || datefbtn.getValue() == null || datedhbtn.getValue() == null) {
    Alert alert = new Alert(Alert.AlertType.ERROR, "Veuillez vérifier les champs", ButtonType.OK);
    alert.showAndWait();
}

                else if (!rec.validerSalaire(Double.parseDouble(salairebtn.getText()))) {
                               Alert alert = new Alert(Alert.AlertType.ERROR, "valider Salaire", ButtonType.OK);
                               alert.showAndWait();
             }
                else if (rec.validerDate(datefbtn.getValue(),datedhbtn.getValue()) ){
                               Alert alert = new Alert(Alert.AlertType.ERROR, "valider la date debut et date fin", ButtonType.OK);
                               alert.showAndWait();
             }else{
       
       
          String type= typebtn.getValue();    
          java.sql.Date localDateDebut = Date.valueOf(datefbtn.valueProperty().getValue());
          java.sql.Date localDateFin = Date.valueOf(datefbtn.valueProperty().getValue());
          double salaire = Double.parseDouble(salairebtn.getText());         
          Employees e = es.listerEmployeesparNom(empcombo.getValue());
          Contrat c = new Contrat(type,salaire,localDateDebut,localDateFin,es.listerEmployeesparNom(empcombo.getValue()));
          rec.ajouter_contrat(c);
            sendmail(e.getEmail(), c,e.getPrenom());
          
                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow(); 
                    //stage.close();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("ListeContrat.fxml")));       
                    stage.setScene(scene);
                    stage.setTitle("Listes des Contrats");
     
                }
               
    }
    
}
