/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionrh.gui;

import gestionrh.services.EmployeesService;
import gestionrh.utils.MyConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author derou
 */
public class StatempController implements Initializable {
    
    
    

EmployeesService su = new EmployeesService();
    @FXML
    private PieChart pie;
     private ObservableList data;
    @FXML
    private Button retournbtn;
    /**
     * Initializes the controller class.
     */
    @Override
     public void initialize(URL url, ResourceBundle rb) {
         buildData();
        pie.getData().addAll(data);
     }
     
     public void buildData(){
          java.sql.Connection cnx;
  cnx = MyConnection.getinstance().getCnx();
          data = FXCollections.observableArrayList();
          try{
            //SQL FOR SELECTING NATIONALITY OF CUSTOMER
            String SQL =" SELECT COUNT(*) AS EMPLOYEE_count, formationA FROM employees GROUP BY formationA;";

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

    @FXML
    private void Retour(ActionEvent event) throws IOException {
        
         Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow(); 
                    stage.close();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("ListeEmployeeFXML.fxml")));       
                    stage.setScene(scene);
                    stage.setTitle("modifier");
                    
                    stage.show(); 
    }
        
   
}

    
    
