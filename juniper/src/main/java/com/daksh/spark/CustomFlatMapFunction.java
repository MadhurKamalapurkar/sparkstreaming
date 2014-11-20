package com.daksh.spark;

import java.util.Arrays;

import org.apache.spark.api.java.function.FlatMapFunction;

import analytics.Analytics;
import analytics.Analytics.AnRecord;

import com.daksh.project.vo.AnRecordCustom;

/**
 * 
 * @author Team Daksh
 *
 */
public class CustomFlatMapFunction implements FlatMapFunction<Analytics.AnRecord, AnRecordCustom>{
	
		public Iterable<AnRecordCustom> call(AnRecord t) throws Exception {
			 AnRecordCustom[] anRecordCustomArray = null;
			 int portCount = 0;
			if(t.getInterfaceCount()>0)
			{
				portCount = t.getInterfaceCount();
			    anRecordCustomArray = new AnRecordCustom[t.getInterfaceCount()];
				for(int j = 0; j < t.getInterfaceCount() ; j++)
				{
					AnRecordCustom anrecordCustom = new AnRecordCustom();
					anrecordCustom.set_interface_id(t.getInterface(j).getName().toString());
					anrecordCustom.setDeviceName(t.getInterface(j).getName().toString().split(":")[0].toString());
					anrecordCustom.set_speed((int) t.getInterface(j).getStatus().getLink().getSpeed());
					anrecordCustom.set_queue_depth((int)t.getInterface(j).getStats().getQueueStats().getQueueDepth());
					anrecordCustom.set_total_rcvd_pkt((long)t.getInterface(j).getStats().getTrafficStats().getRxpkt());
					anrecordCustom.set_rcvd_pkts_per_sec((long)t.getInterface(j).getStats().getTrafficStats().getRxpps());
					anrecordCustom.set_rcvd_total_byte((long)t.getInterface(j).getStats().getTrafficStats().getRxbyte());
					anrecordCustom.set_rcvd_byte_per_sec((long)t.getInterface(j).getStats().getTrafficStats().getRxbps());
					anrecordCustom.set_rcvd_crc_err((long)t.getInterface(j).getStats().getTrafficStats().getRxcrcerr());
					anrecordCustom.set_rcvd_droped_pkt((long)t.getInterface(j).getStats().getTrafficStats().getRxdroppkt());
					anrecordCustom.set_totoal_trnsmt_pkt((long)t.getInterface(j).getStats().getTrafficStats().getTxpkt());
					anrecordCustom.set_trnsmt_pkts_per_sec((long)t.getInterface(j).getStats().getTrafficStats().getTxpps());
					anrecordCustom.set_trnsmt_totalbyte((long)t.getInterface(j).getStats().getTrafficStats().getTxbyte());
					anrecordCustom.set_trnsmt_byte_per_sec((long)t.getInterface(j).getStats().getTrafficStats().getTxbps());
					anrecordCustom.set_trnsmt_dropped_pkt((long)t.getInterface(j).getStats().getTrafficStats().getTxdroppkt());
					anrecordCustom.setPortcount(portCount);
					anRecordCustomArray[j] = anrecordCustom;
				}
				return Arrays.asList(anRecordCustomArray);
			}
			else
				if(  null != anRecordCustomArray )
					return Arrays.asList(anRecordCustomArray);
				else
						return null;
		}
	
}
