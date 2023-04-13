/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static controller.AfficherPointVenteController.ide;
import entity.PointVente;
import entity.Vente;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLDataException;
import java.util.ArrayList;
import java.util.Date;
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
import service.VenteService;

/**
 * FXML Controller class
 *
 * @author Abderrazekbenhamouda
 */
public class AfficherVenteController implements Initializable {

    @FXML
    private TableView<Vente> table;
    @FXML
    private TableColumn<Vente , String> produit;
    @FXML
    private TableColumn<Vente , String> client;
    @FXML
    private TableColumn<Vente , Integer> quanatite;
    @FXML
    private TableColumn<Vente, Double> prix;
    @FXML
    private TableColumn<Vente , Double> taxe;
    @FXML
    private TableColumn<Vente , Double> prixtotal;
    @FXML
    private TableColumn<Vente, Date> date;
    
    
        
    private ObservableList<Vente> UserData = FXCollections.observableArrayList();
    
    VenteService pv = new VenteService();
    
    public static int idVente ;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                     try {
            List<Vente> listb= new ArrayList<Vente>();
            
            listb = pv.getAllVente();
     
            UserData.clear();
            UserData.addAll(listb);
            table.setItems(UserData);
            
            produit.setCellValueFactory
                      (
                              new PropertyValueFactory<>("produit")
                      );
            client.setCellValueFactory
                      (
                              new PropertyValueFactory<>("client")
                      );
            prix.setCellValueFactory
                      (
                              new PropertyValueFactory<>("prix_unite")
                      );
            prixtotal.setCellValueFactory
                      (
                              new PropertyValueFactory<>("prix_totale")
                      );
            taxe.setCellValueFactory
                      (
                              new PropertyValueFactory<>("taxe")
                      );
            quanatite.setCellValueFactory
                      (
                              new PropertyValueFactory<>("quantite")
                      );
                        date.setCellValueFactory
                      (
                              new PropertyValueFactory<>("date")
                      );
            
        } catch (SQLDataException ex) {
            Logger.getLogger(AfficherVenteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void Ajouter(ActionEvent event) {
        
                             Parent root;
               try {
              root = FXMLLoader.load(getClass().getResource("/gui/AjouterVente.fxml"));
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
        pv.deleteVente(id);
        resetTableData();
        
    }

    @FXML
    private void Modifer(ActionEvent event) {
        
                    idVente =  table.getSelectionModel().getSelectedItem().getId();     

        
                  Parent root;
               try {
              root = FXMLLoader.load(getClass().getResource("/gui/ModifierVente.fxml"));
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
    
    
      public void resetTableData() throws SQLDataException
    {
        List<Vente> lisre = new ArrayList<>();
        lisre = pv.getAllVente();
        ObservableList<Vente> data = FXCollections.observableArrayList(lisre);
        table.setItems(data);
    }
}
