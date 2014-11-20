package com.daksh.project.vo;

import java.io.Serializable;

/**
 * 
 * @author Team Daksh
 *
 */
public class AnRecordCustom implements Serializable{

	private String deviceName;
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	private String _interface_id;
	private int _queue_depth;
	private long _total_rcvd_pkt; //rxpkt
	public long get_total_rcvd_pkt() {
		return _total_rcvd_pkt;
	}
	public void set_total_rcvd_pkt(long _total_rcvd_pkt) {
		this._total_rcvd_pkt = _total_rcvd_pkt;
	}
	public long get_rcvd_pkts_per_sec() {
		return _rcvd_pkts_per_sec;
	}
	public void set_rcvd_pkts_per_sec(long _rcvd_pkts_per_sec) {
		this._rcvd_pkts_per_sec = _rcvd_pkts_per_sec;
	}
	public long get_rcvd_total_byte() {
		return _rcvd_total_byte;
	}
	public void set_rcvd_total_byte(long _rcvd_total_byte) {
		this._rcvd_total_byte = _rcvd_total_byte;
	}
	public long get_rcvd_byte_per_sec() {
		return _rcvd_byte_per_sec;
	}
	public void set_rcvd_byte_per_sec(long _rcvd_byte_per_sec) {
		this._rcvd_byte_per_sec = _rcvd_byte_per_sec;
	}
	public long get_rcvd_crc_err() {
		return _rcvd_crc_err;
	}
	public void set_rcvd_crc_err(long _rcvd_crc_err) {
		this._rcvd_crc_err = _rcvd_crc_err;
	}
	public long get_rcvd_droped_pkt() {
		return _rcvd_droped_pkt;
	}
	public void set_rcvd_droped_pkt(long _rcvd_droped_pkt) {
		this._rcvd_droped_pkt = _rcvd_droped_pkt;
	}
	public long get_totoal_trnsmt_pkt() {
		return _totoal_trnsmt_pkt;
	}
	public void set_totoal_trnsmt_pkt(long _totoal_trnsmt_pkt) {
		this._totoal_trnsmt_pkt = _totoal_trnsmt_pkt;
	}
	public long get_trnsmt_pkts_per_sec() {
		return _trnsmt_pkts_per_sec;
	}
	public void set_trnsmt_pkts_per_sec(long _trnsmt_pkts_per_sec) {
		this._trnsmt_pkts_per_sec = _trnsmt_pkts_per_sec;
	}
	public long get_trnsmt_totalbyte() {
		return _trnsmt_totalbyte;
	}
	public void set_trnsmt_totalbyte(long _trnsmt_totalbyte) {
		this._trnsmt_totalbyte = _trnsmt_totalbyte;
	}
	public long get_trnsmt_byte_per_sec() {
		return _trnsmt_byte_per_sec;
	}
	public void set_trnsmt_byte_per_sec(long _trnsmt_byte_per_sec) {
		this._trnsmt_byte_per_sec = _trnsmt_byte_per_sec;
	}
	public long get_trnsmt_dropped_pkt() {
		return _trnsmt_dropped_pkt;
	}
	public void set_trnsmt_dropped_pkt(long _trnsmt_dropped_pkt) {
		this._trnsmt_dropped_pkt = _trnsmt_dropped_pkt;
	}
	private long _rcvd_pkts_per_sec; //rxpps
	private long _rcvd_total_byte; //rxbyte
	private long _rcvd_byte_per_sec;//rxbps
	private long _rcvd_crc_err;//rxcrcerr
	private long _rcvd_droped_pkt;//rxdroppkt
	private long _totoal_trnsmt_pkt; //txpkt
	private long _trnsmt_pkts_per_sec;//txpps
	private long _trnsmt_totalbyte; //txbyte
	private long _trnsmt_byte_per_sec;//txbps
	private long _trnsmt_dropped_pkt; //txdroppkt
	private int portcount;//Total interface count

	
	public int getPortcount() {
		return portcount;
	}
	public void setPortcount(int portcount) {
		this.portcount = portcount;
	}
	public int get_queue_depth() {
		return _queue_depth;
	}
	public void set_queue_depth(int _queue_depth) {
		this._queue_depth = _queue_depth;
	}
	public String get_interface_id() {
		return _interface_id;
	}
	public void set_interface_id(String _interface_id) {
		this._interface_id = _interface_id;
	}
	private int _speed;
	public int get_speed() {
		return _speed;
	}
	public void set_speed(int _speed) {
		this._speed = _speed;
	}
	
}
