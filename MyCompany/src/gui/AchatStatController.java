/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import utils.Maconnexion;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.chart.PieChart;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class AchatStatController implements Initializable {

    @FXML
    private PieChart for_stat;
     private Statement st;
    private ResultSet rs;
    private Connection cnx;
        ObservableList<PieChart.Data>data=FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Connection cnx = Maconnexion.getInstance().getConnection();
            stat(); 
        // TODO
    }
      private void showStat(MouseEvent event) {
           try {
            //navigation
            Parent loader = FXMLLoader.load(getClass().getResource("listeachat.fxml"));
           
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
       private void stat() {
          try{
           // String query ="select COUNT(*),reservation_voyage.travel_class from voyage INNER JOIN reservation_voyage on reservation_voyage.voyage_id =voyage.id GROUP BY travel_class;";
           
           String query ="select COUNT(*),`Produit` from achat GROUP BY `Produit`;";

           PreparedStatement PreparedStatement = cnx.prepareStatement(query);
             rs = PreparedStatement.executeQuery();
             while (rs.next()){               
               data.add(new PieChart.Data(rs.getString("Produit"),rs.getInt("COUNT(*)")));
                 System.out.println(data);
            }  
             
        } catch (SQLException ex) {
              System.out.println(ex.getMessage());
        }
        
         //for_stat.setTitle("**Statistiques Des formations par type **");
        for_stat.setLegendSide(Side.LEFT);
         for_stat.setData(data);
       
    
}
    
}
