package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Application {
	protected static Connection db;

	/**
	 * This method establishes a connection to the base
	 */
	public static void init() throws SQLException {
		db = DriverManager.getConnection("jdbc:sqlite:phonebook.db"); //establishing connection to the base
	}

	/**
	 * This method requires certain contact with a id
	 *  which we sends as a first parameter,
	 *  second parameter is a table name from which we take informations
	 */
	protected static ResultSet find(int id, String tableName) {
		
		try {
			Statement stmt = db.createStatement();  //creating statement object
			
			String sql = String.format("SELECT * FROM %s where id = '%d' ;", 
					
					tableName, id);    //query which we use to get informations from table
			
			return stmt.executeQuery(sql);   //information which we get from table with query above
			
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			return null;
		}
	}

	/**
	 * Method for saving new contact into base
	 * First parameter is a table name
	 * Second parameter is the string which contains values to save
	 */
	protected static boolean save(String tableName, String values) {
		Statement stmt = null;
		try {
			stmt = db.createStatement();
			String sql = String.format("INSERT INTO %s VALUES %s;", tableName,
					values);           //query for saving values into the base
			stmt.execute("begin;");
			stmt.execute(sql);     //read(run) the query
			stmt.execute("commit;");
			return true;
		} catch (SQLException e) {
			if (stmt != null) {
				try {
					stmt.execute("rollback;");
				} catch (SQLException e1) {
					System.err.println(e.getMessage());
					return false;     // return false if query didn't passed
				}
			}
			System.err.println(e.getMessage());
			return false;
		}
	}

	// columnNames = name,surname, *;
	
	/**
	 * This method requires data from the base and return it
	 * First parameter is base name
	 * Second parameter is the column name from which we want to read data
	 */
	protected static ResultSet all(String tableName, String columnNames) {
		try {
			Statement stmt = db.createStatement(); 
			String sql = String.format("SELECT %s FROM %s;", columnNames, tableName);
			return stmt.executeQuery(sql); // read-run query
		} catch (SQLException e) {
			System.err.println(e.getMessage()); //return null if query is empty(data in base does not exists)
			return null;
		}
	}
}
