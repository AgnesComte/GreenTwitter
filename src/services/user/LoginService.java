package services.user;

import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

import services.ServicesTools;
//import core.database.BDException;
import core.user.AuthentificationTools;

public class LoginService {

	public static JSONObject loginService(String login, String password){
		
		if ((login==null) || (password==null)){
			return(ServicesTools.error001());
		}
		
		try {
				//Verifie que l utilisateur existe sinon ERROR 1
				boolean is_user=AuthentificationTools.userExists(login);
				if (!is_user) return(ServicesTools.error("Unknown user "+login,1));
        
				//Verifie que le password et l utilisateur sont OK sinon ERROR 2
				boolean password_ok=AuthentificationTools.checkPassword(login,password);
				if (!password_ok) return(ServicesTools.error("Bad password "+login,2));
        
				//Recupere l id de l utilisateur
				int id_user=AuthentificationTools.getIdUser(login);
				JSONObject retour=new JSONObject();
				
				//Si Administrateur
				if (id_user==0){
					retour.put("id",id_user);
					retour.put("login", login);
					retour.put("key","hfepaq2k45heo0kk52akl73gqa");
					return(retour);
				}
        
				//Insere une nouvelle session dans la base de donnees
				String key= AuthentificationTools.insertSession(id_user,false);
				retour.put("id", id_user);
				retour.put("login",login);
				retour.put("key",key);
			
					return(retour);
					
			} catch(JSONException e)  {
					return(ServicesTools.error("JSON Problem "+e.getMessage(),100));
			} catch (SQLException e){
					return(ServicesTools.error("Problem while generating session key",1000));
			} catch (Exception e) {
					return(ServicesTools.error("Problem..."+e.getMessage(),10000));
			}
	}
	
}
