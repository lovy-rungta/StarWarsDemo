package starwars.lovy.com.starwarsdemo.di;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import starwars.lovy.com.starwarsdemo.contract.CharactersContract;
import starwars.lovy.com.starwarsdemo.contract.DetailsContract;
import starwars.lovy.com.starwarsdemo.router.CharacterRouter;
import starwars.lovy.com.starwarsdemo.router.DetailsRouter;
import starwars.lovy.com.starwarsdemo.sync.api.Service;
import starwars.lovy.com.starwarsdemo.sync.interactor.CharacterInteractor;
import starwars.lovy.com.starwarsdemo.sync.interactor.DetailsInteractor;

/**
 * Created by Lovy on 02-07-2018.
 */

@Module
public class AppModule {

    private Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    public Application provideApplication() {
        return application;
    }

    @Provides
    public CharactersContract.Route provideCharacterRouter() {
        return new CharacterRouter();
    }

    @Provides
    public CharactersContract.UseCase provideCharactersUseCase(Service service) {
        return new CharacterInteractor(service);
    }

    @Provides
    public DetailsContract.Route provideDetailsRouter() {
        return new DetailsRouter();
    }

    @Provides
    public DetailsContract.UseCase provideDetailsUseCase(Service service) {
        return new DetailsInteractor(service);
    }

}

