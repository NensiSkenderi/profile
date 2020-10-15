package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class db_connection {

	public static Connection conn;
	private static db_connection instance = new db_connection();
	
	public db_connection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost/profile", "root","root");
			//79.106.157.145
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
			ex.printStackTrace();
		}
	}
	public static db_connection instance() {
        if (instance == null) {
        	instance = new db_connection();
        }
        return instance;
    }

    public Connection getConnection() {
        return conn;
    }
	
	
}

