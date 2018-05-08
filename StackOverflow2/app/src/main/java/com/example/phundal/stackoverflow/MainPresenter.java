package com.example.phundal.stackoverflow;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.phundal.stackoverflow.api.ApiService;
import com.example.phundal.stackoverflow.api.IApiService;
import com.example.phundal.stackoverflow.api.IRetrofitProvider;
import com.example.phundal.stackoverflow.api.RetrofitProvider;
import com.example.phundal.stackoverflow.model.UserProfileModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


public class MainPresenter implements IMainPresenter {
    private Context context;
    private IApiService apiService;

    public MainPresenter(Context context) {
        this.context = context;
        IRetrofitProvider retrofitProvider = new RetrofitProvider();
        this.apiService = new ApiService(retrofitProvider);
    }

    @Override
    public void callApiForData(final IOnApiCallMade onApiCallMade) {
        apiService.getUserProfilesAsync().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .onErrorReturn(new Function<Throwable, List<UserProfileModel>>() {
                    @Override
                    public List<UserProfileModel> apply(Throwable throwable) throws Exception {
                        onApiCallMade.onApiError(throwable);
                        return new ArrayList<>();
                    }
                })
                .subscribe(new Consumer<List<UserProfileModel>>() {
                    @Override
                    public void accept(List<UserProfileModel> userProfileModels) throws Exception {
                        onApiCallMade.userProfilesReturned(userProfileModels);
                    }
                });
    }

    @Override
    public void bindUserModelsToAdapter(List<UserProfileModel> userProfileModelList, RecyclerView recyclerView) {
        UserprofileAdapter userprofileAdapter = new UserprofileAdapter(userProfileModelList, context);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(userprofileAdapter);
    }

    @Override
    public List<UserProfileModel> getUserModelsFromCache() {
        return apiService.getUserModelsFromCache();
    }

    public interface IOnApiCallMade {
        void userProfilesReturned(List<UserProfileModel> userProfileModels);
        void onApiError(Throwable throwable);
    }

}
