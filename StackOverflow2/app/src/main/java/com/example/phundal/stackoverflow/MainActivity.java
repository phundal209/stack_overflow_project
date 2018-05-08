package com.example.phundal.stackoverflow;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.phundal.stackoverflow.model.UserProfileModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainViewHandler {
    private IMainPresenter mainPresenter;
    private final String TAG = MainActivity.class.getSimpleName();
    private ProgressBar progressBar;

    @BindView(R.id.recyclerView)
    public RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mainPresenter = new MainPresenter(this);

        showProgress();
        if (mainPresenter.getUserModelsFromCache() == null) {
            mainPresenter.callApiForData(new MainPresenter.IOnApiCallMade() {
                @Override
                public void userProfilesReturned(List<UserProfileModel> userProfileModels) {
                    mainPresenter.bindUserModelsToAdapter(userProfileModels, recyclerView);
                    hideProgress();
                }

                @Override
                public void onApiError(Throwable throwable) {
                    Log.e(TAG, "error while parsing api call", throwable);
                }
            });
        } else {
            mainPresenter.bindUserModelsToAdapter(mainPresenter.getUserModelsFromCache(), recyclerView);
            hideProgress();
        }
    }

    @Override
    public void showProgress() {
        if (progressBar == null) {
            progressBar = new ProgressBar(this);
            progressBar.setIndeterminate(true);
        }
    }

    @Override
    public void hideProgress() {
        if (progressBar != null) {
            progressBar.setVisibility(View.GONE);
        }
    }
}
