package com.example.cookit.Listener;

import com.example.cookit.Models.RecipeAPIResponse;

public interface CallSearchedRecipesListener {
    void didFetch(RecipeAPIResponse response, String message);
    void didError(String message);
}
