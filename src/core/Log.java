package core;

import java.sql.SQLException;
import java.util.Date;

import org.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import core.database.Database;


public class Log {

	public static void logs(String service, String adrIP, String nomClient) throws SQLException,Exception{
		DBCollection coll = Database.getMongoCollection("logs");
		BasicDBObject query = new BasicDBObject();
		query.put("service", service);
		query.put("adrIP", adrIP);
		query.put("nomClient", nomClient);
		query.put("created_at", new Date().getTime());
		coll.insert(query);
	}

public static JSONObject searchLog(String query, String keyLogin) throws SQLException, Exception{
		
		JSONObject json = new JSONObject();
		
		DBCollection coll = Database.getMongoCollection("logs");
		BasicDBObject search = new BasicDBObject();
		// exclude keylogin de la recherche
		search.put("$ne", keyLogin);
		
		if (query.equals(null)){
			DBCursor cur = coll.find(search).sort(new BasicDBObject("$natural", -1)).limit(30);
			try{
			while(cur.hasNext()){
				DBObject obj = cur.next();
				DBObject result = new BasicDBObject();
				result.put("_id", obj.get("_id"));
				result.put("service", obj.get("service"));
				result.put("adrIP", obj.get("adrIP"));
				result.put("keyLogin", obj.get("keyLogin"));
				json.accumulate("result", result);
			
			}
			}finally{
				cur.close();
			}
		}
		// TODO MAP REDUCE 
		return json;
	} 
	
	
}
