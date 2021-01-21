package com.viola.eatfit;

import android.app.AlertDialog;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.List;


public class TrackMealsFragment extends Fragment {

    private GraphView mCalorieGraph, mFatsGraph, mVitaminsGraph, mBMIGraph;
    private DatabaseHelper myDB;
    private Cursor res;

/*
    private List<Calorie> calorieData = new ArrayList<>();
    private List<FatsAmount> fatsData = new ArrayList<>();
    private List<VitaminsAmount> vitaminsData = new ArrayList<>();
    private List<BMI> bmiData = new ArrayList<>();
  */
    private float[] calorieData;
    private float[] fatsData;
    private float[] vitaminsData;
    private float[] bmiData;

    public TrackMealsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_track_meals, container, false);

        mCalorieGraph = (GraphView) view.findViewById(R.id.calorieGraphView);
        mFatsGraph = (GraphView) view.findViewById(R.id.fatsGraphView);
        mVitaminsGraph = (GraphView) view.findViewById(R.id.vitaminsGraphView);
        mBMIGraph = (GraphView) view.findViewById(R.id.BMIGraphView);



        myDB = new DatabaseHelper(getContext());

        //load data from db to use in graph
        try {
            res = myDB.getRecipeItems();

            if(res.getCount() != 0) {
                while(res.moveToNext()){

                    int id = res.getInt(0);
                    float calori = res.getFloat(4);
                    float fat = res.getFloat(5);
                    float vitamins = res.getFloat(6);

                    calorieData = new float[7];
                    fatsData = new float[7];
                    vitaminsData = new float[7];

                    for(int i=0; i < res.getCount(); i++){

                        calorieData[i] = res.getFloat(4);
                        fatsData[i] = res.getFloat(5);
                        vitaminsData[i] = res.getFloat(6);
                        res.moveToNext();
                    }
                }

            }else {

                /*
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setCancelable(true);
                builder.setTitle("");
                builder.setMessage("No Recipe Available");
                builder.show();

                 */
            }

        }catch (RuntimeException e){
            /*
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(true);
            builder.setTitle("");
            builder.setMessage("No Recipe Record");
            builder.show();
            */

        }
        try {
            res = myDB.getBMI();
            while (res.moveToNext()){
                if(res.getCount() != 0){
                    int id = res.getInt(0);
                    bmiData = new float[7];

                    for(int i =0; i <res.getCount(); i++){
                        bmiData[i] = res.getFloat(1);
                        res.moveToNext();
                    }

                }
            }
        }catch (Exception e){}

        //calorie graph
        GridLabelRenderer grideLabel = mCalorieGraph.getGridLabelRenderer();
        grideLabel.setHorizontalAxisTitle("Days");
        grideLabel.setVerticalAxisTitle("Calories Intake");
        mCalorieGraph.getViewport().setMinX(1);
        mCalorieGraph.getViewport().setMaxX(7);
        mCalorieGraph.getViewport().setXAxisBoundsManual(true);

        try{

            LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[]{
                    new DataPoint(1,calorieData[0]),
                    new DataPoint(2,calorieData[1]),
                    new DataPoint(3,calorieData[2]),
                    new DataPoint(4,calorieData[3]),
                    new DataPoint(5,calorieData[4]),
                    new DataPoint(6,calorieData[5]),
                    new DataPoint(7,calorieData[6])
            });


            mCalorieGraph.addSeries(series);

        }catch (java.lang.NullPointerException e){
            Log.e("","Amos",null);
            AlertMessage message = new AlertMessage();
            message.showMessage(getContext(),"","No activty yet");
        }


        //fats graph
        GridLabelRenderer fatsGrideLabel = mFatsGraph.getGridLabelRenderer();
        fatsGrideLabel.setHorizontalAxisTitle("Days");
        fatsGrideLabel.setVerticalAxisTitle("Fats Intake");
        mFatsGraph.getViewport().setMinX(1);
        mFatsGraph.getViewport().setMaxX(7);
        mFatsGraph.getViewport().setXAxisBoundsManual(true);


        try{

            LineGraphSeries<DataPoint> fatsSeries = new LineGraphSeries<DataPoint>(new DataPoint[]{
                    new DataPoint(1,fatsData[0]),
                    new DataPoint(2,fatsData[1]),
                    new DataPoint(3,fatsData[2]),
                    new DataPoint(4,fatsData[3]),
                    new DataPoint(5,fatsData[4]),
                    new DataPoint(6,fatsData[5]),
                    new DataPoint(7,fatsData[6])
            });
            mFatsGraph.addSeries(fatsSeries);

        }catch (java.lang.NullPointerException e){
            Log.e("","Amos",null);
        }



        //vitamins graph
        GridLabelRenderer vitaminsGrideLabel = mVitaminsGraph.getGridLabelRenderer();
        vitaminsGrideLabel.setHorizontalAxisTitle("Days");
        vitaminsGrideLabel.setVerticalAxisTitle("Vitamins Intake");
        mVitaminsGraph.getViewport().setMinX(1);
        mVitaminsGraph.getViewport().setMaxX(7);
        mVitaminsGraph.getViewport().setXAxisBoundsManual(true);

        try{

            LineGraphSeries<DataPoint> vitaminsSeries = new LineGraphSeries<DataPoint>(new DataPoint[]{
                    new DataPoint(1,vitaminsData[0]),
                    new DataPoint(2,vitaminsData[1]),
                    new DataPoint(3,vitaminsData[2]),
                    new DataPoint(4,vitaminsData[3]),
                    new DataPoint(5,vitaminsData[4]),
                    new DataPoint(6,vitaminsData[5]),
                    new DataPoint(7,vitaminsData[6])
            });
            mVitaminsGraph.addSeries(vitaminsSeries);

        }catch (java.lang.NullPointerException e){
            Log.e("","Amos",null);
        }

        //bmi activitygraph
        GridLabelRenderer bmiGrideLabel = mBMIGraph.getGridLabelRenderer();
        bmiGrideLabel.setHorizontalAxisTitle("Days");
        bmiGrideLabel.setVerticalAxisTitle("BMI Activity");
        mBMIGraph.getViewport().setMinX(1);
        mBMIGraph.getViewport().setMaxX(7);
        mBMIGraph.getViewport().setXAxisBoundsManual(true);

        try{

            LineGraphSeries<DataPoint> bmiSeries = new LineGraphSeries<DataPoint>(new DataPoint[]{
                    new DataPoint(1,bmiData[0]),
                    new DataPoint(2,bmiData[1]),
                    new DataPoint(3,bmiData[2]),
                    new DataPoint(4,bmiData[3]),
                    new DataPoint(5,bmiData[4]),
                    new DataPoint(6,bmiData[5]),
                    new DataPoint(7,bmiData[6])
            });
            mBMIGraph.addSeries(bmiSeries);

        }catch (java.lang.NullPointerException e){
            Log.e("","Amos",null);
        }

        return view;
    }

}
