package com.example.aayum.courtentry;

import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class RestoreData extends AppCompatActivity implements View.OnClickListener {

    Switch sw1;
    Button bck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restore_data);
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

        sw1 = findViewById(R.id.switch1);
        bck = findViewById(R.id.btn);
        bck.setVisibility(View.INVISIBLE);
        if(sw1!=null){
            sw1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked){
                        bck.setVisibility(View.VISIBLE);
                    }
                    else
                        bck.setVisibility(View.INVISIBLE);
                }
            });
        }


        bck.setOnClickListener(this);
    }

    public void restore(){

        File fin = new File(Environment.getExternalStorageDirectory().getPath()+"/Android/data/com.example.aayum.courtentry/files/backup.db");
        FileInputStream fi1 = null;
        File fout = new File("/data/data/com.example.aayum.courtentry/databases/court3.db");
        FileOutputStream fo1 = null;
        try {

            fi1 = new FileInputStream(fin);
            fo1 = new FileOutputStream(fout);

            while(true){
                int i = fi1.read();
                if(i!=-1){
                    fo1.write(i);
                }
                else
                    break;
            }

            fo1.flush();
            Toast.makeText(this,"Restored",Toast.LENGTH_LONG).show();
            fi1.close();
            fo1.close();

        }catch (Exception e){
            Toast.makeText(this,e.toString(),Toast.LENGTH_LONG).show();
        }


    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.btn :  {
                restore();
                Toast.makeText(this,"Done",Toast.LENGTH_LONG).show();
                break;}

        }



    }

}
