package com.example.odhiambo.labone;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DatabaseHandler db = new DatabaseHandler(this);

        Log.d("Insert:","Inserting..");
        db.addStudents (new Students ("312","Ombogo","4A"));
        db.addStudents (new Students ("312","Janet","3A"));
        db.addStudents (new Students("312","Steve","2A"));
        db.addStudents (new Students ("312","Job","1A"));

        Log.d("Reading:", "Reading all students..");
        List<Students> Students = db.getAllStudents ();
        for (Students cn : Students) {
            String log = "Id" + cn.get_id() + " ,Name: " + cn.get_name() + ",Class Id:" + cn.get_class_id();

            Log.d("Name:", log);
        }

        }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
