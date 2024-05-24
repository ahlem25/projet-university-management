package org.iteam.servlets;


import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.iteam.DAO.StudentDAO;
import org.iteam.javaBeans.Student;

@WebServlet("/AddStudent")
public class AddStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AddStudentServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		this.getServletContext().getRequestDispatcher("/WEB-INF/students/AddStudent.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String email = request.getParameter("email");
		String cin = request.getParameter("cin");
		String level = request.getParameter("level");
		
		StudentDAO studentDAO = new StudentDAO();
		Student student = new Student(id, nom, prenom, email, cin, level);
		try {
			studentDAO.addStudent(student);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		ArrayList<Student> students = null; 
		try {
			students = studentDAO.getAllStudents();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("students", students);
		request.setAttribute("nom", nom);
		request.setAttribute("prenom", prenom);
		request.setAttribute("action", "add");
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/students/Students.jsp").forward(request, response);

	}
}
