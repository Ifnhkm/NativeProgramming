package com.example.passwordkeeper;

import static com.example.passwordkeeper.R.id.item1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<PakmadModal> pakMadModalArrayList;
    private DBHandler dbHandler;
    private ItemRVAdaptor itemRVAdapter;
    private RecyclerView itemsRV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initializing our all variable
        pakMadModalArrayList = new ArrayList<>();
        dbHandler = new DBHandler(MainActivity.this);

        // getting our course array
        // list from db handler class.
        pakMadModalArrayList = dbHandler.readPassTable();

        // on below line passing our array lost to our adapter class.
        itemRVAdapter = new ItemRVAdaptor(pakMadModalArrayList, this);
        itemsRV = findViewById(R.id.idRVItem);

        // setting layout manager for our recycler view.
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        itemsRV.setLayoutManager(linearLayoutManager);

        // setting our adapter to recycler view.
        itemsRV.setAdapter(itemRVAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.add_new, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        super.onOptionsItemSelected(item);

        switch (item.getItemId()) {
            case 1:
                Bundle dataBundle = new Bundle();
                dataBundle.putInt("id", 0);

                Intent intent = new Intent(MainActivity.this, EntitiForm.class);
                intent.putExtras(dataBundle);

                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}