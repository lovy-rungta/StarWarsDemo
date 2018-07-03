package starwars.lovy.com.starwarsdemo.components;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.util.Log;

import java.lang.ref.WeakReference;

import starwars.lovy.com.starwarsdemo.base.Utils;
import starwars.lovy.com.starwarsdemo.contract.CharactersContract;
import starwars.lovy.com.starwarsdemo.model.CharacterResponse;
import starwars.lovy.com.starwarsdemo.model.Results;
import starwars.lovy.com.starwarsdemo.sync.network.Resource;

/**
 * Created by Lovy on 02-07-2018.
 */

public class CharactersPresenter extends AndroidViewModel implements CharactersContract.Presenter {

    private WeakReference<CharactersContract.View> mView;
    private WeakReference<CharactersContract.UseCase> mInteractor;
    private WeakReference<CharactersContract.Route> mRouter;

    private static final String TAG = CharactersPresenter.class.getSimpleName();

    public CharactersPresenter(Application application) {
        super(application);
    }

    private CharactersContract.UseCase.OnCompletion onCompletion =
            new CharactersContract.UseCase.OnCompletion() {
                @Override
                public void onResponse(Resource<CharacterResponse> resource) {
                    switch (resource.status) {
                        case SUCCESS:
                            if (resource.data != null) {
                                mView.get().showCharacters(resource.data);
                            } else {
                                mRouter.get().displayError(resource.message);
                            }
                            break;
                        case ERROR:
                            mView.get().hideProgress();
                            mRouter.get().displayError(resource.message);
                            break;
                        default:
                            break;
                    }
                }

                @Override
                public void hideProgress() {
                    mView.get().hideProgress();
                }
            };

    @Override
    public void setView(CharactersContract.View view) {
        mView = new WeakReference<>(view);
    }

    @Override
    public void setInteractor(CharactersContract.UseCase interactor) {
        mInteractor = new WeakReference<>(interactor);
    }

    @Override
    public void setRouter(CharactersContract.Route router) {
        mRouter = new WeakReference<>(router);
    }

    @Override
    public void getCharacters() {
            mView.get().showProgress();
            mInteractor.get().fetchCharacters(1,onCompletion);
    }

    @Override
    public void characterClicked(Results results) {
     mRouter.get().navigateToDetails(results);
    }

}

