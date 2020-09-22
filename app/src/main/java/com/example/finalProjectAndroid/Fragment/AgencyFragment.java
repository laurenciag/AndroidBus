package com.example.finalProjectAndroid.Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.finalProjectAndroid.Entity.Agency;
import com.example.finalProjectAndroid.R;
import com.example.finalProjectAndroid.Util.AgencyUtil;
import com.example.finalProjectAndroid.Util.SessionManager;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AgencyFragment extends Fragment {

    TextView agName, agDetail;
    AgencyUtil agencyUtil;
    SessionManager sessionManager;

    public AgencyFragment() {
    }

    public AgencyFragment(Context ctx) {
        // Required empty public constructor
        this.agencyUtil = new AgencyUtil();
        this.sessionManager = new SessionManager(ctx);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        agName = getActivity().findViewById(R.id.agName);
//        agDetail = getActivity().findViewById(R.id.agDetail);

        getAgency();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View mView = inflater.inflate(R.layout.fragment_agency, container, false);
        agName = mView.findViewById(R.id.agName);
        agDetail = mView.findViewById(R.id.agDetail);

        // Inflate the layout for this fragment
        return mView;
    }

    public void getAgency (){
        RequestBody rBody = RequestBody.create(MediaType.parse("text/plain"), sessionManager.getToken().getAgencyId());

        agencyUtil.getAgency().getAgencyById(rBody).enqueue(new Callback<Agency>() {
            @Override
            public void onResponse(Call<Agency> call, Response<Agency> response) {
//                agName = getActivity().findViewById(R.id.agName).setText();
                agName.setText(response.body().getAgencyName());
                agDetail.setText(response.body().getAgencyDetails());

//                ((TextView) getActivity().findViewById(R.id.agName)).setText(response.body().getName());
            }

            @Override
            public void onFailure(Call<Agency> call, Throwable t) {

            }
        });
    }
}