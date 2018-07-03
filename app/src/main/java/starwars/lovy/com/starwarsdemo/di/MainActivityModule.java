package starwars.lovy.com.starwarsdemo.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import starwars.lovy.com.starwarsdemo.main.MainActivity;

/**
 * Created by Lovy on 02-07-2018.
 */

@Module
public abstract class MainActivityModule {

    @ContributesAndroidInjector(modules = FragmentBuildersModule.class)
    abstract MainActivity contributeMainActivity();

}
