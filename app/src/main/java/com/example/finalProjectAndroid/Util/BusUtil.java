package com.example.finalProjectAndroid.Util;

import com.example.finalProjectAndroid.services.AgencyService;
import com.example.finalProjectAndroid.services.BusService;
import com.example.finalProjectAndroid.services.ClientService;

public class BusUtil extends UtilHelper {
    public BusUtil() {
    }

    public BusService getBus(){return ClientService.getClient().create(BusService.class);}
}
