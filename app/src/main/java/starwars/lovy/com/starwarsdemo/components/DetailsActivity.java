package starwars.lovy.com.starwarsdemo.components;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import javax.inject.Inject;

import butterknife.ButterKnife;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import starwars.lovy.com.starwarsdemo.R;
import starwars.lovy.com.starwarsdemo.base.BaseActivity;
import starwars.lovy.com.starwarsdemo.base.Navigator;
import starwars.lovy.com.starwarsdemo.base.Utils;
import starwars.lovy.com.starwarsdemo.contract.DetailsContract;
import starwars.lovy.com.starwarsdemo.databinding.ActivityDetailsBinding;
import starwars.lovy.com.starwarsdemo.model.DetailsResponse;

public class DetailsActivity extends BaseActivity implements DetailsContract.View ,HasSupportFragmentInjector{

    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

    @Inject
    DetailsContract.Route mRouter;

    @Inject
    DetailsContract.UseCase mUseCase;

    private DetailsPresenter mPresenter;
    private ActivityDetailsBinding mBinding;

    private String url ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        url = getIntent().getExtras().getString("CHARACTER");
        LayoutInflater inflater = (LayoutInflater)getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        mBinding = DataBindingUtil.inflate(inflater, R.layout.activity_details,
                null, false);
        final View view = mBinding.getRoot();
        setContentView(view);
        mRouter.setActivity(this);
        mPresenter = ViewModelProviders.of(this).get(DetailsPresenter.class);
        mPresenter.setRouter(mRouter);
        mPresenter.setInteractor(mUseCase);
        mPresenter.setView(this);
        getLifecycle().addObserver(mPresenter);
        getDetails();
    }

    public void getDetails() {
        if(Utils.checkIfInternetConnected(this)){
            mPresenter.getDetails(url);
        }else{
            mRouter.displayError(getString(R.string.no_internet));
        }
    }

    @NonNull
    @Override
    protected Navigator getNavigator() {
        return (Navigator) mRouter;
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjector;
    }

    @Override
    public void displayDetails(DetailsResponse detailsResponse) {
        if(detailsResponse!=null){
            mBinding.setDetails(detailsResponse);
            mBinding.executePendingBindings();
        }
    }

    @Override
    public void showProgress() {
        mBinding.setFetching(true);
    }

    @Override
    public void hideProgress() {
        mBinding.setFetching(false);
    }
}
