/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller ;



import Entity.PointVente;
import Entity.Vente;
import javafx.scene.control.ListCell;

/**
 *
 * @author dell
 */
public class ListViewPointVente extends ListCell<PointVente> {
    
    
     @Override
     public void updateItem(PointVente e, boolean empty)
    {
        super.updateItem(e,empty);
        if(e != null)
        {
            
            PointVenteItemController data = new PointVenteItemController();
            data.setInfo(e);
            setGraphic(data.getHbox());
            setGraphic(data.getBox());
        }
    }
    
}
