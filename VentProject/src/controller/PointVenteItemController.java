/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import entity.PointVente;
import entity.Vente;
import java.awt.Point;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class PointVenteItemController implements Initializable {

    @FXML
    private HBox Hbox;
    @FXML
    private Text nomuser;
    @FXML
    private Text type;
    @FXML
    private HBox hbox;
    @FXML
    private ImageView imv;
    
    
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
 
   
    }  
    
    public PointVenteItemController(){
    
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/PointVenteItem.fxml"));
        fxmlLoader.setController(this);
        try
        {
            fxmlLoader.load();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
        
    }

    public HBox getBox() {
        return Hbox;
    }

    public HBox getHbox() {
        return hbox;
    }

    public void setHbox(HBox hbox) {
        this.hbox = hbox;
    }
    
    
    
    
        public void setInfo(PointVente p)  {   
        type.setText(p.getNom());
        nomuser.setText(p.getEmail());
     
     
}
        
}
