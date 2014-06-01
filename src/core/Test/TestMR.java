package core.Test;

import java.sql.SQLException;

import core.comment.SearchComments;
import core.mapreduce.SqlManageMRContentDF;
import core.mapreduce.SqlManageMRContentTF;

public class TestMR {
	
	public static void main(String[] arg) throws Exception{
		
		
		//TEST MR DF 
		//SqlManageMRContentDF.majMapReduceDF("bonjour", 7);
		
		//OK
		//boolean b = SqlManageMRContentDF.checkUpdateDF("bonjour", 6);
		//System.out.println(b);
		
		//ok insert
		//SqlManageMRContentDF.insertMapReduceDF("bonjour", 3.5);
		// update ok
		//SqlManageMRContentDF.updateMapReduceDF("bonjour", 6);
		
		//TEST MR TF
		
		//SqlManageMRContentTF.majMapReduceTF("iefboeigb", "bonjour", 9.6);
		//ok
		//boolean b1 = SqlManageMRContentTF.checkUpdateTF("iefboeigb", "bonjour", 6.4);
		//System.out.println(b1);
		//ok
		//SqlManageMRContentTF.insertMapReduceTF("iefboeigb", "bonjour", 6.4);
		//ok
		//SqlManageMRContentTF.updateMapReduceTF("iefboeigb", "bonjour", 7.8);
		
//		double sw = SearchComments.defineScoreByWord("iefboeigb", "bonjour", 16);
//		System.out.println(sw);
		double sy = SearchComments.defineScoreQuery("53427721e4b00b69d23edf0b", "bonjour", 11);
		System.out.println(sy);
		
	}

}
