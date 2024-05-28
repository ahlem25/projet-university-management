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
import main.java.org.iteam.DAO.UserDAO;
import main.java.org.iteam.javaBeans.User;

@WebServlet("/users")
public class UsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public UsersServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Vérifier la session est encore ouverte
		HttpSession session = request.getSession();
		if(session.getAttribute("user") == null){
			this.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
		}

		response.getWriter().append("Served at: ").append(request.getContextPath());
		UserDAO userDAO = new UserDAO();
		try {
			ArrayList<User> users = userDAO.getAllUsers();
			request.setAttribute("users", users);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.getServletContext().getRequestDispatcher("/users.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		UserDAO userDAO = new UserDAO();
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			userDAO.deleteUserById(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("action", "supprimer");
		doGet(request, response);
	}
}
