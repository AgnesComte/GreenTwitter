package core.comment;


import java.sql.SQLException;
import java.util.Date;
import java.util.GregorianCalendar;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;

import core.database.Database;
import core.user.AuthentificationTools;
import core.user.UsersTools;

public class AddComment {

	public static void addComment(String key, String text) throws SQLException,Exception{
			
		int user_id = AuthentificationTools.getIdUserFromKey(key);
		String login = AuthentificationTools.getLoginUserFromId(user_id);
		DBCollection coll = Database.getMongoCollection("comments");
		BasicDBObject query = new BasicDBObject();
		query.put("key", key);
		query.put("author_id", user_id);
		query.put("author_username", login);
		String name = UsersTools.getNameFromId(user_id);
		query.put("author_name", name);
		query.put("text", text);
		
		GregorianCalendar calendar = new java.util.GregorianCalendar();
		Date today = calendar.getTime();
		
		query.put("created_at", today);
		coll.insert(query);
		
	}
}
