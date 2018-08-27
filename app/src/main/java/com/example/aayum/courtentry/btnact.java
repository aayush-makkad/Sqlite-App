package com.example.aayum.courtentry;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class btnact extends AppCompatActivity {
    Button btn,show;
    DatabaseHelper mydb2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_btnact);
        btn = findViewById(R.id.click);
        show = findViewById(R.id.showall);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),Main2Activity.class);
                startActivity(i);
            }
        });
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showdata();
            }
        });


    }

    private void showdata() {
        new Thread(){
            public void run(){
                try{
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
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

                        }
                    });
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public void showMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

}
