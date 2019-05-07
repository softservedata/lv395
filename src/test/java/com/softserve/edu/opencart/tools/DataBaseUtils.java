package com.softserve.edu.opencart.tools;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Yurii Antokhiv
 * @version 1.0
 */
public class DataBaseUtils {
    private Connection connection;
    private List<String> userInfo;
    private String db_url = "jdbc:mysql://192.168.227.130:3306/opencart?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&autoReconnect=true&useSSL=false";
    private final String setAttemptsToNull = "TRUNCATE opencart.oc_customer_login;";
    private final String retrieveDataFromTable = "SELECT firstname, lastname, email, telephone, fax  FROM opencart.oc_customer where email = ?;";


    public DataBaseUtils() {
        openConnection();
    }

    private void openConnection() {
        try {
            //Open jdbc connection
            connection = DriverManager.getConnection(db_url, "lv395", "Lv395_Taqc");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void setAttemptsToNull() {
        try (PreparedStatement ps = connection.prepareStatement(setAttemptsToNull)) {
            ps.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<String> checkIfUserInfoWasChanged(String userEmail) {
        try (PreparedStatement ps = connection.prepareStatement(retrieveDataFromTable)) {
            ps.setString(1, userEmail);
            ResultSet resultSet = ps.executeQuery();

            userInfo = new ArrayList<>();

            if (resultSet.next()) {
                String firstName = resultSet.getString(1);
                userInfo.add(firstName);
                String lastName = resultSet.getString(2);
                userInfo.add(lastName);
                String email = resultSet.getString(3);
                userInfo.add(email);
                String telephone = resultSet.getString(4);
                userInfo.add(telephone);
                String fax = resultSet.getString(5);
                userInfo.add(fax);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return userInfo;
    }

    public void closeConnection(){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
