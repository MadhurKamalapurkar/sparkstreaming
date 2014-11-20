package com.daksh.project.DAO;

import java.util.List;

import com.daksh.project.factory.MongoObjectFactory;
import com.daksh.project.util.DBObjectUtil;
import com.daksh.project.util.MongoConnectionUtil;
import com.daksh.project.vo.DeviceStats;
import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoException;

/**
 * 
 * @author Team Daksh
 * Class for perform Devicedata collection related activity.
 * 
 */
public class DeviceStatsUtil implements DBObjectUtil<DeviceStats> {

	static DeviceStatsUtil deviceStatsUtil;
	private final String COLLECTION_NAME = "devicestat";


	public static void main(String args[]) {
		String device = "analytics-qfx5100-01";
		DeviceStats stats = new DeviceStats();
		stats.setDeviceName(device);
		stats.setPortCount(100);
		DBObjectUtil<DeviceStats> util = new MongoObjectFactory<DeviceStats>()
				.getDBObjectUtil("MONGODB");
		util.insertData(stats);
	}

	/**
	 * Private constructor;
	 */
	private DeviceStatsUtil() {

	}

	/**
	 * Method to insert data into device stats
	 */
	public boolean insertData(DeviceStats customObj) {
		DB database = MongoConnectionUtil.getMongoConnectionUtilUtil()
				.getDBConnection();
		DBCollection usersCollection = database.getCollection(COLLECTION_NAME);
		Gson gson = new Gson();
		String json = gson.toJson(customObj);

		String[] deviceInfoArray = json.split(",");
		int arrayIndex = 0;

		System.out.println(customObj.toString());
		BasicDBObject document = new BasicDBObject();

		for (; arrayIndex < deviceInfoArray.length; arrayIndex++) {
			String[] keyValuePair = deviceInfoArray[arrayIndex].split(":");
			String key = keyValuePair[0];
			String value = keyValuePair[1];
			if (key != null && value != null && !key.equalsIgnoreCase("0") && !value.equalsIgnoreCase("0")) {
				if (arrayIndex == 0) {
					System.out.println("Key --"+key);
					System.out.println("value ----"+value);

					document.append(key.substring(2, key.length() - 1),
							value.substring(1, value.length() - 1));

				} else if (arrayIndex == deviceInfoArray.length - 1) {
					document.append(key.substring(1, key.length() - 1),
							value.substring(0, value.length() - 1));
				} else {
					document.append(key.substring(1, key.length() - 1), value);
				}
			}
		}
		document.append("lastTimeStamp", customObj.getLastTimeStamp());
		System.out.println(document.toString());
		try {
			usersCollection.insert(document);
			MongoConnectionUtil.getMongoConnectionUtilUtil().returnConnection(database);
			return true;
		} catch (MongoException.DuplicateKey e) {
			return false;
		}
	}

	/**
	 * Method to update the data in device stat
	 * @return
	 */
	public boolean updateData() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean deleteData() {
		// TODO Auto-generated method stub
		return false;
	}

	public String readDeviceLevelData(String deviceId) {
		DB database = MongoConnectionUtil.getMongoConnectionUtilUtil()
				.getDBConnection();
		DBCollection usersCollection = database.getCollection(COLLECTION_NAME);
		DBCursor dataBaseCursor = usersCollection
				.find(new BasicDBObject("deviceName", deviceId))
				.sort(new BasicDBObject("lastTimeStamp", -1)).limit(1);
		MongoConnectionUtil.getMongoConnectionUtilUtil().returnConnection(database);

		DBObject dbObj = null;
		if (dataBaseCursor.hasNext()) {
			dbObj = dataBaseCursor.next();
		}
		Gson gson = new Gson();
		System.out.println(gson.toJson(dbObj));
		return gson.toJson(dbObj);
	}

	/**
	 * Singletone class
	 * 
	 * @return
	 */
	public static DBObjectUtil getDeviceStatUtil() {
		synchronized (DeviceStatsUtil.class) {
			if (deviceStatsUtil == null) {
				deviceStatsUtil = new DeviceStatsUtil();
			}
		}
		return deviceStatsUtil;
	}

	/**
	 * 
	 */
	public List<String> readAllDevicesData() {

		return null;
	}

	public boolean updateData(DeviceStats t) {

		return false;
	}

}
