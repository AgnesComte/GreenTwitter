package core.comment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import org.json.JSONObject;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import core.database.Database;
import core.friend.FriendTools;
import core.user.AuthentificationTools;

public class SearchComments {
	
	
	public static JSONObject searchComments() throws SQLException, Exception{
		return searchComments(null, null,"0");
	}

	public static JSONObject searchComments(String key, String query, String friends) throws SQLException, Exception{
		
		JSONObject json = new JSONObject();
		int idFrom = 0;
		ArrayList<Integer> friendsList= new ArrayList<Integer>();
		boolean contact = false;
		double score = 0.0;
		
		DBCollection coll = Database.getMongoCollection("comments");
		BasicDBObject search = new BasicDBObject();
		
//		DBObject orderBy = new BasicDBObject();
		GregorianCalendar calendar = new java.util.GregorianCalendar();
		Date today = calendar.getTime();
//		orderBy.put("created_at",new BasicDBObject("$lte",today));
		
		
		if (!(key==null)){
		idFrom = AuthentificationTools.getIdUserFromKey(key);
		friendsList = FriendTools.findFriends(idFrom);
		}
		//onlyFriends
		BasicDBList listFriends = new BasicDBList();
		listFriends.addAll(friendsList);
		BasicDBObject filter = new BasicDBObject("$in", listFriends);
			
		if(friends.equals("1")){
		search.put("author_id", filter);
		}
		
		DBCursor cur = coll.find(search);
		int nbComments = coll.find().count();
		
		if (query == null){
			//DBCursor cur = coll.find(search).sort(orderBy).limit(30);
			// DBCursor cur = coll.find(search).sort(new BasicDBObject("$natural", -1));
			// DBCursor cur = coll.find(search).sort(new BasicDBObject("created_at", -1)).limit(30);
			cur.sort(new BasicDBObject("$natural", -1));
		}else{
			cur.sort(new BasicDBObject("score", 1));
		}
		
		try{
		while(cur.hasNext()){
			DBObject obj = cur.next();
			DBObject result = new BasicDBObject();
			result.put("_id", obj.get("_id"));
			result.put("author_id", obj.get("author_id"));
			result.put("author_username", obj.get("author_username"));
			
				if(!(key==null)){
					BasicDBObject idWriter = new BasicDBObject();
					idWriter.put("author_id", obj.get("author_id"));
					String writerId = idWriter.get("author_id").toString();
					if (FriendTools.existsFriend(idFrom, Integer.parseInt(writerId))){
						contact = true;
					}
				}
			result.put("contact", contact);
			result.put("text", obj.get("text"));
			result.put("created_at", obj.get("created_at"));
			
				if(!(query==null)){
					String commentId = obj.get("_id").toString();
					score = defineScoreQuery(commentId, query, nbComments);
				}
			
			result.put("score", score);
			json.accumulate("result", result);
		
			contact = false;
		}
		json.put("query", query);
		json.put("id_author", idFrom);
		json.put("date", today);
		json.put("friends",friends);

		}finally{
			cur.close();
		}
		return json;
	} 
	
	public static double defineScoreQuery(String id_comment, String query, int nbComments) throws SQLException, Exception{
		double sq = 0.0;
		String[] queryTab = query.split(" ");
		for(String word : queryTab){
			String wordLc = word.toLowerCase();
			if (!wordLc.equals(" ")){
			sq += defineScoreByWord(id_comment, wordLc ,nbComments);
			}
		}
		return sq ;
	}
	
	public static double defineScoreByWord(String id_comment, String word, int nbComments) throws SQLException, Exception{
		double sw = 0.0;
		
		Connection connection = Database.getMySQLConnection();
		PreparedStatement statement = connection
				.prepareStatement(			
		"SELECT MAPR_TF.TF * LOG(?/MAPR_DF.DF) "
		+ "FROM MAPR_DF, MAPR_TF WHERE MAPR_TF.WORD=? "
		+ "AND MAPR_TF.WORD=MAPR_DF.WORD AND MAPR_TF.ID_COMMENT=?;"				
						);
		statement.setInt(1, nbComments);
		statement.setString(2, word);
		statement.setString(3, id_comment);
		ResultSet rs = statement.executeQuery();
		while (rs.next()){
			sw = rs.getDouble(1);
		}
		statement.close();
		connection.close();
		
		return sw;
		
	}
	
	
}
