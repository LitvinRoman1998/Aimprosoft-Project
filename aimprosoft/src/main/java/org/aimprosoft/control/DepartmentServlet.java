package org.aimprosoft.control;

import org.aimprosoft.data.Department;
import org.aimprosoft.service.DepartmentService;
import org.aimprosoft.service.IService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "DepartmentServlet", urlPatterns = "/departments")
public class DepartmentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        IService<Department> departmentService = new DepartmentService();
        request.setAttribute("departments", departmentService.readAll());
        request.getRequestDispatcher("WEB-INF/jsp/departments.jsp").forward(request, response);
    }
}
