/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.SQLException;
import java.util.List;


public interface IServiceClient <Client>{
     void ajouter(Client d) throws SQLException;
    boolean delete(Client d) throws SQLException;
    boolean update(Client d) throws SQLException;
    public boolean search(Client d) throws SQLException;
    
    public List<Client> readAll() throws SQLException;
    
}
