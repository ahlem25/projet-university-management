package main.java.org.iteam.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import main.java.org.iteam.DAO.interfaces.HomeService;
import main.java.org.iteam.DAO.interfaces.UserLoginService;
import main.java.org.iteam.DAO.interfaces.UserManagmentService;
import main.java.org.iteam.config.DbConfig;
import main.java.org.iteam.javaBeans.HomeData;
import main.java.org.iteam.javaBeans.User;

public class UserDAO implements UserLoginService, UserManagmentService, HomeService {
	private DbConfig dbInstance;
	private Connection connection;
	
	public UserDAO() {
		super();
		dbInstance=DbConfig.getInstance();
		try {
			connection = dbInstance.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public User login(String email, String password) throws SQLException {
		
		String query = "SELECT * FROM users WHERE email = ? and password = ?";
		PreparedStatement preStat = connection.prepareStatement(query);
		preStat.setString(1,email);
		preStat.setString(2,password);
		ResultSet result = preStat.executeQuery();
		
		User user = null;
		
		if(result.next()) {
			int id = result.getInt("id");
			String firstName = result.getString("firstName");
			String lastName = result.getString("lastName");
			String userEmail = result.getString("email");
			String userPassword = result.getString("password");
			
			user = new User(id, firstName,lastName,userEmail,userPassword);
		}

		preStat.close();

		return user;
	}

	@Override
	public User getUserById(int id) throws SQLException {
		String query = "SELECT * FROM users WHERE id = ?;";
		connection = dbInstance.getConnection();
		PreparedStatement preStat = connection.prepareStatement(query);
		preStat.setInt(1, id);
		ResultSet result = preStat.executeQuery();
		
		User user = null;
		if(result.next()) {
			user = new User();
			user.setId(result.getInt("id"));
			user.setFirstName(result.getString("firstName"));
			user.setLastName(result.getString("lastName"));
			user.setEmail(result.getString("email")); 
			user.setPassword(result.getString("password"));
		}
		preStat.close();
		return user;
	}

	@Override
	public ArrayList<User> getAllUsers() throws SQLException {
		String query = "SELECT * FROM users;";
		connection = dbInstance.getConnection();
		PreparedStatement preStat = connection.prepareStatement(query);
		ResultSet result = preStat.executeQuery();
		
		ArrayList<User> reponse = new ArrayList<User>();
		while(result.next()) {
			User user = new User();
			user.setId(result.getInt("id"));
			user.setFirstName(result.getString("firstName"));
			user.setLastName(result.getString("lastName"));
			user.setEmail(result.getString("email")); 
			user.setPassword(result.getString("password"));
			reponse.add(user);
		}
		preStat.close();
		return reponse;
	}

	@Override
	public boolean addUser(User user) throws SQLException {
		String query = "INSERT INTO users(firstName,lastName,email,password) VALUES (?,?,?,?);";
		connection = dbInstance.getConnection();
		PreparedStatement preStat = connection.prepareStatement(query);
		preStat.setString(1, user.getFirstName());
		preStat.setString(2, user.getLastName());
		preStat.setString(3, user.getEmail());
		preStat.setString(4, user.getPassword());
		int result = preStat.executeUpdate();
		preStat.close();
		return result != 0;
		
	}

	@Override
	public boolean deleteUserById(int id) throws SQLException {
		String query = "DELETE FROM users WHERE id= ?";
		connection = dbInstance.getConnection();
		PreparedStatement preStat = connection.prepareStatement(query);
		preStat.setInt(1, id);
		int state = preStat.executeUpdate();
		preStat.close();
		return state != 0;
	}

	@Override
	public boolean updateUserById(int id, User user) throws SQLException {
		String query = "UPDATE users SET firstName=?,lastName=?,email=?,password=? WHERE id= ?;";
		connection = dbInstance.getConnection();
		PreparedStatement preStat = connection.prepareStatement(query);
		preStat.setString(1, user.getFirstName());
		preStat.setString(2, user.getLastName());
		preStat.setString(3, user.getEmail());
		preStat.setString(4, user.getPassword());
		preStat.setInt(5, id);
		int result = preStat.executeUpdate();
		preStat.close();
		return result != 0;
	}

	public HomeData getData() {
		String usersQuery = "SELECT COUNT(*) AS usersNbr FROM users";
		String studentsQuery = "SELECT COUNT(*) AS studentsNbr FROM students";
		String subscriptionsQuery = "SELECT COUNT(*) AS subscriptionsNbr FROM subscriptions";
		String payementsQuery = "SELECT COUNT(*) AS payementsNbr FROM payements";
		HomeData homeData = new HomeData();
		ResultSet result;
		PreparedStatement preStat;
		try{
			// nombre d'utilisateurs
			connection = dbInstance.getConnection();
			preStat = connection.prepareStatement(usersQuery);
			result = preStat.executeQuery(usersQuery);
			if (result.next()){
				homeData.setUsersNbr(result.getInt("usersNbr"));
			}
			preStat.close();

			// nombre d'etudiants
			connection = dbInstance.getConnection();
			preStat = connection.prepareStatement(studentsQuery);
			result = preStat.executeQuery(studentsQuery);
			if (result.next()){
				homeData.setStudentsNbr(result.getInt("studentsNbr"));
			}
			preStat.close();

			// nombre d'inscriptions
			connection = dbInstance.getConnection();
			preStat = connection.prepareStatement(subscriptionsQuery);
			result = preStat.executeQuery(subscriptionsQuery);
			if (result.next()){
				homeData.setSubscriptionsNbr(result.getInt("subscriptionsNbr"));
			}
			preStat.close();

			// nombre de payements
			connection = dbInstance.getConnection();
			preStat = connection.prepareStatement(payementsQuery);
			result = preStat.executeQuery(payementsQuery);
			if (result.next()){
				homeData.setPayementsNbr(result.getInt("payementsNbr"));
			}
			preStat.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return homeData;
	}
}
