package core.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;

public class Database {

	private DataSource dataSource;
	private static Database database = null;

	private static DB mongo_database = null;

	public Database(String jndiname) throws SQLException {
		try {
			dataSource = (DataSource) new InitialContext()
					.lookup("java:comp/env/" + jndiname);
		} catch (NamingException e) {
			throw new SQLException(jndiname + " is mising in JNDI! : "
					+ e.getMessage());
		}
	}

	private Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}

	public static Connection getMySQLConnection() throws SQLException {
		if (DBStatic.mysql_pooling == false) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			return (DriverManager.getConnection("jdbc:mysql://"
					+ DBStatic.mysql_host + "/" + DBStatic.mysql_db,
					DBStatic.mysql_username, DBStatic.mysql_password));
		} else {
			if (database == null) {
				database = new Database("jdbc/db");
			}
			return (database.getConnection());
		}
	}

	private static DB getMongoConnection() {
		Mongo m;
		if (mongo_database == null) {
			try {
				m = new Mongo("132.227.201.129",27130);
				mongo_database = m.getDB("COMTE_JANKOVIC");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return mongo_database;
	}

	public static DBCollection getMongoCollection(String collection_name) {
		return Database.getMongoConnection().getCollection(collection_name);
	}
}
