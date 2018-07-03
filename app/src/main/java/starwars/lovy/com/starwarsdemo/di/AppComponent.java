package starwars.lovy.com.starwarsdemo.di;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;
import starwars.lovy.com.starwarsdemo.app.App;
import starwars.lovy.com.starwarsdemo.components.CharactersActivity;
import starwars.lovy.com.starwarsdemo.components.DetailsActivity;
import starwars.lovy.com.starwarsdemo.sync.di.SyncModule;

/**
 * Created by Lovy on 02-07-2018.
 */

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        MainActivityModule.class,
        CharactersActivityModule.class,
        DetailsActivityModule.class,
//        CanvasActivityModule.class,
//        LoginActivityModule.class,
//        DashboardActivityModule.class,
//        AssignmentActivityModule.class,
//        AddMeasurementActivityModule.class,
        AppModule.class,
        SyncModule.class})
public interface AppComponent {

    void inject(App app);

}
