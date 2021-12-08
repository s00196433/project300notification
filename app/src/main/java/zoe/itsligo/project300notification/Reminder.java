package zoe.itsligo.project300notification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.ActivityManagerCompat;
import androidx.core.app.NotificationManagerCompat;


public class Reminder extends BroadcastReceiver {
    @Override
    public void onReceive(Context context,Intent intent){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context,"notifyLemubit" )
        .setSmallIcon(R.drawable.ic_add_alert_balck_24dp)
        .setContentTitle("Project300 Reminder")
        .setContentText("Hello from project 300...")
        .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        notificationManagerCompat.notify(200,builder.build());


    }
}
