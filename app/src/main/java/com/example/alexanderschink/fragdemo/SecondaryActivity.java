package com.example.alexanderschink.fragdemo;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class SecondaryActivity extends AppCompatActivity {

    private SharedPreferences preferences;
    private EditText celebNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondary);

        celebNames = (EditText) findViewById(R.id.celebNames);

    }

    @Override
    protected void onPause() {
        super.onPause();

        preferences = getSharedPreferences("values", MODE_PRIVATE);

        preferences.edit().putString("celebNames", String.valueOf(celebNames.getText())).apply();

        System.out.println(preferences.getString("celebNames","Verkackt"));

    }





}
