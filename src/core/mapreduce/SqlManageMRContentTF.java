package core.mapreduce;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import core.database.Database;

public class SqlManageMRContentTF {
	
	/** MAJ table MAPR_TF
	 * @throws SQLException */
	public static void majMapReduceTF(String idComment, String word,double tf) throws SQLException{
		
		if(checkUpdateTF(idComment,word, tf)){
			updateMapReduceTF(idComment, word, tf);
		}else{
			insertMapReduceTF(idComment, word, tf);
		}
		
	}
	
	/** Si true UPDATE de la base TF false INSERT de la base TF
	 * @param word */
	public static boolean checkUpdateTF(String idComment, String word, double tf) throws SQLException{
		
		boolean update = false;
		
		Connection connection = Database.getMySQLConnection();
		PreparedStatement statement = connection
				.prepareStatement("SELECT * FROM MAPR_TF WHERE ID_COMMENT=? AND WORD=?;");
		statement.setString(1, idComment);
		statement.setString(2, word);
		ResultSet rs = statement.executeQuery();
		if (rs.next()) {
			 update  = true;
		}
		statement.close();
		connection.close();
		
		return update;
	}
	
	
	public static void insertMapReduceTF(String idComment, String word, double tf) throws SQLException{
		Connection connection = Database.getMySQLConnection();
		PreparedStatement statement = connection
				.prepareStatement("INSERT INTO MAPR_TF (ID_COMMENT, WORD, TF) VALUES (?, ?, ?);");
		statement.setString(1, idComment );
		statement.setString(2, word );
		statement.setDouble(3, tf);
		statement.executeUpdate();
		statement.close();
		connection.close();
	}
	
	public static void updateMapReduceTF(String idComment, String word, double tf) throws SQLException{
		Connection connection = Database.getMySQLConnection();
		PreparedStatement statement = connection			
				.prepareStatement("UPDATE MAPR_TF SET TF=?, WORD=? WHERE ID_COMMENT= ? ;");
		statement.setDouble(1, tf);
		statement.setString(2, word);
		statement.setString(3, idComment);
		statement.executeUpdate();
		statement.close();
		connection.close();
		
	}
	

}
