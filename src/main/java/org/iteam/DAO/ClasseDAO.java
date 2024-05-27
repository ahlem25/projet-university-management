package main.java.org.iteam.DAO;

import main.java.org.iteam.DAO.interfaces.ClasseManagmentService;
import main.java.org.iteam.config.DbConfig;
import main.java.org.iteam.javaBeans.Classe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClasseDAO implements ClasseManagmentService {
    private DbConfig dbInstance;
    private Connection connection;

    public ClasseDAO() {
        super();
        dbInstance=DbConfig.getInstance();
        try {
            connection = dbInstance.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Classe getClasseById(int id) throws SQLException {
        String query = "SELECT * FROM classes WHERE id = ?;";
        PreparedStatement preStat = connection.prepareStatement(query);
        preStat.setInt(1,id);
        ResultSet result = preStat.executeQuery();
        Classe classe = null;
        if(result.next()) {
            int idClasse = result.getInt("id");
            String name = result.getString("name");
            String comment = result.getString("comment");
            String of_year = result.getString("of_year");
            classe = new Classe(idClasse,name,comment,of_year);
        }
        preStat.close();
        return classe;
    }

    @Override
    public ArrayList<Classe> getAllClasses() throws SQLException {
        String query = "SELECT * FROM classes;";
        connection = dbInstance.getConnection();
        PreparedStatement preStat = connection.prepareStatement(query);
        ResultSet result = preStat.executeQuery();
        ArrayList<Classe> reponse = new ArrayList<Classe>();
        while(result.next()) {
            Classe student = new Classe();
            student.setId(result.getInt("id"));
            student.setName(result.getString("name"));
            student.setComment(result.getString("comment"));
            student.setOfYear(result.getString("of_year"));
            reponse.add(student);
        }
        preStat.close();
        return reponse;
    }

    @Override
    public boolean addClasse(Classe classe) throws SQLException {
        String query = "INSERT INTO classes (name,comment,of_year) VALUES (?,?,?);";
        connection = dbInstance.getConnection();
        PreparedStatement preStat = connection.prepareStatement(query);
        preStat.setString(1, classe.getName());
        preStat.setString(2, classe.getComment());
        preStat.setString(3, classe.getOfYear());
        int result = preStat.executeUpdate();
        preStat.close();
        return result != 0;
    }

    @Override
    public boolean deleteClasseById(int id) throws SQLException {
        String query = "DELETE FROM classes WHERE id= ?";
        connection = dbInstance.getConnection();
        PreparedStatement preStat = connection.prepareStatement(query);
        preStat.setInt(1, id);
        int state = preStat.executeUpdate();
        preStat.close();
        return state != 0;
    }

    @Override
    public boolean updateClasseById(int id, Classe classe) throws SQLException {
        String query = "UPDATE classes SET name=?,comment=?,of_year=? WHERE id= ?;";
        connection = dbInstance.getConnection();
        PreparedStatement preStat = connection.prepareStatement(query);
        preStat.setString(1, classe.getName());
        preStat.setString(2, classe.getComment());
        preStat.setString(3, classe.getOfYear());
        preStat.setInt(4, id);
        int result = preStat.executeUpdate();
        preStat.close();
        return result != 0;
    }
}
