package com.example.shoppingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class NewProductActivity extends AppCompatActivity {

    DatabaseHelper mDatabaseHelper;
    private Button newBtn;
    private EditText product_nameTxt, priceTxt, quantityTxt;
    private TextView textView, textView2, textView3, textView4;
    private CheckBox broughtCX;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_product);

//        SharedPreferences settings = getSharedPreferences("font_settings", Context.MODE_PRIVATE);
//        int fontSize = settings.getInt("font_size",16);
//        String fontColor = settings.getString("font_color", "Blue");

        product_nameTxt = findViewById(R.id.product_name);
        priceTxt = findViewById(R.id.price);
        quantityTxt = findViewById(R.id.quantity);
        broughtCX = findViewById(R.id.broughtCheckBox);
        newBtn = findViewById(R.id.AddBtn);

        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);
        textView4 = findViewById(R.id.textView4);
        mDatabaseHelper = new DatabaseHelper(this);

//        textView2.setTextSize(fontSize);
//        textView2.setTextColor(Color.parseColor("#"+fontColor));
//
//        textView.setTextSize(fontSize);
//        textView.setTextColor(Color.parseColor("#"+fontColor));
//
//        textView3.setTextSize(fontSize);
//        textView3.setTextColor(Color.parseColor("#"+fontColor));
//
//        textView4.setTextSize(fontSize);
//        textView4.setTextColor(Color.parseColor("#"+fontColor));

        newBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String productN = product_nameTxt.getText().toString();
                String price = priceTxt.getText().toString();
                String quantity = quantityTxt.getText().toString();
                Boolean brought = broughtCX.isChecked();

                if (product_nameTxt.length() != 0 && priceTxt.length() != 0 && quantityTxt.length() != 0) {
                    addProduct(productN, price, quantity, brought);
                    product_nameTxt.setText("");
                    priceTxt.setText("");
                    quantityTxt.setText("");
                    broughtCX.setChecked(false);
                } else {
                    toastMessage("You must enter some input");
                }
            }
        });
    }

    public void addProduct(String p_name, String p_price, String p_quantity, Boolean is_bought) {
        boolean insertData = mDatabaseHelper.addProduct(p_name, p_price, p_quantity, is_bought);
        Intent intent = new Intent();
        intent.putExtra("id", RecyclerViewAdapter.getIdCounts());
        intent.putExtra("p_name", p_name);
        intent.putExtra("p_price", p_price);
        intent.putExtra("p_quantity", p_quantity);
        intent.putExtra("is_bought", is_bought);
        intent.setAction("com.example.MY_CUSTOM_INTENT");
        sendBroadcast(intent);
        if (insertData) {
            ListActivity.getListActivity().finish();
            MainActivity.getMainActivity().onNavToListClick(null);
            finish();
        } else {
            toastMessage("Something went wrong");
        }
    }

    /**
     * customizable toast
     *
     * @param message
     */
    private void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


}