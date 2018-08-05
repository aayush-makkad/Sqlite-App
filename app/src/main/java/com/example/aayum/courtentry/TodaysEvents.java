package com.example.aayum.courtentry;

import android.annotation.TargetApi;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.text.SimpleDateFormat;

import java.util.ArrayList;

public class TodaysEvents extends AppCompatActivity {

    ListView ls;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todays_events);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        ls = findViewById(R.id.list);
        db = new DatabaseHelper(this);
        long date = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String dateString = sdf.format(date);
        ArrayList<String> ar = new ArrayList<String>();
        Cursor res = db.getTodaysData(dateString);


        while (res.moveToNext()) {

            ar.add("Client Name : " + res.getString(3) + "\n\nat Court : " + res.getString(9) + "\n\n");

        }

        String _channel = createNotificationChannel();
        createNotification(_channel,ar);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, android.R.id.text1, ar);
        ls.setAdapter(adapter);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(TodaysEvents.this, Main2Activity.class);
                startActivity(i);
                finish();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                Intent i = new Intent(TodaysEvents.this, Main2Activity.class);
                startActivity(i);
                finish();
                return true;
            }
            default:
                return super.onOptionsItemSelected(item);

        }
    }
    @TargetApi(26)
    public String createNotificationChannel(){
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        String channelId = "events";
        CharSequence channelName = "eve";
        int importance = NotificationManager.IMPORTANCE_HIGH;
        NotificationChannel notificationChannel = new NotificationChannel(channelId, channelName, importance);
        notificationChannel.enableLights(true);
        notificationChannel.setLightColor(Color.RED);
        notificationChannel.enableVibration(true);
        notificationChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
        notificationManager.createNotificationChannel(notificationChannel);
        return channelId;

    }
    @TargetApi(26)
    public void createNotification(String _channel,ArrayList<String> _data){
        int not_id = 1;
        Intent intent = new Intent(this, FingerprintForNotification.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, _channel)
                .setSmallIcon(R.drawable.download)
                .setContentTitle("Cases for today")
                .setContentText(_data.toString())
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText(_data.toString()))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingIntent).setSound(alarmSound);



        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

// notificationId is a unique int for each notification that you must define
        notificationManager.notify(not_id, mBuilder.build());

    }
}
