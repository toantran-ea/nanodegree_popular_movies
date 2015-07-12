package mobi.toan.popularmovies.rest;

import junit.framework.TestCase;

import java.util.HashMap;
import java.util.Map;

import mobi.toan.popularmovies.Constants;
import mobi.toan.popularmovies.Credentials;

/**
 * Created by toan on 7/10/15.
 */
public class RestUtilsTest extends TestCase{
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    public void testCreateBaseRequestParam() {
        Map<String, String> expectedBaseParam = new HashMap<>();
        expectedBaseParam.put(Constants.PARAM_API_KEY, Credentials.API_KEY);
        assertEquals(expectedBaseParam, RestUtils.createBaseRequestParam());
    }

    public void testCreateDiscoverRequestParams() {
        Map<String, String> expectedParams = new HashMap<>();
        expectedParams.put(Constants.PARAM_API_KEY, Credentials.API_KEY);
        expectedParams.put(Constants.PARAM_SORT_BY, Constants.PARAM_VALUE_POPULARITY_DESC);
        assertEquals(expectedParams, RestUtils.createDiscoverRequestParams(true));
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
}
