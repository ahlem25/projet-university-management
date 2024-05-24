package org.iteam.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.iteam.DAO.UserDAO;
import org.iteam.javaBeans.User;

@WebServlet("/Users")
public class UsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UsersServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		
		request.setAttribute("id", id);
		request.setAttribute("action", "supprimer");
		doGet(request, response);
	}


}
