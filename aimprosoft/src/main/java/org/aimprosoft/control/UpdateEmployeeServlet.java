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

@WebServlet(name = "UpdateEmployeeServlet", urlPatterns = "/updateEmployee")
public class UpdateEmployeeServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Employee employee = new Employee();
        employee.setEmployeeId(Integer.valueOf(request.getParameter(ServletDictionary.EMPLOYEE_ID_PARAMETER)));
        checkParameters(request, response, employee);
        IService<Employee> departmentService = new EmployeeService();
        departmentService.update(employee);
        response.sendRedirect("/employees?departmentId=" + request.getParameter(ServletDictionary.DEPARTMENT_ID_PARAMETER)
                + "&departmentName=" + request.getParameter(ServletDictionary.DEPARTMENT_NAME_PARAMETER));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Employee employee = new Employee();
        employee.setEmployeeId(Integer.valueOf(request.getParameter(ServletDictionary.EMPLOYEE_ID_PARAMETER)));
        employee.setLastName(request.getParameter("employeeLastName"));
        employee.setFirstName(request.getParameter("employeeFirstName"));
        employee.setMiddleName(request.getParameter("employeeMiddleName"));
        employee.setBirthday(Date.valueOf(request.getParameter("employeeBirthday")).toLocalDate());
        employee.setEmail(request.getParameter("employeeEmail"));
        employee.setSalary(Integer.valueOf(request.getParameter("employeeSalary")));
        request.setAttribute("updatingEmployee", employee);
        request.getRequestDispatcher("WEB-INF/jsp/employeeUpdateForm.jsp").forward(request, response);
    }

    private void checkAndDecisiveMethod(HttpServletRequest request, HttpServletResponse response, Employee employee, String errorMessage) throws ServletException, IOException {

        employee.setLastName(request.getParameter("oldEmployeeLastName"));
        employee.setFirstName(request.getParameter("oldEmployeeFirstName"));
        employee.setMiddleName(request.getParameter("oldEmployeeMiddleName"));
        employee.setBirthday(Date.valueOf(request.getParameter("oldEmployeeBirthday")).toLocalDate());
        employee.setEmail(request.getParameter("oldEmployeeEmail"));
        employee.setSalary(Integer.valueOf(request.getParameter("oldEmployeeSalary")));
        request.setAttribute("updatingEmployee", employee);
        request.setAttribute(ServletDictionary.ERROR_MESSAGE_ATTRIBUTE, errorMessage);
        request.getRequestDispatcher("WEB-INF/jsp/employeeUpdateForm.jsp").forward(request, response);
    }

    private void checkParameters(HttpServletRequest request, HttpServletResponse response, Employee employee) throws ServletException, IOException {
        String errorMessage;
        EmployeeValidate employeeValidate = new EmployeeValidate();
        if (!request.getParameter("newLastName").equals("")) {
            employee.setLastName(request.getParameter("newLastName"));
            errorMessage = employeeValidate.validateLastName(employee.getLastName());
            if (errorMessage != null) {
                checkAndDecisiveMethod(request, response, employee, errorMessage);
            }
        }
        if (!request.getParameter("newFirstName").equals("")) {
            employee.setFirstName(request.getParameter("newFirstName"));
            errorMessage = employeeValidate.validateFirstName(employee.getFirstName());
            if (errorMessage != null) {
                checkAndDecisiveMethod(request, response, employee, errorMessage);
            }
        }
        if (!request.getParameter("newMiddleName").equals("")) {
            employee.setMiddleName(request.getParameter("newMiddleName"));
            errorMessage = employeeValidate.validateMiddleName(employee.getMiddleName());
            if (errorMessage != null) {
                checkAndDecisiveMethod(request, response, employee, errorMessage);
            }
        }
        if (!request.getParameter("newBirthday").equals("")) {
            employee.setBirthday(Date.valueOf(request.getParameter("newBirthday")).toLocalDate());
            errorMessage = employeeValidate.validateBirthsay(employee.getBirthday());
            if (errorMessage != null) {
                checkAndDecisiveMethod(request, response, employee, errorMessage);
            }
        }
        if (!request.getParameter("newEmail").equals("")) {
            employee.setEmail(request.getParameter("newEmail"));
            errorMessage = employeeValidate.validateEmail(employee.getEmail());
            if (errorMessage != null) {
                checkAndDecisiveMethod(request, response, employee, errorMessage);
            }
        }
        if (!request.getParameter("newSalary").equals("")) {
            employee.setSalary(Integer.valueOf(request.getParameter("newSalary")));
            errorMessage = employeeValidate.validateSalary(employee.getSalary());
            if (errorMessage != null) {
                checkAndDecisiveMethod(request, response, employee, errorMessage);
            }
        }
    }
}
