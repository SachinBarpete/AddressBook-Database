package com.bridgelabz.addressbook.repository;

import java.sql.Connection;
import java.sql.DriverManager;
/**
 * @author : Sachin Barpete
 * @purpose : connect to the MySQL database
 */
public class JDBCConnection {
	public static Connection connection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/AddressBook", "root", "password");
			return con;
		} catch (Exception e) {
			System.out.println("Error " + e);
		}
		return null;
	}

}
