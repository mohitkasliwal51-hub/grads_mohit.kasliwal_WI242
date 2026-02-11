package com.lma.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    private static Connection con = null;

    private DBConnection() {

    }

    public static Connection getConnection() throws Exception {
        if (con == null) {
            // Class.forName("");
            Class.forName("org.postgresql.Driver");

            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/lma", "postgres", "123@Akash");

            return con;
        }
        return con;
    }
}
