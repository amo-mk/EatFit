package com.viola.eatfit;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class CalcCalorieActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mCalcCalorieBTN;
    private EditText mWeight, mHeight, mAge;
    private TextView mBMI, mCategory;
    private DatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc_calorie);

        mCalcCalorieBTN = (Button) findViewById(R.id.calculateCalorieBTN);
        mWeight = (EditText) findViewById(R.id.weightEditText);
        mHeight = (EditText) findViewById(R.id.heightEditText);
        mAge = (EditText) findViewById(R.id.ageEditText);
        mBMI = (TextView) findViewById(R.id.bmiTV);
        mCategory = (TextView) findViewById(R.id.categoryTV);
        myDB = new DatabaseHelper(this);
        mCalcCalorieBTN.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == mCalcCalorieBTN.getId()){
            String W = mWeight.getText().toString();
            String H = mHeight.getText().toString();
            String age = mAge.getText().toString();

            String category = "";

            if(!TextUtils.isEmpty(W) && !TextUtils.isEmpty(H) && !TextUtils.isEmpty(age)){
                float weight = new Float(W);
                float height = new Float(H);
                float bmi = weight/(height/100);
                if(bmi > 40)
                    category = "Extreme";
                if(bmi >= 35.0 && bmi < 40)
                    category = "Obese II";
                if(bmi >= 30.0 && bmi < 35.0)
                    category = "Obese I";
                if(bmi >= 25.0 && bmi < 30.0)
                    category = "Overweight";
                if(bmi >= 18.5 && bmi <25.0)
                    category = "Normal Weight";
                if(bmi >=17.0 && bmi < 18.5)
                    category = "Underweight";
                if(bmi < 17.0)
                    category = "Severe Underweight";

                mBMI.setText(String.valueOf(bmi));
                mCategory.setText(category);


                try {
                    boolean isInserted = myDB.insertBMI(bmi);
                    if(isInserted == true){
                        Toast.makeText(this,"BMI Saved",Toast.LENGTH_SHORT).show();
                    } else{
                        Toast.makeText(this,"BMI NOT saved ",Toast.LENGTH_LONG).show();
                    }
                }catch (Exception e){

                }

            }else {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setCancelable(true);
                builder.setTitle("Error");
                builder.setMessage("Please Enter valid Information");
                builder.show();
            }
        }
    }
}
