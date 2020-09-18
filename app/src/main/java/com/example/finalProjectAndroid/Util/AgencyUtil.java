package com.example.finalProjectAndroid.Util;

import com.example.finalProjectAndroid.services.AgencyService;
import com.example.finalProjectAndroid.services.ClientService;

public class AgencyUtil {
    public AgencyUtil() {
    }

    public AgencyService getAgency() {return ClientService.getClient().create(AgencyService.class);}
}
