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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author hassen
 */
public class DashboarUserController implements Initializable {

    @FXML
    private ImageView img;
    @FXML
    private Pane pane;
    @FXML
    private Label bvn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
          try {
            // TODO
            
            
            String s = User.connecte.getImage().toString();
            
            File file = new File(s);
            
            
            System.out.println(s);
            Image image = new Image(file.toURI().toURL().toExternalForm());
            img.setImage(image);
            
            bvn.setText("Welcome"+" "+User.connecte.getNom());
            

            
            
        } catch (MalformedURLException ex) {
            Logger.getLogger(DashboarUserController.class.getName()).log(Level.SEVERE, null, ex);
        }

        
    }    
    
}
