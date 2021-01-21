package com.viola.eatfit;

import android.app.AlertDialog;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


public class ProfileFragment extends Fragment implements View.OnClickListener {

    private EditText mUserName;
    private EditText mAge;
    private EditText mHeight;
    private EditText mWeight;
    private EditText mAlergies;

    private RadioButton mMale;
    private RadioButton mFemale;
    private RadioButton mVegie;
    private RadioButton mNonVegie;

    private RadioGroup mGender;
    private RadioGroup mMealTypeRB;

    private Button mSaveBTN;
    private ImageButton mEditBTN;


    private TextView mBMI;

    private Spinner activitySpinner;

    private DatabaseHelper myDB;


    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        myDB = new DatabaseHelper(getContext());


        mUserName = (EditText) view.findViewById(R.id.userNameET);
        mAge = (EditText) view.findViewById(R.id.userAgeET);
        mHeight = (EditText) view.findViewById(R.id.userHeightET);
        mWeight = (EditText) view.findViewById(R.id.userWeightET);
        mAlergies = (EditText) view.findViewById(R.id.allergiresET);

        mMale = (RadioButton) view.findViewById(R.id.maleRB);
        mFemale = (RadioButton) view.findViewById(R.id.femaleRB);
        mVegie = (RadioButton) view.findViewById(R.id.vegeRB);
        mNonVegie = (RadioButton) view.findViewById(R.id.nonVegeRB);

        mGender = (RadioGroup) view.findViewById(R.id.genderSelection);
        mMealTypeRB = (RadioGroup) view.findViewById(R.id.mealTypeRadioGroup);

        mSaveBTN = (Button) view.findViewById(R.id.profileSaveBTN);

        mEditBTN = (ImageButton) view.findViewById(R.id.editButton);

        mBMI = (TextView) view.findViewById(R.id.BMIvalue);

        //spinner view
        activitySpinner = (Spinner) view.findViewById(R.id.spinner);
        List<String> spinnerArray = new ArrayList<String>();
        spinnerArray.add("Active");
        spinnerArray.add("Very Active");
        spinnerArray.add("Not Active");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getContext(), android.R.layout.simple_spinner_item, spinnerArray
        );
        activitySpinner.setAdapter(adapter);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mSaveBTN.setOnClickListener(this);
        mEditBTN.setOnClickListener(this);
        loadData();

        return view;
    }

    private void loadData() {
        //init user profile details in DB
        Cursor res = myDB.getProfileData();
        if(res.getCount() != 0){
            // StringBuffer data = new StringBuffer();
            while(res.moveToNext()){
                int id = res.getInt(0);
                mUserName.setText(res.getString(1));

                String gender = res.getString(2);
                if(gender.equals( "Male"))
                    mMale.setChecked(true);
                else
                    mFemale.setChecked(true);

                mAge.setText(String.valueOf(res.getInt(3)));
                mHeight.setText(String.valueOf(res.getInt(4)));
                mWeight.setText(String.valueOf(res.getInt(5)));
                mBMI.setText(String.valueOf(res.getFloat(6)));

                String foodType = res.getString(7);
                if(foodType.equals("Vegeterian"))
                    mVegie.setChecked(true);
                else
                    mNonVegie.setChecked(true);
                activitySpinner.setPrompt(res.getString(8));
                mAlergies.setText(res.getString(9));
            }
        }else{}
    }

    @Override
    public void onClick(View v) {


        if(v.getId() == mSaveBTN.getId()){
            String name = mUserName.getText().toString();
            String age = mAge.getText().toString();
            String height = mHeight.getText().toString();
            String weight = mWeight.getText().toString();
            String allergies = mAlergies.getText().toString();
            String gender = "";
            String foodType = "";
            String activityLevel = "";
            String selectedActivityLevel = activitySpinner.getSelectedItem().toString();

            if(selectedActivityLevel.equals("Active")){
                activityLevel = "Active";
            }else if(selectedActivityLevel.equals("Very Active")){
                activityLevel = "Very Active";
            }else {
                activityLevel = "Not Active";
            }


            if(mMale.getId() == mGender.getCheckedRadioButtonId()){
                gender = "Male";
            }else {
                gender = "Female";
            }

            if(mVegie.getId() == mMealTypeRB.getCheckedRadioButtonId()){
                foodType = "Vegeterian";
            }else {
                foodType = "Non-Vegeterian";
            }

            //calc BMI
            float mweight;
            float mheight;
            float BMI;
            if(!TextUtils.isEmpty(weight) && !TextUtils.isEmpty(height)){
                mweight = new Float(weight);
                mheight = new Float(height);

                BMI = (mweight/mheight);
            } else {
                mweight = 0;
                mheight = 0;
                BMI = 0;
            }


            //Validate data input
            if(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(age) && !TextUtils.isEmpty(height) && !TextUtils.isEmpty(weight) &&
                    !TextUtils.isEmpty(allergies) && !TextUtils.isEmpty(gender) && !TextUtils.isEmpty(foodType))
            {
                int intHeight = new Integer(height);
                int intWeight = new Integer(weight);
                int intAge = new Integer(age);
                //save to db
                boolean isInserted = myDB.insertProfileData(name,gender,intAge,intHeight,intWeight,
                        BMI,foodType,activityLevel,allergies);

                if(isInserted == true){
                    AlertMessage message = new AlertMessage();
                    message.showMessage(getActivity(),"Saved!", "Profile Details Saved!");
                   // Toast.makeText(getActivity(), "Profile Details Saved!", Toast.LENGTH_LONG).show();
                }else {

                    AlertMessage message = new AlertMessage();
                    message.showMessage(getContext(),"Error", "Data could not be saved");
                    //Toast.makeText(getActivity(), "Profile Details  NOT Saved!", Toast.LENGTH_LONG).show();
                }

                mUserName.setEnabled(false);
                mUserName.setFocusable(false);
                mAge.setEnabled(false);
                mHeight.setEnabled(false);
                mWeight.setEnabled(false);
                mAlergies.setEnabled(false);

                mMale.setClickable(false);
                mFemale.setClickable(false);
                mVegie.setClickable(false);
                mNonVegie.setClickable(false);

                activitySpinner.setClickable(false);
                mGender.setEnabled(false);
                mMealTypeRB.setEnabled(false);

                mSaveBTN.setEnabled(false);
                mSaveBTN.setVisibility(View.GONE);

            }else {

                AlertMessage message = new AlertMessage();
                message.showMessage(getContext(),"Error!", "Details not saved. Please ensure to fill all entries");
                //Toast.makeText(getActivity(), "Error! Not Saved\nPlease ensure to fill all entries", Toast.LENGTH_LONG).show();
            }
        }
        if(v.getId() == mEditBTN.getId()){

            mUserName.setEnabled(true);
            mUserName.setFocusable(true);
            mAge.setEnabled(true);
            mHeight.setEnabled(true);
            mWeight.setEnabled(true);
            mAlergies.setEnabled(true);

            mMale.setClickable(true);
            mFemale.setClickable(true);
            mVegie.setClickable(true);
            mNonVegie.setClickable(true);

            mGender.setEnabled(true);
            mMealTypeRB.setEnabled(true);

            activitySpinner.setClickable(true);

            mSaveBTN.setEnabled(true);
            mSaveBTN.setVisibility(View.VISIBLE);

        }
    }
}
