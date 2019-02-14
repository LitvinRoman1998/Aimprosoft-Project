package org.aimprosoft.dao;

import org.aimprosoft.connection.ConnectionFactory;
import org.aimprosoft.data.Employee;
import org.aimprosoft.dict.EmployeeDictionary;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeDao implements IDao<Employee> {
    @Override
    public void create(Employee employee) {
        String insertRequest = "INSERT INTO " + EmployeeDictionary.DB_EMPLOYEE_TABLE_NAME +
                "(" + EmployeeDictionary.DB_TABLE_EMPLOYEE_LAST_NAME + ", " +
                EmployeeDictionary.DB_TABLE_EMPLOYEE_FIRST_NAME + ", " +
                EmployeeDictionary.DB_TABLE_EMPLOYEE_MIDDLE_NAME + ", " +
                EmployeeDictionary.DB_TABLE_EMPLOYEE_BIRTHDAY + ", " +
                EmployeeDictionary.DB_TABLE_EMPLOYEE_EMAIL + ", " +
                EmployeeDictionary.DB_TABLE_EMPLOYEE_SALARY + ", " +
                EmployeeDictionary.DB_TABLE_EMPLOYEE_DEPARTMENT + ") VALUES (?,?,?,?,?,?,?);";
        try (Connection connection = ConnectionFactory.getMySQLConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertRequest)) {
            List<Object> insertInformation = new ArrayList<>();
            insertInformation.add(employee.getLastName());
            insertInformation.add(employee.getFirstName());
            insertInformation.add(employee.getMiddleName());
            insertInformation.add(employee.getBirthday());
            insertInformation.add(employee.getEmail());
            insertInformation.add(employee.getSalary());
            insertInformation.add(employee.getEmployeeDepartment().getDepartmentId());
            fillRequest(preparedStatement, insertInformation);
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                employee.setEmployeeId(resultSet.getInt(1));
            }
            resultSet.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Employee read(String email) {
        Employee employee = new Employee();
        String selectRequest = String.valueOf(formSelectRequest(EmployeeDictionary.DB_TABLE_EMPLOYEE_EMAIL + "=?;"));
        try (Connection connection = ConnectionFactory.getMySQLConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectRequest)) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                employee = parseEmployee(resultSet);
            }
            resultSet.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return employee;
    }

    @Override
    public List<Employee> readAll() {
        List<Employee> employees = new ArrayList<>();
        String selectRequest = String.valueOf(formSelectRequest(""));
        try (Connection connection = ConnectionFactory.getMySQLConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectRequest);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                employees.add(parseEmployee(resultSet));
            }
            resultSet.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return employees;
    }

    public List<Employee> readByDepartmentId(int departmentId) {
        List<Employee> employees = new ArrayList<>();
        String selectRequest = String.valueOf(formSelectRequest(EmployeeDictionary.DB_TABLE_EMPLOYEE_DEPARTMENT + "=?;"));
        try (Connection connection = ConnectionFactory.getMySQLConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectRequest)) {
            preparedStatement.setInt(1, departmentId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                employees.add(parseEmployee(resultSet));
            }
            resultSet.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return employees;
    }

    @Override
    public void update(Employee employee) {
        Map<String, Object> formUpdatingRequestRezultMap = formUpdateRequest(employee);
        if (formUpdatingRequestRezultMap.isEmpty()) {
            return;
        }
        List<Object> updatingList = (List) (formUpdatingRequestRezultMap.get(EmployeeDictionary.EMPLOYEE_UPDATING_LIST));
        updatingList.add(employee.getEmployeeId());
        try (Connection connection = ConnectionFactory.getMySQLConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(String.valueOf(formUpdatingRequestRezultMap.get(EmployeeDictionary.EMPLOYEE_UPDATING_REQUEST)))) {
            fillRequest(preparedStatement, updatingList);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    @Override
    public void delete(int employeeId) {
        String deleteRequest = String.valueOf(formDeleteRequest(EmployeeDictionary.DB_TABLE_EMPLOYEE_ID + "=?;"));
        deletingEmployee(employeeId,deleteRequest);
    }

    public void deleteEmployeesByDepartmentId(int departmentId) {
        String deleteRequest = String.valueOf(formDeleteRequest(EmployeeDictionary.DB_TABLE_EMPLOYEE_DEPARTMENT + "=?;"));
        deletingEmployee(departmentId,deleteRequest);
    }

    public Employee parseEmployee(ResultSet resultSet) throws SQLException {
        return new Employee(resultSet.getInt(EmployeeDictionary.DB_TABLE_EMPLOYEE_ID),
                resultSet.getString(EmployeeDictionary.DB_TABLE_EMPLOYEE_LAST_NAME),
                resultSet.getString(EmployeeDictionary.DB_TABLE_EMPLOYEE_FIRST_NAME),
                resultSet.getString(EmployeeDictionary.DB_TABLE_EMPLOYEE_MIDDLE_NAME),
                resultSet.getDate(EmployeeDictionary.DB_TABLE_EMPLOYEE_BIRTHDAY).toLocalDate(),
                resultSet.getString(EmployeeDictionary.DB_TABLE_EMPLOYEE_EMAIL),
                resultSet.getInt(EmployeeDictionary.DB_TABLE_EMPLOYEE_SALARY),
                new DepartmentDao().read(resultSet.getInt(EmployeeDictionary.DB_TABLE_EMPLOYEE_DEPARTMENT)));
    }

    private StringBuilder formDeleteRequest(String requestRestriction) {
        StringBuilder deleteRequest = new StringBuilder("DELETE FROM " + EmployeeDictionary.DB_EMPLOYEE_TABLE_NAME);
        if (requestRestriction != null && !requestRestriction.equals("")) {
            deleteRequest.append(" WHERE ").append(requestRestriction);
        }
        return deleteRequest;
    }

    private Map<String, Object> formUpdateRequest(Employee employee) {
        Map<String, Object> rezultMap = new HashMap<>();
        int updatingFieldsCount = 0;
        List<Object> updatingList = new ArrayList<>();
        StringBuilder updateRequest = new StringBuilder("UPDATE " + EmployeeDictionary.DB_EMPLOYEE_TABLE_NAME + " SET ");
        if (employee.getLastName() != null) {
            updateRequest.append(EmployeeDictionary.DB_TABLE_EMPLOYEE_LAST_NAME + "=?, ");
            updatingList.add(employee.getLastName());
            updatingFieldsCount++;
        }
        if (employee.getFirstName() != null) {
            updateRequest.append(EmployeeDictionary.DB_TABLE_EMPLOYEE_FIRST_NAME + "=?, ");
            updatingList.add(employee.getFirstName());
            updatingFieldsCount++;
        }
        if (employee.getMiddleName() != null) {
            updateRequest.append(EmployeeDictionary.DB_TABLE_EMPLOYEE_MIDDLE_NAME + "=?, ");
            updatingList.add(employee.getMiddleName());
            updatingFieldsCount++;
        }
        if (employee.getBirthday() != null) {
            updateRequest.append(EmployeeDictionary.DB_TABLE_EMPLOYEE_BIRTHDAY + "=?, ");
            updatingList.add(employee.getBirthday());
            updatingFieldsCount++;
        }
        if (employee.getEmail() != null) {
            updateRequest.append(EmployeeDictionary.DB_TABLE_EMPLOYEE_EMAIL + "=?, ");
            updatingList.add(employee.getEmail());
            updatingFieldsCount++;
        }
        if (employee.getSalary() != 0) {
            updateRequest.append(EmployeeDictionary.DB_TABLE_EMPLOYEE_SALARY + "=?, ");
            updatingList.add(employee.getSalary());
            updatingFieldsCount++;
        }
        updateRequest.deleteCharAt(updateRequest.lastIndexOf(", "));
        updateRequest.append("WHERE " + EmployeeDictionary.DB_TABLE_EMPLOYEE_ID + "=?;");
        if (updatingFieldsCount != 0) {
            rezultMap.put(EmployeeDictionary.EMPLOYEE_UPDATING_COUNT, updatingFieldsCount);
            rezultMap.put(EmployeeDictionary.EMPLOYEE_UPDATING_REQUEST, updateRequest);
            rezultMap.put(EmployeeDictionary.EMPLOYEE_UPDATING_LIST, updatingList);
        }
        return rezultMap;
    }

    private void fillRequest(PreparedStatement preparedStatement, List<Object> fillingList) throws SQLException {
        for (int i = 0; i < fillingList.size(); i++) {
            Object parameter = fillingList.get(i);
            if (parameter instanceof String) {
                preparedStatement.setString(i + 1, String.valueOf(parameter));
            } else if (parameter instanceof LocalDate) {
                preparedStatement.setDate(i + 1, Date.valueOf((LocalDate) parameter));
            } else if (parameter instanceof Integer) {
                preparedStatement.setInt(i + 1, (Integer) (parameter));
            }
        }
    }

    private StringBuilder formSelectRequest(String requestRestriction) {
        StringBuilder selectRequest = new StringBuilder();
        selectRequest.append("SELECT " + EmployeeDictionary.DB_TABLE_EMPLOYEE_ID + ", " +
                EmployeeDictionary.DB_TABLE_EMPLOYEE_LAST_NAME + ", " +
                EmployeeDictionary.DB_TABLE_EMPLOYEE_FIRST_NAME + ", " +
                EmployeeDictionary.DB_TABLE_EMPLOYEE_MIDDLE_NAME + ", " +
                EmployeeDictionary.DB_TABLE_EMPLOYEE_BIRTHDAY + ", " +
                EmployeeDictionary.DB_TABLE_EMPLOYEE_SALARY + ", " +
                EmployeeDictionary.DB_TABLE_EMPLOYEE_EMAIL + ", " +
                EmployeeDictionary.DB_TABLE_EMPLOYEE_SALARY + ", " +
                EmployeeDictionary.DB_TABLE_EMPLOYEE_DEPARTMENT +
                " FROM " + EmployeeDictionary.DB_EMPLOYEE_TABLE_NAME);
        if (requestRestriction != null && !requestRestriction.equals("")) {
            selectRequest.append(" WHERE ").append(requestRestriction);
        }
        return selectRequest;
    }
    private void deletingEmployee(int identification, String deleteRequest){
        try (Connection connection = ConnectionFactory.getMySQLConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(deleteRequest)) {
            preparedStatement.setInt(1, identification);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
