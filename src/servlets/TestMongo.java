package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import core.database.Database;

@SuppressWarnings("serial")
public class TestMongo extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{
		
		DBCollection collection =  Database.getMongoCollection("test");
		BasicDBObject objet = new BasicDBObject();
		objet.put("text", "Coucou");
		collection.insert(objet);
		DBCursor cur = collection.find(objet);
		while(cur.hasNext()){
			DBObject obj = cur.next();
			resp.getWriter().print(obj);
			
		}
		
	}

}
