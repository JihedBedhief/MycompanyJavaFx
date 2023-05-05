/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Entity.User;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import mycompany.database.Database;
import services.ServiceUser;

/**
 * FXML Controller class
 *
 * @author MediaCenter Zaghouan
 */
public class ProfileController implements Initializable {

    @FXML
    private Button up;
    @FXML
    private TextField nom;
    @FXML
    private Button bb;
    @FXML
    private TextField Email;
    @FXML
    private TextField prenom;
    @FXML
    private TextField nd;
    @FXML
    private PasswordField oldpass;
    @FXML
    private PasswordField newpass;
    @FXML
    private PasswordField confpass;
    @FXML
    private ImageView img;
    @FXML
    private Button bb1;
    @FXML
    private TextField urlx;
    @FXML
    private TextField domain;
    @FXML
    private TextField pays;
    
      private Connection cnx;


    
     public ProfileController(){
        Connection cnx = Database.getInstance().getCnx();
    }
    
    ServiceUser us = new ServiceUser();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
           try {
            // TODO
            String s = User.connecte.getImage();
            File file = new File(s);
            System.out.println(s);
            Image image = new Image(file.toURI().toURL().toExternalForm());
            img.setImage(image);
            nom.setText(User.connecte.getNom());
            pays.setText(User.connecte.getPays());
            Email.setText(User.connecte.getEmail());
            nd.setText(User.connecte.getAdresse());
            prenom.setText(User.connecte.getMatricule());
            domain.setText(User.connecte.getDomaine());
            Email.setDisable(true);
            urlx.setText(User.connecte.getImage());
            nd.setDisable(true);
            urlx.setVisible(false);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }    

    @FXML
    private void UpdatePassword(ActionEvent event) throws SQLException {
                if (oldpass.getText().isEmpty()|| newpass.getText().isEmpty() || confpass.getText().isEmpty())
         {
             Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Erreur");
            
            alert.setContentText("Please fill all input");
            Optional<ButtonType> result = alert.showAndWait();}
           
            else if(!Verifpass()){
                 Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Erreur");
            
            alert.setContentText("Incorrect password");
            Optional<ButtonType> result = alert.showAndWait();
             }
           
          
           else if(newpass.getText().length()<8){
                 Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Erreur");
            
            alert.setContentText("Password is too weak");
            Optional<ButtonType> result = alert.showAndWait();
           
           }
             else if(!newpass.getText().equals(confpass.getText())){
                 Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Erreur");
            
            alert.setContentText("confirm your password");
            Optional<ButtonType> result = alert.showAndWait();
             }
          

         else {
           
           Modifier();

           oldpass.clear();
           newpass.clear();
           confpass.clear();

           
           
         
           }
       
    }

    @FXML
    private void changedata(ActionEvent event) throws SQLException {
        
                     if (nom.getText().isEmpty()|| prenom.getText().isEmpty() || nd.getText().isEmpty())
         {
             Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Erreur");
            
            alert.setContentText("Please fill all input");
            Optional<ButtonType> result = alert.showAndWait();}
                 
                    else {
                         Modifier2();
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("");
            
            alert.setContentText("ok");
            Optional<ButtonType> result = alert.showAndWait();
                    
                    }
    }
    
    
               void Modifier2() throws SQLException {
        String n =nom.getText();
        String p = prenom.getText();
String image = urlx.getText();
String adresse = nd.getText();
String domaine = domain.getText();
String pay = pays.getText();
User u = new User(User.connecte.getId(),n,image);
//     int i = User.connecte.getId();
//
//u.setId(i);
//u.setNom(n);
//u.setImage(image);

                   System.out.println(u);



            us.Up(u);
        
  
        

    }
     
     
             
     
     
     
     
     
          void Modifier() {
        String n = us.encryptThisString(newpass.getText());
     int i = User.connecte.getId();
        String sql ="update user set password='"+n+"' where id='"+i+"'";
        try {
         PreparedStatement st = (PreparedStatement) cnx.prepareStatement(sql);
         st.executeUpdate();
    }catch(SQLException ex){
        ex.getMessage();
    }
        

    }
     
     
      public boolean Verifpass() throws SQLException{
      
            String sql = "select * from user where password = ?";
            PreparedStatement ps =  cnx.prepareStatement(sql);
            ps.setString(1,us.encryptThisString(oldpass.getText()));
            
            ResultSet rs = ps.executeQuery();
             boolean ok=false;
            if(rs.next()){
         if (rs.getString(5).equals(us.encryptThisString(oldpass.getText())))
             ok=true;
     }
     return ok;
          }
      
    

      @FXML
    private void Ajoutefichier(ActionEvent event) throws MalformedURLException {
             FileChooser f = new FileChooser();
            
        File S = f.showOpenDialog(null);
         if(S!=null){
       
        
         String n = S.getAbsolutePath();
        urlx.setText(S.getAbsolutePath());
        File file = new File(urlx.getText());
            Image image = new Image(file.toURI().toURL().toExternalForm());
            img.setImage(image);
        
         }
    }
    
        
      

  
    
}
