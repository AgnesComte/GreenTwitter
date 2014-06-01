package services.user;

import java.sql.SQLException;

import org.json.JSONObject;

import core.SessionKeyGenerator;
import services.ServicesTools;


public class LogoutService {

	public static JSONObject logout(String key) {
        if (key == null) {
            return ServicesTools.error001();
        }
        try {
            if (!SessionKeyGenerator.isActiveSession(key)) {
                return ServicesTools.error("Session already expired", 006);
            }
            SessionKeyGenerator.deleteSession(key);
            return ServicesTools.ok();
        } catch (SQLException e) {
			return ServicesTools.error("SQL DataBase error"+e.getMessage(),100 );
		} catch (Exception e) {
			return ServicesTools.error("Problem..."+e.getMessage(),10000);
		}

    }
}
