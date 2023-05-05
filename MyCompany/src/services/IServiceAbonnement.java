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
public interface IServiceAbonnement<Abonnement> {
    
       void ajouter(Abonnement a) throws SQLException;
    boolean delete(Abonnement a) throws SQLException;
    boolean update(Abonnement a) throws SQLException;
    public boolean search(Abonnement a) throws SQLException;
    
    public List<Abonnement> readAll() throws SQLException;
    
}
