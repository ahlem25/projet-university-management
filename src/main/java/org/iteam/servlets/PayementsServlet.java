package main.java.org.iteam.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import main.java.org.iteam.DAO.PayementDAO;
import main.java.org.iteam.javaBeans.Payement;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/payements")
public class PayementsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public PayementsServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Vérifier la session est encore ouverte
        HttpSession session = request.getSession();
        if(session.getAttribute("user") == null){
            this.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
        }

        response.getWriter().append("Served at: ").append(request.getContextPath());
        PayementDAO payementDAO = new PayementDAO();
        try {
            ArrayList<Payement> payements = payementDAO.getAllPayements();
            request.setAttribute("payements", payements);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.getServletContext().getRequestDispatcher("/payements.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PayementDAO payementDAO = new PayementDAO();
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            payementDAO.deletePayementById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("action", "supprimer");
        doGet(request, response);
    }
}
