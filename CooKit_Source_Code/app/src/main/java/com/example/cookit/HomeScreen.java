package com.example.cookit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.cookit.APICalls.CallSearchedRecipes;
import com.example.cookit.Adapters.Meal_Plan_Adapter;
import com.example.cookit.Adapters.SearchedRecipeAdapter;
import com.example.cookit.Listener.CallSearchedRecipesListener;
import com.example.cookit.Listener.RecipeClickListener;
import com.example.cookit.Models.RecipeAPIResponse;
import com.example.cookit.Models.Result;

import java.util.ArrayList;
import java.util.List;

public class HomeScreen extends AppCompatActivity {
    public androidx.appcompat.widget.SearchView searchbar;
    public List<String> dishes;
    public CallSearchedRecipes callsearchedrecipes;
    public Context context;
    public ProgressDialog dialog;
    public RecyclerView recyclerView, recycler_meal_plan;
    public Meal_Plan_Adapter meal_plan_adapter;
    public SearchedRecipeAdapter searchedRecipeAdapter;
    public RequestManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);

        //definitions
        searchbar = findViewById(R.id.searchbar);
        searchbar.clearFocus();


        //recyclerView.setVisibility(View.GONE);


        //prevents user from accessing the app while the data is loading
        dialog = new ProgressDialog(this);
        dialog.setTitle("Loading...");

        manager = new RequestManager(this);

        //query from search bar captured
        searchbar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //recyclerView.setVisibility(View.VISIBLE);
                manager.getRecipes(callSearchedRecipesListener, query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }
    private final CallSearchedRecipesListener callSearchedRecipesListener = new CallSearchedRecipesListener() {
        @Override
        public void didFetch(RecipeAPIResponse response, String message) {
            // searchview recycler view

            recyclerView = findViewById(R.id.recyview);
            recyclerView.setLayoutManager(new GridLayoutManager(HomeScreen.this, 1));
            searchedRecipeAdapter = new SearchedRecipeAdapter(HomeScreen.this, response.results, recipeClickListener);
            recyclerView.setAdapter(searchedRecipeAdapter);
            dialog.dismiss();
        }

        @Override
        public void didError(String message) {
            Toast.makeText(HomeScreen.this, message, Toast.LENGTH_SHORT).show();
        }
    };
    private final RecipeClickListener recipeClickListener = new RecipeClickListener() {
        @Override
        public void onRecipeClicked(String id) {
            startActivity(new Intent(HomeScreen.this, RecipeDetailsActivity.class)
                    .putExtra("id", id));
        }
    };
    private void filterList(String text) {
        List<Result> filteredList = new ArrayList<>();
        //run every dish through for loop and then use if statement on the loop variable
        // use .contains(text)
        //then filteredList.add('that item')
        //if filteredList.isEmpty() you can either make a toast saying no data found or anything else
    }
}