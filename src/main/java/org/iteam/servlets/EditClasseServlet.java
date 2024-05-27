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

@WebServlet("/edit-class")
public class EditClasseServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public EditClasseServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Vérifier la session est encore ouverte
        HttpSession session = request.getSession();
        if(session.getAttribute("user") == null){
            this.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
        }

        ClasseDAO classeDAO = new ClasseDAO();
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            Classe classe = classeDAO.getClasseById(id);

            request.setAttribute("id", classe.getId());
            request.setAttribute("name", classe.getName());
            request.setAttribute("comment", classe.getComment());
            request.setAttribute("ofYear", classe.getOfYear());

        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.getWriter().append("Served at: ").append(request.getContextPath());
        this.getServletContext().getRequestDispatcher("/EditClass.jsp").forward(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String comment = request.getParameter("comment");
        String ofYear = request.getParameter("ofYear");

        ClasseDAO classeDAO = new ClasseDAO();
        Classe classe = new Classe(id, name, comment, ofYear);
        try {
            classeDAO.updateClasseById(id, classe);
            ArrayList<Classe> classes = classeDAO.getAllClasses();
            request.setAttribute("classes", classes);
            request.setAttribute("action", "edit");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        this.getServletContext().getRequestDispatcher("/classes.jsp").forward(request, response);
    }
}
