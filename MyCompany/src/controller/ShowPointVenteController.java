/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;



import Entity.PointVente;
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
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import services.PointVenteService;
import services.VenteService;


/**
 * FXML Controller class
 *
 * @author dell
 */
public class ShowPointVenteController implements Initializable {

    @FXML
    private ListView<PointVente> listView;
   
    ObservableList<PointVente> data;
    
    public static int idpointShow ;
    
    PointVenteService ds = new PointVenteService();

   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            data = (ObservableList<PointVente>) ds.getAllPointVente();   
            listView.setItems(data);
            listView.setCellFactory((ListView<PointVente> param) -> new ListViewPointVente());
            
            
            // TODO
        } catch (SQLDataException ex) {
            Logger.getLogger(ShowPointVenteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void detail(ActionEvent event) {
           ObservableList<PointVente> e = listView.getSelectionModel().getSelectedItems();     
               for (PointVente m : e) {
               idpointShow = m.getId();  
                }
                     Parent root;
               try {
              root = FXMLLoader.load(getClass().getResource("/gui/ShowVente.fxml"));
               Stage myWindow = (Stage) listView.getScene().getWindow();
               Scene sc = new Scene(root);
               myWindow.setScene(sc);
               myWindow.setTitle("page name");
                            //myWindow.setFullScreen(true);
               myWindow.show();
               } catch (IOException ex) {
               Logger.getLogger(ShowPointVenteController.class.getName()).log(Level.SEVERE, null, ex);
               }
    }








    }

    

