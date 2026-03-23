package com.nguyenntd.model.data;

import com.nguyenntd.globals.ConfigsGlobal;
import com.nguyenntd.model.LoginPOJO;
import com.nguyenntd.model.LoginPOJO_Lombok;


public class LoginPOJO_Builder {

    public static LoginPOJO_Lombok getDataLogin() {
        return LoginPOJO_Lombok.builder()
                .username(ConfigsGlobal.USERNAME)
                .password(ConfigsGlobal.PASSWORD)
                .build();
    }

}