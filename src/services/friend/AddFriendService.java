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
		
		// V�rifier args non nuls
		if (key == null || idsTo == null) {
			return(ServicesTools.error001());
		}
		int idTo = Integer.parseInt(idsTo);

		try {
			// V�rifier que la session est active 
			if (!SessionKeyGenerator.isActiveSession(key)) {
				return ServicesTools.error("Session already expired", 006);
			}
			
			// V�rifier que l'ami demand� existe dans la table USERS
			if (!UsersTools.userExistsId(idTo)) {
				return ServicesTools.error("friend does not exist", 005);
			}
			
			// R�cup�rer l'ID_LOGIN � partir de la cl� 
			int idFrom = AuthentificationTools.getIdUserFromKey(key);
			// V�rifier que l'on ne choisit pas en tant qu'ami
			if (idFrom == idTo) {
				return ServicesTools.error("You can not be your own friend", 007);
			}
			// V�rifier que la relation n'existe pas d�j�
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
