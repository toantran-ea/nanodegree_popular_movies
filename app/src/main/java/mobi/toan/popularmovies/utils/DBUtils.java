package mobi.toan.popularmovies.utils;

import io.realm.Realm;

/**
 * Created by toan on 7/30/15.
 */
public class DBUtils {
    private static DBUtils ourInstance = new DBUtils();

    public static DBUtils getInstance() {
        return ourInstance;
    }

    private Realm mRealm;

    private DBUtils() {
        mRealm = Realm.getDefaultInstance();
    }

    public boolean isFavourited() {
        return false;
    }

    public boolean addToFavourite() {
        return false;
    }

    public boolean removeFromFavourite() {
        return false;
    }
}
