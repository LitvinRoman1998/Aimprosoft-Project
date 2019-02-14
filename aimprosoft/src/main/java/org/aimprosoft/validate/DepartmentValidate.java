package org.aimprosoft.validate;

import org.aimprosoft.data.Department;
import org.aimprosoft.service.DepartmentService;

public class DepartmentValidate implements IValidate<Department> {
    @Override
    public String validate(Department entity) {
        String validationMessage = null;
        if (entity.getDepartmentName().trim().equals("")) {
            validationMessage = "Department name is empty";
        } else if (entity.getDepartmentName().trim().length() < 3) {
            validationMessage = "Department name is too short";
        } else if ((new DepartmentService().read(entity.getDepartmentName())).getDepartmentId() > 0) {
            validationMessage = "Department with such name is already exists";
        }
        return validationMessage;
    }
}
