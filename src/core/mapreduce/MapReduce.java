package core.mapreduce;

import java.sql.SQLException;

import org.bson.BasicBSONObject;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MapReduceCommand;
import com.mongodb.MapReduceOutput;

import core.database.Database;

public class MapReduce {
	
	public static void produceDF() throws SQLException, JSONException{
		
		DBCollection collection =  Database.getMongoCollection("comments");

		String mapDF = "function map() { var comt = this.text; var words = comt.toLowerCase().split(\" \"); if (words === null) return; var apparait=[]; for (var i = 0; i < words.length; i++) { apparait[words[i]]=1; } for (w in apparait) {  emit(w, {df: 1}); } }";

		
		String reduceDF = "function reduce(key, values) {"
				+ "var total = 0;"
				+ "for (v in values) { "
				+ "total += parseInt(v); } "
				+ "return {df:total}; }";
		
		MapReduceOutput oDF = collection.mapReduce(mapDF, reduceDF, null, MapReduceCommand.OutputType.INLINE,null );
		for (DBObject i : oDF.results()) {
			BasicDBObject obj = new BasicDBObject();
			obj.put("word", i.get("_id"));
			obj.put("df", i.get("value"));

			JSONObject jsonob = new JSONObject(obj.toString());
			String word = jsonob.getString("word");
			JSONObject jsonob2 = new JSONObject(jsonob.getString("df"));
			String	dfs = jsonob2.getString("df");
			double df = Double.valueOf(dfs);
			SqlManageMRContentDF.majMapReduceDF(word,df);
		
	}
}

	public static void produceTF() throws SQLException, JSONException {

		DBCollection collection =  Database.getMongoCollection("comments");
		
		String mapTF ="function map() { var comt = this.text; var words = comt.toLowerCase().split(\" \"); if (words === null) return; var apparait=[]; for (var i = 0; i < words.length; i++) { if (apparait[words[i]]==undefined) {apparait[words[i]]=0;} apparait[words[i]]++; } for (w in apparait) { emit(this._id, {word: w, count: apparait[w]}); } }";
		String reduceTF="function reduce(key, values) {return {tfs:values}; }";
	

		MapReduceOutput oTF = collection.mapReduce(mapTF, reduceTF, null, MapReduceCommand.OutputType.INLINE,null );
		for (DBObject i : oTF.results()) {
			
			BasicDBObject ob = new BasicDBObject();
			ob.put("_id", i.get("_id"));
			ob.put("value", i.get("value"));
			String idComment = "";
			String word = "";
			double tf = 0.0;
			
			// différence de sortie pour les mots uniques 
			boolean contain=false;
			if(!((BasicBSONObject) ob.get("value")).containsField("tfs")){
				contain = true ;
			};
			JSONObject jsonob = new JSONObject(ob.toString());

			//intégration de l'id Object du tweet
			JSONObject jsonob1 = new JSONObject(jsonob.getString("_id"));
			idComment = jsonob1.getString("$oid");
			
			// traitement de la value
			JSONObject jsonob2 = new JSONObject(jsonob.getString("value"));
			
			if(contain){
				word = jsonob2.getString("word");
				tf = jsonob2.getDouble("count");
				SqlManageMRContentTF.majMapReduceTF(idComment,word,tf);
			}else{
				//mots utilisés plusieurs fois
				JSONArray tfs = jsonob2.getJSONArray("tfs");
				for( int j = 0; j < tfs.length(); j++) {
					JSONObject o2 = tfs.getJSONObject(j);
					word = o2.getString("word");
					tf = o2.getDouble("count");
					SqlManageMRContentTF.majMapReduceTF(idComment,word,tf);
				}
			}
			
		}
		
		
		
	}

	
	
}
