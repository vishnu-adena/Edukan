package com.adena.productservice.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {
    private String userId;
    //private String otherData;

    public UserResponse(String userId, String otherData) {
        this.userId = userId;
        //this.otherData = otherData;
    }
}