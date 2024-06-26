package main.java.org.iteam.DAO.interfaces;

import java.sql.SQLException;
import java.util.ArrayList;

import main.java.org.iteam.javaBeans.Student;
import main.java.org.iteam.javaBeans.User;

public interface UserManagmentService {
	public User getUserById(int id) throws SQLException;
	public ArrayList<User> getAllUsers() throws SQLException;
	public boolean addUser(User user) throws SQLException;
	public boolean deleteUserById(int id) throws SQLException;
	public boolean updateUserById(int id, User user) throws SQLException;
}
