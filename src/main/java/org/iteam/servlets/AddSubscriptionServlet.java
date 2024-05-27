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

@WebServlet("/add-subscription")
public class AddSubscriptionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AddSubscriptionServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Vérifier la session est encore ouverte
        HttpSession session = request.getSession();
        if(session.getAttribute("user") == null){
            this.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
        }

        response.getWriter().append("Served at: ").append(request.getContextPath());
        this.getServletContext().getRequestDispatcher("/AddSubscription.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String year = request.getParameter("year");
        int student_id = Integer.parseInt(request.getParameter("student_id"));
        int class_id = Integer.parseInt(request.getParameter("class_id"));

        SubscriptionDAO subscriptionDAO = new SubscriptionDAO();
        Subscription subscription = new Subscription(year, student_id, class_id);
        try {
            subscriptionDAO.addSubscription(subscription);
            ArrayList<Subscription> subscriptions = subscriptionDAO.getAllSubscriptions();
            request.setAttribute("subscriptions", subscriptions);
            request.setAttribute("action", "add");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        this.getServletContext().getRequestDispatcher("/subscriptions.jsp").forward(request, response);
    }
}
