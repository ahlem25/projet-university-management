package main.java.org.iteam.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import main.java.org.iteam.DAO.ClasseDAO;
import main.java.org.iteam.javaBeans.Classe;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/classes")
public class ClassesServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public ClassesServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        // Vérifier la session est encore ouverte
        HttpSession session = request.getSession();
        if(session.getAttribute("user") == null){
            this.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
        }

        response.getWriter().append("Served at: ").append(request.getContextPath());
        ClasseDAO classeDAO = new ClasseDAO();
        try {
            ArrayList<Classe> classes = classeDAO.getAllClasses();
            request.setAttribute("classes", classes);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.getServletContext().getRequestDispatcher("/classes.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ClasseDAO classeDAO = new ClasseDAO();
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            classeDAO.deleteClasseById(id);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("id", id);
        request.setAttribute("action", "supprimer");
        doGet(request, response);
    }
}
