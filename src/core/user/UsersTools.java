package core.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import core.database.Database;

public class UsersTools {
	
	public static String getNameFromId(int id) throws SQLException, Exception {
		String name = "";
		Connection connection = Database.getMySQLConnection();
		PreparedStatement statement = connection.prepareStatement("SELECT FIRSTNAME, LASTNAME FROM USERS WHERE ID_LOGIN=?;");
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			rs.next();
			name = rs.getString(1);
			statement.close();
			connection.close();
			return name;
}

	public static boolean userExistsId(int idTo)throws SQLException, Exception {
		boolean b = false;
			Connection connection = Database.getMySQLConnection();
			PreparedStatement statement = connection
					.prepareStatement("SELECT * FROM USERS WHERE ID_LOGIN=?;");
			statement.setInt(1, idTo);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				b = true;
			} else {
				b = false;
			}
			statement.close();
			connection.close();
		return b;
	}


}
