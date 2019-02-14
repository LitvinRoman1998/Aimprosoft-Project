package org.aimprosoft.service;

import org.aimprosoft.dao.DepartmentDao;
import org.aimprosoft.dao.IDao;
import org.aimprosoft.data.Department;


public class DepartmentService implements IService<Department> {

    private IDao<Department> departmentIDao;

    public DepartmentService() {
        departmentIDao = new DepartmentDao();
    }

    @Override
    public IDao<Department> getDao() {
        return departmentIDao;
    }
}
