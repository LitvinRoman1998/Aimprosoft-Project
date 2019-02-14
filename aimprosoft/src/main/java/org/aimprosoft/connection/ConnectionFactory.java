package org.aimprosoft.connection;


import org.apache.commons.dbcp.BasicDataSourceFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    private static DataSource dataSource;
    private ConnectionFactory() {
    }

    static {
        Properties property = new Properties();
        property.setProperty("driverClassName","com.mysql.jdbc.Driver");
        property.setProperty("url","jdbc:mysql://localhost:3306/company?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
        property.setProperty("username","root");
        property.setProperty("password","12345678");
        try {
            dataSource = BasicDataSourceFactory.createDataSource(property);
        } catch (Exception exception) {
            System.out.println("Error: " + exception.getMessage());
        }
    }

    public static Connection getMySQLConnection() throws SQLException {
        return dataSource.getConnection();
    }

}
