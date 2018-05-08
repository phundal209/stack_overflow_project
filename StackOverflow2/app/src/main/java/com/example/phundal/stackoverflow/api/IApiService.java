package com.example.phundal.stackoverflow.api;


import com.example.phundal.stackoverflow.model.UserProfileModel;

import java.util.List;

import io.reactivex.Observable;

public interface IApiService {
    List<UserProfileModel> getUserModelsFromCache();

    Observable<List<UserProfileModel>> getUserProfilesAsync();
}
