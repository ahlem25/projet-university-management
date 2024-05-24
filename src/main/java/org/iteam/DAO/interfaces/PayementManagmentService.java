package org.iteam.DAO.interfaces;

import org.iteam.javaBeans.Payement;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PayementManagmentService {
    public Payement getPayementById(int id) throws SQLException;
    public ArrayList<Payement> getAllPayements() throws SQLException;
    public boolean addPayement(Payement payement) throws SQLException;
    public boolean deletePayementById(int id) throws SQLException;
    public boolean updatePayementById(int id, Payement payement) throws SQLException;
}
