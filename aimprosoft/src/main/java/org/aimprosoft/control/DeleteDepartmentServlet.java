package org.aimprosoft.control;

import org.aimprosoft.data.Department;
import org.aimprosoft.dict.ServletDictionary;
import org.aimprosoft.service.DepartmentService;
import org.aimprosoft.service.IService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteDepartmentServlet", urlPatterns = "/deleteDepartment")
public class DeleteDepartmentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        IService<Department> departmentService = new DepartmentService();
        departmentService.delete(Integer.valueOf(request.getParameter(ServletDictionary.DEPARTMENT_ID_PARAMETER)));
        response.sendRedirect("/departments");
    }
}
