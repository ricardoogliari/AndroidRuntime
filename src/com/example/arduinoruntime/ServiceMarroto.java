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
		// TODO Auto-generated constructor stub
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
					Log.e("RECEBEU", "no aguard meu fio");
					socket = serverSocket.accept();
					Log.e("RECEBEU", "aceitou: " + socket.getLocalAddress());
					CommunicationThread commThread = new CommunicationThread(
							socket);
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
				this.input = new BufferedReader(new InputStreamReader(
						this.clientSocket.getInputStream()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		public void run() {
			try {
				final String read = input.readLine();
				Log.e("RECEBEU", read);// updateConversationHandler.post(new
										// updateUIThread(read));
				
					new Thread(){
						@Override
						public void run() {
								Intent i = new Intent("RECEIVE_ACTION");
								i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
								i.putExtra("action", read);
								sendBroadcast(i);
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
