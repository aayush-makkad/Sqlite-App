package com.example.aayum.courtentry;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class OthersearchMenu extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_othersearch_menu);
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


        CardView client_name,court_name,case_num,ndoh;

        client_name = findViewById(R.id.case_num);
        client_name.setOnClickListener(this);
        court_name = findViewById(R.id.name);
        court_name.setOnClickListener(this);
        case_num = findViewById(R.id.ldoh);
        case_num.setOnClickListener(this);
        ndoh = findViewById(R.id.ndoh);
        ndoh.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.case_num: {
                Intent i = new Intent(this, clientNameSearch.class);
                i.putExtra("id", 1);
                startActivity(i);
                break;
            }

            case R.id.name: {
                Intent i = new Intent(this, CourtNameSearch.class);
                i.putExtra("id", 2);
                startActivity(i);
                break;
            }

            case R.id.ldoh: {
                Intent i = new Intent(this, CaseNumSearch.class);
                i.putExtra("id", 1);
                startActivity(i);
                break;
            }

            case R.id.ndoh: {
                Intent i = new Intent(this, DohSearch.class);
                i.putExtra("id", 2);
                startActivity(i);
                break;
            }

        }
    }

}
