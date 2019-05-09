package com.softserve.edu.opencart.tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import com.softserve.edu.opencart.data.IProduct;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Yurii Antokhiv
 * @version 1.0
 */
public final class DataBaseUtils {

    // private static final String DATABASE_PARTIAL_URL = "192.168.227.130:3306";
    private static final String DATABASE_PARTIAL_URL = "192.168.239.130:3306";
    private static final String DB_URL = "jdbc:mysql://" + DATABASE_PARTIAL_URL + "/opencart?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&autoReconnect=true&useSSL=false";
    private static final String PRODUCT_QUANTITY_STATEMENT = "select oc_product.quantity as quantity from oc_product " +
            "inner join oc_product_description on oc_product.product_id = oc_product_description.product_id" +
            " where oc_product_description.name = ?;";
    //
    private static final String HOST = "192.168.239.129";  // IP-adress of remote server.
    private static final int PORT = 22;                    // Remote server port.
    private static  final String NAME = "root";             // Linux profile name.
    private static final String PASSWORD = "root";         // Linux password.
    //
    private static final String DUMP_DATABASE = "/home/backupdb.sh";       // Script for dumping DB.
    private static final String RESTORE_DATABASE = "/home/restoredb.sh";   // Script for restoring DB.
    private static final String DROP_DATABASE_STATEMENT = "DROP DATABASE opencart";

    private final String SET_ATTEMPTS_TO_NULL = "TRUNCATE opencart.oc_customer_login;";
    private final String RETRIVE_DATA_FROM_TABLE = "SELECT firstname, lastname, email, telephone, fax  FROM opencart.oc_customer where email = ?;";
    //
    private static Connection connection;
    private static JSch jsch = new JSch();
    private static Session session;
    //
    private List<String> userInfo;

    public DataBaseUtils() {
        openConnection();
    }

    public static void openConnection() {
        try {
            //Open jdbc connection
            connection = DriverManager.getConnection(DB_URL, "lv395", "Lv395_Taqc");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void closeConnection() {
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
        openConnection();
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
        } finally {
            closeConnection();
        }
        return productQuantity;
    }

    /**
     * Method for connect to remote Linux server
     * by SSH connection.
     */
    public static void remoteServerConnect() {
        try {
            // Open a Session to remote SSH server and Connect.
            // Set User and IP of the remote host and SSH port.
            session = jsch.getSession(NAME, HOST, PORT);
            // When we do SSH to a remote host for the 1st time or if key at the remote host
            // changes, we will be prompted to confirm the authenticity of remote host.
            // This check feature is controlled by StrictHostKeyChecking ssh parameter.
            // By default StrictHostKeyChecking  is set to yes as a security measure.
            session.setConfig("StrictHostKeyChecking", "no");
            //Set password
            session.setPassword(PASSWORD);
            session.connect();

            if(session.isConnected()) {
                System.out.println("Connected to remote server is successful!");
            }
        } catch (JSchException e) {
            e.printStackTrace();
        }
    }

    /**
     * Start Shell script for dump and restore database.
     */
    public static void runShellScript(String script) {
        try {
            // create the execution channel over the session
            ChannelExec channelExec = (ChannelExec) session.openChannel("exec");
            // Set the command to execute on the channel and execute the command
            channelExec.setCommand(script);
            channelExec.connect();

            // Get an InputStream from this channel and read messages, generated
            // by the executing command, from the remote side.
            InputStream in = channelExec.getInputStream();
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            // Command execution completed here.

            // Retrieve the exit status of the executed command
            int exitStatus = channelExec.getExitStatus();
            if (exitStatus > 0) {
                System.out.println("Remote script exec error! " + exitStatus);
            }
        } catch (JSchException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Disconnect from SSH connection.
     */
    public static void remoteServerDisconnect() {
        //Disconnect the Session
        session.disconnect();
        if(!session.isConnected()) {
            System.out.println("Disconnected from remote server is successful!");
        }
    }

    /**
     * Method which deletes database.
     */
    public static void dropDatabase() {
        try (PreparedStatement ps = connection.prepareStatement(DROP_DATABASE_STATEMENT)) {
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void dumpDb() {
        openConnection();
        remoteServerConnect();
        runShellScript(DUMP_DATABASE);
    }

    public static void restoreDb() {
        dropDatabase();
        closeConnection();
        runShellScript(RESTORE_DATABASE);
        remoteServerDisconnect();
    }

}
