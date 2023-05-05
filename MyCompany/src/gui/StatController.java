/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import utils.Maconnexion;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;

/**
 * FXML Controller class
 *
 * @author Hammouda
 */
public class StatController implements Initializable {

    @FXML
    private PieChart pie;

    
    private ObservableList data;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        buildData();
        pie.getData().addAll(data);
    }    
    
    public void buildData(){
          java.sql.Connection cnx;
     cnx = Maconnexion.getInstance().getConnection();
          data = FXCollections.observableArrayList();
          try{
            //SQL FOR SELECTING NATIONALITY OF CUSTOMER
            String SQL =" select COUNT(*),`Produit` from achat GROUP BY `Produit`";

            ResultSet rs = cnx.createStatement().executeQuery(SQL);
            while(rs.next()){
                //adding data on piechart data
                data.add(new PieChart.Data(rs.getString(2),rs.getInt(1)));
            }
          }catch(Exception e){
              System.out.println("Error on DB connection");
              return;
          }

      }
    
    
}
