package main.java.org.iteam.DAO;

import main.java.org.iteam.DAO.interfaces.PayementManagmentService;
import main.java.org.iteam.config.DbConfig;
import main.java.org.iteam.javaBeans.Payement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class PayementDAO implements PayementManagmentService {
    private DbConfig dbInstance;
    private Connection connection;

    public PayementDAO() {
        super();
        dbInstance=DbConfig.getInstance();
        try {
            connection = dbInstance.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public Payement getPayementById(int id) throws SQLException {
        String query = "SELECT * FROM payements WHERE id = ?;";
        PreparedStatement preStat = connection.prepareStatement(query);
        preStat.setInt(1,id);
        ResultSet result = preStat.executeQuery();
        Payement payement = null;
        if(result.next()) {
            int idPayement = result.getInt("id");
            String amount = result.getString("amount");
            Date date = result.getDate("date");
            String comment = result.getString("comment");
            int studentId = result.getInt("student_id");
            payement = new Payement(idPayement,amount,date,comment,studentId);
        }
        preStat.close();
        return payement;
    }

    @Override
    public ArrayList<Payement> getAllPayements() throws SQLException {
        String query = "SELECT * FROM payements;";
        connection = dbInstance.getConnection();
        PreparedStatement preStat = connection.prepareStatement(query);
        ResultSet result = preStat.executeQuery();
        ArrayList<Payement> reponse = new ArrayList<Payement>();
        while(result.next()) {
            Payement payement = new Payement();
            payement.setId(result.getInt("id"));
            payement.setAmount(result.getString("amount"));
            payement.setDate(result.getDate("date"));
            payement.setComment(result.getString("comment"));
            payement.setStudentId(result.getInt("student_id"));
            reponse.add(payement);
        }
        preStat.close();
        return reponse;
    }

    @Override
    public boolean addPayement(Payement payement) throws SQLException {
        String query = "INSERT INTO payements (amount,date,comment,student_id) VALUES (?,?,?,?);";
        connection = dbInstance.getConnection();
        PreparedStatement preStat = connection.prepareStatement(query);
        preStat.setString(1, payement.getAmount());
        preStat.setDate(2, (java.sql.Date) payement.getDate());
        preStat.setString(3, payement.getComment());
        preStat.setInt(4, payement.getStudentId());
        int result = preStat.executeUpdate();
        preStat.close();
        return result != 0;
    }

    @Override
    public boolean deletePayementById(int id) throws SQLException {
        String query = "DELETE FROM payements WHERE is= ?";
        connection = dbInstance.getConnection();
        PreparedStatement preStat = connection.prepareStatement(query);
        preStat.setInt(1, id);
        int state = preStat.executeUpdate();
        preStat.close();
        return state != 0;
    }

    @Override
    public boolean updatePayementById(int id, Payement payement) throws SQLException {
        String query = "UPDATE payements SET amount=?,date=?,comment=?,student_id=? WHERE is= ?;";
        connection = dbInstance.getConnection();
        PreparedStatement preStat = connection.prepareStatement(query);
        preStat.setString(1, payement.getAmount());
        preStat.setDate(2, (java.sql.Date) payement.getDate());
        preStat.setString(3, payement.getComment());
        preStat.setInt(4, payement.getStudentId());
        preStat.setInt(5, id);
        int result = preStat.executeUpdate();
        preStat.close();
        return result != 0;
    }
}
