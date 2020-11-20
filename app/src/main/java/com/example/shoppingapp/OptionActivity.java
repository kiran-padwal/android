package com.example.shoppingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class OptionActivity extends AppCompatActivity {

    Spinner spFontSize;
    Spinner spFontColor;
    TextView fontTxt;
    TextView fontSizeTxt;
    TextView fontColorTxt;
    Button saveBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);

        spFontSize = findViewById(R.id.spFontSize);
        spFontColor = findViewById(R.id.spFontColor);
        fontTxt = findViewById(R.id.fontTxt);
        fontSizeTxt = findViewById(R.id.fontSize);
        fontColorTxt = findViewById(R.id.fontColor);
        saveBtn = findViewById(R.id.saveBtn);
        ArrayAdapter<CharSequence> dataAdapter = ArrayAdapter.createFromResource(this,R.array.font_size, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> dataAdapter2 = ArrayAdapter.createFromResource(this,R.array.font_color, android.R.layout.simple_spinner_item);


        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spFontSize.setAdapter(dataAdapter);

        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spFontColor.setAdapter(dataAdapter2);

        SharedPreferences settings = getSharedPreferences("font_settings", Context.MODE_PRIVATE);
        int fontSize = settings.getInt("font_size",16);
        String fontColor = settings.getString("font_color", "3ed2f2");

        fontTxt.setTextSize(fontSize);
        fontTxt.setTextColor(Color.parseColor("#"+fontColor));

        fontSizeTxt.setTextSize(fontSize);
        fontSizeTxt.setTextColor(Color.parseColor("#"+fontColor));

        fontColorTxt.setTextSize(fontSize);
        fontColorTxt.setTextColor(Color.parseColor("#"+fontColor));

        saveBtn.setTextSize(fontSize);
        saveBtn.setTextColor(Color.parseColor("#"+fontColor));
    }

    public void onSaveFontSettings(View v){
        String fontSize = spFontSize.getSelectedItem().toString();
        String fontColor = spFontColor.getSelectedItem().toString();

        SharedPreferences optionSettings =getSharedPreferences("font_settings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = optionSettings.edit();

        switch(fontSize) {
            case "Small" :
                editor.putInt("font_size", 16);
                editor.apply();
                break;
            case "Normal" :
                editor.putInt("font_size", 22);
                editor.apply();
                break;
            case "Big" :
                editor.putInt("font_size", 28);
                editor.apply();
                break;
            case "Huge" :
                editor.putInt("font_size", 32);
                editor.apply();
                break;
            default :

        }

        switch(fontColor) {
            case "Black" :
                editor.putString("font_color", "1B32B3");
                editor.apply();
                break;
            case "Green" :
                editor.putString("font_color", "259129");
                editor.apply();

                break;
            case "Red" :
                editor.putString("font_color", "D81B60");
                editor.apply();

                break;
            case "Yellow" :
                editor.putString("font_color", "FFEB3B");
                editor.apply();

                break;
            case "Pink" :
                editor.putString("font_color", "D586BA");
                editor.apply();

                break;
            case "Navy" :
                editor.putString("font_color", "141E49");
                editor.apply();

                break;

            default :
        }

        startActivity(new Intent(OptionActivity.this, MainActivity.class));
    }



}