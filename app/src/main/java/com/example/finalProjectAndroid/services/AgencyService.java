package com.example.finalProjectAndroid.services;

import com.example.finalProjectAndroid.Entity.Agency;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface AgencyService {
    @POST("agency/createAgency")
    Call<Agency> createAgency (@Body Agency inpAgency);

    @Multipart
    @POST("agency/getAgencyById")
    Call<Agency> getAgencyById (@Part("agencyId")RequestBody agencyId);
}
