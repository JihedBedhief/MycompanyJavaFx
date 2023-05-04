/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionrh.gui;

import static com.sun.javafx.animation.TickCalculation.sub;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
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
    
     EmployeesService gr = new EmployeesService();
    String cinfind;
    String formA;
    String formP;
    String exper;
    
    
    
    EmployeesService es = new EmployeesService() ; 

    @FXML
    private AnchorPane datedbtn;
    @FXML
    private ComboBox<String> typebtn;
    @FXML
    private DatePicker datefbtn;
    @FXML
    private TextField salairebtn;
    private ComboBox<String> empcombo;
    @FXML
    private Button ajoutbtn;
    @FXML
    private DatePicker datedhbtn;
    @FXML
    private Label nomempbtn;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       //
     
         ArrayList<Employees> listeEmpl = es.listerEmployees() ; 
           // for(Employees em : listeEmpl){     
           // empcombo.getItems().add(em.getCin());   }
            
            int lastIndex = listeEmpl.size() - 1; 
            String dernierNomEmp = listeEmpl.get(lastIndex).getNom();
            cinfind = listeEmpl.get(lastIndex).getCin();
            formA = listeEmpl.get(lastIndex).getFormationA();
            formP = listeEmpl.get(lastIndex).getFormationP();
            exper = listeEmpl.get(lastIndex).getExperience();  
            double salary = calculateSalary(formA, formP, exper);
            salairebtn.setText(String.format("%.2f €", salary));
            nomempbtn.setText(dernierNomEmp);
    
            
        List<String> cont = Arrays.asList("CDI", "CIVP");       
        typebtn.setItems(FXCollections.observableArrayList(cont));
        
            
    }    
    
      public static final String ACCOUNT_SID = "AC75038046937bd10e2a96db08e57129da";
    public static final String AUTH_TOKEN = "35cd0e2c6b64d006d5ed1532126d8f07";
    public static final String AUTH_TOKEN2 = "cd6dddaf3a44d9ad5613bf197048b56f";
    
        public void sendsms(String code ){
    
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber("+21698781664"),
                new com.twilio.type.PhoneNumber("+17407167788"),
                code)
            .create();

        System.out.println(message.getSid());

        
    
    
    
    }
    
    
   
    
    public void sendmail(String email, Contrat name, String prenom) {
       
    Properties props = new Properties();
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.port", 465);
    props.put("mail.smtp.user", "pidevmycompany2023@gmail.com");
    props.put("mail.smtp.auth", true);
    props.put("mail.smtp.starttls.enable", true);
    props.put("mail.smtp.debug", true);
    props.put("mail.smtp.socketFactory.port", 465);
    props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
    props.put("mail.smtp.socketFactory.fallback", false); 
        
    try {             
        Session session = Session.getDefaultInstance(props, null);
        session.setDebug(true);
        MimeMessage message = new MimeMessage(session);
        message.setSubject("Contract: " + name);
        message.setFrom(new InternetAddress("pidevmycompany2023@gmail.com"));
        message.addRecipient(RecipientType.TO, new InternetAddress(email.trim()));
        message.setText("Dear " + prenom + ",\n\nPlease find attached your contract. If you have any questions, please let me know.\n\nBest regards,\n[Your name]");
        message.saveChanges();
        try {
            Transport transport = session.getTransport("smtp");
            transport.connect("smtp.gmail.com", "pidevmycompany2023@gmail.com","dsliqejzczcxqkxz");
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
                
            JOptionPane.showMessageDialog(null, "The contract has been sent successfully."); 
        } catch (MessagingException me) {
            JOptionPane.showMessageDialog(null, "Failed to send the contract. Please check your internet connection.");
            me.printStackTrace();
        }              
    } catch (Exception e) {
        e.printStackTrace();  
        JOptionPane.showMessageDialog(null, e);
    }  
}
    
    //***********************************************************
