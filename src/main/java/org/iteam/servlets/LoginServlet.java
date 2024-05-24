package org.iteam.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.iteam.DAO.UserDAO;
import org.iteam.javaBeans.User;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public LoginServlet() {
        super();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession();
    	// check the session if exist
    	if(session.getAttribute("user")!=null) {
    		User user = (User) session.getAttribute("user");
    		// continue to the home (session exist)
    		this.getServletContext().getRequestDispatcher("/home.jsp").forward(request, response);
    	}
    	// Redirection to login if not connected (using session)
		this.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		UserDAO userDAO = new UserDAO();
		User user = null ;
		try {
			user=userDAO.login(email, password);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		if(user != null) {
			// ajouter une session 
			HttpSession session = request.getSession();
            session.setAttribute("user", user);
            // redirection vers la page home
            this.getServletContext().getRequestDispatcher("/home.jsp").forward(request, response);
		}
		else {
			String message = "E-mail et/ou mot de passe incorrect(s)";
            request.setAttribute("message", message);
            doGet(request, response);                
		}
	}
}
