package com.example.cookit.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cookit.Listener.RecipeClickListener;
import com.example.cookit.Models.Result;
import com.example.cookit.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SearchedRecipeAdapter extends RecyclerView.Adapter<SearchedRecipeViewHolder>{
    Context context;
    List<Result> list;
    RecipeClickListener listener;

    public SearchedRecipeAdapter(Context context, List<Result> list, RecipeClickListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public SearchedRecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SearchedRecipeViewHolder(LayoutInflater.from(context).inflate(R.layout.list_searched_recipe, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SearchedRecipeViewHolder holder, int position) {

        holder.textView_title.setText(list.get(position).title);
        //test out what this "horizontal scrolling" does
        holder.textView_title.setSelected(true);
        Picasso.get().load(list.get(position).image).into(holder.imageview_dish);

        holder.SearchedRecipeContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onRecipeClicked(String.valueOf(list.get(holder.getAdapterPosition()).id));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
class SearchedRecipeViewHolder extends RecyclerView.ViewHolder{
    CardView SearchedRecipeContainer;
    TextView textView_title;
    ImageView imageview_dish;

    public SearchedRecipeViewHolder(@NonNull View itemView) {
        super(itemView);
        SearchedRecipeContainer = itemView.findViewById(R.id.SearchedRecipeContainer);
        textView_title = itemView.findViewById(R.id.textView_title);
        imageview_dish = itemView.findViewById(R.id.imageview_dish);
    }
}
