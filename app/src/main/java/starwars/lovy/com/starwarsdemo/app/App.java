package starwars.lovy.com.starwarsdemo.app;

import android.app.Activity;
import android.app.Application;

import javax.inject.Inject;

import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import starwars.lovy.com.starwarsdemo.di.AppComponent;
import starwars.lovy.com.starwarsdemo.di.AppInjector;
import starwars.lovy.com.starwarsdemo.sync.api.Service;

/**
 * Created by Lovy on 02-07-2018.
 */

public class App extends Application implements HasActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

    private AppComponent appComponent;

    @Inject
    Service service;

      public AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = AppInjector.init(this);
        appComponent.inject(this);
    }

    @Override
    public DispatchingAndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }
}

