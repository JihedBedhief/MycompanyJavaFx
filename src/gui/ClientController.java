/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Entity.Client;
import Entity.Division;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import javax.swing.JOptionPane;
import mycompany.database.Database;
import services.ServiceClient;

/**
 * FXML Controller class
 *
 * @author MediaCenter Zaghouan
 */
public class ClientController implements Initializable {

    @FXML
    private TableView<Client> tab;
    @FXML
    private TableColumn<Client, Integer> id;
    @FXML
    private TableColumn<Client, String> div;
    @FXML
    private TableColumn<Client, String> N;
    @FXML
    private TableColumn<Client, Integer> tel;
    @FXML
    private TableColumn<Client, String> E;
    @FXML
    private TableColumn<Client, String> v;
    @FXML
    private TableColumn<Client, String> C;
    @FXML
    private TableColumn<Client, String> Ci;
    @FXML
    private TableColumn<Client, String> D;
  
    @FXML
    private TextField name;
    @FXML
    private TextField idx;
    @FXML
    private TextField telephone;
    @FXML
    private TextField email;
    @FXML
    private TextField ville;
    @FXML
    private TextField code;
    @FXML
    private TextField cin;
  
    @FXML
    private ComboBox<String> comm;
    @FXML
    private TextField recherche;

    public ClientController() {
        Connection cnx = Database.getInstance().getCnx();
    }
    private Connection cnx;

    ServiceClient client = new ServiceClient();
          ObservableList<Client> Chercheclient;

    /**
     * Initializes the controller class.
     */
    public void onEdit() {

        java.sql.Connection cnx;
        cnx = Database.getInstance().getCnx();

        if (tab.getSelectionModel().getSelectedItem() != null) {
            Client f = tab.getSelectionModel().getSelectedItem();
            String n = String.valueOf(f.getTelephone());
            String id = String.valueOf(f.getId());
            idx.setText(id);
            name.setText(f.getName());
            telephone.setText(n);
            email.setText(f.getEmail());
            ville.setText(f.getVille());
            code.setText(f.getCode_postal());
            cin.setText(f.getCin());

        }
    }
       

    public void table2() {

//        r.setCellValueFactory( new PropertyValueFactory<>("Role"));
        id.setCellValueFactory(new PropertyValueFactory<>("id"));

        div.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Client, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Client, String> param) {
                return new SimpleStringProperty(param.getValue().getDivision_id().getType());
            }
        });
        N.setCellValueFactory(new PropertyValueFactory<>("name"));
        tel.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        E.setCellValueFactory(new PropertyValueFactory<>("email"));
        v.setCellValueFactory(new PropertyValueFactory<>("ville"));
        C.setCellValueFactory(new PropertyValueFactory<>("code_postal"));
        Ci.setCellValueFactory(new PropertyValueFactory<>("cin"));
        D.setCellValueFactory(new PropertyValueFactory<>("date_ajout"));

        tab.setItems(client.RecupBase2());
        System.out.println(client.RecupBase2());

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        idx.setVisible(false);

        
        comm.setItems(client.RecupCombo());
        
        table2();
                                ChercheFichier();

             tab.setOnMouseClicked((MouseEvent event) -> {
    if (event.getClickCount() > 0) {
        onEdit();
        
    }
});
    }

    @FXML
    private void AddDivision(ActionEvent event) {
               if (email.getText().isEmpty()|| name.getText().isEmpty() || telephone.getText().isEmpty() || ville.getText().isEmpty() || code.getText().isEmpty() || comm.getValue().isEmpty()|| cin.getText().isEmpty())
         {
             Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Erreur");
            
            alert.setContentText("Please fill all input");
            Optional<ButtonType> result = alert.showAndWait();}
           
           else if(!client.VerifEmail(email.getText())){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Erreur");
            
            alert.setContentText("invalid Email format");
            Optional<ButtonType> result = alert.showAndWait();
           }
           else if(cin.getText().length()!=8){
                 Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Erreur");
            
            alert.setContentText("invalid cin form");
            Optional<ButtonType> result = alert.showAndWait();
           
           }
             else if(telephone.getText().length()!=8){
                 Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Erreur");
            
            alert.setContentText("invalid phone number");
            Optional<ButtonType> result = alert.showAndWait();
             }
        
            
           
           
         else {
           
           EnregistrerVersBase();
           table2();
//           JOptionPane.showMessageDialog(null,"Account created successfully");

          
           
          
//           
//           
//         
           }
       
        
    }

    @FXML
    private void Modifier(ActionEvent event) throws SQLException {
        
        int i = Integer.valueOf(idx.getText());
    int num = Integer.valueOf(telephone.getText());
        
        Client c = new Client();
        c.setId(i);
        c.setName(name.getText());
        c.setTelephone(num);
                c.setEmail(email.getText());
        c.setVille(ville.getText());
        c.setCode_postal(code.getText());
        c.setCin(cin.getText());

        
        
client.Updateclient(c);
table2();
        
    }

    @FXML
    private void Supprimer(ActionEvent event) throws SQLException {
        
          String idf=idx.getText();
        int i=Integer.valueOf(idf);
        Client r = new Client();
        
        r.setId(i);
         
              System.out.println(r);
              client.delete(r);
        
                         table2();

//        SuppRole(i);
     
       
        
                JOptionPane.showMessageDialog(null,"Le client a été supprimer avec succés");
        
        
        
        
        
    }
    
       private void EnregistrerVersBase() {
           java.sql.Connection cnx;
     cnx = Database.getInstance().getCnx();
             
           try {
            String sql = "INSERT INTO client (division_id,name,telephone,email,ville,code_postal,cin) VALUES (?,?,?,?,?,?,?)";
            
        PreparedStatement st = (PreparedStatement) cnx.prepareStatement(sql);
        String n = telephone.getText();
        int numero = Integer.valueOf(n);
              
              


        
        int i = comm.getSelectionModel().getSelectedIndex()+1;
               System.out.println(i);
            st.setInt(1, i);
            st.setString(2, name.getText());
            st.setString(3, n);
              st.setString(4, email.getText());
            st.setString(5, ville.getText());
              st.setString(6, code.getText());
            st.setString(7, cin.getText());
          //  st.setString(8, date.getText());

        
            st.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error");
        }
    } 

       
        public void ChercheFichier(){
      Client f = new Client();  
        Ci.setCellValueFactory( new PropertyValueFactory<>("cin"));       
        E.setCellValueFactory( new PropertyValueFactory<>("email"));

    Chercheclient = client.RecupBase2();
    tab.setItems(client.RecupBase2());
    FilteredList<Client> filtreddata;
     filtreddata = new FilteredList<>(Chercheclient ,b ->true);
    recherche.textProperty().addListener((observable,oldValue,newValue)->{
      filtreddata.setPredicate((u  ->  {
          
          if((newValue ==null) || newValue.isEmpty())
          { return true;}
      
      String lowerCaseFilter = newValue.toLowerCase();
      if (u.getCin().toLowerCase().contains(lowerCaseFilter)){
      return true;
      } else if (u.getEmail().toLowerCase().contains(lowerCaseFilter))
          {return true;}   
      return false;
      })); 
    });
    
    SortedList<Client> srt = new SortedList<>(filtreddata);
    srt.comparatorProperty().bind(tab.comparatorProperty());
    tab.setItems(srt);
    }
    
    

}
