package com.bridgelabz.addressbook.controller;

import java.util.Scanner;

import com.bridgelabz.addressbook.model.Person;
import com.bridgelabz.addressbook.repository.Dao;
import com.bridgelabz.addressbook.service.Service;
/**
 * @author : Sachin Barpete
 * @purpose : controller class 
 */
public class AddressBook {
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {

		do {
			Dao dao = new Dao();
			System.out.println(
					" Enter : \n 'New' for create new address book " + "\n 'Open' for open existing address book");
			String input = sc.next().toLowerCase();
			String tableName;
			Service service = new Service();
			switch (input) {
			case "new":
				System.out.println("Enter AddressBook name ");
				tableName = sc.next();
				dao.newAddressBook(tableName);
				System.out.println("Enter the details of person to add");
				Person person = service.addPerson();
				dao.add(person, tableName);
				break;
			case "open":
				System.out.println("Total address book in database");
				dao.showAddressBook();
				System.out.println("Enter the AddressBook name you want to open");
				tableName = sc.next();
				dao.openAddressBook(tableName);
				do {
					System.out.println(" Enter : \n 1 for add person" + "\n 2 for edit details"
							+ "\n 3 for delete person record" + "\n 4 for sort by firstName"
							+ "\n 5 for sort by zipcode" + "\n 6 for delete AddressBook"
							+ "\n 7 for delete all all records from AddressBook");
					System.out.println(" --------------------- ");
					int ch = sc.nextInt();
					switch (ch) {
					case 1:
						System.out.println("Enter the details of person to add");
						person = service.addPerson();
						dao.add(person, tableName);
						break;
					case 2:
						service.updateUserDetails(tableName);
						break;
					case 3:
						System.out.println("Enter  registerd first name");
						String firstName = sc.next();
						System.out.println("Enter registerd last name");
						String lastName = sc.next();
						dao.delete(tableName, firstName, lastName);
						break;
					case 4:
						dao.sortByfirstName(tableName);
						break;
					case 5:
						dao.sortByZipcode(tableName);
						break;
					case 6:
						dao.dropTable(tableName);
						break;
					case 7:
						dao.truncateTable(tableName);
						break;
					default:
						System.out.println("Enter valid selection");
					}

					System.out.println();
					System.out.println(
							"Enter 'stay' for continue to stay in the same Address book \n otherwise 'Close' for Quit");
				} while (sc.next().equalsIgnoreCase("stay"));

				break;
			default:
				System.out.println("Enter valid selection");
			}
			System.out.println();
			System.out.println("Enter 'true' for continue otherwise 'false' for Quit");
		} while (sc.next().equalsIgnoreCase("true"));

	}
}
