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
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {

    Button timeB;
    Button dateB;


    int hourOfDay1;
    int minute1;
    int day1;
    int month1;
    int year1;

private TextView mDisplayDate;
private TextView mDisplayTime;
private DatePickerDialog.OnDateSetListener onDateSetListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Date start
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
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();


            }
        });




        createNotificationChannel();

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
                    mDisplayDate.setText(day + "/" + monthDisplay + "/" + year);

            }
        };



        Button setReminder = (Button) findViewById(R.id.NotifyButton);
        setReminder.setOnClickListener( v -> {

            boolean validInput = false;

            String dispDate = mDisplayDate.getText().toString();
            String dispTime = mDisplayTime.getText().toString();

            //calander
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis());
            calendar.set(Calendar.YEAR, year1);
            calendar.set(Calendar.MONTH, month1);
            calendar.set(Calendar.DAY_OF_MONTH,day1);
            calendar.set(Calendar.HOUR_OF_DAY, hourOfDay1);
            calendar.set(Calendar.MINUTE, minute1);
            calendar.set(Calendar.SECOND, 0);

/*--------------------------------------------------------------------------*/
            Calendar time = Calendar.getInstance();
            Calendar date = Calendar.getInstance();
            Calendar c = Calendar.getInstance();
            time.set(Calendar.HOUR_OF_DAY,hourOfDay1);
            time.set(Calendar.MINUTE,minute1);
            date.set(Calendar.YEAR,year1);
            date.set(Calendar.MONTH,month1);
            date.set(Calendar.DAY_OF_MONTH,day1);


            if (dispDate.contains("Select date") || dispTime.contains("Select time"))
            {
                Toast.makeText( this , "You must select a time and date!" , Toast.LENGTH_SHORT).show();
            }
            else if(date.getTimeInMillis() == c.getTimeInMillis()){

                Toast.makeText( this , "its today" , Toast.LENGTH_SHORT).show();
                 if(time.getTimeInMillis() < c.getTimeInMillis())
                {
                    Toast.makeText( this , "Your time has passed" , Toast.LENGTH_SHORT).show();
                }
                 else
                 {
                     Toast.makeText( this , "Valid time" , Toast.LENGTH_SHORT).show();
                     validInput = true;
                 }

            }
            else if (date.getTimeInMillis() < c.getTimeInMillis())
            {
                Toast.makeText( this , "you want a time machine?" , Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText( this , "valid day/mouth/year" , Toast.LENGTH_SHORT).show();
                validInput = true;
            }

            if (validInput == true) {

                Toast.makeText(this, "Remind set!", Toast.LENGTH_SHORT).show();


                Intent intent = new Intent(MainActivity.this, Reminder.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, intent, 0);

                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);



                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                        AlarmManager.INTERVAL_DAY, pendingIntent);

            }

        });

    }

    @Override
    public void onTimeSet(TimePicker view , int hourOfDay, int minute) {
        hourOfDay1 = hourOfDay;
        minute1 = minute;

            mDisplayTime.setText(hourOfDay + ":" + minute);

    }

    private void createNotificationChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "project300ReminderChannel";
            String description = "Channel for Project300  reminder";
            int important = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("notifyLemubit", name, important);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }


    }

}