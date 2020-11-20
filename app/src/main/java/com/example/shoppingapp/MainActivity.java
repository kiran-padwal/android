package com.example.shoppingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.graphics.Color;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static MainActivity mainActivity;
    Button navToList;
    Button navToOption;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initSharedPref();
        mainActivity=this;
    }

    public static MainActivity getMainActivity(){
        return mainActivity;
    }
    public void onNavToListClick(View v){
        Intent myIntent = new Intent(getBaseContext(), ListActivity.class);
        startActivity(myIntent);
    }

    public void onNavToOptionClick(View v){
        Intent myIntent = new Intent(getBaseContext(), OptionActivity.class);
        startActivity(myIntent);
    }

    public void initSharedPref(){
        SharedPreferences settings = getSharedPreferences("font_settings", Context.MODE_PRIVATE);
        int fontSize = settings.getInt("font_size",16);
        String fontColor = settings.getString("font_color", "3ed2f2");

        navToList = findViewById(R.id.navToList);
        navToOption = findViewById(R.id.navToOption);

        navToList.setTextSize(fontSize);
        navToList.setTextColor(Color.parseColor("#"+fontColor));

        navToOption.setTextSize(fontSize);
        navToOption.setTextColor(Color.parseColor("#"+fontColor));
    }
}