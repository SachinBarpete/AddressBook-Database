package com.bridgelabz.addressbook.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.bridgelabz.addressbook.model.Person;

/**
 * @author : Sachin Barpete
 * @purpose : Dao layer for CRUD operation 
 */
public class Dao {
	static Scanner sc = new Scanner(System.in);

	/**
	 * @param tableName
	 * @purpose for creating new address book
	 */
	public void newAddressBook(String tableName) {
		String sql = "create table " + tableName
				+ " (firstName varchar(15), lastName varchar(15), address varchar(50),city varchar(12),state varchar(15),zipcode varchar(10),phoneNo varchar(12), primary key (firstName,lastName))";
		Connection connection = JDBCConnection.connection();
		try {
			connection.setAutoCommit(false);
			Statement statement = connection.createStatement();
			statement.execute(sql);
			System.out.println("Enter 'Save' for save the changes otherwise 'Close'");
			if (sc.next().equalsIgnoreCase("save")) {
				connection.commit();
				System.out.println("new AddressBook created succsessfully");
			} else
				System.out.println("Changes are not done");
			statement.close();
			connection.close();
		} catch (SQLException e) {
			System.out.println("Error " + e);
		}
	}

	/**
	 * @purpose for show existing address book on database
	 */
	public void showAddressBook() {

		Connection connection = JDBCConnection.connection();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("show tables");
			while (resultSet.next()) {
				System.out.println(resultSet.getString(1));
			}
			statement.close();
			connection.close();
		} catch (SQLException e) {
			System.out.println("Error " + e);
		}
	}

	/**
	 * @param tableName
	 * @purpose for open address book
	 */
	public void openAddressBook(String tableName) {
		String sql = "select * from " + tableName;
		Connection connection = JDBCConnection.connection();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			String userData = "";
			while (resultSet.next()) {
				userData = resultSet.getString("firstName") + " : " + resultSet.getString("lastName") + " : "
						+ resultSet.getString("address") + " : " + resultSet.getString("city") + " : "
						+ resultSet.getString("state") + " : " + resultSet.getString("zipcode") + " : "
						+ resultSet.getString("phoneNo");
				System.out.println(userData);
			}
			statement.close();
			connection.close();
		} catch (SQLException e) {
			System.out.println("Error " + e);
		}
	}

	/**
	 * @param person
	 * @param tableName
	 * @purpose add user details
	 */
	public void add(Person person, String tableName) {

		String sql = "insert into " + tableName + " values(?,?,?,?,?,?,?)";
		Connection connection = JDBCConnection.connection();
		PreparedStatement preparedStatement;
		try {
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, person.getFirstName());
			preparedStatement.setString(2, person.getLastName());
			preparedStatement.setString(3, person.getAddress());
			preparedStatement.setString(4, person.getCity());
			preparedStatement.setString(5, person.getState());
			preparedStatement.setInt(6, person.getZipcode());
			preparedStatement.setLong(7, person.getPhoneNumber());
			preparedStatement.executeUpdate();
			System.out.println("Enter 'Save' for save the changes otherwise 'Close'");
			if (sc.next().equalsIgnoreCase("save")) {
				connection.commit();
				System.out.println("person details added successfully");
			} else
				System.out.println("Changes are not done");
			preparedStatement.close();
			connection.close();
		} catch (SQLException e) {
			System.out.println("Error " + e);
		}
	}

	/**
	 * @param tableName
	 * @param updateField
	 * @param updateInput
	 * @param firstName
	 * @param lastName
	 * @purpose update existing user details
	 */
	public void update(String tableName, String updateField, String updateInput, String firstName, String lastName) {

		String sql = "update " + tableName + " set " + updateField + " = ? where firstName = ? and lastName = ?";
		Connection connection = JDBCConnection.connection();
		PreparedStatement preparedStatement;
		try {
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, updateInput);
			preparedStatement.setString(2, firstName);
			preparedStatement.setString(3, lastName);
			int count = preparedStatement.executeUpdate();
			if (count == 0) {
				System.out.println("you entered wrong firstName or lastName it is not exist in our addressBook");
				preparedStatement.close();
				connection.close();
				return;
			}
			System.out.println("Enter 'Save' for save the changes otherwise 'Close'");
			if (sc.next().equalsIgnoreCase("save")) {
				connection.commit();
				System.out.println("person detail updated successfully");
			} else
				System.out.println("Changes are not done");
			preparedStatement.close();
			connection.close();
		} catch (SQLException e) {
			System.out.println("Error " + e);
		}
	}

	/**
	 * @param tableName
	 * @param firstName
	 * @param lastName
	 * @purpose delete user details
	 */
	public void delete(String tableName, String firstName, String lastName) {
		String sql = "delete from " + tableName + " where firstName = ? and lastName = ? ";
		Connection connection = JDBCConnection.connection();
		PreparedStatement preparedStatement;
		try {
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, firstName);
			preparedStatement.setString(2, lastName);
			int count = preparedStatement.executeUpdate();
			if (count == 0) {
				System.out.println("you entered wrong firstName or lastName it is not exist in our addressBook");
				preparedStatement.close();
				connection.close();
				return;
			}
			System.out.println("Enter 'Save' for save the changes otherwise 'Close'");
			if (sc.next().equalsIgnoreCase("save")) {
				connection.commit();
				System.out.println("person detail deleted successfully");
			} else
				System.out.println("Changes are not done");
			preparedStatement.close();
			connection.close();
		} catch (SQLException e) {
			System.out.println("Error " + e);
		}
	}

	/**
	 * @param tableName
	 * @purpose for sort user details by first name
	 */
	public void sortByfirstName(String tableName) {
		String sql = "select * from " + tableName + " order by firstName";
		Connection connection = JDBCConnection.connection();
		Statement statement;
		try {
			connection.setAutoCommit(false);
			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			String userData;
			while (resultSet.next()) {
				userData = resultSet.getString("firstName") + " : " + resultSet.getString("lastName") + " : "
						+ resultSet.getString("address") + " : " + resultSet.getString("city") + " : "
						+ resultSet.getString("state") + " : " + resultSet.getString("zipcode") + " : "
						+ resultSet.getString("phoneNo");
				System.out.println(userData);
			}
			System.out.println("Enter 'Save' for save the changes otherwise 'Close'");
			if (sc.next().equalsIgnoreCase("save")) {
				connection.commit();
				System.out.println("person detail sorted by firstName successfully");
			} else
				System.out.println("Changes are not done");
			statement.close();
			connection.close();
		} catch (SQLException e) {
			System.out.println("Error " + e);
		}
	}

	/**
	 * @param tableName
	 * @purpose for sort user details by Zipcode
	 */
	public void sortByZipcode(String tableName) {
		String sql = "select * from " + tableName + " order by zipcode";
		Connection connection = JDBCConnection.connection();
		Statement statement;
		try {
			connection.setAutoCommit(false);
			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			String userData;
			while (resultSet.next()) {
				userData = resultSet.getString("firstName") + " : " + resultSet.getString("lastName") + " : "
						+ resultSet.getString("address") + " : " + resultSet.getString("city") + " : "
						+ resultSet.getString("state") + " : " + resultSet.getString("zipcode") + " : "
						+ resultSet.getString("phoneNo");
				System.out.println(userData);
			}
			System.out.println("Enter 'Save' for save the changes otherwise 'Close'");
			if (sc.next().equalsIgnoreCase("save")) {
				connection.commit();
				System.out.println("person detail sorted by zipcode successfully");
			} else
				System.out.println("Changes are not done");
			statement.close();
			connection.close();
		} catch (SQLException e) {
			System.out.println("Error " + e);
		}
	}

	/**
	 * @param tableName
	 * @purpose drop existing address book
	 */
	public void dropTable(String tableName) {
		String sql = "drop table " + tableName;
		Connection connection = JDBCConnection.connection();
		try {
			connection.setAutoCommit(false);
			Statement statement = connection.createStatement();
			statement.executeUpdate(sql);
			System.out.println("Enter 'Save' for save the changes otherwise 'Close'");
			if (sc.next().equalsIgnoreCase("save")) {
				connection.commit();
				System.out.println("person detail sorted by zipcode successfully");
			} else
				System.out.println("Changes are not done");

			statement.close();
			connection.close();
			System.out.println("AddressBook deleted successfully");
		} catch (SQLException e) {
			System.out.println("Error " + e);
		}

	}

	/**
	 * @param tableName
	 * @purpose delete all record from address book
	 */
	public void truncateTable(String tableName) {
		String sql = "truncate table " + tableName;
		Connection connection = JDBCConnection.connection();
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate(sql);
			statement.close();
			connection.close();
			System.out.println("All record is deleted successfully from AddressBook " + tableName);
		} catch (SQLException e) {
			System.out.println("Error " + e);
		}

	}

}
