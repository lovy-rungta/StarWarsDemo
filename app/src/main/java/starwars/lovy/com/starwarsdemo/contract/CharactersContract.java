package starwars.lovy.com.starwarsdemo.contract;

import android.content.Context;

import java.util.List;

import starwars.lovy.com.starwarsdemo.base.BasePresenter;
import starwars.lovy.com.starwarsdemo.base.BaseRouter;
import starwars.lovy.com.starwarsdemo.model.CharacterResponse;
import starwars.lovy.com.starwarsdemo.model.Results;
import starwars.lovy.com.starwarsdemo.sync.network.Resource;

/**
 * Created by Lovy on 02-07-2018.
 */

public interface CharactersContract {
    interface View {
        void showCharacters(CharacterResponse response);
        void showProgress();
        void hideProgress();
    }

    interface Route extends BaseRouter {
        void displayError(String message);
        void navigateToDetails(Results result);
    }

    interface UseCase {
        void fetchCharacters(int page, OnCompletion onCompletion);
        interface OnCompletion {
            void onResponse(Resource<CharacterResponse> resource);
            void hideProgress();
        }
    }

    interface Presenter extends BasePresenter<View, UseCase, Route> {
        void getCharacters();
        void characterClicked(Results results);
    }
}
