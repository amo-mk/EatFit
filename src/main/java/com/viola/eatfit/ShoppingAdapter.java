package com.viola.eatfit;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class ShoppingAdapter extends RecyclerView.Adapter<ShoppingAdapter.MyViewHolder> {

    List<ShoppingItem> items;
    Context context;
    int id;
    private AdapterView.OnItemClickListener mListener;



    public ShoppingAdapter(Context ct, List<ShoppingItem> itms){
        items =itms;
        context = ct;
    }
    public void setOnClickListener(AdapterView.OnItemClickListener listener){
        mListener = listener;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.shopping_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        //i = position;
        ShoppingItem item = items.get(position);
        id = item.getId();
       // i = position;
        String name  = item.getName();
        holder.mItem.setText(name);

       // items.get(i);


    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView mItem;
        ImageButton mDelete;
        private DatabaseHelper myDB;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            mItem = itemView.findViewById(R.id.shoppingItem);
            mDelete = itemView.findViewById(R.id.imageButton);
            //myDB = new DatabaseHelper(this);


            mDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        int i = getAdapterPosition();
                        removeAt(i);
                       // onDelete(i);
                    }catch (Exception e){
                        Log.e("Error",null,e);
                    }
                }
            });
        }
        private void removeAt(int position) {
            items.remove(position);
            notifyItemRemoved(position);
            notifyItemChanged(position, items.size());


        }

    }
}
