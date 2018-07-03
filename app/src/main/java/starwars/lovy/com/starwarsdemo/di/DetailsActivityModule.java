package starwars.lovy.com.starwarsdemo.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import starwars.lovy.com.starwarsdemo.components.DetailsActivity;

/**
 * Created by Lovy on 02-07-2018.
 */

@Module
public abstract class DetailsActivityModule {

    @ContributesAndroidInjector(modules = FragmentBuildersModule.class)
    abstract DetailsActivity contributeDetailsActivity();

}
