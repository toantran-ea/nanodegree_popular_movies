package mobi.toan.popularmovies.utils;

import android.content.Context;
import android.util.Log;

import io.realm.Realm;
import io.realm.RealmResults;
import mobi.toan.popularmovies.Constants;
import mobi.toan.popularmovies.models.MovieDetails;
import mobi.toan.popularmovies.models.realm.FavouriteMovie;

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

    private DBUtils(Realm realm) {
        mDefaultRealm = realm;
    }

    public static void reset() {
        sDefaultInstance = null;
    }

    public boolean isFavourited(String movieId) {
        return mDefaultRealm.where(FavouriteMovie.class).equalTo(Constants.MOVIE_ID_DB, movieId).findAll().size() == 1;
    }

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

    public boolean removeFromFavourite(String movieId) {
        try {
            mDefaultRealm.beginTransaction();
            RealmResults<FavouriteMovie> realmResults = mDefaultRealm.where(FavouriteMovie.class).equalTo(Constants.MOVIE_ID_DB, movieId).findAll();
            realmResults.clear();
            mDefaultRealm.commitTransaction();
        }catch (Exception ex) {
            Log.e(TAG, ex.getMessage());
            return false;
        }
        return true;
    }

    public boolean deleteDB(Context context) {
        mDefaultRealm.close();
        return mDefaultRealm.deleteRealm(mDefaultRealm.getConfiguration());
    }
}
