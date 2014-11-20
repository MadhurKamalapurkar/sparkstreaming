/**
 * 
 */
package com.daksh.spark;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.UnknownHostException;

import org.apache.spark.storage.StorageLevel;
import org.apache.spark.streaming.receiver.Receiver;

import analytics.Analytics;

import com.google.protobuf.CodedInputStream;

import edu.rit.util.Packing;

public class CustomReceiver extends Receiver<Analytics.AnRecord> {
	private static final long serialVersionUID = 1L;
	private String _host = "";
	private int _port = -1;
	private static ServerSocket _socket = null;

	public CustomReceiver(String _host, int _port) {
		super(StorageLevel.MEMORY_ONLY_2());
		this._host = _host;
		this._port = _port;
	}

	@Override
	public void onStart() {
		new Thread() {
			@Override
			public void run() {
				System.out.println("Start");
				receive();
			}
		}.start();

	}

	@Override
	public void onStop() {
		// TODO Auto-generated method stub

	}

	private void receive() {
		
		String _userInput = null;
		try {
			
			System.out.println("receive");
			
			if( _socket == null)
			{
				_socket = new ServerSocket(_port);
				System.out.println("Socket WAS not available- Daksh ");
			}
			InputStream inputStream = _socket.accept().getInputStream();
			// ObjectInputStream os = new ObjectInputStream(inputStream);
			byte[] ba = new byte[4];
			byte[] ca = new byte[4];
			while (!isStopped()) {

				inputStream.read(ba);
				inputStream.read(ca);
				int size = Packing.packIntLittleEndian(ba, 0);
				int one = Packing.packIntLittleEndian(ca, 0);
				System.out.println("Checking 1::::::"+one);
				System.out.println("Checking 1::::::"+one);
				System.out.println("Checking 1::::::"+one);
				System.out.println("Checking 1::::::"+one);
				System.out.println("Checking 1::::::"+one);
				System.out.println("Checking 1::::::"+one);
				byte[] objectBinary = new byte[size];
				inputStream.read(objectBinary);
				//CodedInputStream cStream = CodedInputStream
				//		.newInstance(objectBinary);
				Analytics.AnRecord.Builder anRecBuilder = Analytics.AnRecord
						.newBuilder();
				anRecBuilder.mergeFrom(objectBinary);
				Analytics.AnRecord anRecord = anRecBuilder.build();
				System.out.println(anRecord.toString());
				store(anRecord);
			}
			inputStream.close();
			_socket.close();
			restart("Trying to connect again");

		} catch (UnknownHostException e) {
			e.printStackTrace();
			restart("Could not connect", e);
		} catch (IOException e) {
			e.printStackTrace();
			restart("Could not connect", e);
		}
	}

}
