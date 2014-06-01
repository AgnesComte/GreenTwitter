package core.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import core.database.Database;


public class CreateUser {

	private static int id = 0;
	
	public static int incrementId() throws SQLException, Exception {
			Connection connection = Database.getMySQLConnection();
			PreparedStatement statement = connection.prepareStatement("SELECT ID_LOGIN FROM USERS ORDER BY ID_LOGIN DESC LIMIT 1;");
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				id = rs.getInt(1) + 1;
			} else {
				id = 1;
			}
			statement.close();
			connection.close();
			return id;
	}
	
	public static void createUser(String lastname, String firstname, String login, String password)
			throws SQLException, Exception {
			incrementId();
			Connection connection = Database.getMySQLConnection();
			PreparedStatement statement = connection
					.prepareStatement("INSERT INTO USERS (LOGIN, PASSWORD, ID_LOGIN, FIRSTNAME, LASTNAME) VALUES (?, ?, ?, ?, ?);");
			statement.setString(1, login);
			statement.setString(2, password);
			statement.setInt(3, id);
			statement.setString(4, firstname);
			statement.setString(5, lastname);
			statement.executeUpdate();
			statement.close();
			connection.close();
	}
	
}
