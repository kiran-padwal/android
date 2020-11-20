package com.example.shoppingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    private static final String TAG = "ListDataActivity";
    Button getProduct;
    DatabaseHelper mDatabaseHelper;


    private ArrayList<Integer> mids = new ArrayList<>();
    private ArrayList<String> mPNames = new ArrayList<>();
    private ArrayList<String> mPPrices = new ArrayList<>();
    private ArrayList<String> mPQuantities = new ArrayList<>();
    private ArrayList<Boolean> mPBroughts = new ArrayList<>();
    private  static  ListActivity listA;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        mDatabaseHelper = new DatabaseHelper(this);
        initProducts();
        onGetProduct();
        listA=this;
    }
    public static ListActivity getListActivity(){
        return listA;
    }
    private void initProducts() {
        Log.d(TAG, "initProducts: preparing products");

        Cursor data = mDatabaseHelper.getProducts();
        if(data.getCount() == 0){
            toastMessage("No data");
        } else {
            while(data.moveToNext()){
                mids.add(data.getInt(0));
                mPNames.add(data.getString(1 ));
                mPPrices.add(data.getString(2 ));
                mPQuantities.add(data.getString(3 ));
                if(data.getInt(4) == 1){
                    mPBroughts.add(true);
                }else {
                    mPBroughts.add(false);
                }
            }
        }

        initReclyclerView();
    }

    private void initReclyclerView() {
        Log.d(TAG, "initReclyclerView: init reclyclerview");

        RecyclerView recyclerView = findViewById(R.id.recyclerViewList);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, mids, mPNames, mPPrices, mPQuantities, mPBroughts);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void onNavToNewProduct(View v){
        Intent myIntent = new Intent(getBaseContext(),   NewProductActivity.class);
        startActivity(myIntent);
    }

    public void onGetProduct() {
        Log.d(TAG, "populateListView: Displaying data in the ListView.");
        Cursor data = mDatabaseHelper.getProducts();
        if(data.getCount() == 0){
            toastMessage("No data");
        } else {
            while(data.moveToNext()){
                System.out.println(data.getString(2 )+ "First Product");
            }
        }

    }

    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}