package org.aimprosoft.service;

import org.aimprosoft.dao.EmployeeDao;
import org.aimprosoft.dao.IDao;
import org.aimprosoft.data.Employee;

import java.util.List;

public class EmployeeService implements IService<Employee> {

    private IDao<Employee> employeeIDao;

    public EmployeeService() {
        employeeIDao = new EmployeeDao();
    }

    @Override
    public IDao<Employee> getDao() {
        return employeeIDao;
    }

    public List<Employee> readByDepartmentId(int departmentId) {
        return ((EmployeeDao) getDao()).readByDepartmentId(departmentId);
    }
}
