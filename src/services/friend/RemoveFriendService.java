package services.friend;

import java.sql.SQLException;

import org.json.JSONObject;

import services.ServicesTools;
import core.SessionKeyGenerator;
import core.friend.FriendTools;
import core.user.AuthentificationTools;

public class RemoveFriendService {

	public static JSONObject removeFriendService(String key, String idsTo) {
		// Vérifier args non nul
		if (key == null || idsTo == null) {
			return(ServicesTools.error001());
		}
		int idTo = Integer.parseInt(idsTo);

		try {
			// Vérifier que la session est active 
			if (!SessionKeyGenerator.isActiveSession(key)) {
				return ServicesTools.error601();
			}
			// Récupérer l'ID_LOGIN à partir de la clé 
				int idFrom = AuthentificationTools.getIdUserFromKey(key);
			
			// Vérifier que la relation existe
			if (!FriendTools.existsFriend(idFrom, idTo)) {
				return ServicesTools.error("You are not friend already", 19);
			}
		
			// Il le supprime de ses amis :-)
			FriendTools.suppOneFriend(idFrom, idTo);
		} catch (SQLException e) {
			return ServicesTools.error("SQL DataBase error"+e.getMessage(),100 );
		} catch (Exception e) {
			return ServicesTools.error("Problem..."+e.getMessage(),10000);
		}
		// Renvoyer JSON OK
		return ServicesTools.ok();
	}

}
