package services.user;

import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

import services.ServicesTools;
import core.user.AuthentificationTools;
import core.user.CreateUser;

public class CreateUserService {

	public static JSONObject createUser(String lastname, String firstname, String login, String password){
		
		if (lastname == null || firstname==null || login== null || password==null){ 	
			return ServicesTools.error001();
		}try {
			if (AuthentificationTools.userExists(login))
				return(ServicesTools.error002());
		
			CreateUser.createUser(lastname, firstname, login, password);
			return(ServicesTools.ok());
		}catch(JSONException e)  {
			return(ServicesTools.error("JSON Problem "+e.getMessage(),100));
		}catch(SQLException e){
			return(ServicesTools.error("Problem while generating the new user",1001));	
		}catch(Exception e) {
			return(ServicesTools.error("Problem..."+e.getMessage(),10000));
		}
	}
}
