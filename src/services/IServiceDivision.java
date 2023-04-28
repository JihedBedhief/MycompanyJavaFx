/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.SQLException;
import java.util.List;


public interface IServiceDivision <Division>{
    void ajouter(Division d) throws SQLException;
    boolean delete(Division d) throws SQLException;
    boolean update(Division d) throws SQLException;
    public boolean search(Division d) throws SQLException;
    
    public List<Division> readAll() throws SQLException;
}
