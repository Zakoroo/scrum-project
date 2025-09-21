package com.ecologicstudios;

import java.sql.*;
import java.util.Properties;

public class EcoConnection {
    static final String DBNAME = "ecobase";
    static final String DATABASE = "jdbc:postgresql://localhost/" + DBNAME;
    static final String USERNAME = "postgres";
    static final String PASSWORD = "databaser";

    private Connection conn;

    public EcoConnection() throws SQLException, ClassNotFoundException {
        this(DATABASE, USERNAME, PASSWORD);
    }

    public EcoConnection(String db, String user, String pwd) throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        Properties props = new Properties();
        props.setProperty("user", user);
        props.setProperty("password", pwd);
        conn = DriverManager.getConnection(db, props);
    }


public String signup(int id, String username, String password) {
    String query = "INSERT INTO users (id, username, password) VALUES (?,?,?) ON CONFLICT DO NOTHING";
    try (PreparedStatement ps = conn.prepareStatement(query)) {
        ps.setInt(1, id);
        ps.setString(2, username);
        ps.setString(3, password);
        ps.executeUpdate();
    } catch (SQLException e) {
        return "{\"success\":false, \"error\":\"" + getError(e) + "\"}";
    }
    return "{\"success\":true}";
}

    public static String getError(SQLException e) {
        String message = e.getMessage();
        int ix = message.indexOf('\n');
        if (ix > 0) message = message.substring(0, ix);
        message = message.replace("\"", "\\\"");
        return message;
    }
}