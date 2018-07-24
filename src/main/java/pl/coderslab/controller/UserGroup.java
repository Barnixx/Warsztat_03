package pl.coderslab.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "UserGroup", urlPatterns = "/user_group")
public class UserGroup extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            pl.coderslab.model.UserGroup[] userGroups = pl.coderslab.model.UserGroup.loadAll();
            request.setAttribute("usersGroups", userGroups);
            request.setAttribute("title", "Users Groups");
            getServletContext().getRequestDispatcher("/userGroup.jsp")
                    .forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
