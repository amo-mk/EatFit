package com.viola.eatfit;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.MyRecipeViewHolder> {
    private List<Recipe> details;//dummy data: should come from db
    private Context context;

    public RecipeAdapter(Context ct, List<Recipe> detl){
        details = detl;
        context = ct;
    }

    @NonNull
    @Override
    public MyRecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recipe_item, parent, false);
        return new MyRecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyRecipeViewHolder holder, int position) {

      /*  int id = new Integer(details.get(0));
        String name = details.get(1);
        String ingredients = details.get(2);
        String procedure = details.get(3);
        String calorie = details.get(4);
        String fats = details.get(5);
        String vitamins = details.get(6);

        holder.mMealName.setText(name);
        holder.mIngredients.setText(ingredients);
        holder.mCookProcedure.setText(procedure);
        holder.mCalorieAmt.setText(calorie);
        holder.mFatsAmt.setText(fats);
        holder.mVitAmt.setText(vitamins);

        holder.setIsRecyclable(false);
       */



        Recipe recipe =  details.get(position);

        holder.mMealName.setText(recipe.getName());
        holder.mIngredients.setText(recipe.getIngredients());
        holder.mCookProcedure.setText(recipe.getCookingProcedure());
        holder.mCalorieAmt.setText(recipe.getCalorieAmount());
        holder.mFatsAmt.setText(recipe.getFatsAmount());
        holder.mVitAmt.setText(recipe.getVitaminsAmount());
    }


    @Override
    public int getItemCount() {
/*
        int i = 0;
        while(details.get(0) != null){
            ++i;
            break;
        }

 */

        return details.size();
    }

    public class MyRecipeViewHolder extends RecyclerView.ViewHolder{

        TextView mMealName, mIngredients, mCookProcedure, mCalorieAmt, mFatsAmt, mVitAmt;
        public MyRecipeViewHolder(@NonNull View itemView) {
            super(itemView);

            mMealName = itemView.findViewById(R.id.meal_name);
            mIngredients = itemView.findViewById(R.id.ingredients);
            mCookProcedure = itemView.findViewById(R.id.cooking_procedure);
            mCalorieAmt = itemView.findViewById(R.id.amount_calorie);
            mFatsAmt = itemView.findViewById(R.id.amount_fats);
            mVitAmt = itemView.findViewById(R.id.amount_vitamins);
//            setHasStableIds(true);

        }

    }
}
