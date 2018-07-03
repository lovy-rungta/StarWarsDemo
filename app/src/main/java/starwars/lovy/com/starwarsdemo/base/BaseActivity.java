package starwars.lovy.com.starwarsdemo.base;

import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import dagger.android.support.HasSupportFragmentInjector;

/**
 * Created by Lovy on 02-07-2018.
 */

public abstract class BaseActivity extends AppCompatActivity implements LifecycleRegistryOwner {

    private static final String TAG = BaseActivity.class.getSimpleName();

    LifecycleRegistry mLifecycleRegistry = new LifecycleRegistry(this);

    @Override
    public LifecycleRegistry getLifecycle() {
        return mLifecycleRegistry;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        getNavigator().setActivity(this);
    }

    protected abstract @NonNull
    Navigator getNavigator();

}
