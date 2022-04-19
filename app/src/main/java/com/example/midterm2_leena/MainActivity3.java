package com.example.midterm2_leena;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity3 extends AppCompatActivity {
    DatabaseHelper db;
    String idValue;
    int count =1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        Button delete=(Button)findViewById(R.id.bttnDelete);
        Button view=(Button)findViewById(R.id.buttonView);
        EditText id_val=(EditText)findViewById(R.id.editTextId);
        Button bttnAct2=(Button)findViewById(R.id.bttnact2);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MainActivity2.idValue =id_val.getText().toString();
                db.DeleteData(MainActivity2.idValue);

                Toast.makeText(MainActivity3.this,"Successful Delete",Toast.LENGTH_LONG).show();
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cursor cur = db.ViewData();
                StringBuffer buffer = new StringBuffer();

                while (cur.moveToNext()){

                    buffer.append("ID: "+ cur.getString(0) + "\n");
                    buffer.append("Name: "+ cur.getString(1) + "\n");
                    buffer.append("Email: "+ cur.getString(2) + "\n\n");
                    buffer.append("Phone: "+ cur.getString(3) + "\n\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity3.this);
                builder.setCancelable(true);
                builder.setTitle("All Data");
                builder.setMessage(buffer.toString());
                builder.show();

                Toast.makeText(MainActivity3.this, "Successful View", Toast.LENGTH_LONG).show();

            }
        });


        bttnAct2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity3.this,MainActivity2.class));
            }
        });


    }
}