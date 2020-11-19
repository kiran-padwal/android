package com.example.shoppingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button manageProducts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manageProducts = (Button) findViewById(R.id.button);
        manageProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manageProductsActivity();
            }
        });
    }
    public void manageProductsActivity(){
        Intent intent = new Intent(this,ManageProducts.class);
        startActivity(intent);
    }
}