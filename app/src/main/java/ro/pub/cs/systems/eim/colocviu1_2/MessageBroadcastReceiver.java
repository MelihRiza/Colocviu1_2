package ro.pub.cs.systems.eim.colocviu1_2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MessageBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
//        Log.d("broadcast", String.valueOf(intent.getStringExtra("broadcast")));
//        Toast.makeText(this, "The activity returned with result ", Toast.LENGTH_LONG).show();
        String message = String.valueOf(intent.getStringExtra("broadcast"));
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
