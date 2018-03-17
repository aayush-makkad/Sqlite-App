package com.example.aayum.courtentry;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    EditText case_num, case_name, title, last, next, total, fees,number,court_name;
    Button add;
    DatabaseHelper mydb2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        case_num = (EditText) findViewById(R.id.case_num);
        case_name = (EditText) findViewById(R.id.case_name);
        title = (EditText) findViewById(R.id.title);
        last = (EditText) findViewById(R.id.last_date);
        next = (EditText) findViewById(R.id.next_date);
        total = (EditText) findViewById(R.id.total_fees);
        fees = (EditText) findViewById(R.id.fees);
        add = (Button) findViewById(R.id.save);
        number = (EditText)findViewById(R.id.phone);
        court_name=(EditText)findViewById(R.id.c_name);
        mydb2 = new DatabaseHelper(this);
        AddData();


//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    public void AddData() {
        add.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = mydb2.insertData(case_num.getText().toString(),
                                case_name.getText().toString(), title.getText().toString(), last.getText().toString(), next.getText().toString(), total.getText().toString(), fees.getText().toString(),number.getText().toString(),court_name.getText().toString());
                        if (isInserted == true) {
                            Toast.makeText(Main2Activity.this, "Data Inserted", Toast.LENGTH_LONG).show();
                            case_num.setText("");
                            case_name.setText("");
                            title.setText("");
                            last.setText("");
                            next.setText("");
                            total.setText("");
                            fees.setText("");
                            number.setText("");
                        }
                        else
                            Toast.makeText(Main2Activity.this, "Data not Inserted", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void showMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            Cursor res = mydb2.getAllData();
            if (res.getCount() == 0) {
                // show message
                showMessage("Error", "Nothing found");

            }

            StringBuffer buffer = new StringBuffer();
            while (res.moveToNext()) {
                buffer.append("Id :" + res.getString(0) + "\n");
                buffer.append("Case Number :" + res.getString(1) + "\n");
                buffer.append("Client Name :" + res.getString(2) + "\n");
                buffer.append("Title :" + res.getString(3) + "\n");
                buffer.append("Last date of hearing :" + res.getString(4) + "\n");
                buffer.append("Next Date of hearing :" + res.getString(5) + "\n");
                buffer.append("Total fees:" + res.getString(6) + "\n");
                buffer.append("Fees paid :" + res.getString(7) + "\n");
                buffer.append("Mobile number :" + res.getString(8) + "\n");
                buffer.append("Court name:" + res.getString(9) + "\n\n");

            }

            // Show all data
            showMessage("Data", buffer.toString());

            // Handle the camera action
        }  else if (id == R.id.nav_slideshow) {
            Intent i = new Intent(Main2Activity.this,DeleteActivity.class);
            startActivity(i);
        }
         else if (id == R.id.nav_update) {

            //update activity starts

            Intent i = new Intent(Main2Activity.this,updateActivity.class);
            startActivity(i);


        }
        else if (id == R.id.nav_client) {
            //client details

            Cursor res = mydb2.getAllData();
            if (res.getCount() == 0) {
                // show message
                showMessage("Error", "Nothing found");

            }

            StringBuffer buffer = new StringBuffer();
            while (res.moveToNext()) {
                buffer.append("Id :" + res.getString(0) + "\n");
                buffer.append("Name :" + res.getString(2) + "\n");
                buffer.append("Total fees:" + res.getString(6) + "\n");
                buffer.append("Fees paid :" + res.getString(7) + "\n");
                buffer.append("Mobile number :" + res.getString(8) + "\n\n");
                
            }

            // Show all data
            showMessage("Data", buffer.toString());



        }
//
        else if (id == R.id.nav_diary) {

            Cursor res = mydb2.getAllData();
            if (res.getCount() == 0) {
                // show message
                showMessage("Error", "Nothing found");

            }

            StringBuffer buffer = new StringBuffer();
            while (res.moveToNext()) {
                buffer.append("Id :" + res.getString(0) + "\n");
                buffer.append("Case Number :" + res.getString(1) + "\n");
                buffer.append("Title :" + res.getString(3) + "\n");
                buffer.append("Last date of hearing :" + res.getString(4) + "\n");
                buffer.append("Next Date of hearing :" + res.getString(5) + "\n\n");


            }

            // Show all data
            showMessage("Data", buffer.toString());


        }
        else if(id == R.id.nav_today){
            Intent i = new Intent(Main2Activity.this,TodaysEvents.class);
            startActivity(i);
            finish();
        }
        else if(id == R.id.nav_backup){
            Intent i = new Intent(Main2Activity.this,Backup.class);
            startActivity(i);
            finish();
        }

            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;
        }
    }

