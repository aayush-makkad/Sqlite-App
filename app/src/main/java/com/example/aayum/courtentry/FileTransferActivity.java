package com.example.aayum.courtentry;

import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.format.Formatter;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class FileTransferActivity extends AppCompatActivity {
    EditText ipc,portc;
    FileSocket fs = new FileSocket();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_transfer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView ipd,portd;

        ipd = findViewById(R.id.textViewfirst);


        WifiManager wm = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
        String ip = Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress());
        ipd.setText(ip);

        portd = findViewById(R.id.textView0);
        portd.setText("4444");

        ipc=findViewById(R.id.id);

        portc = findViewById(R.id.id2);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);

        //  portc = findViewById(R.id.textView1);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // transfer code
                if(ipc.getText().toString().length()>0) {
                    boolean success = fs.filetrans(ipc.getText().toString(), 4444);

                }

            }
        });
    }

}
