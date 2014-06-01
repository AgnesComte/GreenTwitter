package services;

import org.json.JSONException;
import org.json.JSONObject;

public class ServicesTools {
	
	    public static JSONObject error(String error, int code){
	        JSONObject json = new JSONObject();
	        try {
	        	json.put("error", error);
	            json.put("code", code);
	        } catch (JSONException e) {
	        	e.printStackTrace();
	            System.out.println("Error Json"+e.getMessage());
	        }
	        return json;
	    }

	    public static JSONObject ok() {
	        JSONObject json = new JSONObject();
	        return json;
	    }
	    
	    public static JSONObject error001() {
	        return ServicesTools.error("Missing parameter", 001);
	    }
	    public static JSONObject error002() {
	        return ServicesTools.error("login already exists", 002);
	    }
	    
	    public static JSONObject error601() { 
	        return ServicesTools.error("Session expired", 601);
	    }
	
	    public static JSONObject error101(){
	    	return ServicesTools.error("Bad Request", 101);
	    	
	    }
}
