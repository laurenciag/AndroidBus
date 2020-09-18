package com.example.finalProjectAndroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.example.finalProjectAndroid.Fragment.AgencyFragment;
import com.example.finalProjectAndroid.Fragment.BusFragment;
import com.example.finalProjectAndroid.Fragment.DetailBusFragment;
import com.example.finalProjectAndroid.Fragment.ProfileFragment;
import com.example.finalProjectAndroid.Helper.CustomActivity;
import com.example.finalProjectAndroid.Util.SessionManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomePage extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bottomNavigationView;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_home_page);

        bottomNavigationView = findViewById(R.id.bottomMenu);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.fragmentProfile);
        sessionManager = new SessionManager(getApplicationContext());

//        AgencyFragment agencyFragment = new AgencyFragment();
        BusFragment busFragment = new BusFragment(this);
        loadFragment(busFragment);
    }

//    public void clickProfile(){
//        ProfileFragment profileFragment = new ProfileFragment();
//        loadFragment(profileFragment);
//    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        Fragment fragment;

        switch (item.getItemId()){
            case R.id.agencyMenu :
                fragment = new AgencyFragment(getApplicationContext());
                break;
            case R.id.profileMenu :
                fragment = new ProfileFragment(getApplicationContext());
                break;
            case R.id.busMenu :
                fragment = new BusFragment(this);
                break;
//            case R.id.detailBus :
//                fragment = new DetailBusFragment(getApplicationContext());
//                break;
            default:
                fragment = new BusFragment(this);
        }

        return loadFragment(fragment);
    }

    public boolean loadFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.layoutFragment, fragment).commit();
        return true;

    }

//    public void clickDetail (View v){
//        Log.d("test", "activity");
//    }

    public void clickLogout(View v){
        sessionManager.removeToken();
        new CustomActivity(HomePage.this).startAndDestroy(LoginPage.class);
//        Intent intent = new Intent(HomePage.this, LoginPage.class);
//        startActivity(intent);
    }

    public  void clickList(View v){
        new CustomActivity(HomePage.this).start(BusFragment.class);
    }

}