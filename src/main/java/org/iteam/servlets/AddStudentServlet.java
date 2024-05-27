package main.java.org.iteam.servlets;


import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.servlet.http.HttpSession;
import main.java.org.iteam.DAO.StudentDAO;
import main.java.org.iteam.javaBeans.Student;

@WebServlet("/add-student")
public class AddStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AddStudentServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Vérifier la session est encore ouverte
		HttpSession session = request.getSession();
		if(session.getAttribute("user") == null){
			this.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
		}

		response.getWriter().append("Served at: ").append(request.getContextPath());
		this.getServletContext().getRequestDispatcher("/AddStudent.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String email = request.getParameter("email");
		String cin = request.getParameter("cin");
		String level = request.getParameter("level");
		
		StudentDAO studentDAO = new StudentDAO();
		Student student = new Student(nom, prenom, email, cin, level);
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
		
		this.getServletContext().getRequestDispatcher("/students.jsp").forward(request, response);

	}
}
