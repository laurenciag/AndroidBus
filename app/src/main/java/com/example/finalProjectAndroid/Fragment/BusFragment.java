package com.example.finalProjectAndroid.Fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.finalProjectAndroid.Adapter.BusAdapter;
import com.example.finalProjectAndroid.Entity.Bus;
import com.example.finalProjectAndroid.HomePage;
import com.example.finalProjectAndroid.R;
import com.example.finalProjectAndroid.Util.BusUtil;
import com.example.finalProjectAndroid.Util.SessionManager;
import com.example.finalProjectAndroid.Util.UtilHelper;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BusFragment extends Fragment {

    TextView busCode, busMake;
    BusUtil busUtil;
    SessionManager sessionManager;
    UtilHelper utilHelper;
    RecyclerView recyclerView;
    Activity activity;
    Fragment fragment;

    public BusFragment(Activity activity) {
        // Required empty public constructor
        this.sessionManager = new SessionManager(activity.getApplicationContext());
        this.busUtil = new BusUtil();
        this.activity = activity;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_bus, container, false);

        recyclerView = mView.findViewById(R.id.listBus);
        // Inflate the layout for this fragment
        getListBus();
        return mView;
    }

    public void getListBus() {

        busUtil.getBus()
                .getBusByAgencyId(busUtil.convertToParam(sessionManager.getToken().getAgencyId()))
                .enqueue(new Callback<List<Bus>>() {
                    @Override
                    public void onResponse(Call<List<Bus>> call, Response<List<Bus>> response) {
                        List<Bus> buses = response.body();
                        BusAdapter busAdapter = new BusAdapter(buses,getFragmentManager());


                        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(activity.getApplicationContext());
                        recyclerView.setHasFixedSize(true);
                        recyclerView.setLayoutManager(mLayoutManager);
//                        recyclerView.addItemDecoration(new DividerItemDecoration(activity.getApplicationContext(),LinearLayoutManager.VERTICAL));
                        recyclerView.setItemAnimator(new DefaultItemAnimator());
                        busAdapter.notifyDataSetChanged();
                        recyclerView.setAdapter(busAdapter);
                    }

                    @Override
                    public void onFailure(Call<List<Bus>> call, Throwable t) {

                    }
                });
    }


}