package starwars.lovy.com.starwarsdemo.base;

import android.app.Activity;
import android.content.Context;
import android.view.View;

/**
 * Created by Lovy on 02-07-2018.
 */


public interface BaseRouter {

    void setActivity(Activity activity);

    Activity getActivity();

}
