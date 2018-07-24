package pl.coderslab.controller.UsersGroup;

import pl.coderslab.model.UserGroup;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "AddGroup", urlPatterns = "/addGroup")
public class AddGroup extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("name");

        UserGroup userGroup = new UserGroup(name);
        try {
            userGroup.saveToDB();
            response.sendRedirect("/groups");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        getServletContext().getRequestDispatcher("/addGroup.jsp")
                .forward(request, response);
    }
}
