package com.viola.eatfit;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Arrays;

public class AddRecipeActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mAddRecipe, mAddShoppingCart;
    private EditText mMealName, mIgredients, mCookingProcedure, mCalorieAmount, mFatsAmount, mVitaminsAmount;
    private DatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);
        myDB = new DatabaseHelper(this);
        mAddRecipe = (Button) findViewById(R.id.addRecipeBTN);
        mAddShoppingCart = (Button) findViewById(R.id.addShoppingCartBTN);

        mMealName = (EditText) findViewById(R.id.mealNameEDT);
        mIgredients = (EditText) findViewById(R.id.ingredientsEDT);
        mCookingProcedure = (EditText) findViewById(R.id.cookingProcdureEDT);
        mCalorieAmount = (EditText) findViewById(R.id.calorieAmountEDT);
        mFatsAmount = (EditText) findViewById(R.id.fatsAmountEDT);
        mVitaminsAmount = (EditText) findViewById(R.id.vitaminsAmountEDT);

        mAddRecipe.setOnClickListener(this);
        mAddShoppingCart.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == mAddRecipe.getId()){
            addRecipe();
        }
        if(v.getId() == mAddShoppingCart.getId()){
            addShoppingcart();
        }

    }

    private void addShoppingcart() {
        String ingredients = mIgredients.getText().toString();

        if(!TextUtils.isEmpty(ingredients)){
            boolean isInserted = myDB.insertShoppingItem(ingredients);
            if(isInserted == true){
                Toast.makeText(this,"Shopping Item inserted succesfully",Toast.LENGTH_SHORT).show();
            } else{
                Toast.makeText(this,"Shopping Item NOT inserted ",Toast.LENGTH_LONG).show();
            }
        }else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(true);
            builder.setTitle("Error");
            builder.setMessage("Make sure you enter ingredients to shop.");
            builder.show();
        }
        //myDB.insertMealPlaneData()
    }

    private void addRecipe() {
        String name = mMealName.getText().toString();
        String ingredients = mIgredients.getText().toString();
        String cookingProcedure = mCookingProcedure.getText().toString();
        String calorieAmount = mCalorieAmount.getText().toString();
        String fatsAmount = mFatsAmount.getText().toString();
        String vitaminsAmount = mVitaminsAmount.getText().toString();

        if(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(ingredients) && !TextUtils.isEmpty(cookingProcedure)&&
                !TextUtils.isEmpty(calorieAmount) && !TextUtils.isEmpty(fatsAmount) && !TextUtils.isEmpty(vitaminsAmount)){

            int calorie = new Integer(calorieAmount);
            int fats = new Integer(fatsAmount);
            int vitamins = new Integer(vitaminsAmount);
            boolean isInserted = myDB.insertRecipe(name,ingredients,cookingProcedure,calorie,fats,vitamins);

            if(isInserted == true){

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setCancelable(true);
                builder.setTitle("Sucess!");
                builder.setMessage("Recipe saved successfully.");
                builder.show();

            }else {

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setCancelable(true);
                builder.setTitle("");
                builder.setMessage("Recipe Not saved. Enter all the fields.");
                builder.show();

            }
        }else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(true);
            builder.setTitle("Error");
            builder.setMessage("Make sure you enter all the fields.");
            builder.show();
        }
    }
}
