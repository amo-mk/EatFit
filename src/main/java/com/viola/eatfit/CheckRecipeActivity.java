package com.viola.eatfit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

public class CheckRecipeActivity extends AppCompatActivity {

    private RecyclerView mRVC;
    private Button mAddRecipe;
    private DatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_recipe);
        myDB = new DatabaseHelper(this);

        mAddRecipe = (Button) findViewById(R.id.button2addrecipeBTN);

        mRVC = findViewById(R.id.checkRecipeRVC);

        List<Recipe> recipeItems = new ArrayList<Recipe>();
        recipeItems.clear();

        try {
            Cursor cursor = myDB.getRecipeItems();

            if(cursor.getCount() != 0) {
                while(cursor.moveToNext()){
                    int id = cursor.getInt(0);
                    String name = cursor.getString(1);
                    String ingredients = cursor.getString(2);
                    String procedure = cursor.getString(3);
                    String calorie = String.valueOf(cursor.getFloat(4));
                    String fats = String.valueOf(cursor.getFloat(5));
                    String vitamins = String.valueOf(cursor.getFloat(6));

                    Recipe recipe = new Recipe(name,ingredients,procedure,calorie,fats,vitamins);
                    recipeItems.add(recipe);

/*
                    int id = cursor.getInt(0);
                    recipeItems.add(String.valueOf(id));
                    recipeItems.add(cursor.getString(1));
                    recipeItems.add(cursor.getString(2));
                    recipeItems.add(cursor.getString(3));
                    recipeItems.add(String.valueOf(cursor.getFloat(4)));
                    recipeItems.add(String.valueOf(cursor.getFloat(5)));
                    recipeItems.add(String.valueOf(cursor.getFloat(6)));

 */
                }

            }else {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setCancelable(true);
                builder.setTitle("");
                builder.setMessage("No Recipe Available");
                builder.show();
            }

        }catch (java.lang.RuntimeException e){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(true);
            builder.setTitle("");
            builder.setMessage("No Recipe Record");
            builder.show();

        }

        RecipeAdapter myAdapter = new RecipeAdapter(this, recipeItems);
        //myAdapter = new RecipeAdapter(this, recipeItems);
        myAdapter.setHasStableIds(true);
        mRVC.setAdapter(myAdapter);
        mRVC.setLayoutManager(new LinearLayoutManager(this));

        mAddRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), AddRecipeActivity.class);
                startActivity(i);
            }
        });
    }
}
