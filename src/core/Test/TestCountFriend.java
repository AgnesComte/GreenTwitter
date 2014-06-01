package core.Test;

import java.sql.SQLException;

import core.friend.FriendTools;

public class TestCountFriend {

	public static void main(String[] args) {
	
		try {
			int nbFriends = FriendTools.countFriends("uvth9m18aois6o77klbv2as7s");
			System.out.println(nbFriends);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
