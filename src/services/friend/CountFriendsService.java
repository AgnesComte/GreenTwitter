package services.friend;

import java.sql.SQLException;

import org.json.JSONObject;

import services.ServicesTools;
import core.SessionKeyGenerator;
import core.friend.FriendTools;

public class CountFriendsService {

	public static JSONObject countFriendsService(String key) {
		// Vérifier args non nul
		if (key == null) {
			return(ServicesTools.error001());
		}

		try {
			// Vérifier que la session est active 
			if (!SessionKeyGenerator.isActiveSession(key)) {
				return ServicesTools.error("Session already expired", 006);
			}
			
			JSONObject retour=new JSONObject();
			// On compte le nbre d'ami
			int nbFriends = FriendTools.countFriends(key);
			retour.put("nbFriends", nbFriends);
				return (retour);
			
		} catch (SQLException e) {
			return ServicesTools.error("SQL DataBase error"+e.getMessage(),1000 );
		} catch (Exception e) {
			return ServicesTools.error("Problem..."+e.getMessage(),10000);
		}
	}
	
}
