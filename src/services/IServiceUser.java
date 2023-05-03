/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author MediaCenter Zaghouan
 */
public interface IServiceUser<User> {
    
      void ajouter(User t) throws SQLException;
    boolean delete(User t) throws SQLException;
    boolean update(User t) throws SQLException;
    public boolean search(User t) throws SQLException;
    public List<User> readAll() throws SQLException;
    public User login(String mail , String mdpasse) throws SQLException;
    
}
