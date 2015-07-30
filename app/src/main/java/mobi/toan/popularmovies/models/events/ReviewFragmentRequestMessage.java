package mobi.toan.popularmovies.models.events;

/**
 * Created by toan on 7/30/15.
 */
public class ReviewFragmentRequestMessage {
    private String movieId;

    public ReviewFragmentRequestMessage(String movieId) {
        this.movieId = movieId;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }
}
