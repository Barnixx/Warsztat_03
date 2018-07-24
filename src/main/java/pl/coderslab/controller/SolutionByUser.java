package pl.coderslab.controller;

import pl.coderslab.model.Solution;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "SolutionByUser", urlPatterns = "/user_solutions")
public class SolutionByUser extends HttpServlet {
    private static final String TITLE = "User Solutions";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            Solution[] solutions = Solution.loadAllByUserId(Integer.parseInt(request.getParameter("user_id")));
            request.setAttribute("solutions", solutions);
            request.setAttribute("title", TITLE);
            getServletContext().getRequestDispatcher("/index.jsp")
                    .forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
