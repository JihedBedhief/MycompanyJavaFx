/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;



import static controller.ShowPointVenteController.idpointShow;
import Entity.Vente;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLDataException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import services.VenteService;


/**
 * FXML Controller class
 *
 * @author dell
 */
public class ShowVenteController implements Initializable {

    @FXML
    private ListView<Vente> listView;
   
    ObservableList<Vente> data;
    
    
    VenteService ds = new VenteService();

   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            data = (ObservableList<Vente>) ds.getAllVenteByPointVente(idpointShow);   
            listView.setItems(data);
            listView.setCellFactory((ListView<Vente> param) -> new ListViewVente());
            
            
            // TODO
        } catch (SQLDataException ex) {
            Logger.getLogger(ShowVenteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    









    }

    

