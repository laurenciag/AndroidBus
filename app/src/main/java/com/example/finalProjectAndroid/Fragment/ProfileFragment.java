package com.example.finalProjectAndroid.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.finalProjectAndroid.Entity.User;
import com.example.finalProjectAndroid.Helper.CustomActivity;
import com.example.finalProjectAndroid.HomePage;
import com.example.finalProjectAndroid.LoginPage;
import com.example.finalProjectAndroid.R;
import com.example.finalProjectAndroid.Util.SessionManager;
import com.example.finalProjectAndroid.Util.UserUtil;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProfileFragment extends Fragment {

    TextView firstName, lastName, email, mobileNumber, name,logoutBtn;
    UserUtil userUtil;
    SessionManager sessionManager;
    Context context;

    public ProfileFragment(Context ctx) {
        // Required empty public constructor
        this.userUtil = new UserUtil();
        this.sessionManager = new SessionManager(ctx);
        this.context = ctx;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getProfile();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_profile, container, false);
        name = mView.findViewById(R.id.name);
        email = mView.findViewById(R.id.email);
        mobileNumber = mView.findViewById(R.id.mobileNumber);
        return mView;
    }

    public void getProfile(){

        RequestBody rBody = RequestBody.create(MediaType.parse("text/plain"), sessionManager.getToken().getUserId());

        userUtil.getUser().getUserById(rBody).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
                name.setText(user.getFirstName()+ " " + user.getLastName());
                email.setText(user.getEmail());
                mobileNumber.setText(user.getMobileNumber());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });

    }


}