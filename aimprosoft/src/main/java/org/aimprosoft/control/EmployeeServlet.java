package org.aimprosoft.control;

import org.aimprosoft.data.Employee;
import org.aimprosoft.dict.ServletDictionary;
import org.aimprosoft.service.EmployeeService;
import org.aimprosoft.service.IService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "EmployeeServlet", urlPatterns = "/employees")
public class EmployeeServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        IService<Employee> employeeService = new EmployeeService();
        List<Employee> employees = ((EmployeeService) employeeService)
                .readByDepartmentId(Integer.valueOf(request.getParameter(ServletDictionary.DEPARTMENT_ID_PARAMETER)));
        request.setAttribute("employees", employees);
        request.setAttribute("employeeExists", employees.size() > 0);
        request.getRequestDispatcher("WEB-INF/jsp/employees.jsp").forward(request, response);
    }
}
