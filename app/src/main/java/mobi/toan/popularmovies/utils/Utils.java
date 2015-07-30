package mobi.toan.popularmovies.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import mobi.toan.popularmovies.models.TrailerList;
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
}
