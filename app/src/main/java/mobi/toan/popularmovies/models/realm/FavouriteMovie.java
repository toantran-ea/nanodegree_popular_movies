package mobi.toan.popularmovies.models.realm;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.realm.RealmObject;
import io.realm.annotations.Index;

/**
 * Created by toan on 7/30/15.
 */
public class FavouriteMovie extends RealmObject {
    @Index
    private String movieId;

    @JsonProperty("movieId")
    public String getMovieId() {
        return movieId;
    }

    @JsonProperty("movieId")
    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }
}
