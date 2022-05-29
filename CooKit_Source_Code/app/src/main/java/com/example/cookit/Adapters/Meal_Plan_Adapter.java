package com.example.cookit.Adapters;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cookit.Models.Result;
import com.example.cookit.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Meal_Plan_Adapter extends RecyclerView.Adapter<Meal_Plan_Viewholder>{
    Context context;
    List<Result> list;

    public Meal_Plan_Adapter(Context context, List<Result> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public Meal_Plan_Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Meal_Plan_Viewholder(LayoutInflater.from(context).inflate(R.layout.list_meal_plan, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Meal_Plan_Viewholder holder, int position) {
        holder.textView_meal_plan_title.setText(list.get(position).title);
        holder.textView_meal_plan_title.setSelected(true);
        Picasso.get().load(list.get(position).image).into(holder.imageView_meal_plan);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
class Meal_Plan_Viewholder extends RecyclerView.ViewHolder {
    TextView textView_meal_plan_title;
    ImageView imageView_meal_plan;
    public Meal_Plan_Viewholder(@NonNull View itemView) {
        super(itemView);
        textView_meal_plan_title = itemView.findViewById(R.id.textView_meal_plan_title);
        imageView_meal_plan = itemView.findViewById(R.id.imageView_meal_plan);
    }
}
