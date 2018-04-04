package utils;

import java.util.List;
import java.util.Map;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;

public class MongoWorker {
	public static boolean inserts(String name, List<Map> documents) {

		MongoClient mc = new MongoClient("13.125.143.220", 51249);
		MongoCollection collection = mc.getDatabase("saansoo").getCollection(name);
		int cnt=0;
		try {
			for(Map map : documents) {
				org.bson.Document d = new org.bson.Document(map);
				collection.insertOne(d);
				cnt++;
			}
		}catch (Exception e) {
			e.printStackTrace();
			collection.drop();
		}
		return documents.size()==cnt;
	}
	
}
