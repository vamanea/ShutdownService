package ro.mrs.nero.shutdownservice;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class ShutdownReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        try {
            Log.i("ShutdownService", "Intent Received");
            if (intent.getAction().compareTo("ro.mrs.nero.shutdownservice.SHUTDOWN") == 0) {
                Log.i("ShutdownService", "Poweroff phone");
                try {
                    Process proc = Runtime.getRuntime()
                            .exec(new String[]{ "su", "-c", "reboot -p" });
                    proc.waitFor();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            Toast.makeText(context, "Intent Received",
                    Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(context, "Intercepted", Toast.LENGTH_LONG).show();
        }
    }

}
