/**
 * 
 */
package com.daksh.project.util;

import java.util.List;

/**
 * @author Team Daksh
 * 
 */
public interface DBObjectUtil<T> {
	/*
	 * Method to insert data into DB
	 */
	public boolean insertData(T t);

	/*
	 * Method to update data into DB
	 */
	public boolean updateData(T t);

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
	public List<String> readAllDevicesData();

}
