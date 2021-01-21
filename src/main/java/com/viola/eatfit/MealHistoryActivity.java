package com.viola.eatfit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MealHistoryActivity extends AppCompatActivity {

    private  DatabaseHelper myDB;
    private TextView mTextView;
    private RecyclerView myRCV;
    Cursor res;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_history);

        myDB = new DatabaseHelper(this);
        mTextView = findViewById(R.id.textView);

        List<MealHistory> meals = new ArrayList<MealHistory>();
        meals.clear();


        List<Integer> id = new ArrayList<Integer>();
        id.clear();

        try{
            res = myDB.getMealPlanHistory();

            if(res.getCount() != 0){
                while (res.moveToNext()){

                    String dat = res.getString(1);
                    String bname = res.getString(2);
                    String bserve = res.getString(3);
                    String btime = res.getString(4);
                    String lname = res.getString(5);
                    String lserve = res.getString(6);
                    String ltime = res.getString(7);
                    String sname = res.getString(8);
                    String sserve = res.getString(9);
                    String stime = res.getString(10);

                    MealHistory history = new MealHistory(dat,bname,bserve,btime,lname,lserve,ltime,sname,sserve,stime);

                    meals.add(history);


               /*
                id.add(res.getInt(0));
                meals[0] = res.getString(1);
                meals[1] = res.getString(2);
                meals[2] = res.getString(3);
                meals[3] = res.getString(4);
                meals[4] = res.getString(5);
                meals[5] = res.getString(6);
                meals[6] = res.getString(7);
                meals[7] = res.getString(8);
                meals[8] = res.getString(9);
                meals[9] = res.getString(10);

                */
                }

            }else{
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setCancelable(true);
                builder.setTitle("");
                builder.setMessage("No Meal History");
                builder.show();
            }
        }catch (java.lang.RuntimeException e){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(true);
            builder.setTitle("Error");
            builder.setMessage("No Meal History Record");
            builder.show();

        }


        myRCV = findViewById(R.id.mealHistoryRCV);

        MealHistoryAdapter myAdapter = new MealHistoryAdapter(this,meals);
        myAdapter.setHasStableIds(true);
        myRCV.setAdapter(myAdapter);

       // DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(myRCV.getContext(), myRCV.LayoutManager.getO);

        myRCV.setLayoutManager(new LinearLayoutManager(this));
    }
}
