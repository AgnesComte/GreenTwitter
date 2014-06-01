package core.Test;

import java.sql.SQLException;

import core.SessionKeyGenerator;
import core.friend.FriendTools;
import core.user.AuthentificationTools;

public class TestCreateUser {
	
	public static void main(String[] args) throws SQLException {
		 
		String key = SessionKeyGenerator.nextSessionId();
		System.out.println(key);
		
		//Test user exist
				boolean b ;
				try {
					 b = AuthentificationTools.userExists("toto");
					 System.out.println(b);
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				//TEST  check
				boolean c;
				try {
					c = AuthentificationTools.checkPassword("toto","titi");
					System.out.println(c);
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				//test friends boolean
				boolean b1 = FriendTools.existsFriend(5, 0);
				System.out.println(b1);
				
				
		
	}

	
}
