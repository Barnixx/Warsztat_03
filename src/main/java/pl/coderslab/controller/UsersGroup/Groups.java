package pl.coderslab.controller.UsersGroup;

import pl.coderslab.model.UserGroup;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "Groups", urlPatterns = "/groups")
public class Groups extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            UserGroup[] userGroups = UserGroup.loadAll();
            request.setAttribute("userGroups", userGroups);
            request.setAttribute("title", "User Groups");

            getServletContext().getRequestDispatcher("/groups.jsp")
                    .forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
