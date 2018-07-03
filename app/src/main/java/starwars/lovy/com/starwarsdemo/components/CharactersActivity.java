package starwars.lovy.com.starwarsdemo.components;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import starwars.lovy.com.starwarsdemo.R;
import starwars.lovy.com.starwarsdemo.base.BaseActivity;
import starwars.lovy.com.starwarsdemo.base.Navigator;
import starwars.lovy.com.starwarsdemo.base.RecyclerViewAdapter;
import starwars.lovy.com.starwarsdemo.base.Utils;
import starwars.lovy.com.starwarsdemo.contract.CharactersContract;
import starwars.lovy.com.starwarsdemo.databinding.ActivityCharactersBinding;
import starwars.lovy.com.starwarsdemo.model.CharacterResponse;
import starwars.lovy.com.starwarsdemo.model.Results;

public class CharactersActivity extends BaseActivity implements CharactersContract.View, HasSupportFragmentInjector {

    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

    @Inject
    CharactersContract.Route mRouter;

    @Inject
    CharactersContract.UseCase mUseCase;

    private CharactersPresenter mPresenter;
    private ActivityCharactersBinding mBinding;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mBinding = DataBindingUtil.inflate(inflater, R.layout.activity_characters,
                null, false);
        final View view = mBinding.getRoot();
        setContentView(view);
        mRouter.setActivity(this);
        mPresenter = ViewModelProviders.of(this).get(CharactersPresenter.class);
        mPresenter.setRouter(mRouter);
        mPresenter.setInteractor(mUseCase);
        mPresenter.setView(this);
        getLifecycle().addObserver(mPresenter);
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecyclerViewAdapter(mPresenter);
        getCharacters();
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

    public void getCharacters() {
        if (Utils.checkIfInternetConnected(this)) {
            mPresenter.getCharacters();
        } else {
            mRouter.displayError(getString(R.string.no_internet));
        }
    }

    public List<Results> data = new ArrayList<>();

    @Override
    public void showCharacters(CharacterResponse response) {
        data.addAll(response.getResults());
        adapter.setData(data);
        mBinding.recyclerView.setAdapter(adapter);
        mBinding.executePendingBindings();
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
