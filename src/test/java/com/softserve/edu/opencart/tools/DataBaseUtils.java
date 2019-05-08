package com.softserve.edu.opencart.tools;

import com.softserve.edu.opencart.data.IProduct;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Yurii Antokhiv
 * @version 1.0
 */
public final class DataBaseUtils {
    //
    // private final String DATABASE_URL = "192.168.227.130:3306";
    private final String DATABASE_URL = "192.168.239.130:3306";
    private final String SET_ATTEMPTS_TO_NULL = "TRUNCATE opencart.oc_customer_login;";
    private final String RETRIVE_DATA_FROM_TABLE = "SELECT firstname, lastname, email, telephone, fax  FROM opencart.oc_customer where email = ?;";
    private static final String PRODUCT_QUANTITY_STATEMENT = "select oc_product.quantity as quantity from oc_product " +
            "inner join oc_product_description on oc_product.product_id = oc_product_description.product_id" +
            " where oc_product_description.name = ?;";
    //
    private static Connection connection;
    private List<String> userInfo;
    private String db_url = "jdbc:mysql://" + DATABASE_URL + "/opencart?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&autoReconnect=true&useSSL=false";

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

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setAttemptsToNull() {
        try (PreparedStatement ps = connection.prepareStatement(SET_ATTEMPTS_TO_NULL)) {
            ps.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<String> checkIfUserInfoWasChanged(String userEmail) {
        try (PreparedStatement ps = connection.prepareStatement(RETRIVE_DATA_FROM_TABLE)) {
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

    public static int getProductQuantityFromDb(IProduct product) {
        ResultSet resultSet = null;
        int productQuantity = 0;
        try (PreparedStatement ps = connection.prepareStatement(PRODUCT_QUANTITY_STATEMENT)) {
            ps.setString(1, product.getName());
            resultSet = ps.executeQuery();
            if (resultSet.next())
                productQuantity = Integer
                        .parseInt(resultSet.getString(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productQuantity;
    }

    public void dumpDB() {
        //TODO add functionality to create DB dump
    }

    public void omportDBFromDump() {
        //TODO add functionality to create DB from dump
    }
}
