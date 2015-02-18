package Controller;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import Model.*;
import View.*;

public class ApplicationController {

	
	/**
	 * In this method we call "home" method from ApplicationView class
	 * which shows home view
	 */
	public static void home() {
		
		// home view
		ApplicationView.home();
	}

	/**
	 * In this method we call "addContact" method from ApplicationView class
	 * We use this for adding a new contact
	 */
	public static void addContact() {
		ApplicationView.addContact();
	}

	/**
	 * In this method we create a new contact for phonebook
	 * @param name - name of contact
	 * @param surname - surname of contact
	 * @param number - number of contact
	 */
	public static void create(String name, String surname, String number) {
		Contact newContact = new Contact(name, surname, number);
		if(newContact.save() == true) {
			//TODO redirect to contact info
			home();
			JOptionPane.showMessageDialog(null, "Successfuly saved " + newContact.getName(), "Contact added", JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, "There has been a mistake", "Error saving contact", JOptionPane.WARNING_MESSAGE);
		}
	}
/**
 * Here we make an array of contacts with method "all" from Contact class
 * and put each of contacts on one single button with method "list"
 * from ApplicationView class
 */
	public static void list() {
		Contact[] all = Contact.all();
		ApplicationView.list(all);
	}
	
	public static void main(String[] args) {

		/* TODO establishing connection to the database */
		try {
			Application.init(); //establishing connection to the base
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			System.exit(1);
		}
		Main.init();   //creates main frame
		home();       //shows home view

	}

}
