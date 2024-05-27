package main.java.org.iteam.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import main.java.org.iteam.DAO.interfaces.StudentsManagementService;
import main.java.org.iteam.config.DbConfig;
import main.java.org.iteam.javaBeans.Student;

public class StudentDAO implements StudentsManagementService {

	private final DbConfig dbInstance;
	private Connection connection;
	
	public StudentDAO() {
		super();
		dbInstance=DbConfig.getInstance();
		try {
			connection = dbInstance.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Student getStudentById(int id) throws SQLException {
		String query = "SELECT * FROM students WHERE id = ?;";
		connection = dbInstance.getConnection();
		PreparedStatement preStat = connection.prepareStatement(query);
		preStat.setInt(1, id);
		ResultSet result = preStat.executeQuery();
		Student student = null;
		if(result.next()) {
			student = new Student();
			student.setId(result.getInt("id"));
			student.setFirstName(result.getString("firstName"));
			student.setLastName(result.getString("lastName"));
			student.setEmail(result.getString("email")); 
			student.setCin(result.getString("cin"));
			student.setLevel(result.getString("level"));
		}
		preStat.close();
		return student;
	}

	@Override
	public ArrayList<Student> getAllStudents() throws SQLException {
		String query = "SELECT * FROM students;";
		connection = dbInstance.getConnection();
		PreparedStatement preStat = connection.prepareStatement(query);
		ResultSet result = preStat.executeQuery();
		ArrayList<Student> reponse = new ArrayList<Student>();
		while(result.next()) {
			Student student = new Student();
			student.setId(result.getInt("id"));
			student.setFirstName(result.getString("firstName"));
			student.setLastName(result.getString("lastName"));
			student.setEmail(result.getString("email")); 
			student.setCin(result.getString("cin"));
			student.setLevel(result.getString("level"));
			reponse.add(student);
		}
		preStat.close();
		return reponse;
	}

	@Override
	public boolean addStudent(Student student) throws SQLException {
		String query = "INSERT INTO students (firstName,lastName,email,cin,level) VALUES (?,?,?,?,?);";
		connection = dbInstance.getConnection();
		PreparedStatement preStat = connection.prepareStatement(query);
		preStat.setString(1, student.getFirstName());
		preStat.setString(2, student.getLastName());
		preStat.setString(3, student.getEmail());
		preStat.setString(4, student.getCin());
		preStat.setString(5, student.getLevel());
		int result = preStat.executeUpdate();
		preStat.close();
		return result != 0;
	}

	@Override
	public boolean deleteStudentById(int id) throws SQLException {
		String query = "DELETE FROM students WHERE id= ?";
		connection = dbInstance.getConnection();
		PreparedStatement preStat = connection.prepareStatement(query);
		preStat.setInt(1, id);
		int state = preStat.executeUpdate();
		preStat.close();
		return state != 0;
	}

	@Override
	public boolean updateStudentById(int id, Student student) throws SQLException {
		String query = "UPDATE students SET firstName=?,lastName=?,email=?,cin=?,level=? WHERE id= ?;";
		connection = dbInstance.getConnection();
		PreparedStatement preStat = connection.prepareStatement(query);
		preStat.setString(1, student.getFirstName());
		preStat.setString(2, student.getLastName());
		preStat.setString(3, student.getEmail());
		preStat.setString(4, student.getCin());
		preStat.setString(5, student.getLevel());
		preStat.setInt(6, id);
		int result = preStat.executeUpdate();
		preStat.close();
		return result != 0;
	}
}
