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

@WebServlet("/edit-payement")
public class EditPayementServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public EditPayementServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Vérifier la session est encore ouverte
        HttpSession session = request.getSession();
        if(session.getAttribute("user") == null){
            this.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
        }

        PayementDAO payementDAO = new PayementDAO();
        StudentDAO studentDAO = new StudentDAO();
        int id = Integer.parseInt(request.getParameter("id"));
        try{
            ArrayList<Student> students = studentDAO.getAllStudents();
            Payement payement = payementDAO.getPayementById(id);
            request.setAttribute("id", payement.getId());
            request.setAttribute("amount", payement.getAmount());
            request.setAttribute("date", payement.getDate());
            request.setAttribute("comment", payement.getComment());
            request.setAttribute("student_id", payement.getStudentId());
            request.setAttribute("students", students);
        }catch (SQLException e) {
            e.printStackTrace();
        }
        response.getWriter().append("Served at: ").append(request.getContextPath());
        this.getServletContext().getRequestDispatcher("/EditPayement.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String amount = request.getParameter("amount");
        LocalDate date = LocalDate.parse(request.getParameter("date"));
        String comment = request.getParameter("comment");
        int student_id = Integer.parseInt(request.getParameter("student_id"));

        PayementDAO payementDAO = new PayementDAO();
        Payement payement = new Payement(id, amount, date, comment, student_id);
        ArrayList<Payement> payements = null;
        try {
            payementDAO.updatePayementById(id, payement);
            payements = payementDAO.getAllPayements();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("payements", payements);
        request.setAttribute("action", "edit");

        this.getServletContext().getRequestDispatcher("/payements.jsp").forward(request, response);

    }
}
