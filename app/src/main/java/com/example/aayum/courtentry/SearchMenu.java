package com.example.aayum.courtentry;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class SearchMenu extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        CardView fees,others;

        fees = findViewById(R.id.case_num);
        fees.setOnClickListener(this);
        others = findViewById(R.id.name);
        others.setOnClickListener(this);


    }

    @Override
    public void onBackPressed(){
        Intent i = new Intent(this,Main2Activity.class);
        startActivity(i);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.case_num: {
                Intent i = new Intent(this, feesSearch.class);
                i.putExtra("id", 1);
                startActivity(i);
                break;
            }

            case R.id.name: {
                Intent i = new Intent(this, OthersearchMenu.class);
                i.putExtra("id", 2);
                startActivity(i);
                break;
            }

        }
    }

}
