package zoe.itsligo.project300notification;



import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import android.app.NotificationChannel;
import android.app.NotificationManager;

import java.util.ArrayList;

public class notificationListActivity extends AppCompatActivity {

    ListView notifiList;
    String value;
    Button deleteButton;

    String Ctag;
    String Cnumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_list);

        //deleteButton = (Button) findViewById(R.id.listDeleteB);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            value = extras.getString("alert1");
            Ctag = extras.getString("Ctag");
            Cnumber = extras.getString("Cnumber");
            //The key argument here must match that used in the other activity
        }

        notifiList = (ListView) findViewById(R.id.notifiLV);
        ArrayList<String> arrayList = new ArrayList<>();

        arrayList.add(value);
        arrayList.add("2");
        arrayList.add("3");
        arrayList.add("4");

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);

        notifiList.setAdapter(arrayAdapter);


        notifiList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                {

                    arrayAdapter.remove(arrayList.get(position));
                    arrayAdapter.notifyDataSetChanged();

                    //NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
                    // notificationManager.cancel(200);
                    //((MyApplication)getApplication()).cancelNotification(getResources().getInteger(R.integer.notificationId));
                    // cancelNotification();

                    // NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
                    // notificationManager.cancel(200);


                    //   NotificationManagerCompat.from(notificationListActivity.this).cancelAll();


                    //   dismissNotification(notificationListActivity.this,1);

                    //   NotificationManager notificationManager = getSystemService(NotificationManager.class);

                    // NotificationChannel channel = new NotificationChannel("channelID", channelID1, important);
                    // notificationManager.cancel("channelID", 200);
                    //  cancelNotification(200,"c1");
                    // handleActionDismiss();

                   /* NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
                    notificationManager.cancel(Ctag, Integer.parseInt(Cnumber)); */

                    removeNotification(Ctag, Integer.parseInt(Cnumber));

                    return false;
                }
            }
        });
    }
        /*public void cancelNotification(int id, String tag)
        {
            //you can get notificationManager like this:
            //notificationManage r= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            //you can get notificationManager like this:
            NotificationManager notificationManager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.cancel(tag, id);
        } */

    private void removeNotification(String tag,int id) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            {
                Toast.makeText(this, "made it here", Toast.LENGTH_SHORT).show();
                NotificationManager notificationManager = getSystemService(NotificationManager.class);
                notificationManager.cancelAll();

            }
        }
    }
}



  /*  public static void dismissNotification(Context ctx, int groupId) {
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(ctx);
        notificationManager.cancel(groupId);
    } */

     /*  private void cancelNotification() {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

                // CharSequence name = channelID1;
                //  String description = "Channel for Project300  reminder";
                Toast.makeText(this, "in cancel", Toast.LENGTH_SHORT).show();
                int important = NotificationManager.IMPORTANCE_DEFAULT;
               // NotificationChannel channel = new NotificationChannel("channelID", channelID1, important);
                //channel.setDescription(channelDescription1);

                NotificationManager notificationManager = getSystemService(NotificationManager.class);
                notificationManager.cancel(200,builder.build());
            }
        } */
    
