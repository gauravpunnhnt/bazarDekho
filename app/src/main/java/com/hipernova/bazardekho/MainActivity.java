package com.hipernova.bazardekho;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Handler splash = new Handler();
        splash.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent login = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(login);
                finish();
            }
        },2000);
    }
}
