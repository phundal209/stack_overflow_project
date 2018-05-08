package com.example.phundal.stackoverflow.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ItemsResponse {

    @SerializedName("items")
    private List<UserProfileModel> userProfileModels;

    public List<UserProfileModel> getUserProfileModels() {
        return userProfileModels;
    }

    public void setUserProfileModels(List<UserProfileModel> userProfileModels) {
        this.userProfileModels = userProfileModels;
    }
}
