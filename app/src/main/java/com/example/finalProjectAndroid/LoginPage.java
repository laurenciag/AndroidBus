package com.example.finalProjectAndroid;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.finalProjectAndroid.Fragment.AgencyFragment;
import com.example.finalProjectAndroid.Helper.CustomActivity;
import com.example.finalProjectAndroid.Util.SessionManager;
import com.example.finalProjectAndroid.Util.UserUtil;

import org.json.JSONException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPage extends AppCompatActivity {

    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login_page);

        sessionManager = new SessionManager(getApplicationContext());

        if (!sessionManager.getToken().getUserId().equals("")) {
            new CustomActivity(LoginPage.this).startAndDestroy(HomePage.class);
        }
    }

    public void LoginClick(View v) {
        String inpEmailAddress = ((EditText) findViewById(R.id.inpEmailAddress)).getText().toString();
        String inpPassword = ((EditText) findViewById(R.id.inpPassword)).getText().toString();
        SessionManager sessionManager = new SessionManager(this);

        RequestBody rBodyEmail = RequestBody.create(
                MediaType.parse("Text/plain"), inpEmailAddress
        );
        RequestBody rBodyPassword = RequestBody.create(
                MediaType.parse("Text/plain"), inpPassword
        );

//        Toast.makeText(LoginPage.this, inpEmailAddress, Toast.LENGTH_LONG).show();
        (new UserUtil()).getUser().login(rBodyEmail, rBodyPassword).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.d("Login Response", response.body());
//                Toast.makeText(LoginPage.this, response.body(), Toast.LENGTH_SHORT).show();
                try {
                    if (sessionManager.setSession(response.body())) {
                        Log.d("Login:", "Success");
                        new CustomActivity(LoginPage.this).startAndDestroy(HomePage.class);
//                        Intent loginPage = new Intent(LoginPage.this, HomePage.class);
//                        startActivity(loginPage);
                    }
                    else {
                        Log.d("Login:", "Failed");
                        Toast.makeText(getApplicationContext(),"Email or Password invalid!", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    Log.d("error", "");
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(LoginPage.this, t.getMessage(), Toast.LENGTH_LONG).show();
                Log.d("Error : ", t.getMessage());
            }

        });
    }

    public void registerClick(View view) {
        new CustomActivity(this).start(RegisPage.class);
//        Intent intent = new Intent(LoginPage.this, RegisPage.class);
//        startActivity(intent);
    }
}
