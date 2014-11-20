package com.daksh.spark;

import org.apache.spark.api.java.function.Function2;

import com.daksh.project.factory.MongoObjectFactory;
import com.daksh.project.util.DBObjectUtil;
import com.daksh.project.vo.DeviceStats;

/**
 * 
 * @author Team Daksh
 *
 */
public class CustomFunction2 implements
		Function2<DeviceStats, DeviceStats, DeviceStats> {

	public DeviceStats call(DeviceStats v1, DeviceStats v2) throws Exception {
		DeviceStats deviceStats = new DeviceStats();
		deviceStats.setPortCount((v1.getPortCount() + v2.getPortCount()) / 2);
		deviceStats.setAvrg_rx_pckt_dropped((v1.getAvrg_rx_pckt_dropped() + v2
				.getAvrg_rx_pckt_dropped()) / 2);
		deviceStats.setAvrg_tx_pckt_dropped((v1.getAvrg_tx_pckt_dropped() + v1
				.getAvrg_tx_pckt_dropped()) / 2);
		deviceStats
				.setTx_pckt_lost(v1.getTx_pckt_lost() + v2.getTx_pckt_lost());
		deviceStats
				.setRx_pckt_lost(v1.getRx_pckt_lost() + v2.getRx_pckt_lost());
		deviceStats.setTx_total_pckts(v1.getTx_total_pckts()
				+ v2.getTx_total_pckts());
		deviceStats.setRx_total_pckts(v1.getRx_total_pckts()
				+ v2.getRx_total_pckts());

		DBObjectUtil<DeviceStats> util = new MongoObjectFactory<DeviceStats>()
				.getDBObjectUtil("MONGODB");
		util.insertData(deviceStats);

		return deviceStats;
	}
}
