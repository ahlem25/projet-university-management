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

@WebServlet("/edit-user")
public class EditUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public EditUserServlet() {
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
			int id = Integer.parseInt(request.getParameter("id"));
			User user = userDAO.getUserById(id);
			
			request.setAttribute("id", user.getId());
			request.setAttribute("nom", user.getFirstName());
			request.setAttribute("prenom", user.getLastName());
			request.setAttribute("email", user.getEmail());
			request.setAttribute("password", user.getPassword());

		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.getServletContext().getRequestDispatcher("/EditUser.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		UserDAO userDAO = new UserDAO();
		User user = new User(id, nom, prenom, email, password);
		ArrayList<User> users = null;
		try {
			userDAO.updateUserById(id, user);
			users = userDAO.getAllUsers();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// MAJ du session si c'est néceesaire
		HttpSession session = request.getSession();
		if(session.getAttribute("user")!=null){
			User userSession = (User) session.getAttribute("user");
			if(userSession.getId() == id){
				session.setAttribute("user", user);
			}
		}
		
		request.setAttribute("users", users);
		request.setAttribute("action", "edit");
		
		this.getServletContext().getRequestDispatcher("/users.jsp").forward(request, response);

	}
}
