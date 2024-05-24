package org.iteam.DAO.interfaces;

import java.sql.SQLException;
import java.util.ArrayList;

import org.iteam.javaBeans.Student;

public interface StudentsManagementService {
	public Student getStudentById(int id) throws SQLException;
	public ArrayList<Student> getAllStudents() throws SQLException;
	public boolean addStudent(Student student) throws SQLException;
	public boolean deleteStudentById(int id) throws SQLException;
	public boolean updateStudentById(int id, Student student) throws SQLException;
}
