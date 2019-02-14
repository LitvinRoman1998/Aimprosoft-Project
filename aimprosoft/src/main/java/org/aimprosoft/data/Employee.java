package org.aimprosoft.data;

import java.time.LocalDate;

public class Employee {
    private int employeeId;
    private String lastName;
    private String firstName;
    private String middleName;
    private LocalDate birthday;
    private String email;
    private int salary;
    private Department employeeDepartment;

    public Employee() {
    }

    public Employee(int employeeId, String lastName, String firstName, String middleName, LocalDate birthday, String email, int salary, Department employeeDepartment) {
        this.employeeId = employeeId;
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.birthday = birthday;
        this.email = email;
        this.salary = salary;
        this.employeeDepartment = employeeDepartment;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Department getEmployeeDepartment() {
        return employeeDepartment;
    }

    public void setEmployeeDepartment(Department employeeDepartment) {
        this.employeeDepartment = employeeDepartment;
    }
}
