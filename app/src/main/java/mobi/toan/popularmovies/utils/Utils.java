package mobi.toan.popularmovies.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import mobi.toan.popularmovies.models.PopularMovieData;
import mobi.toan.popularmovies.models.TrailerList;
import mobi.toan.popularmovies.models.realm.FavouriteMovie;
import mobi.toan.popularmovies.models.realm.Trailer;

/**
 * Created by EastAgile Team on 7/29/15.
 */
public class Utils {
    /**
     * Extracts datetime returned to year.
     * @param dateTime
     */
    public static String getYear(String dateTime) {
        DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Calendar cal = Calendar.getInstance();
            cal.setTime(simpleDateFormat.parse(dateTime));
            return String.valueOf(cal.get(Calendar.YEAR));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getLength(String length) {
        return String.format("%s mins", length);
    }

    public static String getRating(String rating) {
        return String.format("%s/10", rating);
    }

    public static List<Trailer> getTrailers(TrailerList trailerList) {
        List<Trailer> trailers = new ArrayList<>();
        if(trailerList == null || trailerList.getTrailers() == null) {
            return trailers;
        }
        for (TrailerList.Trailer trailer : trailerList.getTrailers()) {
            trailers.add(new Trailer(trailer));
        }
        return trailers;
    }

    public static List<PopularMovieData.Movie> getMovies(List<FavouriteMovie> favouriteMovies) {
        List<PopularMovieData.Movie> movies = new ArrayList<>();
        for(FavouriteMovie favouriteMovie : favouriteMovies) {
            movies.add(convertFromFavouriteToMovie(favouriteMovie));
        }
        return movies;
    }

    public static TrailerList getTrailerList(List<Trailer> realmTrailers) {
        TrailerList trailerList = new TrailerList();
        List<TrailerList.Trailer> trailers = new ArrayList<>();
        for(Trailer trailer : realmTrailers) {
            trailers.add(new TrailerList.Trailer(trailer));
        }
        trailerList.setTrailers(trailers);
        return trailerList;
    }

    public static PopularMovieData.Movie convertFromFavouriteToMovie(FavouriteMovie favouriteMovie) {
        PopularMovieData.Movie movie = new PopularMovieData.Movie();
        movie.setId(favouriteMovie.getId());
        movie.setPosterPath(favouriteMovie.getPosterPath());
        return movie;
    }
}
