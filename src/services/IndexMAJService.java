package services;

import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

import core.mapreduce.MapReduce;

public class IndexMAJService {

	public static JSONObject majMapReduceTables() {
		
			try {
				
				MapReduce.produceDF();
				MapReduce.produceTF();
				
				return(ServicesTools.ok());
				
			} catch	(JSONException e){
					return (ServicesTools.error("Problem while generating the index JSON"+e.getMessage(),1000));
			} catch (SQLException e){
					return(ServicesTools.error("Problem while generating the index SQL"+e.getMessage(),1000));
			} catch (Exception e) {
					return(ServicesTools.error("Problem..."+e.getMessage(),10000));
			}

		}
		
}
