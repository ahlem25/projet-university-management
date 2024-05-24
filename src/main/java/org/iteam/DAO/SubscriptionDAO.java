package org.iteam.DAO;

import org.iteam.DAO.interfaces.SubscriptionManagementService;
import org.iteam.config.DbConfig;
import org.iteam.javaBeans.Subscription;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SubscriptionDAO implements SubscriptionManagementService {
    private DbConfig dbInstance;
    private Connection connection;

    public SubscriptionDAO() {
        super();
        dbInstance=DbConfig.getInstance();
        try {
            connection = dbInstance.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public Subscription getSubscriptionById(int id) throws SQLException {
        String query = "SELECT * FROM subscriptions WHERE id = ?;";
        PreparedStatement preStat = connection.prepareStatement(query);
        preStat.setInt(1,id);
        ResultSet result = preStat.executeQuery();
        Subscription subscription = null;
        if(result.next()) {
            int idSubscription = result.getInt("id");
            String year = result.getString("name");
            int student_id = result.getInt("student_id");
            int class_id = result.getInt("class_id");
            subscription = new Subscription(idSubscription,year,student_id,class_id);
        }
        preStat.close();
        return subscription;
    }

    @Override
    public ArrayList<Subscription> getAllSubscriptions() throws SQLException {
        String query = "SELECT * FROM subscriptions;";
        connection = dbInstance.getConnection();
        PreparedStatement preStat = connection.prepareStatement(query);
        ResultSet result = preStat.executeQuery();
        ArrayList<Subscription> reponse = new ArrayList<Subscription>();
        while(result.next()) {
            Subscription subscription = new Subscription();
            subscription.setId(result.getInt("id"));
            subscription.setYear(result.getString("year"));
            subscription.setStudentId(result.getInt("student_id"));
            subscription.setClassId(result.getInt("class_id"));
            reponse.add(subscription);
        }
        preStat.close();
        return reponse;
    }

    @Override
    public boolean addSubscription(Subscription subscription) throws SQLException {
        String query = "INSERT INTO subscriptions (year,student_id,class_id) VALUES (?,?,?);";
        connection = dbInstance.getConnection();
        PreparedStatement preStat = connection.prepareStatement(query);
        preStat.setString(1, subscription.getYear());
        preStat.setInt(2, subscription.getStudentId());
        preStat.setInt(3, subscription.getClassId());
        int result = preStat.executeUpdate();
        preStat.close();
        return result != 0;
    }

    @Override
    public boolean deleteSubscriptionById(int id) throws SQLException {
        String query = "DELETE FROM subscriptions WHERE is= ?";
        connection = dbInstance.getConnection();
        PreparedStatement preStat = connection.prepareStatement(query);
        preStat.setInt(1, id);
        int state = preStat.executeUpdate();
        preStat.close();
        return state != 0;
    }

    @Override
    public boolean updateSubscriptionById(int id, Subscription subscription) throws SQLException {
        String query = "UPDATE subscriptions SET year=?,student_id=?,class_id=? WHERE is= ?;";
        connection = dbInstance.getConnection();
        PreparedStatement preStat = connection.prepareStatement(query);
        preStat.setString(1, subscription.getYear());
        preStat.setInt(2, subscription.getStudentId());
        preStat.setInt(3, subscription.getClassId());
        preStat.setInt(4, id);
        int result = preStat.executeUpdate();
        preStat.close();
        return result != 0;
    }
}
