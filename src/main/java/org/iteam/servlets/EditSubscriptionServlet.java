package main.java.org.iteam.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import main.java.org.iteam.DAO.ClasseDAO;
import main.java.org.iteam.DAO.StudentDAO;
import main.java.org.iteam.DAO.SubscriptionDAO;
import main.java.org.iteam.javaBeans.Classe;
import main.java.org.iteam.javaBeans.Student;
import main.java.org.iteam.javaBeans.Subscription;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/edit-subscription")
public class EditSubscriptionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public EditSubscriptionServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Vérifier la session est encore ouverte
        HttpSession session = request.getSession();
        if(session.getAttribute("user") == null){
            this.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
        }

        SubscriptionDAO subscriptionDAO = new SubscriptionDAO();
        int id = Integer.parseInt(request.getParameter("id"));
        ClasseDAO classeDAO = new ClasseDAO();
        StudentDAO studentDAO = new StudentDAO();
        try {
            ArrayList<Classe> classes = classeDAO.getAllClasses();
            ArrayList<Student> students = studentDAO.getAllStudents();
            Subscription subscription = subscriptionDAO.getSubscriptionById(id);
            request.setAttribute("id", subscription.getId());
            request.setAttribute("year", subscription.getYear());
            request.setAttribute("student_id", subscription.getStudentId());
            request.setAttribute("class_id", subscription.getClassId());
            request.setAttribute("students", students);
            request.setAttribute("classes", classes);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.getWriter().append("Served at: ").append(request.getContextPath());
        this.getServletContext().getRequestDispatcher("/EditSubscription.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String year = request.getParameter("year");
        int student_id = Integer.parseInt(request.getParameter("student_id"));
        int class_id = Integer.parseInt(request.getParameter("class_id"));

        SubscriptionDAO subscriptionDAO = new SubscriptionDAO();
        Subscription subscription = new Subscription(id, year, student_id, class_id);
        try {
            subscriptionDAO.updateSubscriptionById(id, subscription);
            ArrayList<Subscription> subscriptions = subscriptionDAO.getAllSubscriptions();
            request.setAttribute("subscriptions", subscriptions);
            request.setAttribute("action", "edit");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        this.getServletContext().getRequestDispatcher("/subscriptions.jsp").forward(request, response);
    }
}
