package core.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import core.SessionKeyGenerator;
import core.database.Database;

public class AuthentificationTools {
	
	public static boolean userExists(String login) throws SQLException, Exception{
		
			Connection connection = Database.getMySQLConnection();
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM USERS WHERE LOGIN=?;");
				statement.setString(1, login);
				ResultSet rs = statement.executeQuery();
				if (rs.next()) {
					return true;
				}
				statement.close();
				connection.close();
		return false ;
	}


	public static boolean checkPassword(String login, String password) throws SQLException, Exception {
			
				Connection connection = Database.getMySQLConnection();
				PreparedStatement statement = connection
						.prepareStatement("SELECT * FROM USERS WHERE LOGIN=? && PASSWORD=?;");
				statement.setString(1, login);
				statement.setString(2, password);
				ResultSet rs = statement.executeQuery();
				if (rs.next()) {
					return true;
				}
				statement.close();
				connection.close();
		return false;
	}

	public static int getIdUser(String login) throws SQLException, Exception {
			int id = -1;
			Connection connection = Database.getMySQLConnection();
			PreparedStatement statement = connection.prepareStatement("SELECT ID_LOGIN FROM USERS WHERE LOGIN=?;");
				statement.setString(1, login);
				ResultSet rs = statement.executeQuery();
				rs.next();
				id = rs.getInt(1);
				statement.close();
				connection.close();
				return id;
	}

	public static String insertSession(int id_user, boolean b) throws SQLException, Exception {
				String key = SessionKeyGenerator.generateKey();
				Connection connection = Database.getMySQLConnection();
				PreparedStatement statement = connection
						.prepareStatement("INSERT INTO SESSIONS (CLE, ID_USER, DATE) VALUES (?, ?, ?);");
				statement.setString(1, key);
				statement.setInt(2, id_user);
				statement.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
				statement.executeUpdate();
				statement.close();
				connection.close();
		return key ;
	}
	
	public static int getIdUserFromKey(String key) throws SQLException, Exception {
		int id = -1;
		Connection connection = Database.getMySQLConnection();
		PreparedStatement statement = connection.prepareStatement("SELECT ID_USER FROM SESSIONS WHERE CLE=?;");
			statement.setString(1, key);
			ResultSet rs = statement.executeQuery();
			rs.next();
			id = rs.getInt(1);
			statement.close();
			connection.close();
			return id;
	}
	
	public static String getLoginUserFromId(int id) throws SQLException, Exception {
		String login = "";
		Connection connection = Database.getMySQLConnection();
		PreparedStatement statement = connection.prepareStatement("SELECT LOGIN FROM USERS WHERE ID_LOGIN=?;");
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			rs.next();
			login = rs.getString(1);
			statement.close();
			connection.close();
			return login;
	}
	
}
