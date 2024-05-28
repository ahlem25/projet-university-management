package main.java.org.iteam.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import main.java.org.iteam.DAO.PayementDAO;
import main.java.org.iteam.DAO.StudentDAO;
import main.java.org.iteam.javaBeans.Payement;
import main.java.org.iteam.javaBeans.Student;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

@WebServlet("/add-payement")
public class AddPayementServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AddPayementServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Vérifier la session est encore ouverte
        HttpSession session = request.getSession();
        if(session.getAttribute("user") == null){
            this.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
        }

        StudentDAO studentDAO = new StudentDAO();
        try{
            ArrayList<Student> students = studentDAO.getAllStudents();
            request.setAttribute("students", students);
        }catch (SQLException e) {
            e.printStackTrace();
        }
        response.getWriter().append("Served at: ").append(request.getContextPath());
        this.getServletContext().getRequestDispatcher("/AddPayement.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String amount = request.getParameter("amount");
        LocalDate date = LocalDate.parse(request.getParameter("date"));
        String comment = request.getParameter("comment");
        int student_id = Integer.parseInt(request.getParameter("student_id"));

        PayementDAO payementDAO = new PayementDAO();
        Payement payement = new Payement(amount, date, comment, student_id);
        ArrayList<Payement> payements = null;
        try {
            payementDAO.addPayement(payement);
            payements = payementDAO.getAllPayements();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("payements", payements);
        request.setAttribute("action", "add");

        this.getServletContext().getRequestDispatcher("/payements.jsp").forward(request, response);

    }
}
