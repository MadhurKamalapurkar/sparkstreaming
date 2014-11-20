package com.daksh.utility;

public class DatabaseConnectionFactory {

	public DatabaseConnectionFactory() {
		// TODO Auto-generated constructor stub
	}

	public DBUtil getDBUtil(String databaseType) {
		if (databaseType.equalsIgnoreCase("MONGODB")) {
			return MongoDBUtil.getMongoDBUtil();
		} else if (databaseType.equalsIgnoreCase("HADOOP")) {
			// TODO Hadoop Implementation
			return null;
		}

		return null;
	}

}
