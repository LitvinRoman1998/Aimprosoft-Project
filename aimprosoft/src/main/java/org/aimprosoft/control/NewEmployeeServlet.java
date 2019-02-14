package org.aimprosoft.control;

import org.aimprosoft.data.Department;
import org.aimprosoft.data.Employee;
import org.aimprosoft.dict.ServletDictionary;
import org.aimprosoft.service.EmployeeService;
import org.aimprosoft.service.IService;
import org.aimprosoft.validate.EmployeeValidate;
import org.aimprosoft.validate.IValidate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

@WebServlet(name = "NewEmployeeServlet", urlPatterns = "/newEmployee")
public class NewEmployeeServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        IService<Employee> employeeService = new EmployeeService();
        Employee employee = new Employee();
        String errorMessage;
        Department department = new Department(Integer.valueOf(request.getParameter(ServletDictionary.DEPARTMENT_ID_PARAMETER))
                , request.getParameter("departmentName"));
        employee.setLastName(request.getParameter("lastName"));
        employee.setFirstName(request.getParameter("firstName"));
        employee.setMiddleName(request.getParameter("middleName"));
        checkParameters(request,response,"birthday","Birthday field can`t be empty");
        employee.setBirthday(Date.valueOf(request.getParameter("birthday")).toLocalDate().plusDays(1));
        checkParameters(request,response,"email","Email Field can`t be empty");
        employee.setEmail(request.getParameter("email"));
        checkParameters(request,response,"salary","Salary Field can`t be empty");
        employee.setSalary(Integer.valueOf(request.getParameter("salary")));
        employee.setEmployeeDepartment(department);
        IValidate<Employee> employeeValidate = new EmployeeValidate();
        errorMessage = employeeValidate.validate(employee);
        if (errorMessage != null) {
            request.setAttribute(ServletDictionary.ERROR_MESSAGE_ATTRIBUTE, errorMessage);
            request.getRequestDispatcher("WEB-INF/jsp/employeeAddForm.jsp").forward(request, response);
        } else {
            employeeService.create(employee);
            response.sendRedirect("/employees?departmentId=" + request.getParameter(ServletDictionary.DEPARTMENT_ID_PARAMETER) + "&departmentName=" + request.getParameter("departmentName"));
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/jsp/employeeAddForm.jsp").forward(request, response);
    }

    private void checkParameters(HttpServletRequest request, HttpServletResponse response,String checkedParameter ,String errorMessage) throws ServletException, IOException {
        if (request.getParameter(checkedParameter).equals("")) {
            request.setAttribute(ServletDictionary.ERROR_MESSAGE_ATTRIBUTE, errorMessage);
            request.getRequestDispatcher("WEB-INF/jsp/employeeAddForm.jsp").forward(request, response);
        }
    }
}
