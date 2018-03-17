package com.example.aayum.courtentry;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DeleteActivity extends AppCompatActivity {
    EditText id_del;
    Button del;
    DatabaseHelper mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        id_del = (EditText)findViewById(R.id.id_num);
        del = (Button)findViewById(R.id.del);
        mydb = new DatabaseHelper(this);
        DeleteData();
    }
    public void DeleteData() {
        del.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer deletedRows = mydb.deleteData(id_del.getText().toString());
                        if(deletedRows > 0)
                            Toast.makeText(DeleteActivity.this,"Data Deleted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(DeleteActivity.this,"Data not Deleted",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
}
