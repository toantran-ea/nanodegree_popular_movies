package mobi.toan.popularmovies.utils;

import android.util.Log;

import java.util.List;

import io.realm.Realm;
import mobi.toan.popularmovies.Constants;
import mobi.toan.popularmovies.models.MovieDetails;
import mobi.toan.popularmovies.models.realm.FavouriteMovie;
import mobi.toan.popularmovies.models.realm.Trailer;

/**
 * Created by toan on 7/30/15.
 */
public class DBUtils {
    private static String TAG = DBUtils.class.getSimpleName();
    private static DBUtils sDefaultInstance = new DBUtils();
    public static DBUtils getDefaultInstance() {
        if(sDefaultInstance == null) {
            sDefaultInstance = new DBUtils();
        }
        return sDefaultInstance;
    }

    private Realm mDefaultRealm;

    private DBUtils() {
        mDefaultRealm = Realm.getDefaultInstance();
    }

    /**
     * Resets instance of DBUtils.
     */
    public static void reset() {
        sDefaultInstance = null;
    }

    /**
     * Checks if a movie(by movieId) is already added as favourite.
     * @param movieId
     * @return
     */
    public boolean isFavourited(String movieId) {
        return mDefaultRealm.where(FavouriteMovie.class).equalTo(Constants.MOVIE_ID_DB, movieId).findAll().size() == 1;
    }

    /**
     * Adds a movie into favourite list.
     * @param movieDetails
     * @return
     */
    public boolean addToFavourite(MovieDetails movieDetails) {
        FavouriteMovie favouriteMovie = new FavouriteMovie(movieDetails);
        try {
            mDefaultRealm.beginTransaction();
            mDefaultRealm.copyToRealmOrUpdate(favouriteMovie);
            mDefaultRealm.commitTransaction();
        }catch (Exception ex) {
            Log.e(TAG, ex.getMessage());
            return false;
        }
        return true;
    }

    /**
     * Removes a movie from favourite list.
     * @param movieId
     * @return
     */
    public boolean removeFromFavourite(String movieId) {
        try {
            mDefaultRealm.beginTransaction();
            mDefaultRealm.where(FavouriteMovie.class).equalTo(Constants.MOVIE_ID_DB, movieId).findAll().clear();
            mDefaultRealm.where(Trailer.class).equalTo(Constants.TRAILER_MOVIE_ID, movieId).findAll().clear();
            mDefaultRealm.commitTransaction();
        }catch (Exception ex) {
            Log.e(TAG, ex.getMessage());
            return false;
        }
        return true;
    }

    public FavouriteMovie getFavouriteMovie(String moviedId) {
        return mDefaultRealm.where(FavouriteMovie.class).equalTo("id", moviedId).findFirst();
    }

    public List<Trailer> getTrailerOfFavoriteMovie(String movieId) {
        return mDefaultRealm.where(Trailer.class).equalTo("movieId", movieId).findAll();
    }

    /**
     * Deletes the database.
     * @return
     */
    public boolean deleteDB() {
        mDefaultRealm.close();
        return mDefaultRealm.deleteRealm(mDefaultRealm.getConfiguration());
    }

    /**
     * Return all local-stored favourite movies.
     * @return
     */
    public List<FavouriteMovie> getAllFavouriteMovies() {
        return mDefaultRealm.where(FavouriteMovie.class).findAll();
    }
}
