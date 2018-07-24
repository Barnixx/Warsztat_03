package pl.coderslab.controller;

import pl.coderslab.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "UsersFromGroup", urlPatterns = "/users_from_group")
public class UsersFromGroup extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            User[] users = User.loadAllByGroupId(Integer.parseInt(request.getParameter("group_id")));
            request.setAttribute("title", "Users");
            request.setAttribute("users", users);
            getServletContext().getRequestDispatcher("/usersFromGroup.jsp")
                    .forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
