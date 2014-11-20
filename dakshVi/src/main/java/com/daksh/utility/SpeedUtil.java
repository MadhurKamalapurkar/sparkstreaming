package com.daksh.utility;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.WriteResult;

public class SpeedUtil {
	private static String mongoURIString = "localhost";
	private static int port = 27017;
	private static String MongoDB = "project";
	static SpeedUtil mongoDBUtilInstance;
	private List<DB> connectionList = new ArrayList<DB>();
	private DB processingDataBase;

	public SpeedUtil() {
		// TODO Auto-generated constructor stub
		MongoClient mongoClient = null;
		try {
			mongoClient = new MongoClient(mongoURIString, port);
			processingDataBase = mongoClient.getDB(MongoDB);
		} catch (Exception ex) {

		}
	}

	public boolean insertData(String record) {
		// TODO Auto-generated method stub
		DB database = null;
		database = connectionList.get(0);
		connectionList.remove(0);
		// TODO : put the names into configuration file
		DBCollection usersCollection = database.getCollection("processing");
		BasicDBObject document = new BasicDBObject("speed", record);
		usersCollection.insert(document);
		return false;
	}
	/**
	 * 
	 * @param deviceName
	 */
	public void updateData(String deviceName) {
		DBCollection usersCollection = processingDataBase
				.getCollection("processing");
		WriteResult result = usersCollection.remove(new BasicDBObject());
		insertData(deviceName);
	}
	/**
	 * 
	 * @return
	 */
	public boolean deleteData() {
		// TODO Auto-generated method stub
		return false;
	}
	/**
	 * 
	 * @param deviceName
	 * @return
	 */
	public String readData(String deviceName) {
		// TODO Auto-generated method stub
		DBCollection usersCollection = processingDataBase
				.getCollection("processing");
		DBObject result = usersCollection.findOne();
		System.out.println(result.toString());
		return result.toString();
	}

	/**
	 * Singletone class
	 * 
	 * @return
	 */
	public static SpeedUtil getMongoDBUtil() {
		synchronized (SpeedUtil.class) {
			if (mongoDBUtilInstance == null) {
				mongoDBUtilInstance = new SpeedUtil();
			}
		}
		return mongoDBUtilInstance;
	}

}
