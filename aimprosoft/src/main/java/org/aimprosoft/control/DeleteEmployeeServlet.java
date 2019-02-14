package org.aimprosoft.control;

import org.aimprosoft.data.Department;
import org.aimprosoft.data.Employee;
import org.aimprosoft.dict.ServletDictionary;
import org.aimprosoft.service.DepartmentService;
import org.aimprosoft.service.EmployeeService;
import org.aimprosoft.service.IService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteEmployeeServlet", urlPatterns = "/deleteEmployee")
public class DeleteEmployeeServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        IService<Employee> departmentService = new EmployeeService();
        departmentService.delete(Integer.valueOf(request.getParameter(ServletDictionary.EMPLOYEE_ID_PARAMETER)));
        response.sendRedirect("/employees?departmentId=" + request.getParameter(ServletDictionary.DEPARTMENT_ID_PARAMETER)
                + "&departmentName=" + request.getParameter(ServletDictionary.DEPARTMENT_NAME_PARAMETER));
    }
}
