package services;

import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

import core.Log;
import core.SessionKeyGenerator;
import core.user.AuthentificationTools;

public class LogService {


	public static JSONObject logService(String service, String adrIP, String nomClient) {
		if ((service==null) || (adrIP==null) || (nomClient==null)){
			return(ServicesTools.error001());
		}

		try {
			Log.logs(service,adrIP, nomClient);
			return(ServicesTools.ok());
				
		} catch(JSONException e)  {
				return(ServicesTools.error("JSON Problem "+e.getMessage(),100));
		} catch (SQLException e){
				return(ServicesTools.error("Problem while generating session key"+e.getMessage(),1000));
		} catch (Exception e) {
				return(ServicesTools.error("Problem..."+e.getMessage(),10000));
		}
	

	}
	
	public static JSONObject logSearchService(String query, String key) {
		if (key == null) {
			return(ServicesTools.error001());
		}		
		try {
			// Vérifier que la session est active 
			if (!SessionKeyGenerator.isActiveSession(key)) {
					return ServicesTools.error("Session already expired", 006);
			}
			// vérifier que l'id est bien le compte admin
			int idFrom = AuthentificationTools.getIdUserFromKey(key);
			if(idFrom != 0){
				    return ServicesTools.error("You are not authorization to access to log Searchs", 007);
			}
			return(Log.searchLog(query, key));
				
		} catch(JSONException e)  {
				return(ServicesTools.error("JSON Problem "+e.getMessage(),100));
		} catch (SQLException e){
				return(ServicesTools.error("Problem while generating session key"+e.getMessage(),1000));
		} catch (Exception e) {
				return(ServicesTools.error("Problem..."+e.getMessage(),10000));
		}
	

	}

}
