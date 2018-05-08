package com.example.phundal.stackoverflow.api;


import com.example.phundal.stackoverflow.model.ItemsResponse;
import com.example.phundal.stackoverflow.model.UserProfileModel;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import io.realm.Realm;
import io.realm.RealmResults;

public class ApiService implements IApiService {

    private IRestClient restClient;
    private Realm realm;

    public ApiService(IRetrofitProvider retrofitProvider) {
        this.restClient = retrofitProvider.getRetrofit().create(IRestClient.class);
    }

    private List<UserProfileModel> getListFromResults(UserProfileModel[] results) {
        List<UserProfileModel> userProfileModels = new ArrayList<>();
        userProfileModels.addAll(Arrays.asList(results));
        return userProfileModels;
    }

    @Override
    public List<UserProfileModel> getUserModelsFromCache() {
        realm = Realm.getDefaultInstance();
        final RealmResults<UserProfileModel> realmUserProfileModels = realm.where(UserProfileModel.class).findAll();
        UserProfileModel[] resultArr = realmUserProfileModels.toArray(new UserProfileModel[realmUserProfileModels.size()]);
        if (resultArr.length == 0) return null;
        return getListFromResults(resultArr);
    }

    private void saveUserProfileToRealm(final List<UserProfileModel> userProfileModels) {
        realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(userProfileModels);
            }
        });
        realm.commitTransaction();
    }

    @Override
    public Observable<List<UserProfileModel>> getUserProfilesAsync() {
        return restClient.getUserProfiles().map(new Function<ItemsResponse, List<UserProfileModel>>() {
            @Override
            public List<UserProfileModel> apply(ItemsResponse itemsResponse) throws Exception {
                if (itemsResponse != null) {
                    if (itemsResponse.getUserProfileModels() != null
                            && itemsResponse.getUserProfileModels().size() > 0) {
                        saveUserProfileToRealm(itemsResponse.getUserProfileModels());
                        return itemsResponse.getUserProfileModels();
                    }
                }
                return new ArrayList<>();
            }
        });
    }
}
