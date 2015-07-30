package mobi.toan.popularmovies.models.realm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import mobi.toan.popularmovies.models.MovieDetails;

/**
 * Created by toan on 7/30/15.
 */
public class FavouriteMovie extends RealmObject{
    public FavouriteMovie() {
    }

    public FavouriteMovie(MovieDetails movieDetails) {
        setId(movieDetails.getId());
        setLength(movieDetails.getLength());
        setOverview(movieDetails.getOverview());
        setPosterPath(movieDetails.getPosterPath());
        setRating(movieDetails.getRating());
        setTitle(movieDetails.getTitle());
        setYear(movieDetails.getYear());
    }

    @PrimaryKey
    private String id;

    private String title;

    private String overview;

    private String posterPath;

    private String length;

    private String year;

    private String rating;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
