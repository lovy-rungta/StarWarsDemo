package starwars.lovy.com.starwarsdemo.contract;

import android.content.Context;

import starwars.lovy.com.starwarsdemo.base.BasePresenter;
import starwars.lovy.com.starwarsdemo.base.BaseRouter;
import starwars.lovy.com.starwarsdemo.model.DetailsResponse;
import starwars.lovy.com.starwarsdemo.sync.network.Resource;

/**
 * Created by Lovy on 02-07-2018.
 */
public interface DetailsContract {
    interface View {
        void displayDetails(DetailsResponse detailsResponse);
        void showProgress();
        void hideProgress();
    }

    interface Route extends BaseRouter {
        void displayError(String message);
    }

    interface UseCase{
        void fetchDetails(String  url,OnCompletion onCompletion);
        interface OnCompletion{
            void onResponse(Resource<DetailsResponse> resource);
        }
    }

    interface Presenter extends BasePresenter<View, UseCase, Route> {
        void getDetails(String url);
    }
}

