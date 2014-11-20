/**
 * 
 */
package com.daksh.spark;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.spark.SparkConf;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.api.java.JavaInputDStream;
import org.apache.spark.streaming.api.java.JavaPairDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;

import analytics.Analytics;

import com.daksh.project.agent.GoogleMailAgent;
import com.daksh.project.agent.MailAgent;
import com.daksh.project.vo.AnRecordCustom;
import com.daksh.project.vo.DeviceStats;

/**
 * 
 * @author Team Daksh
 *
 */
public class SparMe {

	private static final Pattern SPACE = Pattern.compile(" ");
	public static Map<String, AnRecordCustom> anRecordMap = new HashMap<String, AnRecordCustom>();
	public static Map<String, DeviceStats> deviceMap = new HashMap<String, DeviceStats>();
	static int i =0;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SparkConf sparkConf = new SparkConf().setMaster("local[4]").setAppName(
				"SparMe");
		JavaStreamingContext ssc = new JavaStreamingContext(sparkConf,
				new Duration(1000));
		JavaInputDStream<Analytics.AnRecord> customInputDStream = ssc
				.receiverStream(new CustomReceiver("localhost", 50005));

		JavaPairDStream<String, DeviceStats> finalDeviceStats = customInputDStream
				.flatMap(new CustomFlatMapFunction()).mapToPair(new CustomPairFunction()).reduceByKey(new CustomFunction2());

	if(i==0)
		{
			MailAgent mailAgent = new GoogleMailAgent();
			mailAgent.sendMail("Niveditha", "nivedithahbr@gmail.com", "Network device needs your attention!", "Here is the direct link to your application.");
			i=1;
		}
						
		System.out.println(finalDeviceStats.count());
		finalDeviceStats.print();

		ssc.start();
		ssc.awaitTermination();
	}

}

