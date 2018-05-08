package com.example.phundal.stackoverflow;

import android.support.v7.widget.RecyclerView;

import com.example.phundal.stackoverflow.model.UserProfileModel;

import java.util.List;

public interface IMainPresenter {
    void callApiForData(MainPresenter.IOnApiCallMade onApiCallMade);
    void bindUserModelsToAdapter(List<UserProfileModel> userProfileModelList, RecyclerView recyclerView);

    List<UserProfileModel> getUserModelsFromCache();
}
