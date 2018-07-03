package starwars.lovy.com.starwarsdemo.sync.di;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import starwars.lovy.com.starwarsdemo.sync.api.Service;

/**
 * Created by Lovy on 02-07-2018.
 */

@Module
public class SyncModule {

    private static final String BASE_URL = "https://swapi.co";

    @Provides
    @Singleton
    public Retrofit provideClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        final Gson gson = new GsonBuilder()
                .create();

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient.build())
                .build();
    }

    @Provides
    @Singleton
    public Service provideService(Retrofit retrofit) {
        return retrofit.create(Service.class);
    }

}

