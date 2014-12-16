package com.example.arduinoruntime;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class ServiceMarroto extends IntentService {

	public ServiceMarroto() {
		super("ServiceMarroto");
	}

	class ServerThread implements Runnable {

		public void run() {
			Socket socket = null;
			ServerSocket serverSocket = null;
			try {
				serverSocket = new ServerSocket(8080);
			} catch (IOException e) {
				e.printStackTrace();
			}
			while (!Thread.currentThread().isInterrupted()) {
				try {
					socket = serverSocket.accept();
					CommunicationThread commThread = new CommunicationThread(socket);
					new Thread(commThread).start();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	class CommunicationThread implements Runnable {
		private Socket clientSocket;
		private BufferedReader input;

		public CommunicationThread(Socket clientSocket) {
			this.clientSocket = clientSocket;
			try {
				this.input = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		public void run() {
			try {
				final String read = input.readLine();
				
					new Thread(){
						@Override
						public void run() {
							try {
								Runtime runtime = Runtime.getRuntime();
								Process process = runtime.exec(read);
							} catch (IOException io){}
						}
					}.start();
					
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	protected void onHandleIntent(Intent arg0) {
		Thread serverThread = new Thread(new ServerThread());
		serverThread.start();
	}

}
