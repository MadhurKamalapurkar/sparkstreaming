package com.daksh.project.DAO;

import java.util.List;

import com.daksh.project.factory.MongoObjectFactory;
import com.daksh.project.util.DBObjectUtil;
import com.daksh.project.util.MongoConnectionUtil;
import com.daksh.project.vo.DeviceStats;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;
/**
 * 
 * @author Team Daksh
 *
 */
public class ProcessUtil implements DBObjectUtil<String> {
	static ProcessUtil processUtil;
	private final String COLLECTION_NAME = "networkstats";

	public ProcessUtil() {

	}
	
	public static void main(String args[]) {
		String device = "analytics-qfx5100-01";
		DeviceStats stats = new DeviceStats();
		stats.setDeviceName(device);
		stats.setPortCount(100);
		DBObjectUtil<String> util = new MongoObjectFactory<String>()
				.getDBObjectUtil("PROCESSING");
		util.insertData(device);
	}
	/**
	 * 
	 */
	public boolean insertData(String deviceName) {
		DB database = MongoConnectionUtil.getMongoConnectionUtilUtil()
				.getDBConnection();

		// TODO : put the names into configuration file
		DBCollection usersCollection = database.getCollection(COLLECTION_NAME);
		BasicDBObject document = new BasicDBObject("processingSpeed",
				deviceName);
		usersCollection.insert(document);
		MongoConnectionUtil.getMongoConnectionUtilUtil().returnConnection(database);

		return false;
	}

	/**
	 * 
	 * @param deviceName
	 */
	public boolean updateData(String deviceName) {
		DB database = MongoConnectionUtil.getMongoConnectionUtilUtil()
				.getDBConnection();
		DBCollection usersCollection = database.getCollection(COLLECTION_NAME);
		WriteResult result = usersCollection.remove(new BasicDBObject());
		insertData(deviceName);
		MongoConnectionUtil.getMongoConnectionUtilUtil().returnConnection(database);

		return true;
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

	public String readDeviceLevelData(String deviceName) {
		DB database = MongoConnectionUtil.getMongoConnectionUtilUtil()
				.getDBConnection();
		DBCollection usersCollection = database.getCollection("processing");
		DBObject result = usersCollection.findOne();
		System.out.println(result.toString());
		MongoConnectionUtil.getMongoConnectionUtilUtil().returnConnection(database);

		return result.toString();
	}

	/*
	 * Read all devices data
	 */
	public List<String> readAllDevicesData() {
		return null;
	}

	/**
	 * Singletone class
	 * 
	 * @return
	 */
	public static DBObjectUtil getProcesUtil() {
		synchronized (ProcessUtil.class) {
			if (processUtil == null) {
				processUtil = new ProcessUtil();
			}
		}
		return processUtil;
	}

}
