package com.viola.eatfit;

import android.app.AlertDialog;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class ShoppingCartFragment extends Fragment {

    RecyclerView recyclerView;
     DatabaseHelper myDb;
    private Cursor res;
    private Button mDelete;



    public ShoppingCartFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        final View view = inflater.inflate(R.layout.fragment_shopping_cart, container, false);

        recyclerView =view.findViewById(R.id.shoppingRCV);
        mDelete = view.findViewById(R.id.imageButton);
        myDb = new DatabaseHelper(getContext());

        try {
            res = myDb.getShoppingItems();
        } catch (android.database.sqlite.SQLiteException e){
            AlertMessage message = new AlertMessage();
            message.showMessage(getContext(),"","No shopping Item");
        }

       // items = getDatabaseShoppingItems();
        final List<ShoppingItem> items = new ArrayList<>();

        try {
            if(res.getCount() != 0){
                while(res.moveToNext()) {
                    int id = res.getInt(0);
                    String name = res.getString(1);
                    ShoppingItem item = new ShoppingItem(id, name);
                    items.add(item);
                }

            }else {
                AlertMessage message = new AlertMessage();
                message.showMessage(getContext(),"", "No Shopping Item");
            }

        } catch (java.lang.RuntimeException e){

            AlertMessage message = new AlertMessage();
            message.showMessage(getContext(),"", "No Shopping Items Added");
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.setAdapter(new ShoppingAdapter(getActivity(),items));



/* HOW TO STRIKE THROUGH TEXT WHEN CHECKBOX CHECKED
        final CheckBox selectedItem = (CheckBox) view.findViewById(R.id.itemBox);

        selectedItem.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    TextView mView = view.findViewById(R.id.shoppingItem);
                    mView.setPaintFlags(mView.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
                }else {}
            }
        });
*/
        return view;
    }


  /*  private List<String> getDatabaseShoppingItems() {
        try {
            if(res.getCount() != 0){
                List<String> items = new ArrayList<String>();

                while(res.moveToNext()) {
                    int id = res.getInt(0);
                     items.add(res.getString(1));
                }

            }else {
                AlertMessage message = new AlertMessage();
                message.showMessage(getContext(),"Error", "No Shopping Item");
            }

        } catch (java.lang.RuntimeException e){

            AlertMessage message = new AlertMessage();
            message.showMessage(getContext(),"Error", "No Shopping Item");
        }

        return items;
    }

   */

}
