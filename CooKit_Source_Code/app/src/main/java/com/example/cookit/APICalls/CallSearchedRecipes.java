package com.example.cookit.APICalls;

import com.example.cookit.Models.RecipeAPIResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface CallSearchedRecipes {
    @GET("recipes/complexSearch")
    Call<RecipeAPIResponse> CallSearchedRecipe(
            @Query("apiKey") String apiKey,
            @Query("titleMatch") String titleMatch
    );
}
