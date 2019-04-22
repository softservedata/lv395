/*
 * DatabaseOperator
 *
 * v. 1.0
 *
 * Copyright (c) 2019 Maksym Burko.
 */
package com.softserve.edu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

/**
 * Class establishes SSH connection with remote
 * server and runs Shell scripts for
 * dumping and restoring database.
 */
public class DatabaseOperator {

    private JSch jsch = new JSch();
    private Session session;

    private final String HOST = "192.168.239.129";  // IP-adress of remote server.
    private final int PORT = 22;                    // Remote server port.
    private final String NAME = "root";             // Linux profile name.
    private final String PASSWORD = "root";         // Linux password.

    /**
     * Method for connect to remote Linux server
     * by SSH connection.
     */
    public void remoteServerConnect() {
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
     * Start Shell script and create database dump.
     */
    public void dumpDatabase() {
        try {
            // create the execution channel over the session
            ChannelExec channelExec = (ChannelExec) session.openChannel("exec");
            // Set the command to execute on the channel and execute the command
            channelExec.setCommand("/home/backupdb.sh");
            channelExec.connect();

            // Get an InputStream from this channel and read messages, generated
            // by the executing command, from the remote side.
            InputStream in = channelExec.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
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
     * Restore database from dump.
     */
    public void restoreDatabase() {
        try {
            // create the execution channel over the session
            ChannelExec channelExec = (ChannelExec) session.openChannel("exec");
            // Set the command to execute on the channel and execute the command
            channelExec.setCommand("/home/restoredb.sh");
            channelExec.connect();
            // Retrieve the exit status of the executed command
            int exitStatus = channelExec.getExitStatus();
            if (exitStatus > 0) {
                System.out.println("Remote script exec error! " + exitStatus);
            }
        } catch (JSchException e) {
            e.printStackTrace();
        }
    }

    /**
     * Disconnect from SSH connection.
     */
    public void remoteServerDisconnect() {
        //Disconnect the Session
        session.disconnect();

        if(!session.isConnected()) {
            System.out.println("Disconnected from remote server is successful!");
        }
    }

}