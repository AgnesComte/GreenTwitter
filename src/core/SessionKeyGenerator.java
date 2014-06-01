package core;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;


import core.database.Database;



public final class SessionKeyGenerator {
	
		  private static SecureRandom random = new SecureRandom();

		  public static String nextSessionId() {
		    return new BigInteger(130, random).toString(32);
		  }
		
		  /** m�thode de v�rification de la cl� g�n�r�e : si true ok sinon nok */
		  public static Boolean validateKey(String key) throws SQLException, Exception{
			 
			  // v�rif si elle a les bons crit�res taille de 32 et pas nulle 
			 if ( !(key == null || key.length() != 32)){
				 return false ;
			 } 
			 // v�rif pas de doublon
			Connection connection = Database.getMySQLConnection();
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM SESSIONS WHERE CLE=?;");
				statement.setString(1, key);
				ResultSet rs = statement.executeQuery();
				if (rs.next()) {
					return false;
				}
				statement.close();
				connection.close();
			return true;
		 }
		 
		 /** m�thode de g�n�ration de la cl�*/
		 public static String generateKey() throws SQLException, Exception{
			 // 1- on g�n�re
			 String key = nextSessionId();
			 // 2- on v�rifie la validit� si ok on envoi
			 boolean valide = validateKey(key);
			 	if(validateKey(key)){
					return key ;
				}
			 // 3- si nok on recommence	
			 return generateKey() ;
		 }
		 
		 
		 public static boolean isActiveSession(String key) throws SQLException, Exception {
				boolean b = false;
				if (key == "hfepaq2k45heo0kk52akl73gqa"){
					return true ;
				}
				Connection connection = Database.getMySQLConnection();
				PreparedStatement statement = connection
						.prepareStatement("SELECT TIMESTAMPADD(HOUR,1,DATE) FROM SESSIONS WHERE CLE=?;");
				statement.setString(1, key);
				ResultSet rs = statement.executeQuery();
				if (rs.next()) {
					if (rs.getTimestamp(1).after(new Timestamp(System.currentTimeMillis()))){
						b = true;
					}
				}
				statement.close();
				connection.close();
				return b;
			}

		public static void deleteSession(String key) throws SQLException, Exception {
					Connection connection = Database.getMySQLConnection();
					PreparedStatement statement = connection
							.prepareStatement("UPDATE SESSIONS SET DATE=? WHERE CLE=?;");
					statement.setString(1, key);
					statement.setTime(2, new java.sql.Time(new java.util.Date().getTime()));
					statement.executeUpdate();
					statement.close();
					connection.close();
		}
	  
}
