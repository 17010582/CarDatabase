package com.charlottechia.cardatabase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etBrand, etLitre;
    Button btnInsert, btnRetrieve;
    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etBrand = findViewById(R.id.etBrand);
        etLitre = findViewById(R.id.etLitre);
        tvResult = findViewById(R.id.tvResult);
        btnInsert = findViewById(R.id.btnInsert);
        btnRetrieve = findViewById(R.id.btnRetrieve);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DBHelper db = new DBHelper(MainActivity.this);

                // Insert a task
                db.insertCar(etBrand.getText().toString(), Double.parseDouble(etLitre.getText().toString()));
                db.close();
                Toast.makeText(MainActivity.this, "Car inserted", Toast.LENGTH_SHORT).show();
            }
        });
        btnRetrieve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DBHelper db = new DBHelper(MainActivity.this);

                // Insert a task
                ArrayList<Car> data = db.getCar();
                db.close();

                String txt = "";
                for (int i = 0; i < data.size(); i++){
                    Log.d("Database Content",i + ". " + data.get(i));
                    txt += "Car: " + data.get(i).getId() + "\nBrand: " + data.get(i).getBrand() + "\nLitre: " + data.get(i).getLitre() + "\n";

                }
                tvResult.setText(txt);

            }
        });
    }
}
