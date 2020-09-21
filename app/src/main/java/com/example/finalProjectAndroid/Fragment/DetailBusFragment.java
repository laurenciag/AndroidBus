package com.example.finalProjectAndroid.Fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalProjectAndroid.Adapter.BusAdapter;
import com.example.finalProjectAndroid.Entity.Bus;
import com.example.finalProjectAndroid.Helper.CustomActivity;
import com.example.finalProjectAndroid.HomePage;
import com.example.finalProjectAndroid.R;
import com.example.finalProjectAndroid.Util.BusUtil;
import com.example.finalProjectAndroid.Util.SessionManager;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailBusFragment extends Fragment {

    BusUtil busUtil;
    SessionManager sessionManager;
    TextView busCodeDetail, busMakeDetail, busCapacityDetail;
    ImageView backToHome;
    String busId;
    FragmentManager fragmentManager;
    Context ctx;

    public DetailBusFragment(Context ctx, String busId) {
        this.ctx = ctx;
        // Required empty public constructor
        this.busUtil = new BusUtil();
        this.sessionManager = new SessionManager(ctx);
        this.busId = busId;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mView = inflater.inflate(R.layout.fragment_detail_bus, container, false);
        busCodeDetail = mView.findViewById(R.id.busCodeDetail);
        busMakeDetail = mView.findViewById(R.id.busMakeDetail);
        busCapacityDetail = mView.findViewById(R.id.busCapacity);
        backToHome = mView.findViewById(R.id.backToHome);
        getDetailBus(busId);
        return mView;
    }

    public void getDetailBus(String busId){

        busUtil.getBus().getBusById(busUtil.convertToParam(busId)).enqueue(new Callback<Bus>() {
            @Override
            public void onResponse(Call<Bus> call, Response<Bus> response) {
                Bus bus = response.body();
                busCodeDetail.setText(bus.getCode());
                busMakeDetail.setText(bus.getMake());
                busCapacityDetail.setText(bus.getCapacity());

                backToHome.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Fragment fragment = new BusFragment(ctx);
                        getFragmentManager().beginTransaction().replace(R.id.layoutFragment, fragment).commit();
                    }
                });
            }

            @Override
            public void onFailure(Call<Bus> call, Throwable t) {
            }
        });
    }


}