/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Entity.Client;
import Entity.User;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import javax.swing.JOptionPane;
import mycompany.database.Database;
import services.ServiceUser;

/**
 * FXML Controller class
 *
 * @author MediaCenter Zaghouan
 */
public class UserController implements Initializable {

    @FXML
    private TableView<User> tab;
    @FXML
    private TableColumn<User, Integer> id;
    @FXML
    private TableColumn<User, String> type;
    @FXML
    private TableColumn<User, String> dure;
    @FXML
    private TableColumn<User, String> matricule;
    @FXML
    private TableColumn<User, String> adresse;
    @FXML
    private TableColumn<User, String> domaine;
    @FXML
    private TableColumn<User, String> pays;
    @FXML
    private TableColumn<User, Integer> telephone;
    @FXML
    private TableColumn<User, String> nom;
    @FXML
    private TableColumn<User, String> statuts;
    @FXML
    private TableColumn<User, String> Actions;

 public UserController(){
        Connection cnx = Database.getInstance().getCnx();
    }
      private Connection cnx;
      
      ServiceUser S = new ServiceUser();

    /**
     * Initializes the controller class.
     */
       public void table2(){
         
//        r.setCellValueFactory( new PropertyValueFactory<>("Role"));
        id.setCellValueFactory( new PropertyValueFactory<>("id"));

type.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<User,String>,ObservableValue<String>>(){
    @Override
    public ObservableValue<String> call(TableColumn.CellDataFeatures<User, String> param) {
                            return new SimpleStringProperty(param.getValue().getAbonnement_id().getType());
    }
            });
dure.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<User,String>,ObservableValue<String>>(){
    @Override
    public ObservableValue<String> call(TableColumn.CellDataFeatures<User, String> param) {
                            return new SimpleStringProperty(param.getValue().getAbonnement_id().getDuree());
    }
            });
        matricule.setCellValueFactory( new PropertyValueFactory<>("matricule"));
        adresse.setCellValueFactory(new PropertyValueFactory <>("adresse"));
        domaine.setCellValueFactory( new PropertyValueFactory<>("domaine"));
        pays.setCellValueFactory(new PropertyValueFactory <>("pays"));
        telephone.setCellValueFactory(new PropertyValueFactory <>("telephone"));
        nom.setCellValueFactory(new PropertyValueFactory <>("nom"));
                statuts.setCellValueFactory(new PropertyValueFactory <>("statuts"));


          
        tab.setItems(S.RecupBase2()); 
               System.out.println(S.RecupBase2());
        
       }
           
      
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        table2();
          
                  Callback<TableColumn<User, String>, TableCell<User, String>> cellFoctory = (TableColumn<User, String> param) -> {
            // make cell containing buttons
            final TableCell<User, String> cell = new TableCell<User, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.CLOSE);
                         FontAwesomeIconView tickIcon = new FontAwesomeIconView(FontAwesomeIcon.CHECK);
                        FontAwesomeIconView corIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);

                       

                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#ff1744;"
                        );
                        tickIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#00FF00;"
                        );
                         corIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#ff1744;"
                        );
                        
                         deleteIcon.setOnMouseClicked((MouseEvent event) -> {
                            
                            try {
                                User us = tab.getSelectionModel().getSelectedItem();
                                ServiceUser usr = new ServiceUser();
                                System.out.println(us);
                                usr.BAN(us);
                                table2();
                                JOptionPane.showMessageDialog(null,"l'utilisateur a éte bannée");

                            } catch (SQLException ex) {
                                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                            });
                         
                         tickIcon.setOnMouseClicked((MouseEvent event) -> {
                            
                            try {
                                User us = tab.getSelectionModel().getSelectedItem();
                                ServiceUser usr = new ServiceUser();
                                System.out.println(us);
                                usr.UNBAN(us);
                                table2();
                                JOptionPane.showMessageDialog(null,"l'utilisateur a éte activée");

                            } catch (SQLException ex) {
                                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                            });
                         corIcon.setOnMouseClicked((MouseEvent event) -> {
                            
                            try {
                                User us = tab.getSelectionModel().getSelectedItem();
                                ServiceUser usr = new ServiceUser();
                                System.out.println(us);
                                usr.delete(us);
                                table2();
                                JOptionPane.showMessageDialog(null,"l'utilisateur a éte supprimée");

                            } catch (SQLException ex) {
                                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                            });
                      
                     HBox managebtn = new HBox(deleteIcon,tickIcon,corIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                        HBox.setMargin(deleteIcon, new Insets(2, 3, 0, 2));

                     

                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };
             Actions.setCellFactory(cellFoctory);

                 
    }    

   
}
