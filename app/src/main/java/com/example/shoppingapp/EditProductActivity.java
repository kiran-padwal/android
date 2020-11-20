package com.example.shoppingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EditProductActivity extends AppCompatActivity {

    private static final String TAG = "EditProductActivity";

    DatabaseHelper mDatabaseHelper;
    private EditText product_nameTxt, priceTxt, quantityTxt;
    private CheckBox broughtCX;
    Button editBtn, deleteBtn;
    private TextView textView, textView2, textView3, textView4;
    private int mid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_product);
        getIncomingIntentExtra();

        SharedPreferences settings = getSharedPreferences("font_settings", Context.MODE_PRIVATE);
        int fontSize = settings.getInt("font_size",16);
        String fontColor = settings.getString("font_color", "e32df2");

        product_nameTxt = findViewById(R.id.p_name);
        priceTxt = findViewById(R.id.p_price);
        quantityTxt = findViewById(R.id.p_quantity);
        broughtCX= findViewById(R.id.broughtCheckBox);
        editBtn = findViewById(R.id.EditBtn);
        deleteBtn = findViewById(R.id.deleteBtn);

        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);
        textView4 = findViewById(R.id.textView4);
        mDatabaseHelper = new DatabaseHelper(this);

        textView2.setTextSize(fontSize);
        textView2.setTextColor(Color.parseColor("#"+fontColor));

        textView.setTextSize(fontSize);
        textView.setTextColor(Color.parseColor("#"+fontColor));

        textView3.setTextSize(fontSize);
        textView3.setTextColor(Color.parseColor("#"+fontColor));

        textView4.setTextSize(fontSize);
        textView4.setTextColor(Color.parseColor("#"+fontColor));

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String productN = product_nameTxt.getText().toString();
                String price = priceTxt.getText().toString();
                String quantity = quantityTxt.getText().toString();
                Boolean brought = broughtCX.isChecked();

                if(product_nameTxt.length() != 0 && priceTxt.length() != 0 && quantityTxt.length() != 0) {
                    mDatabaseHelper.updateProduct(productN, price, quantity, brought, mid);
                    product_nameTxt.setText("");
                    priceTxt.setText("");
                    quantityTxt.setText("");
                    broughtCX.setChecked(false);

                    toastMessage("Product Updated");
                } else {
                    toastMessage("Input Required");
                }
                ListActivity.getListActivity().finish();
                MainActivity.getMainActivity().onNavToListClick(null);

                finish();
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toastMessage("hello hjkhsdkhksjdfhkj");
                mDatabaseHelper.deleteProduct(mid);
                product_nameTxt.setText("");
                priceTxt.setText("");
                quantityTxt.setText("");
                broughtCX.setChecked(false);
                toastMessage("Product Deleted");
                ListActivity.getListActivity().finish();
//                MainActivity.getMainActivity().onNavToListClick(null);

                finish();
            }
        });

    }

    private void getIncomingIntentExtra() {
        Log.d(TAG, "getIncomingIntentExtra: Checking for incoming intents");

        if(getIntent().hasExtra("p_name") && getIntent().hasExtra("p_price") && getIntent().hasExtra("p_quantity") && getIntent().hasExtra("is_bought")) {
            Log.d(TAG, "getIncomingIntentExtra: found incoming extras");

            int id = getIntent().getExtras().getInt("id");
            String product_name = getIntent().getStringExtra("p_name");
            String product_price = getIntent().getStringExtra("p_price");
            String product_quantity = getIntent().getStringExtra("p_quantity");
            Boolean product_brought = getIntent().getExtras().getBoolean("is_bought");

            setData(product_name, product_price, product_quantity, product_brought, id);
        }
    }


    private void setData(String product_name, String product_price, String product_quantity, Boolean brought, int id) {
        Log.d(TAG, "setData: Setting the data to widgets");

        EditText pn = findViewById(R.id.p_name);
        EditText pp = findViewById(R.id.p_price);
        EditText pq = findViewById(R.id.p_quantity);
        CheckBox bt = findViewById(R.id.broughtCheckBox);

        pn.setText(product_name);
        pp.setText(product_price);
        pq.setText(product_quantity);
        bt.setChecked(brought);
        mid = id;
    }

    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}