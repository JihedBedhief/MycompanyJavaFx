/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Entity.User;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
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
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;
import services.ServiceUser;

/**
 * FXML Controller class
 *
 * @author MediaCenter Zaghouan
 */
public class LoginController implements Initializable {

    @FXML
    private TextField email;
    @FXML
    private PasswordField password;
    @FXML
    private Button login;
    @FXML
    private Hyperlink regis;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Connexion(ActionEvent event) throws SQLException, IOException {
          ServiceUser met = new ServiceUser();
        User u = met.login(email.getText(), password.getText());
                    System.out.println(u);

        if (u.getId()== 0) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Connexion echoué");
            alert.setHeaderText("Connexion impossible!!");
            alert.setContentText("Vérifier vos paramétres");
            Optional<ButtonType> result = alert.showAndWait();

        }
        else if(u.getRoles().equals("[\\\"ROLE_USER\\\"]")) {
        

           Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow(); 
                    stage.close();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Abonnement.fxml")));       
                    stage.setScene(scene);
                    stage.setTitle("Espace Utilisateur");
                    stage.show(); 
        } else if(u.getRoles().equals("[\\\"ROLE_ADMIN\\\"]")) {

            Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow(); 
                    stage.close();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Division.fxml")));       
                    stage.setScene(scene);
                    stage.setTitle("Espace Admin");
                    stage.show(); 
        } 
    }

    @FXML
    private void Register(ActionEvent event) throws IOException {
        
        Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow(); 
                    stage.close();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Inscription.fxml")));       
                    stage.setScene(scene);
                    stage.setTitle("Login");
                    
                    stage.show(); 
    }
    
}
