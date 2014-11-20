package com.daksh.project.factory;

import com.daksh.project.DAO.DeviceStatsUtil;
import com.daksh.project.DAO.ProcessUtil;
import com.daksh.project.util.DBObjectUtil;

/**
 * 
 * @author Team Daksh
 * 
 */
public class ConnectionPoolFactory {

	public ConnectionPoolFactory() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @param objectType
	 * @return
	 */
	public DBObjectUtil getConnection(String objectType) {
		if (objectType.equalsIgnoreCase("MONGODB")) {
			return DeviceStatsUtil.getDeviceStatUtil();
		} else if (objectType.equalsIgnoreCase("PROCESSING")) {
			return ProcessUtil.getProcesUtil();
		} else if (objectType.equalsIgnoreCase("HADOOP")) {
			// TODO Hadoop Implementation
			return null;
		}
		return null;
	}

}
