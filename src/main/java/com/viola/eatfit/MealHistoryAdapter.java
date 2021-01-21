package com.viola.eatfit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MealHistoryAdapter extends RecyclerView.Adapter<MealHistoryAdapter.MyMealHistoryViewHolder> {

    private Context context;
    List<MealHistory> meals;
    //private List<Integer> id ;



    public MealHistoryAdapter (Context ct, List<MealHistory> meal  /*,List<Integer> ID*/){
        context = ct;
        meals = meal;
        //id = ID;
    }

    @NonNull
    @Override
    public MyMealHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.meal_history_item, parent, false);


        return new MyMealHistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyMealHistoryViewHolder holder, int position) {
        /*
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
        holder.date.setText(dateFormat.format(new Date()));


        String dat = meals.get(0);
        String bname = meals.get(1);
        String bserve = meals.get(2);
        String btime = meals.get(3);
        String lname = meals.get(4);
        String lserve = meals.get(5);
        String ltime = meals.get(6);
        String sname = meals.get(7);
        String sserve = meals.get(8);
        String stime = meals.get(9);

        holder.date.setText(dat);

        holder.breakfastName.setText(bname);
        holder.breakfastServings.setText(bserve);
        holder.breakfastTime.setText(btime);

        holder.lunchName.setText(lname);
        holder.lunchServings.setText(lserve);
        holder.lunchTime.setText(ltime);

        holder.supperName.setText(sname);
        holder.supperServings.setText(sserve);
        holder.supperTime.setText(stime);
        holder.setIsRecyclable(false);

*/
        MealHistory history = meals.get(position);
        holder.date.setText(history.getDate());

        holder.breakfastName.setText(history.getbName());
        holder.breakfastServings.setText(history.getBservings());
        holder.breakfastTime.setText(history.getbTime());

        holder.lunchName.setText(history.getLname());
        holder.lunchServings.setText(history.getLservings());
        holder.lunchTime.setText(history.getLtime());

        holder.supperName.setText(history.getSname());
        holder.supperServings.setText(history.getSservings());
        holder.supperTime.setText(history.getStime());
        holder.setIsRecyclable(false);

    }

    @Override
    public int getItemCount() {
        return meals.size();
    }


    public class MyMealHistoryViewHolder extends RecyclerView.ViewHolder{

        TextView date;
        TextView breakfastName, breakfastServings, breakfastTime;
        TextView lunchName, lunchServings, lunchTime;
        TextView supperName, supperServings, supperTime;

        public MyMealHistoryViewHolder(@NonNull View itemView) {
            super(itemView);

            date = itemView.findViewById(R.id.dateTextView);

            breakfastName = itemView.findViewById(R.id.MNbreakfastTV);
            breakfastServings = itemView.findViewById(R.id.servingsBreakfastTV);
            breakfastTime = itemView.findViewById(R.id.timeBreakfastTV);

            lunchName = itemView.findViewById(R.id.MNLunchTV);
            lunchServings = itemView.findViewById(R.id.servingsLunchTV);
            lunchTime = itemView.findViewById(R.id.timeLunchTV);

            supperName = itemView.findViewById(R.id.MNSupperTV);
            supperServings = itemView.findViewById(R.id.servingsSupperTV);
            supperTime = itemView.findViewById(R.id.timeSuppeTV);

        }
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public int getItemViewType(int position){
        return position;
    }

    @Override
    public void setHasStableIds(boolean hasStableIds){
        super.setHasStableIds(hasStableIds);
    }
}
