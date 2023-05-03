/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Properties;
import java.util.Random;
import java.util.ResourceBundle;
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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;
import mycompany.database.Database;
import services.ServiceUser;

/**
 * FXML Controller class
 *
 * @author MediaCenter Zaghouan
 */
public class InscriptionController implements Initializable {

    @FXML
    private Button close;
    @FXML
    private Button goback;
    @FXML
    private TextField email;
    @FXML
    private TextField adresse;
    @FXML
    private TextField matricule;
    @FXML
    private TextField pays;
    @FXML
    private PasswordField conf_pass;
    @FXML
    private PasswordField pass;
    @FXML
    private Button register;
    @FXML
    private TextField jTextField7;
    @FXML
    private TextField code;
    @FXML
    private Label alert;
    @FXML
    private Button vrif;
    @FXML
    private Label alert2;
    @FXML
    private ComboBox<String> comm;
    @FXML
    private TextField domaine;
    @FXML
    private TextField telephone;
    @FXML
    private TextField nom;

    /**
     * Initializes the controller class.
     */
    
    public InscriptionController(){
        cnx = Database.getInstance().getCnx();
    }
         private Connection cnx;
         
                          ServiceUser us = new ServiceUser();

                          
                           public void Randum(){
       
       Random rd = new Random();
       jTextField7.setText(""+rd.nextInt(1000+1));
       
       }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
            comm.setItems(us.RecupCombo());
                Randum();
                jTextField7.setVisible(false);

        code.setVisible(false);
        vrif.setVisible(false);
        alert.setVisible(false);
                alert2.setVisible(false);

                
                

        // TODO
    }    
    
     public void sendmail(){
       
           Properties props=new Properties();
        props.put("mail.smtp.host","smtp.gmail.com");
        props.put("mail.smtp.port",465);
        props.put("mail.smtp.user","pidevmycompany2023@gmail.com");
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
                message.setText("Your OTP is " + jTextField7.getText());
                message.setSubject("OTP For your Neftola Account");
                message.setFrom(new InternetAddress("pidevmycompany2023@gmail.com"));
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(email.getText().trim()));
                message.saveChanges();
                try
                {
                Transport transport = session.getTransport("smtp");
                transport.connect("smtp.gmail.com","pidevmycompany2023@gmail.com","dsliqejzczcxqkxz");
                transport.sendMessage(message, message.getAllRecipients());
                transport.close();
                
            
                
                JOptionPane.showMessageDialog(null,"OTP has send to your Email id"); 
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
    private void exit(ActionEvent event) {
    }

    @FXML
    private void Goback(ActionEvent event) {
    }

    @FXML
    private void AddUser(ActionEvent event) {
                  if (email.getText().isEmpty()|| matricule.getText().isEmpty() || adresse.getText().isEmpty() || domaine.getText().isEmpty() || pays.getText().isEmpty() || comm.getValue().isEmpty()|| nom.getText().isEmpty()|| telephone.getText().isEmpty())
         {
             Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Erreur");
            
            alert.setContentText("Please fill all input");
            Optional<ButtonType> result = alert.showAndWait();}
           
         
           else if(!us.VerifEmail(email.getText())){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Erreur");
            
            alert.setContentText("invalid Email format");
            Optional<ButtonType> result = alert.showAndWait();
           }
           else if(pass.getText().length()<8){
                 Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Erreur");
            
            alert.setContentText("Password is too weak");
            Optional<ButtonType> result = alert.showAndWait();
           
           }
            else if(telephone.getText().length()<8){
                 Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Erreur");
            
            alert.setContentText("le  numero de telephone doit contenir 8 chiffre");
            Optional<ButtonType> result = alert.showAndWait();
           
           }
             else if(!us.validatePassword(email.getText())){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Erreur");
            
            alert.setContentText("invalid Email format");
            Optional<ButtonType> result = alert.showAndWait();
           }
             else if(!pass.getText().equals(conf_pass.getText())){
                 Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Erreur");
            
            alert.setContentText("confirm your password");
            Optional<ButtonType> result = alert.showAndWait();
             }
            
            
           
           
         else {
           
           sendmail();
           
//           JOptionPane.showMessageDialog(null,"Account created successfully");

          
           
           email.setVisible(false);
           matricule.setVisible(false);
           adresse.setVisible(false);
           domaine.setVisible(false);
           pays.setVisible(false);
           conf_pass.setVisible(false);
           telephone.setVisible(false);
          code.setVisible(true);
           vrif.setVisible(true);
           alert.setVisible(true);
           alert2.setVisible(true);
           pass.setVisible(false);
           telephone.setVisible(false);
           nom.setVisible(false);


           comm.setVisible(false);
//           
//           
//         
           }
       
    }
    
      private void EnregistrerVersBase2() {
           java.sql.Connection cnx;
     cnx = Database.getInstance().getCnx();
             
           try {
            String sql = "INSERT INTO user (abonnement_id,email,password,matricule,adresse,domaine,pays,telephone,nom) VALUES (?,?,?,?,?,?,?,?,?)";
            
        PreparedStatement st = (PreparedStatement) cnx.prepareStatement(sql);
        String n = telephone.getText();
        int numero = Integer.valueOf(n);
               System.out.println(email.getText());
                              System.out.println(us.encryptThisString(pass.getText()));
               System.out.println(matricule.getText());
               System.out.println(adresse.getText());
               System.out.println(domaine.getText());
               System.out.println(pays.getText());

               System.out.println(numero);
                              System.out.println(nom.getText());


        
        int i = comm.getSelectionModel().getSelectedIndex()+1;
               System.out.println(i);
            st.setInt(1, i);
            st.setString(2, email.getText());
            st.setString(3, us.encryptThisString(pass.getText()));
              st.setString(4, matricule.getText());
            st.setString(5, adresse.getText());
              st.setString(6, domaine.getText());
            st.setString(7, pays.getText());
            st.setInt(8, numero);
            st.setString(9, nom.getText());

        
            st.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error");
        }
    } 

    @FXML
    private void Veifcode(ActionEvent event) throws IOException {
        if(!code.getText().equals(jTextField7.getText())){
       
       
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Erreur");
            
            alert.setContentText("invalid code");
            Optional<ButtonType> result = alert.showAndWait();
       
       }
       else {
       EnregistrerVersBase2();
                  JOptionPane.showMessageDialog(null,"Account created successfully");
               email.setVisible(true);
           matricule.setVisible(true);
           adresse.setVisible(true);
           domaine.setVisible(true);
           pays.setVisible(true);
           conf_pass.setVisible(true);
           telephone.setVisible(true);
           pass.setVisible(true);
           telephone.setVisible(true);
           nom.setVisible(true);
           comm.setVisible(true);
          code.setVisible(false);
           vrif.setVisible(false);
           alert.setVisible(false);
           alert2.setVisible(false);

              email.clear();
           matricule.clear();
           adresse.clear();
           domaine.clear();
           pays.clear();
           conf_pass.clear();
           telephone.clear();
           pass.clear();
           telephone.clear();
           nom.clear();
           
           
           Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow(); 
                    stage.close();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Login.fxml")));       
                    stage.setScene(scene);
                    stage.setTitle("Login");
                    
                    stage.show();
 
       }
    }
    
}
