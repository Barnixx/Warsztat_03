package pl.coderslab.controller.UsersGroup;

import pl.coderslab.model.UserGroup;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "editGroup", urlPatterns = "/editGroup")
public class editGroup extends HttpServlet {
    private static Integer groupId;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("name");
        try {
            UserGroup userGroup = UserGroup.loadById(groupId);
            userGroup.setName(name);
            userGroup.saveToDB();
            groupId = null;
        } catch (SQLException e) {
            e.printStackTrace();
            groupId = null;
        }
        response.sendRedirect("/groups");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        groupId = null;
        groupId = Integer.parseInt(request.getParameter("groupId"));
        request.setAttribute("groupName", request.getParameter("groupName"));
        getServletContext().getRequestDispatcher("/editGroup.jsp")
                .forward(request, response);
    }
}
