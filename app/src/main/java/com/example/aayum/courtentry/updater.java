package com.example.aayum.courtentry;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class updater extends AppCompatActivity {


    EditText id,content;
    Button btn;
    DatabaseHelper mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updater);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Bundle b = getIntent().getExtras();
        final int pass = b.getInt("id");
        id = findViewById(R.id.id);
        content = findViewById(R.id.upcontent);
//        btn = findViewById(R.id.updatethis);
//        btn.setOnClickListener(this);
        mydb = new DatabaseHelper(this);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    if(pass == 1) {

                        if (mydb.updateCaseNum(id.getText().toString(), content.getText().toString()))
                            Snackbar.make(view, "Done", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        else
                            Snackbar.make(view, "Not done", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                    }
                    else if(pass == 2)
                    {

                        if (mydb.updateClientName(id.getText().toString(), content.getText().toString()))
                            Snackbar.make(view, "Done", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        else
                            Snackbar.make(view, "Not done", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();

                    }
                    else if(pass == 3)
                    {
                        if (mydb.updateTotalFees(id.getText().toString(), content.getText().toString()))
                            Snackbar.make(view, "Done", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        else
                            Snackbar.make(view, "Not done", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                    }
                    else if(pass ==4){

                        if (mydb.updatePaidFees(id.getText().toString(), content.getText().toString()))
                            Snackbar.make(view, "Done", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        else
                            Snackbar.make(view, "Not done", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                    }
                    else if(pass == 5) {

                        if (mydb.updateldoh(id.getText().toString(), content.getText().toString()))
                            Snackbar.make(view, "Done", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        else
                            Snackbar.make(view, "Not done", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();

                    }
                    else if(pass == 6)
                    {
                        if (mydb.updatendoh(id.getText().toString(), content.getText().toString()))
                            Snackbar.make(view, "Done", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        else
                            Snackbar.make(view, "Not done", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                    }
                    else if(pass == 7)
                    {
                        if (mydb.updatecourt(id.getText().toString(), content.getText().toString()))
                            Snackbar.make(view, "Done", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        else
                            Snackbar.make(view, "Not done", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();

                    }

                    else if(pass == 8)
                    {
                        if (mydb.updatephone(id.getText().toString(), content.getText().toString()))
                            Snackbar.make(view, "Done", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        else
                            Snackbar.make(view, "Not done", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();

                    }
                    else
                    {
                        Snackbar.make(view, "wrong move", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }

            }
        });
    }


        }



