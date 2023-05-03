/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Entity.Abonnement;
import Entity.Division;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;
import mycompany.database.Database;
import services.ServiceAbonnement;
import services.ServiceDivision;

/**
 * FXML Controller class
 *
 * @author hassen
 */
public class AbonnementController implements Initializable {

    @FXML
    private TableView<Abonnement> tab;
    @FXML
    private TableColumn<Abonnement, Integer> id;
    @FXML
    private TableColumn<Abonnement, String> type;
    @FXML
    private TableColumn<Abonnement, String> dur;
    @FXML
    private TableColumn<Abonnement, String> frais;
    @FXML
    private TextField t;
    @FXML
    private TextField idx;
    @FXML
    private TextField F;
    @FXML
    private TextField dure;

    
      public AbonnementController(){
        Connection cnx = Database.getInstance().getCnx();
    }
      private Connection cnx;
    /**
     * Initializes the controller class.
     */
      
       ServiceAbonnement sr = new ServiceAbonnement();
      
         public void table(){
         
        id.setCellValueFactory( new PropertyValueFactory<>("id"));
        type.setCellValueFactory(new PropertyValueFactory <>("type"));
         dur.setCellValueFactory(new PropertyValueFactory <>("duree"));
         frais.setCellValueFactory(new PropertyValueFactory <>("frais"));



        tab.setItems(sr.RecupBase()); 



}
         
           public void onEdit() {
               
               java.sql.Connection cnx;
     cnx = Database.getInstance().getCnx();
     
    if (tab.getSelectionModel().getSelectedItem() != null) {
          Abonnement f = tab.getSelectionModel().getSelectedItem();
      String id=String.valueOf(f.getId());
      idx.setText(id);
          t.setText(f.getType());
            F.setText(f.getFrais());
          dure.setText(f.getDuree());

     
    }
}
      
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        table();
           tab.setOnMouseClicked((MouseEvent event) -> {
    if (event.getClickCount() > 0) {
        onEdit();
        
    }
});
        
    }    

    @FXML
    private void AddDivision(ActionEvent event) {
        
         if(t.getText().isEmpty()|| dure.getText().isEmpty() || F.getText().isEmpty() ){
                   Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Vérifier les paramétres");
            
            alert.setContentText("Remplir tous les paramétres");
            Optional<ButtonType> result = alert.showAndWait();
           }
           else{
//           EnregistrerVersBase();
            
             Abonnement d = new Abonnement();
             d.setType(t.getText());
             d.setDuree(dure.getText());
             d.setFrais(F.getText());
            
            
             
               try {
                   sr.ajouter(d);
                   
//                   pagination.setPageFactory(this::createPage);

               } catch (SQLException ex) {
                   Logger.getLogger(DivisionController.class.getName()).log(Level.SEVERE, null, ex);
               }

           table();
           
           t.clear();
           dure.clear();
           F.clear();

       
           }
           
    }

    @FXML
    private void Modifier(ActionEvent event) throws SQLException {
        
          String id = idx.getText();
                int i=Integer.valueOf(id);
        String type = t.getText();
        String duree = dure.getText();
        String frais = F.getText();

        
        Abonnement a = new Abonnement();
        a.setId(i);
        a.setType(type);
        a.setDuree(duree);
        a.setFrais(frais);
        
        sr.Modif(a);
        table();
         
    
       
     
        
    }

    @FXML
    private void Supprimer(ActionEvent event) throws SQLException {
        
         String idf=idx.getText();
        int i=Integer.valueOf(idf);
        Abonnement r = new Abonnement();
        
        r.setId(i);
         
              System.out.println(r);
              sr.delete(r);
        
                         table();
                         t.clear();
                         dure.clear();
                         F.clear();

//        SuppRole(i);
     
       
        
                JOptionPane.showMessageDialog(null,"Le role a été supprimer avec succés");
//    
        
        
    }
    
}
