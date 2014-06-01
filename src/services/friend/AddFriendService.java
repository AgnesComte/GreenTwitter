package services.friend;

import java.sql.SQLException;

import org.json.JSONObject;

import services.ServicesTools;
import core.SessionKeyGenerator;
import core.friend.FriendTools;
import core.user.AuthentificationTools;
import core.user.UsersTools;

public class AddFriendService {
	
	public static JSONObject addFriendService(String key, String idsTo) {
		
		// Vérifier args non nuls
		if (key == null || idsTo == null) {
			return(ServicesTools.error001());
		}
		int idTo = Integer.parseInt(idsTo);

		try {
			// Vérifier que la session est active 
			if (!SessionKeyGenerator.isActiveSession(key)) {
				return ServicesTools.error("Session already expired", 006);
			}
			
			// Vérifier que l'ami demandé existe dans la table USERS
			if (!UsersTools.userExistsId(idTo)) {
				return ServicesTools.error("friend does not exist", 005);
			}
			
			// Récupérer l'ID_LOGIN à partir de la clé 
			int idFrom = AuthentificationTools.getIdUserFromKey(key);
			// Vérifier que l'on ne choisit pas en tant qu'ami
			if (idFrom == idTo) {
				return ServicesTools.error("You can not be your own friend", 007);
			}
			// Vérifier que la relation n'existe pas déjà
			if (FriendTools.existsFriend(idFrom, idTo)) {
				return ServicesTools.error("You are already friend", 9);
			}
			// Il devient son ami :-)
			FriendTools.addFriend(idFrom, idTo);
		} catch (SQLException e) {
			return ServicesTools.error("SQL DataBase error"+e.getMessage(),100 );
		} catch (Exception e) {
			return ServicesTools.error("Problem..."+e.getMessage(),10000);
		}
		// Renvoyer JSON OK
		return ServicesTools.ok();
	}
}
