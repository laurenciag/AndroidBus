package com.example.finalProjectAndroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.finalProjectAndroid.Helper.CustomActivity;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_splash_screen);
        new CustomActivity(this,3000L).startAndDestroy(LoginPage.class);
    }
}