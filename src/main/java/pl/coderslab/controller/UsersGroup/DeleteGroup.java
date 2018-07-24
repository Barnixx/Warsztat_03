package pl.coderslab.controller.UsersGroup;

import pl.coderslab.model.UserGroup;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "DeleteGroup", urlPatterns = "/deleteGroup")
public class DeleteGroup extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int groupId = Integer.parseInt(request.getParameter("groupId"));
        try {
            UserGroup userGroup = UserGroup.loadById(groupId);
            if (userGroup != null) {
                userGroup.delete();
            }
            response.sendRedirect("/groups");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
