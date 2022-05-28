package com.example.cookit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FinalPage extends AppCompatActivity {
    public Button donebtn;
    public boolean btnpressed = false;
    public Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.finalpage);

        //definitions
        donebtn = findViewById(R.id.donebtn);

        donebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle.putBoolean("btnpressed", btnpressed);
                startActivity(new Intent(FinalPage.this, HomeScreen.class));
            }
        });

    }
}