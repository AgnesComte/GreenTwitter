package services.comment;

import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

import services.ServicesTools;
import core.SessionKeyGenerator;
import core.comment.SearchComments;

public class SearchCommentsService {

	public static JSONObject searchCommentsService(String key, String query, String friends){
		
		JSONObject result = new JSONObject();

		if (friends==null){
			return(ServicesTools.error001());
		}
		try {
			int onlyFriends = Integer.parseInt(friends);
			
			if (onlyFriends == 1) {
				if (key == null || key.isEmpty() || (!SessionKeyGenerator.isActiveSession(key))) {
					return ServicesTools.error601();
				}
				if (query == null || query.isEmpty()) {
					// Search comments from friends
					result = SearchComments.searchComments(key, null,friends);
					result.put("friends", friends);
					return result;
				} else {
					return SearchComments.searchComments(key, query, friends);
				}
			}
			if (query == null || query.isEmpty()) {
					// Search all comments
				if (key == null || key.isEmpty()) {
					result = SearchComments.searchComments();
					result.put("friends", friends);
					return result;
				}else{
					result = SearchComments.searchComments(key, null,friends);
					result.put("friends", friends);
					return result;
				}
			}
			
			result = SearchComments.searchComments(null, query, friends);
			result.put("friends", friends);
			return result;
		
		} catch(JSONException e)  {
				return(ServicesTools.error("JSON Problem "+e.getMessage(),100));
		} catch (SQLException e){
				return(ServicesTools.error("Problem while generating session key"+e.getMessage(),1000));
		} catch (Exception e) {
				return(ServicesTools.error("Problem..."+e.getMessage(),10000));
		}
		
		
	}
}
