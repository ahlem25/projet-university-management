package main.java.org.iteam.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import main.java.org.iteam.DAO.SubscriptionDAO;
import main.java.org.iteam.javaBeans.Subscription;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/subscriptions")
public class SubscriptionsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public SubscriptionsServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Vérifier la session est encore ouverte
        HttpSession session = request.getSession();
        if(session.getAttribute("user") == null){
            this.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
        }

        response.getWriter().append("Served at: ").append(request.getContextPath());
        SubscriptionDAO subscriptionDAO = new SubscriptionDAO();
        try {
            ArrayList<Subscription> subscriptions = subscriptionDAO.getAllSubscriptions();
            request.setAttribute("subscriptions", subscriptions);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.getServletContext().getRequestDispatcher("/subscriptions.jsp").forward(request, response);
    }
}
