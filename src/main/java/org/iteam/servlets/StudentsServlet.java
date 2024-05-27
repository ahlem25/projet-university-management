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

@WebServlet("/students")
public class StudentsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public StudentsServlet() {
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
			ArrayList<Student> students = studentDAO.getAllStudents();
			request.setAttribute("students", students);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.getServletContext().getRequestDispatcher("/students.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		StudentDAO studentDAO = new StudentDAO();
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			studentDAO.deleteStudentById(id);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("id", id);
		request.setAttribute("action", "supprimer");
		doGet(request, response);
	}

}
