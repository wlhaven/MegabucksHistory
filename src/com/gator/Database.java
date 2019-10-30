package com.gator;

import java.sql.*;
import java.util.Objects;
import java.util.Properties;
import java.io.IOException;

/**
 * Created by Wally Haven on 10/29/2019.
 */
class Database {
    private  String userName;
    private  String password;
    private  String url;
    private Connection mConnection = null;
    private final Properties prop = new Properties();
    private static final String INSERT_RESULTS_SQL =
            "INSERT INTO History(Date, Amount, Draw, Result1, Result2, Result3, Result4, Result5, Result6)\n" +
                    "VALUES(convert(Date,?, 101),?,?,?,?,?,?,?,?);";

    Database() {
        getConnectionInfo();
    }

    private void getConnectionInfo() {
        try {
            String filename = "resources/MegabucksSqlServer.properties";
            prop.load(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(filename)));
            url = prop.getProperty("database");
            userName = prop.getProperty("dbuser");
            password = prop.getProperty("dbpassword");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * This connects to database
     */
    void connect() {
        if (mConnection != null)
            return;
        try {
            mConnection = DriverManager.getConnection(url, userName, password);
            System.out.println("\nConnected to database.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This closes connection to database
     */
   void close() {
        if (mConnection != null) {
            System.out.println("Closing database connection.\n");
            try {
                mConnection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


   void insertResults(String Date, String Amount,  int Draw, int result1, int result2, int result3, int result4, int result5, int result6) {
        try {
            PreparedStatement itemQuery = mConnection.prepareStatement(INSERT_RESULTS_SQL);
            itemQuery.setString(1, Date);
            itemQuery.setString(2, Amount);
            itemQuery.setInt(3, Draw);
            itemQuery.setInt(4, result1);
            itemQuery.setInt(5, result2);
            itemQuery.setInt(6, result3);
            itemQuery.setInt(7, result4);
            itemQuery.setInt(8, result5);
            itemQuery.setInt(9, result6);
            itemQuery.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            close();
        }
    }
}
