package com.daksh.spark;

import org.apache.spark.api.java.function.PairFunction;

import scala.Tuple2;

import com.daksh.project.vo.AnRecordCustom;
import com.daksh.project.vo.DeviceStats;

/**
 * 
 * @author Team Daksh
 *
 */
public class CustomPairFunction implements PairFunction<AnRecordCustom, String, DeviceStats> {
	public Tuple2<String, DeviceStats> call(AnRecordCustom t)
			throws Exception {
		DeviceStats deviceStats = new DeviceStats();
		deviceStats.setAvrg_rx_pckt_dropped(t.get_rcvd_droped_pkt());
		deviceStats.setAvrg_tx_pckt_dropped(t.get_trnsmt_dropped_pkt());
		deviceStats.setTx_pckt_lost(t.get_trnsmt_dropped_pkt());
		deviceStats.setRx_pckt_lost(t.get_rcvd_droped_pkt());
		deviceStats.setRx_total_pckts(t.get_total_rcvd_pkt());
		deviceStats.setTx_total_pckts(t.get_totoal_trnsmt_pkt());
		deviceStats.setPortCount(t.getPortcount());
		return new Tuple2<String, DeviceStats>(t.getDeviceName(), deviceStats);

	}

}
