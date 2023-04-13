/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller ;



import entity.Vente;
import javafx.scene.control.ListCell;

/**
 *
 * @author dell
 */
public class ListViewVente extends ListCell<Vente> {
    
    
     @Override
     public void updateItem(Vente e, boolean empty)
    {
        super.updateItem(e,empty);
        if(e != null)
        {
            
            VenteItemController data = new VenteItemController();
            data.setInfo(e);
            setGraphic(data.getHbox());
            setGraphic(data.getBox());
        }
    }
    
}
