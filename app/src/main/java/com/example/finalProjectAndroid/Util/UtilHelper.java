package com.example.finalProjectAndroid.Util;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class UtilHelper {
    public RequestBody convertToParam(String param){
        return RequestBody.create(
                MediaType.parse("text/plain"), param
        );
    }
}
