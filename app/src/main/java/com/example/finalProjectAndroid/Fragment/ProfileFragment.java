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

    ImageButton logoutBtn;
    TextView firstName, lastName, email, mobileNumber;
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
        logoutBtn = mView.findViewById(R.id.logoutBtn);
        firstName = mView.findViewById(R.id.firstName);
        lastName = mView.findViewById(R.id.lastName);
        email = mView.findViewById(R.id.email);
        mobileNumber = mView.findViewById(R.id.mobileNunber);
        // Inflate the layout for this fragment
        return mView;
    }

    public void getProfile(){

        RequestBody rBody = RequestBody.create(MediaType.parse("text/plain"), sessionManager.getToken().getUserId());

        userUtil.getUser().getUserById(rBody).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                firstName.setText(response.body().getFirstName());
                lastName.setText(response.body().getLastName());
                email.setText(response.body().getEmail());
                mobileNumber.setText(response.body().getMobileNumber());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });

    }


}