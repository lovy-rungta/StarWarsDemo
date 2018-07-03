package starwars.lovy.com.starwarsdemo.sync.interactor;

import android.util.Log;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import starwars.lovy.com.starwarsdemo.contract.CharactersContract;
import starwars.lovy.com.starwarsdemo.model.CharacterResponse;
import starwars.lovy.com.starwarsdemo.sync.api.Service;
import starwars.lovy.com.starwarsdemo.sync.network.AppExecutors;
import starwars.lovy.com.starwarsdemo.sync.network.Resource;

/**
 * Created by Lovy on 02-07-2018.
 */

public class CharacterInteractor implements CharactersContract.UseCase {

    private final AppExecutors mExecutors;
    private Service mService;
    private static final String TAG = CharacterInteractor.class.getSimpleName();
    int count = 1;

    public CharacterInteractor(Service service) {
        this.mService = service;
        this.mExecutors = new AppExecutors();
    }

    @Override
    public void fetchCharacters(final int page, final OnCompletion onCompletion) {
        mExecutors.networkIO().execute(new Runnable() {
            @Override
            public void run() {
                mService.fetchCharacters(page).enqueue(new Callback<CharacterResponse>() {
                    @Override
                    public void onResponse(Call<CharacterResponse> call, final Response<CharacterResponse> response) {
                        if (response.isSuccessful()) {
                            mExecutors.mainThread().execute(new Runnable() {
                                @Override
                                public void run() {
                                    Log.v(TAG, "fetchCharacters: success:" + response.body().getCount());
                                    if(response.body().getNext()!=null){
                                        count++;
                                        fetchCharacters(count,onCompletion);
                                    }else{
                                        onCompletion.hideProgress();
                                    }
                                    onCompletion.onResponse(Resource.success(response.body()));
                                }
                            });
                        } else {
                            try {
                                final String string = response.errorBody().string();
                                Log.e(TAG, "fetchCharacters: errorBody: \n" + string);
                            } catch (IOException | NullPointerException e) {
                                Log.e(TAG, "fetchCharacters: errorBody: \n" + e.getMessage());
                            }
                            mExecutors.mainThread().execute(new Runnable() {
                                @Override
                                public void run() {
                                    CharacterResponse resp = null;
                                    onCompletion.onResponse(Resource.error("Failed to fetchCharacters", resp));
                                }
                            });
                        }
                    }

                    @Override
                    public void onFailure(Call<CharacterResponse> call, final Throwable t) {
                        mExecutors.mainThread().execute(new Runnable() {
                            @Override
                            public void run() {
                                Log.e(TAG, "fetchCharacters: onFailure: \n", t);
                                CharacterResponse resp = null;
                                onCompletion.onResponse(Resource.error(t.getMessage(), resp));
                            }
                        });
                    }
                });
            }
        });
    }
}
