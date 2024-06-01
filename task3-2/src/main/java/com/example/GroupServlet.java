package com.example;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "GroupServlet", value = "/groups")
public class GroupServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Group> groups = Database.getAllGroups();
        request.setAttribute("groups", groups);
        request.getRequestDispatcher("/groups.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("add".equals(action)) {
            String name = request.getParameter("name");
            int number = Integer.parseInt(request.getParameter("number"));
            Database.saveGroup(new Group(number, name));
        } else if ("update".equals(action)) {
            String name = request.getParameter("name");
            int number = Integer.parseInt(request.getParameter("number"));
            Database.updateGroup(new Group(number, name));
        } else if ("delete".equals(action)) {
            int number = Integer.parseInt(request.getParameter("number"));
            Database.deleteGroup(number);
        }
        response.sendRedirect("groups");
    }
}

