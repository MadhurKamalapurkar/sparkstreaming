package com.daksh.utility;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.WriteResult;

/**
 * 
 * @author Avadhut Thakar
 * @Implements : DBUtil.
 * 
 *             Class for MongoDB Uitlity.
 * 
 */
public class MongoDBUtil implements DBUtil {

	static DBUtil speedUtilInstance;
	private List<DB> connectionList = new ArrayList<DB>();
	private final int NUMBER_OF_CONNECTIONS = 5;

	public static void main(String args[]) {
		String device = "analytics-qfx5100-01";
		DBUtil util = getMongoDBUtil();
		util.readDeviceLevelData(device);
	}

	/**
	 * Private constructor;
	 */
	private MongoDBUtil() {
		getDBConnection();
	}

	public boolean insertData(DeviceStats customObj) {
		DB database = null;
		database = connectionList.get(0);
		connectionList.remove(0);
		// TODO : put the names into configuration file
		DBCollection usersCollection = database.getCollection("juniper");
		System.out.println(customObj.getGsonObj());
		BasicDBObject document = new BasicDBObject()
				.append("deviceName", customObj.getDeviceName())
				.append("maxLatency:", customObj.getMaxLatency())
				.append("minLatency", customObj.getMinLatency())
				.append("averageLatency", customObj.getAverageLatency())
				.append("latency", customObj.getLatency())
				.append("queueDeapth", customObj.getQueueDeapth())
				.append("averageDeapth", customObj.getAverageDeapth())
				.append("minDeapth", customObj.getMinDeapth())
				.append("maxDeapth", customObj.getMaxDeapth())
				.append("tx_pckt_lost", customObj.getTx_pckt_lost())
				.append("rx_pckt_lost", customObj.getRx_total_pckts())
				.append("portCount", customObj.getPortCount())
				.append("rx_total_pckts", customObj.getRx_total_pckts())
				.append("lastTimeStamp", customObj.getLastTimeStamp());
		try {
			usersCollection.insert(document);
			// database.c
			connectionList.add(database);
			return true;
		} catch (MongoException.DuplicateKey e) {
			return false;
		}
	}

	public boolean updateData() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean deleteData() {
		// TODO Auto-generated method stub
		return false;
	}

	private static String mongoURIString = "localhost";
	private static int port = 27017;
	private static String MongoDB = "project";

	/**
	 * This method will provide DB connection to mongoDB.
	 * 
	 * @return DB object
	 * @throws Exception
	 */
	// TODO : discuss exception handling
	private void getDBConnection() {
		MongoClient mongoClient = null;
		int poolSize = 0;
		for (; poolSize < NUMBER_OF_CONNECTIONS; poolSize++) {
			try {
				mongoClient = new MongoClient(mongoURIString, port);
				DB blogDatabase = mongoClient.getDB(MongoDB);

				connectionList.add(blogDatabase);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// return blogDatabase;
	}

	public String readDeviceLevelData(String deviceId) {
		// TODO Auto-generated method stub

		DB database = null;
		DBObject dbObj = null;
		database = connectionList.get(0);
		DBCollection usersCollection = database.getCollection("juniper");
		DBCursor dataBaseCursor = usersCollection
				.find(new BasicDBObject("deviceName", deviceId))
				.sort(new BasicDBObject("lastTimeStamp", -1)).limit(1);
		DeviceStats devicestat = null;
		if (dataBaseCursor.hasNext()) {
			dbObj = dataBaseCursor.next();
		}
		Gson gson = new Gson();
		return gson.toJson(dbObj);
	}
	/**
	 * 
	 * @param record
	 * @return
	 */
	public boolean insertData(String record) {
		// TODO Auto-generated method stub
		DB database = null;
		database = connectionList.get(0);
		connectionList.remove(0);
		DBCollection usersCollection = database.getCollection("processing");
		BasicDBObject document = new BasicDBObject("speed", record);
		usersCollection.insert(document);
		connectionList.add(database);
		return false;
	}

	/**
	 * 
	 * @param deviceName
	 */
	public void updateData(String deviceName) {
		DB database = connectionList.get(0);

		DBCollection usersCollection = database.getCollection("processing");
		WriteResult result = usersCollection.remove(new BasicDBObject());
		insertData(deviceName);
		connectionList.add(database);

	}

	/**
	 * 
	 * @param deviceName
	 * @return
	 */
	public String readData(String deviceName) {
		DB database = connectionList.get(0);
		DBCollection usersCollection = database.getCollection("processing");
		DBObject result = usersCollection.findOne();
		System.out.println(result.toString());
		connectionList.add(database);
		return result.toString();
	}

	/**
	 * Singletone class
	 * 
	 * @return
	 */
	public static DBUtil getMongoDBUtil() {
		synchronized (MongoDBUtil.class) {
			if (speedUtilInstance == null) {
				speedUtilInstance = new MongoDBUtil();
			}
		}
		return speedUtilInstance;
	}

	public DeviceStats readAllDevicesData() {

		return null;
	}

}
