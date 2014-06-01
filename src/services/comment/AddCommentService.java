package services.comment;

import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

import services.ServicesTools;
import core.SessionKeyGenerator;
import core.comment.AddComment;

public class AddCommentService {
	
	public static JSONObject addCommentService(String key, String text){

		if ((key==null) || (text==null)){
			return(ServicesTools.error001());
		}
		
		try {
			//Verifie que la session est active, sinon error601
			boolean is_session = SessionKeyGenerator.isActiveSession(key);
			if (!is_session) return ServicesTools.error601();
	
			AddComment.addComment(key, text);
			return(ServicesTools.ok());
				
		} catch(JSONException e)  {
				return(ServicesTools.error("JSON Problem "+e.getMessage(),100));
		} catch (SQLException e){
				return(ServicesTools.error("Problem while generating session key"+e.getMessage(),1000));
		} catch (Exception e) {
				return(ServicesTools.error("Problem..."+e.getMessage(),10000));
		}
	

	}
}
