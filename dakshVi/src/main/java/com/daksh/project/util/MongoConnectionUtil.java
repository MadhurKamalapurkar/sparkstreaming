package com.daksh.project.util;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.DB;
import com.mongodb.MongoClient;
/**
 * 
 * @author Team Daksh
 *
 */
public class MongoConnectionUtil {
	private String mongoURIString = "localhost";
	private int port = 27017;
	private String MongoDB = "project";
	private List<DB> connectionList = new ArrayList<DB>();
	private final int NUMBER_OF_CONNECTIONS = 10;
	private static MongoConnectionUtil mongoConnectionUtil;

	/**
	 * Constructor
	 */
	private MongoConnectionUtil() {
		MongoClient mongoClient = null;
		try {
			mongoClient = new MongoClient(mongoURIString, port);
			int poolSize = 0;
			for (; poolSize < NUMBER_OF_CONNECTIONS; poolSize++) {
				try {
					DB blogDatabase = mongoClient.getDB(MongoDB);
					connectionList.add(blogDatabase);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		} catch (Exception ex) {

		}
	}

	/**
	 * Method to get connection
	 * 
	 * @return
	 */
	public DB getDBConnection() {
		DB db = connectionList.get(0);
		connectionList.remove(0);
		return db;
	}

	/**
	 * Method to return the connection
	 * 
	 * @param conn
	 */
	public void returnConnection(DB conn) {
		connectionList.add(conn);
	}

	/**
	 * Singletone class
	 * 
	 * @return
	 */
	public static MongoConnectionUtil getMongoConnectionUtilUtil() {
		synchronized (MongoConnectionUtil.class) {
			if (mongoConnectionUtil == null) {
				mongoConnectionUtil = new MongoConnectionUtil();
			}
		}
		return mongoConnectionUtil;
	}

}
