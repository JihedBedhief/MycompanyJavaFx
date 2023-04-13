/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.PointVente;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLDataException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import service.PointVenteService;

/**
 * FXML Controller class
 *
 * @author Abderrazekbenhamouda
 */
public class AfficherPointVenteController implements Initializable {

    @FXML
    private TableView<PointVente> table;
    @FXML
    private TableColumn<PointVente, String> nom;
    @FXML
    private TableColumn<PointVente, String> region;
    @FXML
    private TableColumn<PointVente, String> email;
    @FXML
    private TableColumn<PointVente, String> ville;
    @FXML
    private TableColumn<PointVente, Integer> code;
    @FXML
    private TableColumn<PointVente, Integer> tel;
    
    
    private ObservableList<PointVente> UserData = FXCollections.observableArrayList();
    
    PointVenteService pv = new PointVenteService();
    
    public static int ide ;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
                try {
            List<PointVente> listb= new ArrayList<PointVente>();
            
            listb = pv.getAllPointVente();
     
            UserData.clear();
            UserData.addAll(listb);
            table.setItems(UserData);
            
            nom.setCellValueFactory
                      (
                              new PropertyValueFactory<>("nom")
                      );
            region.setCellValueFactory
                      (
                              new PropertyValueFactory<>("region")
                      );
            email.setCellValueFactory
                      (
                              new PropertyValueFactory<>("email")
                      );
            ville.setCellValueFactory
                      (
                              new PropertyValueFactory<>("ville")
                      );
            code.setCellValueFactory
                      (
                              new PropertyValueFactory<>("codePostal")
                      );
            tel.setCellValueFactory
                      (
                              new PropertyValueFactory<>("tel")
                      );
            
        } catch (SQLDataException ex) {
            Logger.getLogger(AfficherPointVenteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void Ajouter(ActionEvent event) {
        
                      Parent root;
               try {
              root = FXMLLoader.load(getClass().getResource("/gui/AjouterPointVente.fxml"));
               Stage myWindow = (Stage) table.getScene().getWindow();
               Scene sc = new Scene(root);
               myWindow.setScene(sc);
               myWindow.setTitle("page name");
                            //myWindow.setFullScreen(true);
               myWindow.show();
               } catch (IOException ex) {
               Logger.getLogger(AfficherPointVenteController.class.getName()).log(Level.SEVERE, null, ex);
               }
        
        
    }


    @FXML
    private void Modifer(ActionEvent event) {
        
              ide =  table.getSelectionModel().getSelectedItem().getId();     

        
                  Parent root;
               try {
              root = FXMLLoader.load(getClass().getResource("/gui/ModifierPointVente.fxml"));
               Stage myWindow = (Stage) table.getScene().getWindow();
               Scene sc = new Scene(root);
               myWindow.setScene(sc);
               myWindow.setTitle("page name");
                            //myWindow.setFullScreen(true);
               myWindow.show();
               } catch (IOException ex) {
               Logger.getLogger(AfficherPointVenteController.class.getName()).log(Level.SEVERE, null, ex);
               }
        
    }

    @FXML
    private void Supprimer(ActionEvent event) throws SQLDataException {
        
        int id =  table.getSelectionModel().getSelectedItem().getId();     
        pv.deletePointVente(id);
        resetTableData();
    }
    
  public void resetTableData() throws SQLDataException
    {
        List<PointVente> lisre = new ArrayList<>();
        lisre = pv.getAllPointVente();
        ObservableList<PointVente> data = FXCollections.observableArrayList(lisre);
        table.setItems(data);
    }
    
}
