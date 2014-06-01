package services.test;

import java.sql.SQLException;

import org.json.JSONException;

import core.mapreduce.MapReduce;

public class TestMapReduce {
	
	public static void main(String[] args){
		
		try {
				//MapReduce.produceDF();
				MapReduce.produceTF();
				
		} catch (JSONException e) {
				e.printStackTrace();
		} catch (SQLException e1) {
				e1.printStackTrace();
		}
	}

}
