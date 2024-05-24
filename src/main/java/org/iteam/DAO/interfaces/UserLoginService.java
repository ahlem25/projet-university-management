package org.iteam.DAO.interfaces;

import java.sql.SQLException;

import org.iteam.javaBeans.User;

public interface UserLoginService {
	public User login(String email, String password) throws SQLException;
}
