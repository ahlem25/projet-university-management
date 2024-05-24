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

@WebServlet("/Students")
public class StudentsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public StudentsServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		StudentDAO studentDAO = new StudentDAO();

		try {
			ArrayList<Student> students = studentDAO.getAllStudents();
			request.setAttribute("students", students);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/students/Students.jsp").forward(request, response);
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
