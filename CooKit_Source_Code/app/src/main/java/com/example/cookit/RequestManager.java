package com.example.cookit;

import android.content.Context;

import com.example.cookit.APICalls.CallRecipeDetails;
import com.example.cookit.APICalls.CallSearchedRecipes;
import com.example.cookit.Listener.CallSearchedRecipesListener;
import com.example.cookit.Listener.RecipeDetailsListener;
import com.example.cookit.Models.RecipeAPIResponse;
import com.example.cookit.Models.RecipeDetailsResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RequestManager {
    CallSearchedRecipes callsearchedrecipes;
    Context context;
    //Retrofit definition
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.spoonacular.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public RequestManager(Context context){
        this.context = context;
    }
    public void getRecipes(CallSearchedRecipesListener listener, String query) {

        callsearchedrecipes = retrofit.create(CallSearchedRecipes.class);
        Call<RecipeAPIResponse> call = callsearchedrecipes.CallSearchedRecipe(context.getString(R.string.api_key), query);
        call.enqueue(new Callback<RecipeAPIResponse>() {
            @Override
            public void onResponse(Call<RecipeAPIResponse> call, Response<RecipeAPIResponse> response) {
                if (!response.isSuccessful()) {
                    //Toast.makeText(HomeScreen.this, response.code(), Toast.LENGTH_SHORT).show();
                    listener.didError(response.message());
                    return;
                }
                listener.didFetch(response.body(), response.message());
                //Result postList = response.body();
                //PostAdapter postAdapter = new PostAdapter(MainActivity.this, postList);
                //recyclerView.setAdapter(postAdapter);
            }

            @Override
            public void onFailure(Call<RecipeAPIResponse> call, Throwable t) {
                listener.didError(t.getMessage());
                //Toast.makeText(HomeScreen.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void getRecipeDetails(RecipeDetailsListener listener, int id){
        CallRecipeDetails callRecipeDetails = retrofit.create(CallRecipeDetails.class);
        Call<RecipeDetailsResponse> call = callRecipeDetails.callRecipeDetails(id, context.getString(R.string.api_key));
        call.enqueue(new Callback<RecipeDetailsResponse>() {
            @Override
            public void onResponse(Call<RecipeDetailsResponse> call, Response<RecipeDetailsResponse> response) {
                if(!response.isSuccessful()){
                    listener.didError(response.message());
                }
                listener.didFetch(response.body(), response.message());
            }

            @Override
            public void onFailure(Call<RecipeDetailsResponse> call, Throwable t) {
                listener.didError(t.getMessage());
            }
        });
    }
}
