package org.iteam.DAO.interfaces;

import org.iteam.javaBeans.Classe;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ClasseManagmentService {
    public Classe getClasseById(int id) throws SQLException;
    public ArrayList<Classe> getAllClasses() throws SQLException;
    public boolean addClasse(Classe classe) throws SQLException;
    public boolean deleteClasseById(int id) throws SQLException;
    public boolean updateClasseById(int id, Classe classe) throws SQLException;
}
