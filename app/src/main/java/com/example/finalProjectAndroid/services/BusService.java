package com.example.finalProjectAndroid.services;

import com.example.finalProjectAndroid.Entity.Bus;

import java.util.List;

import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface BusService {
    @Multipart
    @POST("bus/getAllBusByAgencyId")
//    @POST ("/getAllBus")
    Call<List<Bus>> getBusByAgencyId(@Part("agencyId")RequestBody AgencyId);

    @Multipart
    @POST("bus/getBusById")
    Call<Bus> getBusById(@Part("busId")RequestBody busId);
}
