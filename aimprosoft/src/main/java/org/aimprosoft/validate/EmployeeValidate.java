package org.aimprosoft.validate;

import org.aimprosoft.data.Employee;
import org.aimprosoft.service.EmployeeService;
import org.aimprosoft.service.IService;

import java.time.LocalDate;


public class EmployeeValidate implements IValidate<Employee> {
    @Override
    public String validate(Employee entity) {
        String validationMessage;
        if ((validationMessage = validateLastName(entity.getLastName())) != null);
        else if ((validationMessage = validateFirstName(entity.getFirstName())) != null);
        else if ((validationMessage = validateMiddleName(entity.getMiddleName())) != null);
        else if ((validationMessage = validateBirthsay(entity.getBirthday())) != null);
        else if ((validationMessage = validateEmail(entity.getEmail())) != null);
        else if ((validationMessage = validateSalary(entity.getSalary())) != null);


        return validationMessage;
    }

    public String validateLastName(String lastName) {
        String validationMessage = null;
        if (lastName.trim().equals("")) {
            validationMessage = "Last name is empty";
        } else if (!lastName.chars().allMatch(Character::isLetter)) {
            validationMessage = "Last name must contain only letters";
        }
        return validationMessage;
    }

    public String validateFirstName(String firstName) {
        String validationMessage = null;
        if (firstName.trim().equals("")) {
            validationMessage = "First name is empty";
        } else if (!firstName.chars().allMatch(Character::isLetter)) {
            validationMessage = "First name must contain only letters";
        }
        return validationMessage;
    }

    public String validateMiddleName(String middleName) {
        String validationMessage = null;
        if (middleName.trim().equals("")) {
            validationMessage = "Middle name is empty";
        } else if (!middleName.chars().allMatch(Character::isLetter)) {
            validationMessage = "Middle name must contain only letters";
        }
        return validationMessage;
    }

    public String validateBirthsay(LocalDate birthday) {
        String validationMessage = null;
        if (birthday.getYear() > LocalDate.now().getYear() - 14) {
            validationMessage = "Date of birth is not included in the valid limits (Birthday should be earlier than "
                    +LocalDate.now().getDayOfMonth()+"."+LocalDate.now().getMonthValue()+"."
                    +(LocalDate.now().getYear() - 14)+")";
        }
        return validationMessage;
    }

    public String validateEmail(String email) {
        String validationMessage = null;
        IService<Employee> employeeService = new EmployeeService();
        if (employeeService.read(email).getEmployeeId() > 0) {
            validationMessage = "Employee with such email is already exists";
        }
        return validationMessage;
    }

    public String validateSalary(int salary) {
        String validationMessage = null;
        if (!(salary > 0)) {
            validationMessage = "Salary must be greater than zero";
        }
        return validationMessage;
    }
}
