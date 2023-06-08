package org.example.Atto.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static Connection getConnection(){
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver"); // <1>
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/oop3",
                    "user_jon", "12345"); // <2>
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

}
