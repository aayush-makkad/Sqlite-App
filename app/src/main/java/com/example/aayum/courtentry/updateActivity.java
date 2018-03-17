package com.example.aayum.courtentry;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class updateActivity extends AppCompatActivity implements View.OnClickListener {
CardView case_num,name,ldoh,ndoh,total,paid,court,phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        case_num = findViewById(R.id.case_num);
        case_num.setOnClickListener(this);
        name = findViewById(R.id.name);
        name.setOnClickListener(this);
        ldoh = findViewById(R.id.ldoh);
        ldoh.setOnClickListener(this);
        ndoh = findViewById(R.id.ndoh);
        ndoh.setOnClickListener(this);
        total = findViewById(R.id.total);
        total.setOnClickListener(this);
        court = findViewById(R.id.court);
        court.setOnClickListener(this);
        paid = findViewById(R.id.paid);
        paid.setOnClickListener(this);
        phone = findViewById(R.id.phone);
        phone.setOnClickListener(this);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(updateActivity.this,Main2Activity.class);
                startActivity(i);
                finish();
            }
        });
    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.case_num : {
                Intent i = new Intent(updateActivity.this,updater.class);
            i.putExtra("id",1);
            startActivity(i); break; }

            case R.id.name :  {
                Intent i = new Intent(updateActivity.this,updater.class);
                i.putExtra("id",2);
                startActivity(i); break; }

            case R.id.total :  {
                Intent i = new Intent(updateActivity.this,updater.class);
                i.putExtra("id",3);
                startActivity(i); break; }

            case R.id.paid :  {
                Intent i = new Intent(updateActivity.this,updater.class);
                i.putExtra("id",4);
                startActivity(i); break; }

            case R.id.ldoh :  {
                Intent i = new Intent(updateActivity.this,updater.class);
                i.putExtra("id",5);
                startActivity(i); break; }

            case R.id.ndoh :  {
                Intent i = new Intent(updateActivity.this,updater.class);
                i.putExtra("id",6);
                startActivity(i); break; }

            case R.id.court :  {
                Intent i = new Intent(updateActivity.this,updater.class);
                i.putExtra("id",7);
                startActivity(i); break; }

            case R.id.phone :  {
                Intent i = new Intent(updateActivity.this,updater.class);
                i.putExtra("id",8);
                startActivity(i); break; }

            default:  {
                Intent i = new Intent(updateActivity.this,updater.class);
                i.putExtra("id",0);
                startActivity(i); break; }

        }
    }
}
