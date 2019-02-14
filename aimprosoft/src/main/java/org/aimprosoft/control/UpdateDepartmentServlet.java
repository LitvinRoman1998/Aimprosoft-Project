package org.aimprosoft.control;

import org.aimprosoft.data.Department;
import org.aimprosoft.dict.DepartmentDictionary;
import org.aimprosoft.dict.ServletDictionary;
import org.aimprosoft.service.DepartmentService;
import org.aimprosoft.service.IService;
import org.aimprosoft.validate.DepartmentValidate;
import org.aimprosoft.validate.IValidate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UpdateDepartmentServlet", urlPatterns = "/updateDepartment")
public class UpdateDepartmentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Department department = new Department(Integer.valueOf(request.getParameter(ServletDictionary.DEPARTMENT_ID_PARAMETER))
                , request.getParameter("newDepartmentName"));
        IValidate<Department> departmentValidate = new DepartmentValidate();
        String errorMessage = departmentValidate.validate(department);
        if (errorMessage != null) {
            department.setDepartmentId(Integer.valueOf(request.getParameter(ServletDictionary.DEPARTMENT_ID_PARAMETER)));
            department.setDepartmentName(request.getParameter("oldDepartmentName"));
            request.setAttribute("updatingDepartment", department);
            request.setAttribute(ServletDictionary.ERROR_MESSAGE_ATTRIBUTE, errorMessage);
            request.getRequestDispatcher("WEB-INF/jsp/departmentUpdateForm.jsp").forward(request, response);
        } else {
            IService<Department> departmentService = new DepartmentService();
            departmentService.update(department);
            response.sendRedirect("/departments");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Department department = new Department(Integer.valueOf(request.getParameter(ServletDictionary.DEPARTMENT_ID_PARAMETER))
                , request.getParameter(ServletDictionary.DEPARTMENT_NAME_PARAMETER));
        request.setAttribute("updatingDepartment", department);
        request.getRequestDispatcher("WEB-INF/jsp/departmentUpdateForm.jsp").forward(request, response);
    }
}
