package com.example.shoppingapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";


    private static ArrayList<Integer> mids = new ArrayList<>();
    private ArrayList<String> mProductNames = new ArrayList<>();
    private ArrayList<String> mProductPrices = new ArrayList<>();
    private ArrayList<String> mProductQuantities = new ArrayList<>();
    private ArrayList<Boolean> mProductBroughts = new ArrayList<>();
    private Context mcontext;

    public RecyclerViewAdapter(Context mcontext, ArrayList<Integer> mids, ArrayList<String> mProductNames, ArrayList<String> mProductPrices, ArrayList<String> mProductQuantities, ArrayList<Boolean> mProductBroughts) {
        this.mids = mids;
        this.mProductNames = mProductNames;
        this.mProductPrices = mProductPrices;
        this.mProductQuantities = mProductQuantities;
        this.mProductBroughts = mProductBroughts;
        this.mcontext = mcontext;
    }
    public static int getIdCounts(){
        return mids.get(mids.size()-1)+1;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called");

        holder.product_name.setText(mProductNames.get(position));
        holder.product_price.setText(mProductPrices.get(position));
        holder.product_quantity.setText(mProductQuantities.get(position));
        holder.bought_cb.setChecked(mProductBroughts.get(position));

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: cliked on: " + mProductNames.get(position));
                Toast.makeText(mcontext, mProductNames.get(position), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(mcontext, EditProductActivity.class);
                intent.putExtra("id", mids.get(position));
                intent.putExtra("p_name", mProductNames.get(position));
                intent.putExtra("p_price", mProductPrices.get(position));
                intent.putExtra("p_quantity", mProductQuantities.get(position));
                intent.putExtra("is_bought", mProductBroughts.get(position));
                mcontext.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return mProductNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView product_name;
        TextView product_price;
        TextView product_quantity;
        CheckBox bought_cb;
        LinearLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            product_name = itemView.findViewById(R.id.product_name);
            product_price = itemView.findViewById(R.id.product_price);
            product_quantity = itemView.findViewById(R.id.product_quantity);
            bought_cb = itemView.findViewById(R.id.bought_cb);
            parentLayout = itemView.findViewById(R.id.parent_layout);

            SharedPreferences settings = itemView.getContext().getSharedPreferences("font_settings", Context.MODE_PRIVATE);
            int fontSize = settings.getInt("font_size", 16);
            String fontColor = settings.getString("font_color", "3ed2f2");

            product_name.setTextSize(fontSize);
            product_name.setTextColor(Color.parseColor("#" + fontColor));

            product_price.setTextSize(fontSize);
            product_price.setTextColor(Color.parseColor("#" + fontColor));

            product_quantity.setTextSize(fontSize);
            product_quantity.setTextColor(Color.parseColor("#" + fontColor));

        }
    }
}
