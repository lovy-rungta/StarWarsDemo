package starwars.lovy.com.starwarsdemo.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import starwars.lovy.com.starwarsdemo.components.CharactersActivity;

/**
 * Created by Lovy on 02-07-2018.
 */

@Module
public abstract class CharactersActivityModule {

    @ContributesAndroidInjector(modules = FragmentBuildersModule.class)
    abstract CharactersActivity contributeCharactersActivity();

}
