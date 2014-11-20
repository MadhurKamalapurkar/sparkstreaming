package com.daksh.project.vo;

import java.io.Serializable;

public class DeviceStats implements Serializable{

	public DeviceStats() {
		// TODO Auto-generated constructor stub
	}

	String deviceName;
	long maxLatency;
	long minLatency;
	long averageLatency;
	long latency;
	long queueDeapth;
	long averageDeapth;
	long minDeapth;
	long lastTimeStamp;
	long maxDeapth;
	int numberOfEvents;
	long tx_pckt_lost;
	long rx_pckt_lost;
	int portCount;
	long tx_total_pckts;
	long rx_total_pckts;
	int interfaceCount;
	long avrg_rx_pckt_dropped;
	long avrg_tx_pckt_dropped;
	
	
	public long getAvrg_rx_pckt_dropped() {
		return avrg_rx_pckt_dropped;
	}

	public void setAvrg_rx_pckt_dropped(long avrg_rx_pckt_dropped) {
		this.avrg_rx_pckt_dropped = avrg_rx_pckt_dropped;
	}

	public long getAvrg_tx_pckt_dropped() {
		return avrg_tx_pckt_dropped;
	}

	public void setAvrg_tx_pckt_dropped(long avrg_tx_pckt_dropped) {
		this.avrg_tx_pckt_dropped = avrg_tx_pckt_dropped;
	}

	

	public int getInterfaceCount() {
		return interfaceCount;
	}

	public void setInterfaceCount(int interfaceCount) {
		this.interfaceCount = interfaceCount;
	}

	public long getTx_total_pckts() {
		return tx_total_pckts;
	}

	public void setTx_total_pckts(long tx_total_pckts) {
		this.tx_total_pckts = tx_total_pckts;
	}

	public long getRx_total_pckts() {
		return rx_total_pckts;
	}

	public void setRx_total_pckts(long rx_total_pckts) {
		this.rx_total_pckts = rx_total_pckts;
	}

	public int getPortCount() {
		return portCount;
	}

	public void setPortCount(int portCount) {
		this.portCount = portCount;
	}

	public long getTx_pckt_lost() {
		return tx_pckt_lost;
	}

	public void setTx_pckt_lost(long tx_pckt_lost) {
		this.tx_pckt_lost = tx_pckt_lost;
	}

	public long getRx_pckt_lost() {
		return rx_pckt_lost;
	}

	public void setRx_pckt_lost(long rx_pckt_lost) {
		this.rx_pckt_lost = rx_pckt_lost;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public long getMaxLatency() {
		return maxLatency;
	}

	public void setMaxLatency(long maxLatency) {
		this.maxLatency = maxLatency;
	}

	public long getMinLatency() {
		return minLatency;
	}

	public void setMinLatency(long minLatency) {
		this.minLatency = minLatency;
	}

	public long getAverageLatency() {
		return averageLatency;
	}

	public void setAverageLatency(long averageLatency) {
		this.averageLatency = averageLatency;
	}

	public long getQueueDeapth() {
		return queueDeapth;
	}

	public void setQueueDeapth(long queueDeapth) {
		this.queueDeapth = queueDeapth;
	}

	public long getAverageDeapth() {
		return averageDeapth;
	}

	public void setAverageDeapth(long averageDeapth) {
		this.averageDeapth = averageDeapth;
	}

	public long getMinDeapth() {
		return minDeapth;
	}

	public void setMinDeapth(long minDeapth) {
		this.minDeapth = minDeapth;
	}

	public long getLastTimeStamp() {
		return lastTimeStamp;
	}

	public void setLastTimeStamp(long lastTimeStamp) {
		this.lastTimeStamp = lastTimeStamp;
	}

	public long getMaxDeapth() {
		return maxDeapth;
	}

	public void setMaxDeapth(long maxDeapth) {
		this.maxDeapth = maxDeapth;
	}

	public int getNumberOfEvents() {
		return numberOfEvents;
	}

	public void setNumberOfEvents(int numberOfEvents) {
		this.numberOfEvents = numberOfEvents;
	}

	public long getLatency() {
		return latency;
	}

	public void setLatency(long latency) {
		this.latency = latency;
	}

}
