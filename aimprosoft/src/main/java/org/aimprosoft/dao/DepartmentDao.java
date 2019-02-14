package org.aimprosoft.dao;

import org.aimprosoft.connection.ConnectionFactory;
import org.aimprosoft.data.Department;
import org.aimprosoft.data.Employee;
import org.aimprosoft.dict.DepartmentDictionary;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDao implements IDao<Department> {
    @Override
    public void create(Department department) {
        String insertRequest = "INSERT INTO " + DepartmentDictionary.DB_DEPARTMENT_TABLE_NAME +
                "(" + DepartmentDictionary.DB_TABLE_DEPARTMENT_NAME + ") VALUES (?);";
        try (Connection connection = ConnectionFactory.getMySQLConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertRequest)) {
            preparedStatement.setString(1, department.getDepartmentName());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                department.setDepartmentId(resultSet.getInt(1));
            }
            resultSet.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Department read(String departmentName) {
        Department department = new Department();
        String selectRequest = "SELECT " + DepartmentDictionary.DB_TABLE_DEPARTMENT_ID + ", " +
                DepartmentDictionary.DB_TABLE_DEPARTMENT_NAME + " FROM " +
                DepartmentDictionary.DB_DEPARTMENT_TABLE_NAME + " WHERE " +
                DepartmentDictionary.DB_TABLE_DEPARTMENT_NAME + "=?;";
        try (Connection connection = ConnectionFactory.getMySQLConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectRequest)) {
            preparedStatement.setString(1, departmentName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                department = parseDepartment(resultSet);
            }
            resultSet.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return department;
    }

    public Department read(int departmentId) {
        Department department = new Department();
        String selectRequest = "SELECT " + DepartmentDictionary.DB_TABLE_DEPARTMENT_ID + ", " +
                DepartmentDictionary.DB_TABLE_DEPARTMENT_NAME + " FROM " +
                DepartmentDictionary.DB_DEPARTMENT_TABLE_NAME + " WHERE " +
                DepartmentDictionary.DB_TABLE_DEPARTMENT_ID + "=?;";
        try (Connection connection = ConnectionFactory.getMySQLConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectRequest)) {
            preparedStatement.setInt(1, departmentId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                department = parseDepartment(resultSet);
            }
            resultSet.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return department;
    }

    @Override
    public List<Department> readAll() {
        List<Department> departments = new ArrayList<>();
        String selectRequest = "SELECT " + DepartmentDictionary.DB_TABLE_DEPARTMENT_ID + ", " +
                DepartmentDictionary.DB_TABLE_DEPARTMENT_NAME + " FROM " +
                DepartmentDictionary.DB_DEPARTMENT_TABLE_NAME + ";";
        try (Connection connection = ConnectionFactory.getMySQLConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectRequest);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                departments.add(parseDepartment(resultSet));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return departments;
    }

    @Override
    public void update(Department department) {
        String updateRequest = "UPDATE " + DepartmentDictionary.DB_DEPARTMENT_TABLE_NAME
                + " SET " + DepartmentDictionary.DB_TABLE_DEPARTMENT_NAME
                + "=? WHERE " + DepartmentDictionary.DB_TABLE_DEPARTMENT_ID + "=?;";
        try (Connection connection = ConnectionFactory.getMySQLConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateRequest)) {
            preparedStatement.setString(1, department.getDepartmentName());
            preparedStatement.setInt(2, department.getDepartmentId());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(int departmentId) {
        String deleteRequest = "DELETE FROM " + DepartmentDictionary.DB_DEPARTMENT_TABLE_NAME
                + " WHERE " + DepartmentDictionary.DB_TABLE_DEPARTMENT_ID + "=?;";
        IDao<Employee> employeeDao=new EmployeeDao();
        ((EmployeeDao) employeeDao).deleteEmployeesByDepartmentId(departmentId);
        try (Connection connection = ConnectionFactory.getMySQLConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(deleteRequest)) {
            preparedStatement.setInt(1, departmentId);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public Department parseDepartment(ResultSet resultSet) throws SQLException {
        return new Department(resultSet.getInt(DepartmentDictionary.DB_TABLE_DEPARTMENT_ID), resultSet.getString(DepartmentDictionary.DB_TABLE_DEPARTMENT_NAME));
    }
}
