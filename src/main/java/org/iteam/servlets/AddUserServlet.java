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

@WebServlet("/add-user")
public class AddUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AddUserServlet() {
        super();
    }

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Vérifier la session est encore ouverte
		HttpSession session = request.getSession();
		if(session.getAttribute("user") == null){
			this.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
		}

		response.getWriter().append("Served at: ").append(request.getContextPath());
		this.getServletContext().getRequestDispatcher("/AddUser.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		UserDAO userDAO = new UserDAO();
		User user = new User(nom, prenom, email, password);
		try {
			userDAO.addUser(user);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		ArrayList<User> users = null;
		try {
			users = userDAO.getAllUsers();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("users", users);
		request.setAttribute("nom", nom);
		request.setAttribute("prenom", prenom);
		request.setAttribute("action", "add");
		this.getServletContext().getRequestDispatcher("/users.jsp").forward(request, response);

	}
}
