package com.example.aayum.courtentry;

import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

public class CaseNumSearch extends AppCompatActivity {
    DatabaseHelper mdb = new DatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case_num_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final EditText _client = findViewById(R.id.id);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = mdb.getCaseDataFromNumber(_client.getText().toString());
                if (res.getCount() == 0) {
                    // show message
                    showMessage("Error", "Nothing found");

                }
                else {

                    StringBuffer buffer = new StringBuffer();
                    while (res.moveToNext()) {
                        buffer.append("Id :" + res.getString(0) + "\n");
                        buffer.append("Case Number :" + res.getString(1) + "\n");
                        buffer.append("Title :" + res.getString(2) + "\n");
                        buffer.append("Last date of hearing :" + res.getString(3) + "\n");
                        buffer.append("Next Date of hearing :" + res.getString(4) + "\n\n");


                    }
                    showMessage("Data", buffer.toString());
                }

            }
        });
    }

    public void showMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}
