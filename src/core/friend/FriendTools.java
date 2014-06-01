package core.friend;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import core.database.Database;
import core.user.AuthentificationTools;

public class FriendTools {

	
	public static void addFriend(int idFrom, int idTo) throws SQLException {
			Connection connection = Database.getMySQLConnection();
			PreparedStatement statement = connection
					.prepareStatement("INSERT INTO FRIENDS (ID_FROM, ID_TO) VALUES (?, ?);");
			statement.setInt(1, idFrom );
			statement.setInt(2, idTo);
			statement.executeUpdate();
			statement.close();
			connection.close();
	}

	/** true friend exist false Friend not exist already*/
	public static boolean existsFriend(int idFrom, int idTo) throws SQLException {
		boolean b = false;
			Connection connection = Database.getMySQLConnection();
			PreparedStatement statement = connection
					.prepareStatement("SELECT * FROM FRIENDS WHERE ID_FROM=? && ID_TO=?;");
			statement.setInt(1, idFrom);
			statement.setInt(2, idTo);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				b = true;
			}
			statement.close();
			connection.close();
		return b;
	}

	public static void suppOneFriend(int idFrom, int idTo) throws SQLException {
			Connection connection = Database.getMySQLConnection();
			PreparedStatement statement = connection
					.prepareStatement("DELETE FROM FRIENDS WHERE ID_FROM=? && ID_TO=?;");
			statement.setInt(1, idFrom);
			statement.setInt(2, idTo);
			statement.executeUpdate();
			statement.close();
			connection.close();
	}

	public static ArrayList<Integer> findFriends(int idFrom) throws SQLException {
		ArrayList<Integer> friends = new ArrayList<Integer>();
			Connection connection = Database.getMySQLConnection();
			PreparedStatement statement = connection
					.prepareStatement("SELECT * FROM FRIENDS WHERE ID_FROM=?;");
			statement.setInt(1, idFrom);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				friends.add(rs.getInt("ID_TO"));
			}
			statement.close();
			connection.close();
		return friends;
	}
	
	public static int countFriends(String key) throws SQLException, Exception{
		int nbFriends = -1;
		int idFrom = AuthentificationTools.getIdUserFromKey(key);
		Connection connection = Database.getMySQLConnection();
		PreparedStatement statement = connection
				.prepareStatement("SELECT COUNT(ID_TO) FROM FRIENDS WHERE ID_FROM=?;");
		statement.setInt(1, idFrom);
		ResultSet rs = statement.executeQuery();
		while (rs.next()){
			nbFriends = rs.getInt(1);
		}
		statement.close();
		connection.close();
		return nbFriends;
	} 
}

