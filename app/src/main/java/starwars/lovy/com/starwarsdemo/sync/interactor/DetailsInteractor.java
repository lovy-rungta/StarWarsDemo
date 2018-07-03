package starwars.lovy.com.starwarsdemo.sync.interactor;

import android.util.Log;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import starwars.lovy.com.starwarsdemo.contract.DetailsContract;
import starwars.lovy.com.starwarsdemo.model.DetailsResponse;
import starwars.lovy.com.starwarsdemo.sync.api.Service;
import starwars.lovy.com.starwarsdemo.sync.network.AppExecutors;
import starwars.lovy.com.starwarsdemo.sync.network.Resource;

/**
 * Created by Lovy on 02-07-2018.
 */

public class DetailsInteractor implements DetailsContract.UseCase {

    private final AppExecutors mExecutors;
    private Service mService;
    private static final String TAG = DetailsInteractor.class.getSimpleName();

    public DetailsInteractor(Service service) {
        this.mService = service;
        this.mExecutors = new AppExecutors();
    }

    @Override
    public void fetchDetails(final String url, final OnCompletion onCompletion) {
        mExecutors.networkIO().execute(new Runnable() {
            @Override
            public void run() {
                mService.fetchDetails(url).enqueue(new Callback<DetailsResponse>() {
                    @Override
                    public void onResponse(Call<DetailsResponse> call, final Response<DetailsResponse> response) {
                        if (response.isSuccessful()) {
                            mExecutors.mainThread().execute(new Runnable() {
                                @Override
                                public void run() {
                                    Log.v(TAG, "fetchDetails: success:" + response.body().getName());
                                    onCompletion.onResponse(Resource.success(response.body()));
                                }
                            });
                        } else {
                            try {
                                final String string = response.errorBody().string();
                                Log.e(TAG, "fetchDetails: errorBody: \n" + string);
                            } catch (IOException | NullPointerException e) {
                                Log.e(TAG, "fetchDetails: errorBody: \n" + e.getMessage());
                            }
                            mExecutors.mainThread().execute(new Runnable() {
                                @Override
                                public void run() {
                                    DetailsResponse resp = null;
                                    onCompletion.onResponse(Resource.error("Failed to fetch details ", resp));
                                }
                            });
                        }
                    }

                    @Override
                    public void onFailure(Call<DetailsResponse> call, final Throwable t) {
                        mExecutors.mainThread().execute(new Runnable() {
                            @Override
                            public void run() {
                                Log.e(TAG, "fetchDetails: onFailure: \n", t);
                                DetailsResponse resp = null;
                                onCompletion.onResponse(Resource.error(t.getMessage(), resp));
                            }
                        });
                    }
                });
            }
        });
    }
}

