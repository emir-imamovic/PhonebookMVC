package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class Contact extends Application {
	private int id;
	private String name;
	private String surname;
	private String number;

	private final static String tableName = "contacts";

	/**
	 * Default constructor
	 */
	public Contact() {
		this.id = -1;
		this.name = "Unknown";
		this.surname = "Unknown";
		this.number = "";
	}

	/**
	 * Constructor with three parameters - id, name, surname
	 */
	public Contact(int id, String name, String surname) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.number = "";
	}

	/**
	 * Constructor with all parameters except id
	 */
	public Contact(String name, String surname, String number) {
		this.id = -1;
		this.name = name;
		this.surname = surname;
		this.number = number;
	}

	/**
	 * Constructor with all parameters
	 */
	public Contact(int id, String name, String surname, String number) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.number = number;
	}

	/**
	 * This method requires a certain contact with a id which we sends as a
	 * parameter In this method we run other "find" method from Application
	 * class
	 */
	public static Contact find(int id) {
		ResultSet res = Application.find(id, tableName); // find method from Application class
		try {
			int cId = res.getInt("id");
			String contactName = res.getString("name");
			String contactSurname = res.getString("surname");
			String contactNumber = res.getString("number");
			// in last four lines we read data from ResultSet which we'll use to
			// create a new contact
			return new Contact(cId, contactName, contactSurname, contactNumber); // create a new contact
		} catch (SQLException e) {
			System.err.println(e.getMessage()); // return null if we didn't get
												// anything from base
			return null;
		}
	}

	/**
	 * In this method we create string with values for creating a new contact
	 * Then we call "save" method from Application class which actually save a
	 * new contact into base
	 */
	public boolean save() {
		String values = String.format("(?, '%s', '%s', '%s')", this.name,
				this.surname, this.number);
		return Application.save(tableName, values);
	}

	/**
	 * Method which makes an array of contacts and return it
	 */
	public static Contact[] all() {
		ResultSet rs = Application.all(tableName, "id, name, surname");
		// on line above we call method "all" from Application class and get the
		// contacts info
		if (rs == null)
			return new Contact[0]; // return null if contact info is empty
		LinkedList<Contact> cl = new LinkedList<Contact>();
		try {
			while (rs.next()) {
				int cId = rs.getInt("id");
				String cName = rs.getString("name");
				String cSurname = rs.getString("surname");
				cl.add(new Contact(cId, cName, cSurname));
				// on four last lines we get contact informations and put it
				// into LinkedList
			}
			Contact[] all = new Contact[cl.size()];
			cl.toArray(all); // "convert" from linked list to array
			return all;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			return new Contact[0]; // return null if we don't get anything
		}
	}

	/**
	 * Return contact's name and surname We'll use this on the buttons
	 */
	public String getDisplayName() {
		return this.name + " " + this.surname;
	}
	
	

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the surname
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * @param surname
	 *            the surname to set
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/**
	 * @return the number
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * @param number
	 *            the number to set
	 */
	public void setNumber(String number) {
		this.number = number;
	}
}
