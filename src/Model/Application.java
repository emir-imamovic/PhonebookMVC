package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Application {
	protected static Connection db;

	public static void init() throws SQLException {
		db = DriverManager.getConnection("jdbc:sqlite:phonebook.db");
	}

	protected static ResultSet find(int id, String tableName) {
		try {
			Statement stmt = db.createStatement();
			String sql = String.format("SELECT * FROM %s where id = '%d' ;",
					tableName, id);
			return stmt.executeQuery(sql);
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			return null;
		}
	}
}
