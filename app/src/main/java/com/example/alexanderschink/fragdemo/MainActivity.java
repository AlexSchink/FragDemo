package com.example.alexanderschink.fragdemo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences preferences;
    private ArrayAdapter<String> names;
    private ImageView celebView;
    private GridView celebOptions;
    private String[] namesArray;
    private int name;
    private String guess;

    private static final Random randomGen = new Random();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        namesArray = new String[6];
        namesArray[0] = "Snape";
        namesArray[1] = "Harry";
        namesArray[2] = "Ron";
        namesArray[3] = "Hermine";
        namesArray[4] = "Dobby";
        namesArray[5] = "Hagrid";


        names = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);

        celebOptions = (GridView) findViewById(R.id.celebOptions);
        celebOptions.setAdapter(names);


        celebView = (ImageView) findViewById(R.id.celebView);

    }

    @Override
    protected void onStart() {
        super.onStart();
        preferences = getSharedPreferences("values", MODE_PRIVATE);


        name = randomGen.nextInt(6) + 1;
        String imgNumber = "img" + name;
        int imgID = getResources().getIdentifier(imgNumber, "drawable", getPackageName());
        celebView.setImageResource(imgID);


        names.clear();

        names.add(namesArray[name]);
        System.out.println("Test");

        int aux = Integer.parseInt(preferences.getString("celebNames", "1")) - 1;
        System.out.println(aux);
        System.out.println("Test2");

        for (int i = 0; i < aux; i++) {

            int name2 = randomGen.nextInt(6) + 1;
            names.add((namesArray[name2]));
        }


        celebOptions.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


               guess = names.getItem(position);
                System.out.println(guess);
              String answer = namesArray[name];

               if (guess == answer) {

                     Toast.makeText(MainActivity.this, "Correct!", Toast.LENGTH_SHORT).show();
                     onStart();
                } else {
                     Toast.makeText(MainActivity.this, "Try again.", Toast.LENGTH_SHORT).show();

                }
            }


        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {

            getMenuInflater().inflate(R.menu.menu_main, menu);
        }
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
            Intent intent = new Intent (this, SecondaryActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        preferences = getSharedPreferences("values", MODE_PRIVATE);
        preferences.edit().remove("celebOptions").commit();
    }
}
