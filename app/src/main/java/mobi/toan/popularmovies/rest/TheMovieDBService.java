package mobi.toan.popularmovies.rest;

import java.util.Map;

import mobi.toan.popularmovies.models.PopularMovieData;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.QueryMap;

/**
 * Created by toan on 7/9/15.
 */
public interface  TheMovieDBService {
    @GET("/discover/movie")
    void discoverMovies(@QueryMap Map<String, String> options, Callback<PopularMovieData> callback);
}
