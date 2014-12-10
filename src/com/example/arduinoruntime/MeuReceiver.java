package com.example.arduinoruntime;

import java.io.IOException;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MeuReceiver extends BroadcastReceiver{

	@Override
	public void onReceive(Context arg0, Intent arg1) {
		String action = arg1.getExtras().getString("action");
		
		try {
			Runtime runtime = Runtime.getRuntime();
			//Process process = runtime.exec("input keyevent "+read);
			Process process = runtime.exec(action);
			Log.e("RECEBEU", "FEZ PROCESSO");
			Log.e("RECEBEU", "FEZ PROCESSO: "+process.toString());
		} catch (IOException e) {
			Log.e("RECEBEU", "NÃO FEZ PROCESSO: "+e.getMessage());
		}
	}

}
