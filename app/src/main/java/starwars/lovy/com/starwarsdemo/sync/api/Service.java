package starwars.lovy.com.starwarsdemo.sync.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;
import starwars.lovy.com.starwarsdemo.model.CharacterResponse;
import starwars.lovy.com.starwarsdemo.model.DetailsResponse;

/**
 * Created by Lovy on 02-07-2018.
 */

public interface Service {

    @GET("/api/people")
    Call<CharacterResponse> fetchCharacters(@Query("page") int page) ;

    @GET()
    Call<DetailsResponse> fetchDetails(@Url String url) ;
}

