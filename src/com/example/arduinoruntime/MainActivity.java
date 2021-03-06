package com.example.arduinoruntime;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.List;

import org.apache.http.conn.util.InetAddressUtils;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		TextView txt = (TextView) findViewById(R.id.txtIp);

		try {
			txt.setText(getIPAddress());
		} catch (Exception e) {
			txt.setText("Exception caught =" + e.getMessage());
		}

	}

	public String getIPAddress() throws SocketException {
		List<NetworkInterface> interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
		for (NetworkInterface intf : interfaces) {
			List<InetAddress> addrs = Collections.list(intf.getInetAddresses());
			for (InetAddress addr : addrs) {
				if (!addr.isLoopbackAddress()) {
					String sAddr = addr.getHostAddress();
					boolean isIPv4 = InetAddressUtils.isIPv4Address(sAddr);
					//if (useIPv4) {
						if (isIPv4) {
							return sAddr;
						}
						/*} else {
						if (!isIPv4) {
							int delim = sAddr.indexOf('%');
							return delim < 0 ? sAddr : sAddr.substring(0, delim);
						}
					}*/
				}
			}
		}
		return "";
	}

	public void iniciar(View v) {
		Intent i = new Intent(this, ServiceMarroto.class);
		startService(i);
	}

}
