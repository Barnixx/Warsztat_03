package pl.coderslab.controller;

import pl.coderslab.model.Solution;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ExerciseSolution", urlPatterns = "/exercise_solution")
public class ExerciseSolution extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int solutionId = Integer.parseInt(request.getParameter("exercise"));
        try {
            request.setAttribute("solution", Solution.loadById(solutionId));
            request.setAttribute("title", "Solution");
            getServletContext().getRequestDispatcher("/solution.jsp")
                    .forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
