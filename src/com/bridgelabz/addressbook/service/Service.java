package com.bridgelabz.addressbook.service;

import java.util.Scanner;

import com.bridgelabz.addressbook.model.Person;
import com.bridgelabz.addressbook.repository.Dao;
/**
 * @author : Sachin Barpete
 * @purpose : provide service for add user and edit user
 */
public class Service {
	static Scanner sc = new Scanner(System.in);

	/**
	 * @return person
	 */
	public Person addPerson() {
		Person person = new Person();
		System.out.println("Enter first name");
		person.setFirstName(sc.next());
		System.out.println("Enter last name");
		person.setLastName(sc.next());
		System.out.println("Enter Address");
		person.setAddress(sc.next());
		System.out.println("Enter city");
		person.setCity(sc.next());
		System.out.println("Enter state");
		person.setState(sc.next());
		System.out.println("Enter zipcode");
		person.setZipcode(sc.nextInt());
		System.out.println("Enter phone number");
		person.setPhoneNumber(sc.nextLong());
		return person;
	}

	/**
	 * @param updateField
	 * @return updated information
	 */
	public String editPersonDetails(String updateField) {

		String updateInput;
		switch (updateField) {
		case "address":
			System.out.println("Enter new address");
			updateInput = sc.next().toLowerCase();
			return updateInput;
		case "city":
			System.out.println("Enter new city");
			updateInput = sc.next().toLowerCase();
			return updateInput;
		case "state":
			System.out.println("Enter new state");
			updateInput = sc.next().toLowerCase();
			return updateInput;
		case "zipcode":
			System.out.println("Enter new zipcode");
			updateInput = sc.next().toLowerCase();
			return updateInput;
		case "phoneNo":
			System.out.println("Enter new phoneNo");
			updateInput = sc.next().toLowerCase();
			return updateInput;
		default:
			System.out.println("Enter valid selection");
			return null;
		}
	}

	/**
	 * @param tableName
	 * @purpose update user details
	 */
	public void updateUserDetails(String tableName) {
		Dao dao = new Dao();
		String firstName;
		String lastName;
		System.out.println("Enter  registerd first name");
		firstName = sc.next();
		System.out.println("Enter registerd last name");
		lastName = sc.next();
		System.out.println("which detail you want to update 'address', 'city', 'state', 'zipcode', 'phoneNo'");
		String updateField = sc.next();
		String updateInput = editPersonDetails(updateField);
		dao.update(tableName, updateField, updateInput, firstName, lastName);

	}
}
