package main.java.org.iteam.DAO.interfaces;

import main.java.org.iteam.javaBeans.Subscription;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SubscriptionManagementService {
    public Subscription getSubscriptionById(int id) throws SQLException;
    public ArrayList<Subscription> getAllSubscriptions() throws SQLException;
    public boolean addSubscription(Subscription subscription) throws SQLException;
    public boolean deleteSubscriptionById(int id) throws SQLException;
    public boolean updateSubscriptionById(int id, Subscription subscription) throws SQLException;
}
