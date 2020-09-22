package com.example.finalProjectAndroid;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.finalProjectAndroid.Entity.Agency;
import com.example.finalProjectAndroid.Entity.User;
import com.example.finalProjectAndroid.Util.AgencyUtil;
import com.example.finalProjectAndroid.Util.UserUtil;
import com.example.finalProjectAndroid.services.UserService;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RegisPage extends AppCompatActivity {

    UserUtil userUtil;
//    User user;
//    String firstName, lastName, password, mobileNumber, email;
    EditText edPassword,rePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_regis_page);
        userUtil = new UserUtil();

//        String firstName = ((EditText) findViewById(R.id.inpFirstName)).getText().toString();
//        String lastName = ((EditText) findViewById(R.id.inpLastName)).getText().toString();
        edPassword = findViewById(R.id.inpPassword);
//        String password = edPassword.getText().toString();
        rePassword =  findViewById(R.id.inpCheckPass);
        rePassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String typePass = edPassword.getText().toString();
                String typeRePass = s.toString();
                Log.d("test", s.toString());
                Log.d("test1", typePass);
                if (typeRePass.length() == 0) {
                    Toast.makeText(getApplicationContext(), "Please enter the password", Toast.LENGTH_SHORT).show();
                } else {
                    if (typeRePass.equals(typePass)) {
                        Toast.makeText(getApplicationContext(), "Password match", Toast.LENGTH_LONG).show();
                        Log.d("password", "match");
                    } else {
                        Toast.makeText(getApplicationContext(), "Password does not match", Toast.LENGTH_SHORT).show();
                        Log.d("password", "not match");
                    }
//                Log.d("password","passworddd");
//                if (s.length()==0){
//                    Toast.makeText(getApplicationContext(), "Please fill the password", Toast.LENGTH_LONG).show();
//                }
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
//        String mobileNumber = ((EditText) findViewById(R.id.inpMobileNumber)).getText().toString();
//        String email = ((EditText) findViewById(R.id.inpEmail)).getText().toString();
//        if (!emailValid(email)) {
//            Toast.makeText(getApplicationContext(), "Email valid", Toast.LENGTH_LONG).show();
//        } else {
//            Toast.makeText(getApplicationContext(),"Please enter valid email address", Toast.LENGTH_LONG).show();
//        }

    }


    public void registerUser(View v) {
        String firstName = ((EditText) findViewById(R.id.inpFirstName)).getText().toString();
        String lastName = ((EditText) findViewById(R.id.inpLastName)).getText().toString();
        String password = edPassword.getText().toString();
        String mobileNumber = ((EditText) findViewById(R.id.inpMobileNumber)).getText().toString();
        String email = ((EditText) findViewById(R.id.inpEmail)).getText().toString();
        User user = new User(firstName, lastName, password, mobileNumber, email);

//        if (confirmPassword(password,rePassword)){
//            checkEmail(user);
//        }
        checkEmail(user);


    }

    public boolean emailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public void doRegister(User user) {
//        UserUtil userUtil = new UserUtil();
        userUtil.getUser().register(user).enqueue(new Callback<User>() {

            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                AgencyUtil agencyUtil = new AgencyUtil();
                User userRsp = response.body();

                String agencyName = ((EditText) findViewById(R.id.inpAgencyName)).getText().toString();
                String agencyDetail = ((EditText) findViewById(R.id.inpAgencyName)).getText().toString();
                Agency agency = new Agency(agencyName, agencyDetail, userRsp.getId());
                agency.setOwner(userRsp.getId());

                agencyUtil.getAgency().createAgency(agency).enqueue(new Callback<Agency>() {
                    @Override
                    public void onResponse(Call<Agency> call, Response<Agency> response) {
                        Intent intent = new Intent(RegisPage.this, LoginPage.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<Agency> call, Throwable t) {
                        Log.d("failed", t.getMessage());
                        Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();

                    }
                });
                Toast.makeText(getApplicationContext(), response.body().toString(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });
    }

    public void checkEmail(User user) {
//        UserUtil userUtil = new UserUtil();
        userUtil.getUser().checkEmail(user).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User userResp = response.body();
                if (userResp.getId()==null) {
                    doRegister(user);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Email has been registered", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }

//    public boolean confirmPassword(String password, CharSequence rePassword) {
//        Pattern pattern = Pattern.compile(password, Pattern.CASE_INSENSITIVE);
//        Matcher match = pattern.matcher(rePassword);
//
//        if (!match.matches()) {
//            Toast.makeText(getApplicationContext(), "Password does not match", Toast.LENGTH_LONG).show();
//            return false;
//        }
//        return true;
//    }


}