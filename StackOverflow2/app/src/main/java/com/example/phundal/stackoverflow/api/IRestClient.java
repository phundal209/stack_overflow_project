package com.example.phundal.stackoverflow.api;

import com.example.phundal.stackoverflow.model.ItemsResponse;
import com.example.phundal.stackoverflow.model.UserProfileModel;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by phundal on 5/7/18.
 */

public interface IRestClient {

    @GET("/2.2/users?site=stackoverflow")
    Observable<ItemsResponse> getUserProfiles();
}
