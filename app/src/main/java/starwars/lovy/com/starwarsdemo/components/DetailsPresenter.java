package starwars.lovy.com.starwarsdemo.components;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;

import java.lang.ref.WeakReference;

import starwars.lovy.com.starwarsdemo.contract.DetailsContract;
import starwars.lovy.com.starwarsdemo.model.DetailsResponse;
import starwars.lovy.com.starwarsdemo.sync.network.Resource;

/**
 * Created by Lovy on 02-07-2018.
 */

public class DetailsPresenter extends AndroidViewModel implements DetailsContract.Presenter {

    private WeakReference<DetailsContract.View> mView;
    private WeakReference<DetailsContract.UseCase> mInteractor;
    private WeakReference<DetailsContract.Route> mRouter;

    private static final String TAG = CharactersPresenter.class.getSimpleName();

    public DetailsPresenter(Application application) {
        super(application);
    }

    private DetailsContract.UseCase.OnCompletion onCompletion =
            new DetailsContract.UseCase.OnCompletion() {
                @Override
                public void onResponse(Resource<DetailsResponse> resource) {
                    mView.get().hideProgress();
                    switch (resource.status) {
                        case SUCCESS:
                            if (resource.data != null) {
                                mView.get().displayDetails(resource.data);
                            } else {
                                mRouter.get().displayError(resource.message);
                            }
                            break;
                        case ERROR:
                            mRouter.get().displayError(resource.message);
                            break;
                        default:
                            break;
                    }
                }
            };

    @Override
    public void setView(DetailsContract.View view) {
        mView = new WeakReference<>(view);
    }

    @Override
    public void setInteractor(DetailsContract.UseCase interactor) {
        mInteractor = new WeakReference<>(interactor);
    }

    @Override
    public void setRouter(DetailsContract.Route router) {
        mRouter = new WeakReference<>(router);
    }

    @Override
    public void getDetails(String url) {
        mView.get().showProgress();
        mInteractor.get().fetchDetails(url, onCompletion);
    }

}
