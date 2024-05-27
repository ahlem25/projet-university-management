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

@WebServlet("/edit-student")
public class EditStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public EditStudentServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Vérifier la session est encore ouverte
		HttpSession session = request.getSession();
		if(session.getAttribute("user") == null){
			this.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
		}

		response.getWriter().append("Served at: ").append(request.getContextPath());
		StudentDAO studentDAO = new StudentDAO();
		
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			Student student = studentDAO.getStudentById(id);
			
			request.setAttribute("id", student.getId());
			request.setAttribute("nom", student.getFirstName());
			request.setAttribute("prenom", student.getLastName());
			request.setAttribute("email", student.getEmail());
			request.setAttribute("cin", student.getCin());
			request.setAttribute("level", student.getLevel());

		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.getServletContext().getRequestDispatcher("/EditStudent.jsp").forward(request, response);
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
			studentDAO.updateStudentById(id, student);
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
		request.setAttribute("action", "edit");
		
		this.getServletContext().getRequestDispatcher("/students.jsp").forward(request, response);

	}
}
