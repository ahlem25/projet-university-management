package main.java.org.iteam.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import main.java.org.iteam.DAO.UserDAO;
import main.java.org.iteam.javaBeans.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public ProfileServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
        HttpSession session = request.getSession();
        // check the session if exist
        if(session.getAttribute("user")!=null) {
            User user = (User) session.getAttribute("user");
            request.setAttribute("id", user.getId());
            request.setAttribute("nom", user.getFirstName());
            request.setAttribute("prenom", user.getLastName());
            request.setAttribute("email", user.getEmail());
            request.setAttribute("password", user.getPassword());
            this.getServletContext().getRequestDispatcher("/profile.jsp").forward(request, response);
        }else {
            // Redirection to login if not connected (using session)
            this.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        UserDAO userDAO = new UserDAO();
        User user = new User(id, nom, prenom, email, password);
        try {
            userDAO.updateUserById(id, user);
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
        request.setAttribute("action", "updated");
        this.doGet(request, response);
    }
}
