package mobi.toan.popularmovies.rest;

import java.util.Map;

import mobi.toan.popularmovies.models.MovieDetails;
import mobi.toan.popularmovies.models.PopularMovieData;
import mobi.toan.popularmovies.models.TrailerList;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.QueryMap;

/**
 * Created by toan on 7/9/15.
 */
public interface  TheMovieDBService {
    @GET("/discover/movie")
    void discoverMovies(@QueryMap Map<String, String> options, Callback<PopularMovieData> callback);

    @GET("/movie/{id}")
    void getMovieDetails(@QueryMap Map<String, String> options, @Path("id") String movieId, Callback<MovieDetails> callback);

    @GET("/movie/{id}/videos")
    void getTrailers(@QueryMap Map<String, String> options, @Path("id") String movieId, Callback<TrailerList> callback);
}
