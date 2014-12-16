package com.example.arduinoruntime;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

public class MeuReceiver extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		Toast.makeText(context, "recebeu", 8000).show();
		Intent i = new Intent(Intent.ACTION_CALL);
		i.setData(Uri.parse("tel:"+intent.getExtras().getString("teste")));
		i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(i);
	}

}
