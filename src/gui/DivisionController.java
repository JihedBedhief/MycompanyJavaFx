/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

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
import services.ServiceDivision;

/**
 * FXML Controller class
 *
 * @author user
 */
public class DivisionController implements Initializable {

    @FXML
    private TableView<Division> tab;
    @FXML
    private TableColumn<Division, Integer> id;
    @FXML
    private TableColumn<Division, String> type;
    @FXML
    private TableColumn<Division, Double> taux;
    @FXML
    private TextField t;
    @FXML
    private TextField ta;
    @FXML
    private TextField idx;
    
     public DivisionController(){
        Connection cnx = Database.getInstance().getCnx();
    }
      private Connection cnx;
      
      
      ServiceDivision sr = new ServiceDivision();
      
         public void table(){
         
        id.setCellValueFactory( new PropertyValueFactory<>("id"));
        type.setCellValueFactory(new PropertyValueFactory <>("type"));
         taux.setCellValueFactory(new PropertyValueFactory <>("taux_remise"));


        tab.setItems(sr.RecupBase()); 



}
         
           public void onEdit() {
               
               java.sql.Connection cnx;
     cnx = Database.getInstance().getCnx();
     
    if (tab.getSelectionModel().getSelectedItem() != null) {
          Division f = tab.getSelectionModel().getSelectedItem();
      String n = String.valueOf(f.getTaux_remise());
      String id=String.valueOf(f.getId());
      idx.setText(id);
          t.setText(f.getType());
          ta.setText(n);
  
     
    }
}
           
           @FXML
           private void AddDivision(ActionEvent event){
           
             if(t.getText().isEmpty()|| ta.getText().isEmpty()){
                   Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Vérifier les paramétres");
            
            alert.setContentText("Remplir tous les paramétres");
            Optional<ButtonType> result = alert.showAndWait();
           }
           else{
//           EnregistrerVersBase();
            
             Division d = new Division();
             d.setType(t.getText());
             Double taux = Double.valueOf(ta.getText());
             d.setTaux_remise(taux);
            
             
               try {
                   sr.ajouter(d);
                   
//                   pagination.setPageFactory(this::createPage);

               } catch (SQLException ex) {
                   Logger.getLogger(DivisionController.class.getName()).log(Level.SEVERE, null, ex);
               }

           table();
           
           t.clear();
           ta.clear();
       
           }
           
           }
           
            @FXML
             void Modifier(ActionEvent event) {
                 
    int i = Integer.valueOf(idx.getText());
    String typ = t.getText();
    Double taux = Double.valueOf(ta.getText());
    String s = ta.getText();
        
        
 
       String sql =" UPDATE `division` SET `type`='"+typ+"',`taux_remise`='"+taux+"'  where id='"+i+"' ";
        try {
//        try {
         PreparedStatement st =  cnx.prepareStatement(sql);
         st.executeUpdate();
            table();
          JOptionPane.showMessageDialog(null,"Le role a été modifier");
    }catch(SQLException ex){
        ex.getMessage();
    
    }
        

    }
   
           
           
    
           @FXML
    private void Supprimer(ActionEvent event) throws SQLException {
         
        
        String idf=idx.getText();
        int i=Integer.valueOf(idf);
        Division r = new Division();
        
        r.setId(i);
         
              System.out.println(r);
              sr.delete(r);
        
                         table();

//        SuppRole(i);
     
       
        
                JOptionPane.showMessageDialog(null,"Le role a été supprimer avec succés");
//    
    }


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        table();
        idx.setVisible(false);
        
          tab.setOnMouseClicked((MouseEvent event) -> {
    if (event.getClickCount() > 0) {
        onEdit();
        
    }
});
        
    }    
    
    
    
    
}
