package core.mapreduce;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import core.database.Database;

public class SqlManageMRContentDF {
	
	/** MAJ table MAPR_DF
	 * @throws SQLException */
	public static void majMapReduceDF(String word,double df) throws SQLException{
		
		if(checkUpdateDF(word, df)){
			updateMapReduceDF(word, df);
		}else{
			insertMapReduceDF(word, df);
		}
		
	}
	
	/** Si true UPDATE de la base DF false INSERT de la base DF*/
	public static boolean checkUpdateDF(String word, double df) throws SQLException{
		
		boolean update = false;
		
		Connection connection = Database.getMySQLConnection();
		PreparedStatement statement = connection
				.prepareStatement("SELECT * FROM MAPR_DF WHERE WORD=?;");
		statement.setString(1, word);
		ResultSet rs = statement.executeQuery();
		if (rs.next()) {
			 update  = true;
		}
		statement.close();
		connection.close();
		
		return update;
	}
	
	
	public static void insertMapReduceDF(String word, double df) throws SQLException{
		Connection connection = Database.getMySQLConnection();
		PreparedStatement statement = connection
				.prepareStatement("INSERT INTO MAPR_DF (WORD, DF) VALUES (?, ?);");
		statement.setString(1, word );
		statement.setDouble(2, df);
		statement.executeUpdate();
		statement.close();
		connection.close();
	}
	
	public static void updateMapReduceDF(String word, double df) throws SQLException{
		Connection connection = Database.getMySQLConnection();
		PreparedStatement statement = connection
				.prepareStatement("UPDATE MAPR_DF SET DF=? WHERE WORD=? ;");
		statement.setDouble(1, df);
		statement.setString(2, word );
		statement.executeUpdate();
		statement.close();
		connection.close();
		
	}
	

}
