package com.viola.eatfit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String MY_DATABASE = "eatfit.db";
    //DB Tables
    public final static String USER_PROFILE_TABLE = "profile_table";
    public final static String MEAL_PLAN_TABLE = "meal_plan_table";
    public final static String SHOPPING_TABLE = "shopping_table";
    public final static String RECIPE_TABLE = "recipe_table";
    public final static String BMI_TABLE = "bmi_table";

    //bmi table column
    public static final String B_COL_1 = "bmi";


    //shopping table column
    public static final String S_COL_1 = "ITEM";

    //Recipe table columns
    public static final String R_COL_1 ="MEAL_NAME";
    public static final String R_COL_2 ="INGREDIENTS";
    public static final String R_COL_3 ="COOKING_PROCEDURE";
    public static final String R_COL_4 ="CALORIE_AMOUNT";
    public static final String R_COL_5 ="FATS_AMOUNT";
    public static final String R_COL_6 ="VITAMINS_AMOUNT";

    //profile table columns

    public static final String P_COL_1 = "NAME";
    public static final String P_COL_2 = "GENDER";
    public static final String P_COL_3 = "AGE";
    public static final String P_COL_4 = "HEIGHT";
    public static final String P_COL_5 = "WEIGHT";
    public static final String P_COL_6 = "BMI";
    public static final String P_COL_7 = "FOOD_TYPE";
    public static final String P_COL_8 = "ACTIVITY_LEVEL";
    public static final String P_COL_9 = "ALLERGIES";

    //meal plan table columns
    public static final String M_COL_1 = "DATE";
    public static final String M_COL_2 = "BREAKFAST_MEAL_NAME";
    public static final String M_COL_3 = "BREAKFAST_SERVINGS";
    public static final String M_COL_4 = "BREAKFAST_TIME";
    public static final String M_COL_5 = "LUNCH_MEAL_NAME";
    public static final String M_COL_6 = "LUNCH_SERVINGS";
    public static final String M_COL_7 = "LUNCH_TIME";
    public static final String M_COL_8 = "SUPPER_MEAL_NAME";
    public static final String M_COL_9 = "SUPPER_SERVINGS";
    public static final String M_COL_10 = "SUPPER_TIME";

    public DatabaseHelper(@Nullable Context context) {
        super(context, MY_DATABASE, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE "+USER_PROFILE_TABLE+"(ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, GENDER TEXT,AGE INTEGER, HEIGHT INTEGER," +
                "WEIGHT INTEGER, BMI FLOAT, FOOD_TYPE TEXT, ACTIVITY_LEVEL TEXT, ALLERGIES TEXT)");

        db.execSQL("CREATE TABLE "+MEAL_PLAN_TABLE+"(ID INTEGER PRIMARY KEY AUTOINCREMENT, DATE TEXT, BREAKFAST_MEAL_NAME TEXT, BREAKFAST_SERVINGS TEXT," +
                "BREAKFAST_TIME TEXT, LUNCH_MEAL_NAME TEXT, LUNCH_SERVINGS TEXT, LUNCH_TIME TEXT, SUPPER_MEAL_NAME TEXT, SUPPER_SERVINGS TEXT, SUPPER_TIME TEXT)");

        db.execSQL("CREATE TABLE "+RECIPE_TABLE+"(ID INTEGER PRIMARY KEY AUTOINCREMENT, MEAL_NAME TEXT, INGREDIENTS TEXT, COOKING_PROCEDURE TEXT," +
                "CALORIE_AMOUNT INTEGER, FATS_AMOUNT INTEGER, VITAMINS_AMOUNT INTEGER)");

        db.execSQL("CREATE TABLE "+SHOPPING_TABLE+"(ID INTEGER PRIMARY KEY AUTOINCREMENT, ITEM TEXT)");

        db.execSQL("CREATE TABLE "+BMI_TABLE+"(ID INTEGER PRIMARY KEY AUTOINCREMENT, BMI FLOAT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE "+USER_PROFILE_TABLE);
        db.execSQL("DROP TABLE "+MEAL_PLAN_TABLE);
        db.execSQL("DROP TABLE "+RECIPE_TABLE);
        db.execSQL("DROP TABLE "+SHOPPING_TABLE);
        db.execSQL("DROP TABLE "+BMI_TABLE);
        onCreate(db);

    }

    //query bmi
    public Cursor getBMI(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+BMI_TABLE, null);
        return res;

    }
    //insert bmi
    public boolean insertBMI(float bmi){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(S_COL_1, bmi);

        long result = db.insert(SHOPPING_TABLE,null,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    //get bmi activity, fats, calorie and vitamins activity in 7 days.

    public Integer deleteShoppingItem(String id){
        SQLiteDatabase db = this.getWritableDatabase();
       return  db.delete(SHOPPING_TABLE, "ID = ?", new String[]{id});

    }


    public Cursor getShoppingItems(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+SHOPPING_TABLE, null);
        return res;

    }

    public boolean insertShoppingItem(String item){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(S_COL_1, item);

        long result = db.insert(SHOPPING_TABLE,null,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getRecipeItems(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+RECIPE_TABLE, null);
        return res;
    }
    public boolean insertRecipe(String name, String ingre, String procedure, int calorie, int fats, int vitamins){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(R_COL_1, name);
        contentValues.put(R_COL_2, ingre);
        contentValues.put(R_COL_3, procedure);
        contentValues.put(R_COL_4, calorie);
        contentValues.put(R_COL_5, fats);
        contentValues.put(R_COL_6, vitamins);

        long result = db.insert(RECIPE_TABLE,null, contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }



    public Cursor getMealPlanHistory(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+MEAL_PLAN_TABLE, null);
        return res;
    }

    public boolean insertMealPlaneData(String date, String bname, String bservings, String btime, String lname, String lservings,
                                       String ltime, String sname, String sservings, String stime){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(M_COL_1, date);
        contentValues.put(M_COL_2, bname);
        contentValues.put(M_COL_3, bservings);
        contentValues.put(M_COL_4, btime);
        contentValues.put(M_COL_5, lname);
        contentValues.put(M_COL_6, lservings);
        contentValues.put(M_COL_7, ltime);
        contentValues.put(M_COL_8, sname);
        contentValues.put(M_COL_9,sservings);
        contentValues.put(M_COL_10, stime);

        long result = db.insert(MEAL_PLAN_TABLE, null, contentValues);
        if(result == -1)
            return false;
        else
            return true;

    }

    public boolean insertProfileData(String name, String gender, int age, int height, int weight, float bmi, String foodType, String activityLevel,
                                     String allergies){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(P_COL_1, name);
        contentValues.put(P_COL_2, gender);
        contentValues.put(P_COL_3, age);
        contentValues.put(P_COL_4, height);
        contentValues.put(P_COL_5, weight);
        contentValues.put(P_COL_6, bmi);
        contentValues.put(P_COL_7, foodType);
        contentValues.put(P_COL_8, activityLevel);
        contentValues.put(P_COL_9, allergies);

        long result = db.insert(USER_PROFILE_TABLE, null, contentValues);

        if(result == -1)
            return false;
        else
            return true;
    }
    public Cursor getProfileData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+USER_PROFILE_TABLE, null);
        return res;
    }
}
