package starwars.lovy.com.starwarsdemo.base;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;

/**
 * Created by Lovy on 02-07-2018.
 */

public interface BasePresenter<V, I, R> extends LifecycleObserver {

    void setView(V view);

    void setInteractor(I interactor);

    void setRouter(R router);

}

