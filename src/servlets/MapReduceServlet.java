package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

@SuppressWarnings("serial")
public class MapReduceServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{
		
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
			String word = "";
			String df = "";
			try {
				JSONObject jsonob = new JSONObject(obj.toString());
				word = jsonob.getString("word");
				//df = jsonob.getString("df");
				JSONObject jsonob2 = new JSONObject(jsonob.getString("df"));
				df = jsonob2.getString("df");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				resp.getWriter().println(e);
				//e.printStackTrace();
			}
			
			
//			BasicDBObject obj2 = new BasicDBObject("value", i.get("value"));
//			obj.put("df", obj2.get("df"));
//			String word = obj.get("word").toString();
//			String dfs = obj.get("df").toString();
//			double df = Double.valueOf(dfs);
			resp.getWriter().println("df: "+df +" word: "+word);
			
		}
		
		//TF
		
		String mapTF ="function map() { var comt = this.text; var words = comt.toLowerCase().split(\" \"); if (words === null) return; var apparait=[]; for (var i = 0; i < words.length; i++) { if (apparait[words[i]]==undefined) {apparait[words[i]]=0;} apparait[words[i]]++; } for (w in apparait) { emit(this._id, {word: w, count: apparait[w]}); } }";

		String reduceTF="function reduce(key, values) {return {tfs:values}; }";
	

		MapReduceOutput oTF = collection.mapReduce(mapTF, reduceTF, null, MapReduceCommand.OutputType.INLINE,null );
		for (DBObject i : oTF.results()) {
			
			resp.getWriter().println(i);	
			
			BasicDBObject ob = new BasicDBObject();
			ob.put("_id", i.get("_id"));
			ob.put("value", i.get("value"));
			JSONObject jsonob;
			String idComment = "";
			String word = "";
			//BasicDBObject ob2 = new BasicDBObject();
			//ob2.put("value", ob.get("value"));
			resp.getWriter().println("ob2: "+ob.get("value"));
			
			
			boolean contain=false;
			if(!((BasicBSONObject) ob.get("value")).containsField("tfs")){
				contain = true ;
			};
			
			resp.getWriter().println("contain: "+contain);
			
			
			double tf = 0.0;
			try {
				jsonob = new JSONObject(ob.toString());
				
				JSONObject jsonob1 = new JSONObject(jsonob.getString("_id"));
				idComment = jsonob1.getString("$oid");
				
				JSONObject jsonob2 = new JSONObject(jsonob.getString("value"));
				
				//Tester si il contient tfs
				if(contain){
					word = jsonob2.getString("word");
					tf = jsonob2.getDouble("count");
				}else{
					
					JSONArray tfs = jsonob2.getJSONArray("tfs");
					
					//for(JSONObject jsonob3 : tfs){
					for( int j = 0; j < tfs.length(); j++) {
						JSONObject o2 = tfs.getJSONObject(j);
						word = o2.getString("word");
						tf = o2.getDouble("count");
						
						resp.getWriter().println("idComment: "+idComment+" word: "+word+" tf: "+tf);	
					}
					JSONObject jsonob3 = tfs.getJSONObject(0);

					word = jsonob3.getString("word");
					tf = jsonob3.getDouble("count");
				}
				
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				resp.getWriter().println(e);
			}
		
//			
//			String idComment = jsonob.getString("_id");
//			
//			JSONArray tfs = jsonob.getJSONArray("tfs");
//			JSONObject jsonob2 = tfs.getJSONObject(0);
//			
//			
//			String word = jsonob2.getString("word");
//			double tf = jsonob2.getDouble("count");
			
			resp.getWriter().println("idComment: "+idComment+" word: "+word+" tf: "+tf);	
		}
		
		
		// calcul de DF
		
		/*//comt.match(/\\w+/g);
		String mapDF = "function() {"
				+ "var comt = this.text;"
				+ "var words = comt.toLowerCase().split(\" \");"
				+ "if (words === null) return;"
		//		+ "var df = [];"
				+ "for (var i = 0; i < words.length; i++) {"
		//		+ "console.log(words[i]);"
		//		+ "if (words[i])"
		//		+ " df[words[i]] = 1; "
				+ " emit(words[i], {count: 1}); }"
				+ "};";
//				+ "for (w in df) {"
//				+ "var d= df[w];"
//				+ "emit(w, {df:d}); }"
//				+ " }";
		
		String reduceDF = "function(key, values) {"
				+ "var total = 0;"
			//	+ "values.forEach(function(v) { total += parseInt(v); });"
				+ "for (v in values) { "
			//	+ "console.log(v);"
				+ "total += parseInt(v); } "
				+ "return total; };";
		//{word: key, df: total}
		//resp.getWriter().println(m);
		//resp.getWriter().println(r);
		
		

		MapReduceOutput oDF = collection.mapReduce(mapDF, reduceDF, null, MapReduceCommand.OutputType.INLINE,null );
		for (DBObject i : oDF.results()) {
			resp.getWriter().println(i);	
		
		//insérer SQL - appel à la bas SQL et faire un insert/update (?) de valeur de fréquence de chaque mot
		}
		
	
	// calcul de TF
		String mapTF = "function() {"
				+ "var tf = [][];"				
				+ "for (int j = 0; j < this.text.length; j++) {"
						+ "var idDoc = this._id[j][];"
						+ " tf[j] = idDoc;"
				+ "	var comt = this.text[j];"
						
				+ "	var words = comt.toLowerCase().split(\" \");"
				+ "	if (words === null) return;"
		//		+ "var df = [];"
				+ "	for (var i = 0; i < words.length; i++) {"
//tfji
				//		+ "console.log(words[i]);"
		//		+ "if (words[i])"
		//		+ " df[words[i]] = 1; "
				+ " 	emit(words[i], {count: 1}); }"
				+ "	}"
				+ "};";
//				+ "for (w in df) {"
//				+ "var d= df[w];"
//				+ "emit(w, {df:d}); }"
//				+ " }";
		
		String reduceTF = "function(key, values) {"
				+ "var total = 0;"
			//	+ "values.forEach(function(v) { total += parseInt(v); });"
				+ "for (v in values) { "
			//	+ "console.log(v);"
				+ "total += parseInt(v); } "
				+ "return total; };";
		//{word: key, df: total}
		//resp.getWriter().println(m);
		//resp.getWriter().println(r);
		
		

		MapReduceOutput oTF = collection.mapReduce(mapTF, reduceTF, null, MapReduceCommand.OutputType.INLINE,null );
		for (DBObject i : oTF.results()) {
			resp.getWriter().println(i);	
		
		//insérer SQL - appel à la bas SQL et faire un insert/update (?) de valeur de fréquence de chaque mot
		 * 
		 */
		
		
		
		
		
	}
}

