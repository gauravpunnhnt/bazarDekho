package com.hipernova.bazardekho;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        if (findViewById(R.id.fragment_container) != null) {

            if(savedInstanceState != null){
                return;
            }

            AdFragment adFragment = new AdFragment();
            adFragment.setArguments(getIntent().getExtras());

            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, adFragment).commit();
        }
    }
}
