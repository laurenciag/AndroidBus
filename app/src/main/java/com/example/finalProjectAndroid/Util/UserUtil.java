package com.example.finalProjectAndroid.Util;

import com.example.finalProjectAndroid.services.ClientService;
import com.example.finalProjectAndroid.services.UserService;

public class UserUtil {
    public UserUtil(){

    }

    public UserService getUser() {
        return ClientService.getClient().create(UserService.class);
    }
}
