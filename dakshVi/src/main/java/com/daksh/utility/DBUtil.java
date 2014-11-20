/**
 * 
 */
package com.daksh.utility;

import com.daksh.utility.DeviceStats;

/**
 * @author Avadhut
 * 
 */
public interface DBUtil {
	/*
	 * Method to insert data into DB
	 */
	public boolean insertData(DeviceStats record);

	/*
	 * Method to update data into DB
	 */
	public boolean updateData();

	/*
	 * Method to delete data into DB
	 */
	public boolean deleteData();

	/*
	 * Method to read data from DB
	 */
	public String readDeviceLevelData(String deviceName);
	
	/*
	 * Read all devices data
	 */
	public DeviceStats readAllDevicesData();



}
