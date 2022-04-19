package com.example.midterm2_leena;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    public static String idValue;
    public static String nameValue;
    public static String surnameValue;
    public static String natIdValue;
    public static EditText id;
    public static EditText name;
    public static EditText surname;
    public static EditText nationalID;
    int count =1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        id = (EditText) findViewById(R.id.editTextID);
        name = (EditText) findViewById(R.id.editTextName);
        surname = (EditText) findViewById(R.id.editSurname);
        nationalID = (EditText) findViewById(R.id.editNationalID);
        TextView result = (TextView) findViewById(R.id.disp_res);
        final Button add = (Button) findViewById(R.id.bttnAdd);
        final Button viewData = (Button) findViewById(R.id.buttonView);
        final Button deleteData = (Button) findViewById(R.id.buttonDelete);
        final Button searchData = (Button) findViewById(R.id.buttonSearch);
        final Button activity1 = (Button) findViewById(R.id.button);
        final Button activity3 = (Button) findViewById(R.id.button2);
        final DatabaseHelper db = new DatabaseHelper(this);

        idValue = id.getText().toString();
        nameValue = name.getText().toString();
        surnameValue = surname.getText().toString();
        natIdValue = nationalID.getText().toString();


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                idValue = id.getText().toString();
                nameValue = name.getText().toString();
                surnameValue = surname.getText().toString();
                natIdValue = nationalID.getText().toString();

                db.AddData( idValue, nameValue, surnameValue,natIdValue);
                Toast.makeText(MainActivity2.this, "Successful Add", Toast.LENGTH_LONG).show();
            }
        });

        viewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cursor cur = db.ViewData();
                StringBuffer buffer = new StringBuffer();

                while (cur.moveToNext()){

                    buffer.append("ID: "+ cur.getString(0) + "\n");
                    buffer.append("Name: "+ cur.getString(1) + "\n");
                    buffer.append("Email: "+ cur.getString(2) + "\n");
                    buffer.append("Phone: "+ cur.getString(3) + "\n\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity2.this);
                builder.setCancelable(true);
                builder.setTitle("All Data");
                builder.setMessage(buffer.toString());
                builder.show();

                Toast.makeText(MainActivity2.this, "Successful View", Toast.LENGTH_LONG).show();

            }
        });

        deleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                idValue = id.getText().toString();
                db.DeleteData(idValue);

                Toast.makeText(MainActivity2.this, "Successful Delete", Toast.LENGTH_LONG).show();

            }
        });

        searchData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                idValue = id.getText().toString();

                if (idValue.equals(""))
                    Toast.makeText(MainActivity2.this, "make sure the filed is filled", Toast.LENGTH_SHORT).show();

                else {
                    Cursor search = db.structuredQuery(idValue);

                    if (search.getCount() == 0){
                        Toast.makeText(MainActivity2.this, "no data found", Toast.LENGTH_SHORT).show();
                    }

                    else{
                        result.setText(""+search);
                        System.out.print(search);
                        Log.d("Leena",""+search.getString(0));
                        id.setText(search.getString(0));
                        name.setText(search.getString(1));
                        surname.setText(search.getString(2));
                        nationalID.setText(search.getString(3));
                        result.setText("Query: \nID: "+id.getText().toString()+
                                "\nName: "+name.getText().toString()+
                                "\nEmail: "+surname.getText().toString()+
                                "\nPhone: "+nationalID.getText().toString());
                    }
                }
            }
        });

        activity1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity2.this,MainActivity.class));
            }
        });

        activity3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity2.this,MainActivity3.class));
            }
        });

    }
}