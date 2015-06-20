package com.example.ktaylor.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class dataBaseStorage extends ActionBarActivity {

    SharedPreferences savedInfo;
    int count = 0;
    TextView countText;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        savedInfo = this.getPreferences(Context.MODE_PRIVATE);
        count = savedInfo.getInt("test",count);
        
        countText = (TextView) findViewById(R.id.textView);
        countText.setText(count + "");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //advance the textview by one
    public void advance(View view) {
        countText.setText(++count + "");
    }

    public void save(View view) {
        savedInfo.edit().putInt("test",count).commit();
    }
}
