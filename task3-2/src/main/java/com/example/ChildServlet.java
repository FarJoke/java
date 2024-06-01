package com.example;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ChildServlet", value = "/children")
public class ChildServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Child> children = Database.getAllChildren();
        request.setAttribute("children", children);
        request.getRequestDispatcher("/children.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("add".equals(action)) {
            String name = request.getParameter("name");
            String gender = request.getParameter("gender");
            int age = Integer.parseInt(request.getParameter("age"));
            int number = Integer.parseInt(request.getParameter("number"));
            int groupNumber = Integer.parseInt(request.getParameter("groupNumber"));
            Database.saveChild(new Child(number, name, gender, age, groupNumber));
        } else if ("update".equals(action)) {
            String name = request.getParameter("name");
            String gender = request.getParameter("gender");
            int age = Integer.parseInt(request.getParameter("age"));
            int number = Integer.parseInt(request.getParameter("number"));
            int groupNumber = Integer.parseInt(request.getParameter("groupNumber"));
            Database.updateChild(new Child(number, name, gender, age, groupNumber));
        } else if ("delete".equals(action)) {
            int number = Integer.parseInt(request.getParameter("number"));
            Database.deleteChild(number);
        }
        response.sendRedirect("children");
    }
}
