package com.example.cookit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cookit.Listener.RecipeDetailsListener;
import com.example.cookit.Models.ExtendedIngredient;
import com.example.cookit.Models.RecipeDetailsResponse;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RecipeDetailsActivity extends AppCompatActivity {
    int id;
    TextView recipeName, summary, source, textView_Ingredients, sourceurl, duration;
    ImageView image_recipe;
    List<ExtendedIngredient> extendedIngredients;
    String ingredients_string = "";
    RequestManager manager;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landingpage);

        ActionBar actionBar = getSupportActionBar();

        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);
        findViews();

        id = Integer.parseInt(getIntent().getStringExtra("id"));

        //setting up recycler view for ingredients
        manager = new RequestManager(this);
        manager.getRecipeDetails(recipeDetailsListener, id);
        dialog = new ProgressDialog(this);
        dialog.setTitle("Loading...");
        dialog.show();
        extendedIngredients = new ArrayList<ExtendedIngredient>();

    }

    private void findViews() {
        recipeName = findViewById(R.id.textView_RecipeName);
        summary = findViewById(R.id.textView_summary);
        duration = findViewById(R.id.textView_Duration);
        source = findViewById(R.id.textView_Source);
        sourceurl = findViewById(R.id.textView_SoureURL);
        image_recipe = findViewById(R.id.imageView_recipe);
        textView_Ingredients = findViewById(R.id.textView_ingredients);
    }
    private final RecipeDetailsListener recipeDetailsListener = new RecipeDetailsListener() {
        @Override
        public void didFetch(RecipeDetailsResponse response, String message) {
            dialog.dismiss();
            recipeName.setText(response.title);
            duration.setText("Duration:  " + response.readyInMinutes + " minutes");
            source.setText("Source: " + response.sourceName);
            sourceurl.setText(response.sourceUrl);
            summary.setText(response.summary.replaceAll("<[^>]*>", ""));
            Picasso.get().load(response.image).into(image_recipe);
            for (int position = 0; position < response.extendedIngredients.size(); position++){
                ingredients_string = ingredients_string + "-" + response.extendedIngredients.get(position).original + "\n";
            }
            textView_Ingredients.setText(ingredients_string);
        }

        @Override
        public void didError(String message) {
            Toast.makeText(RecipeDetailsActivity.this, message, Toast.LENGTH_SHORT).show();
        }
    };
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}