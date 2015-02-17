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

	public Contact() {
		this.id = -1;
		this.name = "Unknown";
		this.surname = "Unknown";
		this.number = "";
	}
	public Contact(int id, String name, String surname) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.number = "";
	}

	public Contact(String name, String surname, String number) {
		this.id = -1;
		this.name = name;
		this.surname = surname;
		this.number = number;
	}

	public Contact(int id, String name, String surname, String number) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.number = number;
	}

	public static Contact find(int id) {
		ResultSet res = Application.find(id, tableName);
		try {
			int cId = res.getInt("id");
			String contactName = res.getString("name");
			String contactSurname = res.getString("surname");
			String contactNumber = res.getString("number");
			return new Contact(cId, contactName, contactSurname, contactNumber);
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			return null;
		}
	}

	public boolean save() {
		String values = String.format("(?, '%s', '%s', '%s')", this.name,
				this.surname, this.number);
		return Application.save(tableName, values);
	}

	
	public static Contact[] all() {
		ResultSet rs = Application.all(tableName, "id, name, surname");
		if(rs == null) 
			return new Contact[0];
		LinkedList<Contact> cl = new LinkedList<Contact>();
		try {
			while(rs.next()) {
				int cId = rs.getInt("id");
				String cName = rs.getString("name");
				String cSurname = rs.getString("surname");
				cl.add(new Contact(cId, cName, cSurname));
			}
			Contact[] all = new Contact[cl.size()];
			cl.toArray(all);
			return all;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			return new Contact[0];
		}		
	}
	
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
