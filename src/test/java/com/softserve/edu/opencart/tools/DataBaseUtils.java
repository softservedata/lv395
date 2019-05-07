package com.softserve.edu.opencart.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DataBaseUtils {
    private Connection connection;
    private String db_url = "jdbc:mysql://192.168.227.130:3306/opencart?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&autoReconnect=true&useSSL=false";
    private final String setAttemptsToNull = "TRUNCATE opencart.oc_customer_login;";


    public DataBaseUtils() {
        openConnection();
    }

    private void openConnection(){
        try {
            //Open jdbc connection
            connection = DriverManager.getConnection(db_url, "lv395", "Lv395_Taqc");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void setAttemptsToNull() {
        try (PreparedStatement ps = connection.prepareStatement(setAttemptsToNull)){
            ps.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


}
