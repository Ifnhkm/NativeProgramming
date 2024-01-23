package com.example.passwordkeeper;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditEntitiForm extends AppCompatActivity {
    private EditText entiti1, username1, password1, description1;
    private Button updateButton,deleteButton;
    private DBHandler dbHandler;
    String entitiStr, usernameStr, passwordStr, descriptionStr;
    String oriEntiti="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_entiti_form);

        //initialize all variable
        entiti1 = findViewById(R.id.etEntitiEdt);
        username1 = findViewById(R.id.etUsernameEdt);
        password1 = findViewById(R.id.etPasswordEdt);
        description1 = findViewById(R.id.etDescriptionEdt);
        updateButton = findViewById(R.id.btnUpdateEntiti);
        deleteButton = findViewById(R.id.btnDeleteEntiti);

        dbHandler = new DBHandler(EditEntitiForm.this);

        //get data from intent
        entitiStr = getIntent().getStringExtra("entitiShr");
        usernameStr = getIntent().getStringExtra("usernameShr");
        passwordStr = getIntent().getStringExtra("passwordShr");
        descriptionStr = getIntent().getStringExtra("descriptionShr");
        oriEntiti=entitiStr;
        //pass data to edit form
        entiti1.setText(entitiStr);
        username1.setText(usernameStr);
        password1.setText(passwordStr);
        description1.setText(descriptionStr);

        //update process
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int rValue = dbHandler.updateEntiti(entitiStr, entiti1.getText().toString(), username1.getText().toString(), password1.getText().toString(), description1.getText().toString());
                if (rValue > 0) {
                    Toast.makeText(getApplicationContext(), "Data updated Successfully!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "No Data updated!", Toast.LENGTH_SHORT).show();
                }
                Intent intent = new Intent(EditEntitiForm.this, MainActivity.class); // Change here
                startActivity(intent);
            }
        });


        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(EditEntitiForm.this);
                builder.setMessage("Are you sure, you want to delete it?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                int rValue = dbHandler.deleteEntiti(entitiStr);
                                if (rValue > 0){
                                    Toast.makeText(getApplicationContext(), "Deleted Successfully",
                                            Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(getApplicationContext(), "No Entiti Deleted",
                                            Toast.LENGTH_SHORT).show();
                                }
                                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // User cancelled the dialog
                            }
                        });

                AlertDialog d = builder.create();
                d.setTitle("Are you sure");
                d.show();
            }
        });
    }
}