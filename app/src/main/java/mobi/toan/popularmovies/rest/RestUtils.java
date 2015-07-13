package mobi.toan.popularmovies.rest;

import java.util.HashMap;
import java.util.Map;

import mobi.toan.popularmovies.Constants;
import mobi.toan.popularmovies.Credentials;

/**
 * Created by toan on 7/10/15.
 */
public class RestUtils {
    public static Map<String, String> createDiscoverRequestParams(boolean byPopularity) {
        Map<String, String> discoverRequestParams = createBaseRequestParam();
        discoverRequestParams.put(Constants.PARAM_SORT_BY, (byPopularity ? Constants.PARAM_VALUE_POPULARITY_DESC : Constants.PARAM_VALUE_HIGHEST_RATE));
        return discoverRequestParams;
    }

    public static Map<String, String> createBaseRequestParam() {
        Map<String, String> map = new HashMap<>();
        map.put(Constants.PARAM_API_KEY, Credentials.API_KEY);
        return map;
    }

    public static String getPosterPath(String path, boolean isPhone) {
        return Constants.POSTER_PATH + (isPhone ? Constants.POSTER_PHONE : Constants.POSTER_TABLET) + path;
    }
}
