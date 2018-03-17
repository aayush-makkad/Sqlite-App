package com.example.aayum.courtentry;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.util.ArrayList;

public class Backup extends AppCompatActivity implements View.OnClickListener{

    Switch sw1;
    Button bck;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_backup);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
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


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Backup.this,Main2Activity.class);
                startActivity(i);
                finish();
            }
        });
    }



    public boolean backup()
    {

        File f = new File("/data/data/com.example.aayum.courtentry/databases/court3.db");
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try{

            fis = new FileInputStream(f);
            fos = new FileOutputStream("/sdcard/Android/data/com.example.aayum.courtentry/files/backup.db");
            while(true){
                int i = fis.read();
                if(i!=-1){
                    fos.write(i);
                }
                else
                    break;
            }

            fos.flush();
            Toast.makeText(this,"BackupCreated",Toast.LENGTH_LONG);
            return true;
        }
        catch (Exception ex){
            ex.printStackTrace();
            Toast.makeText(this,"sorry",Toast.LENGTH_LONG);

            return false;
        }
        finally {


            try {
                fos.close();
                fis.close();
            }
            catch (Exception ex){
                ex.printStackTrace();
            }
        }

    }


    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.btn :  {backup();
                                Toast.makeText(this,"Done",Toast.LENGTH_LONG);
                                break;}
                                
        }

    }
}