//    public void sendmail(){
//       
//           Properties props=new Properties();
//        props.put("mail.smtp.host","smtp.gmail.com");
//        props.put("mail.smtp.port",465);
//        props.put("mail.smtp.user","pidevmycompany2023@gmail.com");
//        props.put("mail.smtp.auth",true);
//        props.put("mail.smtp.starttls.enable",true);
//        props.put("mail.smtp.debug",true);
//        props.put("mail.smtp.socketFactory.port",465);
//        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//        props.put("mail.smtp.socketFactory.fallback",false); 
//        
//        try {             
//                Session session = Session.getDefaultInstance(props, null);
//                session.setDebug(true);
//                MimeMessage message = new MimeMessage(session);
//                message.setText("Your OTP is " + jTextField7.getText());
//                message.setSubject("OTP For your Neftola Account");
//                message.setFrom(new InternetAddress("pidevmycompany2023@gmail.com"));
//                message.addRecipient(Message.RecipientType.TO, new InternetAddress(email.getText().trim()));
//                message.saveChanges();
//                try
//                {
//                Transport transport = session.getTransport("smtp");
//                transport.connect("smtp.gmail.com","pidevmycompany2023@gmail.com","dsliqejzczcxqkxz");
//                transport.sendMessage(message, message.getAllRecipients());
//                transport.close();
//                
//            
//                
//                JOptionPane.showMessageDialog(null,"OTP has send to your Email id"); 
//                }catch(Exception e)
//                {
//                    JOptionPane.showMessageDialog(null,"Please check your internet connection");
//                }              
//            
//        } catch (Exception e) {
//            e.printStackTrace();  
//            JOptionPane.showMessageDialog(null,e);
//        }  
//       }
    //***********************************************************

    
    

    @FXML
    private void ajouter(ActionEvent event) throws IOException {
        
         LocalDate dateDebut = datefbtn.getValue();
        LocalDate dateFin = datedhbtn.getValue();
        
        if (typebtn.getSelectionModel().isEmpty()  || salairebtn.getText().equals("") || datefbtn.getValue() == null || datedhbtn.getValue() == null) {
    Alert alert = new Alert(Alert.AlertType.ERROR, "Veuillez vérifier les champs", ButtonType.OK);
    alert.showAndWait();
}

//                else if (!rec.validerSalaire(Double.parseDouble(salairebtn.getText()))) {
//                               Alert alert = new Alert(Alert.AlertType.ERROR, "valider Salaire", ButtonType.OK);
//                               alert.showAndWait();
//             }
                else if (rec.validerDate(datefbtn.getValue(),datedhbtn.getValue()) ){
                               Alert alert = new Alert(Alert.AlertType.ERROR, "valider la date debut et date fin", ButtonType.OK);
                               alert.showAndWait();
             }else{
       
          
          String type= typebtn.getValue();    
          java.sql.Date localDateDebut = Date.valueOf(datedhbtn.valueProperty().getValue());
          java.sql.Date localDateFin = Date.valueOf(datefbtn.valueProperty().getValue());
          //double salaire = Double.parseDouble(salairebtn.getText());
         // String salaire =salairebtn.getText();
         // double salary = Double.parseDouble(salaire);
          String inputString = salairebtn.getText();
          String numericString = inputString.replaceAll("[^\\d.,-]", "").replace(",", ".");
          double salary = Double.parseDouble(numericString);

          Employees e = es.listerEmployeesparCin(cinfind);
          Contrat c = new Contrat(type,salary,localDateDebut,localDateFin,es.listerEmployeesparCin(cinfind));
          rec.ajouter_contrat(c);
          
 
         
         salairebtn.clear();
         nomempbtn.setText("");
         typebtn.setValue("");
         datedhbtn.setValue(null);
         datefbtn.setValue(null);
         
                 sendmail(e.getEmail(), c,e.getPrenom());              
                // sendsms("sdsdsdsd");
                 
                  Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow(); 
                    //stage.close();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("ListeContrat.fxml")));       
                    stage.setScene(scene);
                    stage.setTitle("Listes des Contrats");
                    
                }
               
    }
   public double calculateSalary(String formA, String formP, String exper) {
    double baseSalary = 1000; 
    

    if (formA.equals("Licence")) {
        if (formP.equals("Superieur")) {
            baseSalary += 500;
        } else if (formP.equals("Professionnel")) {
            baseSalary += 1000;
        }
    } else if (formA.equals("Master")) {
        if (formP.equals("Superieur")) {
            baseSalary += 2000;
        } else if (formP.equals("Professionnel")) {
            baseSalary += 1800;
        }
    } else if (formA.equals("Ingénierie")) {
        if (formP.equals("Professionnel")) {
            baseSalary += 1900;
        } else if (formP.equals("Superieur")) {
            baseSalary += 2200;
        }
    } else if (formA.equals("Doctorat")) {
        baseSalary += 2500;
    }
    
    
    if (exper.equals("Il n'a aucune experience")) {
        baseSalary += 0;
    } else if (exper.equals("Il a moins d'un an d'experience")) {
        baseSalary += 800;
    }else if (exper.equals("Il a plus d'un an d'experience")) {
        baseSalary += 2000;
    }
    
    return baseSalary;
}

    
}
