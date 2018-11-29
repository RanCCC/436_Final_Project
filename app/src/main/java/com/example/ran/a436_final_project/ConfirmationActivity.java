package com.example.ran.a436_final_project;

import android.Manifest;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class ConfirmationActivity extends AppCompatActivity {

    private TextView pageHeading;
    private TextView date;
    private TextView time;
    private TextView pickUp;
    private TextView dropOff;
    private TextView timeRemin;
    private TextView relRemin;
    private AlarmManager mAlarmManager;
    private PendingIntent mNotificationReceiverPendingIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);
        pageHeading = (TextView) findViewById(R.id.textView2);
        date = (TextView) findViewById(R.id.textView3);
        time = (TextView) findViewById(R.id.textView4);
        pickUp = (TextView) findViewById(R.id.textView5);
        dropOff = (TextView) findViewById(R.id.textView6);
        timeRemin = (TextView) findViewById(R.id.textView7);
        relRemin = (TextView) findViewById(R.id.textView8);
        Calendar cal = Calendar.getInstance();
        cal.set(2018, Calendar.NOVEMBER, 18, 16, 20);
        Intent mNotificationReceiverIntent = new Intent(ConfirmationActivity.this,
                AlarmNotificationReceiver.class);
        mNotificationReceiverPendingIntent = PendingIntent.getBroadcast(
                getApplicationContext(), 0, mNotificationReceiverIntent, 0);
        mAlarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        final int PERMISSION_REQUEST_CODE = 1;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.SEND_SMS)
                    == PackageManager.PERMISSION_GRANTED) {

                mAlarmManager.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), mNotificationReceiverPendingIntent);
                Toast.makeText(this, "Alarm Set For 10 min Before Departure",
                        Toast.LENGTH_LONG).show();
            }
        }
    }
}
