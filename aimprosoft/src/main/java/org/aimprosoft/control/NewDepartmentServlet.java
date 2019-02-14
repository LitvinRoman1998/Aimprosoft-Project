package org.aimprosoft.control;

import org.aimprosoft.data.Department;
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

@WebServlet(name = "NewDepartmentServlet", urlPatterns = "/newDepartment")
public class NewDepartmentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Department department=new Department();
        department.setDepartmentName(request.getParameter(ServletDictionary.DEPARTMENT_NAME_PARAMETER));
        IValidate<Department> departmentValidate=new DepartmentValidate();
        String errorMessage=departmentValidate.validate(department);
        if(errorMessage != null){
            request.setAttribute(ServletDictionary.ERROR_MESSAGE_ATTRIBUTE,errorMessage);
            request.getRequestDispatcher("WEB-INF/jsp/departmentAddForm.jsp").forward(request,response);
        }else {
            IService<Department> departmentService = new DepartmentService();
            departmentService.create(department);
            response.sendRedirect("/departments");
        }

    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/jsp/departmentAddForm.jsp").forward(request,response);
    }
}
