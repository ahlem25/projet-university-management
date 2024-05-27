package main.java.org.iteam.DAO.interfaces;

import java.sql.SQLException;

import main.java.org.iteam.javaBeans.User;

public interface UserLoginService {
	public User login(String email, String password) throws SQLException;
}
