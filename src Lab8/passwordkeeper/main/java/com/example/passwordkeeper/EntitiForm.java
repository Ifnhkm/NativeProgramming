package com.example.passwordkeeper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EntitiForm extends AppCompatActivity {
    private Button addEntitiBtn;
    private EditText entitiEdt, usernameEdt, passwordEdt, descriptionEdt;
    private DBHandler dbHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entiti_form);

        //instantiates the DB Handler
        dbHandler = new DBHandler(EntitiForm.this);

        //associating data in View to variable in Coding
        addEntitiBtn = findViewById(R.id.btnAddEntiti);
        entitiEdt = findViewById(R.id.etEntiti);
        usernameEdt = findViewById(R.id.etUsername);
        passwordEdt = findViewById(R.id.etPassword);
        descriptionEdt = findViewById(R.id.etDescription);

        addEntitiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long rValue = dbHandler.addNewEntiti(entitiEdt.getText().toString(),usernameEdt.getText().toString(),passwordEdt.getText().toString(),descriptionEdt.getText().toString());
                if(rValue == -1) {
                    Toast.makeText(getApplicationContext(), "Data Not Added, Please Try Again!",
                            Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplicationContext(), "New Entiti Added!",
                            Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

}