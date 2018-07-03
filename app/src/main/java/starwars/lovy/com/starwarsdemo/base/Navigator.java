package starwars.lovy.com.starwarsdemo.base;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import java.lang.ref.WeakReference;

/**
 * Created by Lovy on 02-07-2018.
 */

public class Navigator implements BaseRouter {

    protected WeakReference<Activity> activity;

    public void setActivity(Activity activity) {
        this.activity = new WeakReference<>(activity);
    }

    @Override
    public Activity getActivity() {
        return activity.get();
    }

}
