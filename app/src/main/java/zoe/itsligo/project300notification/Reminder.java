package zoe.itsligo.project300notification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.nfc.Tag;

import androidx.core.app.NotificationCompat;
import androidx.core.app.ActivityManagerCompat;
import androidx.core.app.NotificationManagerCompat;



public class Reminder extends BroadcastReceiver {
    int cat = new Integer(MainActivity.Cnumber);
    @Override

    public void onReceive(Context context,Intent intent){

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, String.valueOf(cat))
        .setSmallIcon(R.drawable.ic_add_alert_balck_24dp)
        /*.setContentTitle(MainActivity.moduleTitle)
        .setContentText(MainActivity.channelDescription1)*/
        .setContentTitle(MainActivity.Ctag)
        .setContentText(MainActivity.Cdescription)
        .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
       notificationManagerCompat.notify(cat,builder.build());



    }


}
