package mobi.toan.popularmovies.rest;

import android.util.Log;

import com.squareup.okhttp.OkHttpClient;

import mobi.toan.popularmovies.Constants;
import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
 * Created by toan on 7/9/15.
 */
public class TheMovieDBAPI {
    private static final String TAG = TheMovieDBAPI.class.getSimpleName();

    private static TheMovieDBAPI sInstance = new TheMovieDBAPI();
    private TheMovieDBService mService;

    private TheMovieDBAPI() {
        OkHttpClient client = new OkHttpClient();
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(Constants.API_URL)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setLog(new RestAdapter.Log() {
                    @Override
                    public void log(String message) {
                        Log.d(TAG, message);
                    }
                }).setConverter(new mobi.toan.popularmovies.rest.JacksonConverter())
                .setClient(new OkClient(client))
                .build();

        mService = restAdapter.create(TheMovieDBService.class);
    }

    public static TheMovieDBAPI getInstance() {
        return sInstance;
    }

    public void setService(TheMovieDBService service) {
        mService = service;
    }

    public TheMovieDBService getService() {
        return mService;
    }
}
