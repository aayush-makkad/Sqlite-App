package com.example.aayum.courtentry;

import android.Manifest;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;

public class ReminderActivity extends AppCompatActivity implements View.OnClickListener{

   static int day = 0;
    static int month = 0;
    static int year = 0;
    static int hour = 0;
    static int minutes = 0;
    static boolean isTimeSet = false;
    String description = null;

    public void setTime(int _day,int _month, int _year, int _hour,int _minutes){


        day = _day;
        month = _month;
        year = _year;
        hour = _hour;
        minutes = _minutes;
        isTimeSet = true;
    }






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Button btn = findViewById(R.id.btn);
        btn.setOnClickListener(this);

        final EditText data = findViewById(R.id.data);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //main code for setting reminder

                if(isTimeSet){


//                    getMiliDiff();
                    final int callbackid = 42;
                    checkPermissions(callbackid, Manifest.permission.READ_CALENDAR,Manifest.permission.WRITE_CALENDAR);
                    if(!data.getText().equals("")) {
                        description = data.getText().toString();
                        addReminderInCalendar();
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Enter data to enter", Toast.LENGTH_LONG).show();
                    }


                }



            }
        });
    }

    private void checkPermissions(int callbackId, String... permissionId){


        boolean permissions = true;
        for(String p : permissionId){

            permissions = permissions && ContextCompat.checkSelfPermission(this,p) == PERMISSION_GRANTED;

        }

        if(!permissions){
            ActivityCompat.requestPermissions(this,permissionId,callbackId);
        }
    }


    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.btn :  {



                new CustomDateTimePicker(this,
                        new CustomDateTimePicker.ICustomDateTimeListener() {
                            @Override
                            public void onSet(Dialog dialog, Calendar calendarSelected,
                                              Date dateSelected, int year,
                                              String monthFullName,
                                              String monthShortName,
                                              int monthNumber, int date,
                                              String weekDayFullName,
                                              String weekDayShortName, int hour24,
                                              int hour12,
                                              int min, int sec, String AM_PM) {

                           }

                            @Override
                            public void onCancel() {

                            }
                        }).set24HourFormat(true).setDate(Calendar.getInstance())
                        .showDialog();



                                break;}




            }

        }

        private Long getMiliDiff()
        {


            GregorianCalendar nextday = new GregorianCalendar(year,month,day,hour,minutes);
            Long nextmili = nextday.getTimeInMillis();
           return nextmili;
        }


    private void addReminderInCalendar() {
        Calendar cal = Calendar.getInstance();
        Uri EVENTS_URI = Uri.parse(getCalendarUriBase(true) + "events");
        ContentResolver cr = getContentResolver();
        TimeZone timeZone = TimeZone.getDefault();

        /** Inserting an event in calendar. */
        ContentValues values = new ContentValues();
        values.put(CalendarContract.Events.CALENDAR_ID, 1);
        values.put(CalendarContract.Events.TITLE, description);
        values.put(CalendarContract.Events.DESCRIPTION, description);
        values.put(CalendarContract.Events.ALL_DAY, 0);
        // event starts at 11 minutes from now
        values.put(CalendarContract.Events.DTSTART, getMiliDiff());
        // ends 60 minutes from now
        values.put(CalendarContract.Events.DTEND, getMiliDiff() + 2 * 60 * 1000);
        values.put(CalendarContract.Events.EVENT_TIMEZONE, timeZone.getID());
        values.put(CalendarContract.Events.HAS_ALARM, 1);
        Uri event = cr.insert(EVENTS_URI, values);

        // Display event id.
        Toast.makeText(getApplicationContext(), "Event added :: ID :: " + event.getLastPathSegment(), Toast.LENGTH_SHORT).show();

        /** Adding reminder for event added. */
        Uri REMINDERS_URI = Uri.parse(getCalendarUriBase(true) + "reminders");
        values = new ContentValues();
        values.put(CalendarContract.Reminders.EVENT_ID, Long.parseLong(event.getLastPathSegment()));
        values.put(CalendarContract.Reminders.METHOD, CalendarContract.Reminders.METHOD_ALERT);
        values.put(CalendarContract.Reminders.MINUTES, 10);
        cr.insert(REMINDERS_URI, values);
    }

    /** Returns Calendar Base URI, supports both new and old OS. */
    private String getCalendarUriBase(boolean eventUri) {
        Uri calendarURI = null;
        try {
            if (android.os.Build.VERSION.SDK_INT <= 7) {
                calendarURI = (eventUri) ? Uri.parse("content://calendar/") : Uri.parse("content://calendar/calendars");
            } else {
                calendarURI = (eventUri) ? Uri.parse("content://com.android.calendar/") : Uri
                        .parse("content://com.android.calendar/calendars");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return calendarURI.toString();
    }


    }


