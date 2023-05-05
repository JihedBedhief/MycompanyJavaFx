/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import services.ServiceDivision;

/**
 *
 * @author user
 */
public class MyCompany extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        try {
           // Parent root = FXMLLoader.load(getClass(). getResource("Login.fxml"));
             //Parent root = FXMLLoader.load(getClass().getResource("/gui/AfficherPointVente.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("/gui/AfficherVente.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("/gui/ShowPointVente.fxml"));
            Scene scene = new Scene(root);
          

        //sorry about that - Windows defender issue.
        //move around heresss
      
            
            
            primaryStage.setTitle("client");
            primaryStage.setScene(scene);
            primaryStage.show();         
            
        } catch (IOException ex) {
            Logger.getLogger(MyCompany.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        launch(args);
        ServiceDivision sd = new ServiceDivision();
        System.out.println(sd.readAll());
    }
    
}
