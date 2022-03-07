package zoe.itsligo.project300notification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.DialogFragment;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {

    Button timeB;
    Button dateB;


    int hourOfDay1;
    int minute1;
    int day1;
    int month1;
    int year1;

    int Rnum;

    private TextView descInput;
    private TextView mDisplayDate;
    private TextView mDisplayTime;
    private DatePickerDialog.OnDateSetListener onDateSetListener;

    /*---------------------------------------------------------------------*/
    /*public static final String channelID1 = "200";
    public static final int channelID1no = 200;
    public static String channelDescription1 = null;
    public static final String moduleTitle = "RAD";*/
  //  public static String channelDescription1 = null;
   // public static final String moduleTitle = "RAD";
    //public static final int NOTIFICATION_ID = 200;

    //private static final String TAG = "YourNotification";
    //private static final int NOTIFICATION_ID = 200;
    /*---------------------------------------------------------------------*/
   /* ArrayList<notificationClass> notifiList=new ArrayList<notificationClass>();
    notificationClass c1=new notificationClass(200,"BaseChannel");*/
    ArrayList<notificationClass> notifiList=new ArrayList<notificationClass>();
    int  lastScore, best1,best2,best3,best4,best5;


    //public static String ModuleCtag ;
    public static String Cdescription ;
    public static String Ctag ;
    public static String Cnumber ;
   // public static String Moduale ;
/*----------------------------------------------------
    public static String Ctag = "c1";
    public static String Cnumber = "200";
 ----------------------------------------------------*/


   // public static CharSequence Ctag = "c1";
   // public static int Cnumber ;

    String NotifiDate ;
    String NotifiTime ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Date start
        descInput = (EditText) findViewById(R.id.ETdescription);
        mDisplayTime = (TextView) findViewById(R.id.ETtime);
        mDisplayDate = (TextView) findViewById(R.id.ETdate);

        dateB = (Button) findViewById(R.id.DateButton);
        dateB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //gets current date and has it set when open picker
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        MainActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        onDateSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();


            }

        });


       // createNotificationChannel();
        ChannelNumber();


        timeB = (Button) findViewById(R.id.TimeButton);
        timeB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");


            }

        });




        onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                day1 = day;
                month1 = month;
                year1 = year;
                int monthDisplay = month + 1;
                NotifiDate =  day + "/" + monthDisplay + "/" + year;
                mDisplayDate.setText(NotifiDate);

            }
        };

        //button1 :
        Button setReminder = (Button) findViewById(R.id.NotifyButton);
        setReminder.setOnClickListener(v -> {

            String name =  descInput.getText().toString();
            Ctag = "button1-module1";
                Cdescription = name;
                Toast.makeText(this, Cdescription, Toast.LENGTH_SHORT).show();
                TimeCheck();


        });

        //button2 :
        Button setReminder2 = (Button) findViewById(R.id.Notify2Button);
        setReminder2.setOnClickListener(v -> {

            Ctag = "button2-module2";
            String name =  descInput.getText().toString();
            Cdescription = name;
            Toast.makeText(this, Cdescription, Toast.LENGTH_SHORT).show();


            TimeCheck();

        });


    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        hourOfDay1 = hourOfDay;
        minute1 = minute;
        NotifiTime = hourOfDay + ":" + minute;
        mDisplayTime.setText(NotifiTime);

    }

    public void TimeCheck() {
        boolean validInput = false;

        String dispDate = mDisplayDate.getText().toString();
        String dispTime = mDisplayTime.getText().toString();

        //calander
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.YEAR, year1);
        calendar.set(Calendar.MONTH, month1);
        calendar.set(Calendar.DAY_OF_MONTH, day1);
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay1);
        calendar.set(Calendar.MINUTE, minute1);
        calendar.set(Calendar.SECOND, 0);

        /*--------------------------------------------------------------------------*/
        Calendar time = Calendar.getInstance();
        Calendar date = Calendar.getInstance();
        Calendar c = Calendar.getInstance();
        time.set(Calendar.HOUR_OF_DAY, hourOfDay1);
        time.set(Calendar.MINUTE, minute1);
        date.set(Calendar.YEAR, year1);
        date.set(Calendar.MONTH, month1);
        date.set(Calendar.DAY_OF_MONTH, day1);


        if (dispDate.contains("Select date") || dispTime.contains("Select time")) {
            Toast.makeText(this, "You must select a time and date!", Toast.LENGTH_SHORT).show();
        } else if (date.getTimeInMillis() == c.getTimeInMillis()) {

            Toast.makeText(this, "its today", Toast.LENGTH_SHORT).show();
            if (time.getTimeInMillis() < c.getTimeInMillis()) {
                Toast.makeText(this, "Your time has passed", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Valid time", Toast.LENGTH_SHORT).show();
                validInput = true;
            }

        } else if (date.getTimeInMillis() < c.getTimeInMillis()) {
            Toast.makeText(this, "you want a time machine?", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "valid day/mouth/year", Toast.LENGTH_SHORT).show();
            validInput = true;
        }

        if (validInput == true) {

            Toast.makeText(this, "Remind set!", Toast.LENGTH_SHORT).show();


            Intent intent = new Intent(MainActivity.this, Reminder.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, intent, 0);

            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);


            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                    AlarmManager.INTERVAL_DAY, pendingIntent);

            //activity link
            createNotificationChannel();

            String notification =  Ctag  +"   "+ NotifiTime +"   "+ NotifiDate + "  "+Cnumber;

            Intent i = new Intent(MainActivity.this, notificationListActivity.class);
            i.putExtra("alert1",notification);
            i.putExtra("Ctag",Ctag);
            i.putExtra("Cnumber",Cnumber);

            startActivity(i);

        }
    }

    public void ChannelNumber() {
        HashSet hs=new HashSet();
        while(hs.size()<1){
            int num=(int)(Math.random()*10000);
            Object Rnumbers = null;
            hs.add(num);
            Iterator it=hs.iterator();
            while(it.hasNext()) {

                Rnumbers = it.next();
            }
            int Rnum = (Integer) Rnumbers;
            channelDetails(Rnum);

        }
    }

  /*  Iterator it=hs.iterator();

while(it.hasNext()) {

    System.out.println(it.next()); */


   public void channelDetails(int num) {

       Ctag = "c1";
       //Cnumber = "200";
       Cnumber = String.valueOf(num);
       TimeCheck();
      // createNotificationChannel();
    }


    private void createNotificationChannel() {



        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
           // CharSequence name = channelID1;
          //  String description = "Channel for Project300  reminder";
         //   notificationClass[] notificationList;


            int important = NotificationManager.IMPORTANCE_DEFAULT;
          // NotificationChannel channel = new NotificationChannel("c1.notificationNumber",c1.notificationTag,important);
          // NotificationChannel channel = new NotificationChannel( "TAG",NOTIFICATION_ID,important);

            NotificationChannel channel = new NotificationChannel(Cnumber,Ctag,important);

            channel.setDescription(Cdescription);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);


        }
    }

}