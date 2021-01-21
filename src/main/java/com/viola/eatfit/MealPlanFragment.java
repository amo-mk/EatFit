package com.viola.eatfit;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;


public class MealPlanFragment extends Fragment implements View.OnClickListener {

    private Button mCalcRecipeBreakfastBTN;
    private Button mCheckRecipeBreakfastBTN;
    private Button mCalcCalorieLunchBTN;
    private Button mCheckRecipeLunchBTN;
    private Button mCalcCalorieSupperBTN;
    private Button mCheckRecipeSupperBTN;
    private Button mSaveMealPlanBTN;
    private Button mMealPlanHistory;

    private TextView mMealPlanDate;

    private EditText mMNBreakfastET;
    private EditText mServingsBreakfastET;
    private EditText mTimeBreakfastET;
    private EditText mMNLunchET;
    private EditText mServingsLunchET;
    private EditText mTimeLunchtET;
    private EditText mMNSupperET;
    private EditText mServingsSupperET;
    private EditText mTimeSupperET;

    private DatabaseHelper myDB;

    String date;

    public MealPlanFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_meal_plan, container, false);

        //instantiating Button Views
        mCalcRecipeBreakfastBTN = (Button) view.findViewById(R.id.calcCalorieBreakfastBtn);
        mCheckRecipeBreakfastBTN = (Button) view.findViewById(R.id.checkRecipeBreakfastBTN);
        mCalcCalorieLunchBTN = (Button) view.findViewById(R.id.calcCalorieLunchBTN);
        mCheckRecipeLunchBTN = (Button) view.findViewById(R.id.checkRecipeLunchBTN);
        mCalcCalorieSupperBTN = (Button) view.findViewById(R.id.calcCalorieSupperBTN);
        mCheckRecipeSupperBTN = (Button) view.findViewById(R.id.checkRecipeSupperBTN);
        mSaveMealPlanBTN = (Button) view.findViewById(R.id.saveMealPlanBTN);
        mMealPlanHistory = (Button) view.findViewById(R.id.mealHistoryBTN);

        //instatiating Edit Text
        mMNBreakfastET = (EditText) view.findViewById(R.id.mealNameBreakfastET);
        mServingsBreakfastET = (EditText) view.findViewById(R.id.servingsBreakfastET);
        mTimeBreakfastET = (EditText) view.findViewById(R.id.timeBreakfastET);
        mMNLunchET = (EditText) view.findViewById(R.id.MLLunchET);
        mServingsLunchET = (EditText) view.findViewById(R.id.servingsLunchET);
        mTimeLunchtET = (EditText) view.findViewById(R.id.timeLunchET);
        mMNSupperET = (EditText) view.findViewById(R.id.MLSupperET);
        mServingsSupperET = (EditText) view.findViewById(R.id.servingsSupperET);
        mTimeSupperET = (EditText) view.findViewById(R.id.timeSupperET);


        //Button onclick event
        mCalcRecipeBreakfastBTN.setOnClickListener(this);
        mCheckRecipeBreakfastBTN.setOnClickListener(this);
        mCalcCalorieLunchBTN.setOnClickListener(this);
        mCheckRecipeLunchBTN.setOnClickListener(this);
        mCalcCalorieSupperBTN.setOnClickListener(this);
        mCheckRecipeSupperBTN.setOnClickListener(this);
        mSaveMealPlanBTN.setOnClickListener(this);
        mMealPlanHistory.setOnClickListener(this);

        //Getting date
        mMealPlanDate = (TextView) view.findViewById(R.id.mealPlaneDate);
        SimpleDateFormat dateFormat =new SimpleDateFormat("dd MMM yyyy");
        date = dateFormat.format(new Date());
        mMealPlanDate.setText(date);

        //instantiate db
        myDB = new DatabaseHelper(getContext());

        return view;
    }

    @Override
    public void onClick(View v) {
        //calc calorie and check recipe respectively
        if (v.getId() == mCalcRecipeBreakfastBTN.getId() || v.getId() == mCalcCalorieLunchBTN.getId() || v.getId() == mCalcCalorieSupperBTN.getId() ){
            Intent i = new Intent(getContext(), CalcCalorieActivity.class);
            startActivity(i);
        }

        if (v.getId() == mCheckRecipeBreakfastBTN.getId() || v.getId() == mCheckRecipeLunchBTN.getId() || v.getId() == mCheckRecipeSupperBTN.getId()){
            Intent i = new Intent(getContext(), CheckRecipeActivity.class);
            startActivity(i);
        }

        //saveMealPlan and view Meal history respectively

        if (v.getId() == mSaveMealPlanBTN.getId()){
            String breakfastMN = mMNBreakfastET.getText().toString();
            String breakfastServings = mServingsBreakfastET.getText().toString();
            String breakfastTime = mTimeBreakfastET.getText().toString();
            String lunchMN =mMNLunchET.getText().toString();
            String lunchServings = mServingsLunchET.getText().toString();
            String lunchTime = mTimeLunchtET.getText().toString();
            String supperMN = mMNSupperET.getText().toString();
            String supperServings = mServingsSupperET.getText().toString();
            String supperTime = mTimeSupperET.getText().toString();

            if(!TextUtils.isEmpty(breakfastMN) && !TextUtils.isEmpty(breakfastServings) && !TextUtils.isEmpty(breakfastTime) &&
            !TextUtils.isEmpty(lunchMN) && !TextUtils.isEmpty(lunchServings) && !TextUtils.isEmpty(lunchTime) &&
            !TextUtils.isEmpty(supperMN) && !TextUtils.isEmpty(supperServings) && !TextUtils.isEmpty(supperTime))
            {
                //save meal plan to db
                boolean isInserted = myDB.insertMealPlaneData(date,breakfastMN, breakfastServings, breakfastTime,
                        lunchMN,lunchServings,lunchTime, supperMN, supperServings,supperTime);
                if(isInserted == true){
                    AlertMessage message = new AlertMessage();
                    message.showMessage(getContext(),"Saved", "Meal plane details saved sucessfully");

                    mMNBreakfastET.setText("");
                    mServingsBreakfastET.setText("");
                    mTimeBreakfastET.setText("");
                    mMNLunchET.setText("");
                    mServingsLunchET.setText("");
                    mTimeLunchtET.setText("");
                    mMNSupperET.setText("");
                    mServingsSupperET.setText("");
                    mTimeSupperET.setText("");
                }else {
                    AlertMessage message = new AlertMessage();
                    message.showMessage(getContext(),"Error!", "Meal plane details NOT saved");
                }


            }else {
                Toast.makeText(getActivity(), "Error! Meal Plan not saved! \nPlease enter all the meal details",
                        Toast.LENGTH_LONG).show();
            }
        }

        if (v.getId() == mMealPlanHistory.getId()){

            Intent i = new Intent(getContext(),MealHistoryActivity.class);
            startActivity(i);
        }
    }
}
