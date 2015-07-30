package mobi.toan.popularmovies.utils;

import android.test.AndroidTestCase;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import mobi.toan.popularmovies.models.MovieDetails;
import mobi.toan.popularmovies.models.TrailerList;
import mobi.toan.popularmovies.models.realm.FavouriteMovie;
import mobi.toan.popularmovies.models.realm.Trailer;

/**
 * Created by toan on 7/30/15.
 */
public class DBUtilsTest extends AndroidTestCase {
    @Override
    protected void setUp() throws Exception {
        RealmConfiguration config = new RealmConfiguration.Builder(getContext()).build();
        Realm.setDefaultConfiguration(config);
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        DBUtils.getDefaultInstance().deleteDB();
        DBUtils.reset();
    }

    public void testCreateDefaultInstance() {
        DBUtils defaultInstance = DBUtils.getDefaultInstance();
        assertNotNull(defaultInstance);
    }

    public void testAddToFavourite() {
        MovieDetails movieDetails = createTestMovieDetails();
        assertTrue(DBUtils.getDefaultInstance().addToFavourite(movieDetails));
        try(Realm realm = Realm.getDefaultInstance()){
            int size = realm.where(FavouriteMovie.class).findAll().size();
            assertEquals(1, size);
        }
    }

    public void testRemoveFromFavourite() {
        MovieDetails movieDetails = createTestMovieDetails();
        assertTrue(DBUtils.getDefaultInstance().addToFavourite(movieDetails));
        movieDetails.setId("456789");
        assertTrue(DBUtils.getDefaultInstance().addToFavourite(movieDetails));
        try(Realm realm = Realm.getDefaultInstance()) {
            assertEquals(2, realm.where(FavouriteMovie.class).findAll().size());
        }
        DBUtils.getDefaultInstance().removeFromFavourite(movieDetails.getId());
        try(Realm realm = Realm.getDefaultInstance()) {
            assertEquals(1, realm.where(FavouriteMovie.class).findAll().size());
        }
    }

    public void testDeleteDB() {
        assertTrue(DBUtils.getDefaultInstance().deleteDB());
    }

    public void testIsFavourite() {
        MovieDetails movieDetails = createTestMovieDetails();
        assertTrue(DBUtils.getDefaultInstance().addToFavourite(movieDetails));
        assertTrue(DBUtils.getDefaultInstance().isFavourited(movieDetails.getId()));
    }

    public void testRemoveDependentTrailers() {
        MovieDetails movieDetails = createTestMovieDetails();
        TrailerList trailerList = new TrailerList();
        TrailerList.Trailer trailer = new TrailerList.Trailer();
        trailer.setSite("YouTube");
        trailer.setName("The super code trailer 1");
        trailer.setId("123123");
        trailer.setKey("zxcvbnm");
        List<TrailerList.Trailer> trailers = new ArrayList<>();
        trailers.add(trailer);
        trailerList.setTrailers(trailers);
        movieDetails.setTrailerList(trailerList);
        try(Realm realm = Realm.getDefaultInstance()) {
            assertEquals(0, realm.where(FavouriteMovie.class).findAll().size());
            assertEquals(0, realm.where(Trailer.class).findAll().size());
        }
        DBUtils.getDefaultInstance().addToFavourite(movieDetails);
        try(Realm realm = Realm.getDefaultInstance()) {
            assertEquals(1, realm.where(FavouriteMovie.class).findAll().size());
            assertEquals(1, realm.where(Trailer.class).findAll().size());
        }
        DBUtils.getDefaultInstance().removeFromFavourite(movieDetails.getId());
        try(Realm realm = Realm.getDefaultInstance()) {
            assertEquals(0, realm.where(FavouriteMovie.class).findAll().size());
            assertEquals(0, realm.where(Trailer.class).findAll().size());
        }
    }


    private MovieDetails createTestMovieDetails() {
        MovieDetails movieDetails = new MovieDetails();
        movieDetails.setId("12345");
        movieDetails.setRating("8");
        movieDetails.setPosterPath("/my/path");
        movieDetails.setYear("07-15-2015");
        movieDetails.setOverview("Movie overview");
        movieDetails.setTitle("The super coder");
        TrailerList trailerList = new TrailerList();
        TrailerList.Trailer trailer = new TrailerList.Trailer();
        trailer.setSite("YouTube");
        trailer.setName("The super code trailer 1");
        trailer.setId("123123");
        trailer.setKey("zxcvbnm");
        List<TrailerList.Trailer> trailers = new ArrayList<>();
        trailers.add(trailer);
        trailerList.setTrailers(trailers);
        movieDetails.setTrailerList(trailerList);
        return movieDetails;
    }
}
