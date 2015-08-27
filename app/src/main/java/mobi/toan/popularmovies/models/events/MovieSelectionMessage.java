package mobi.toan.popularmovies.models.events;

/**
 * Created by toan on 8/25/15.
 */
public class MovieSelectionMessage {
    private String mMovieId;

    public MovieSelectionMessage(String movieId) {
        setMovieId(movieId);
    }

    public String getMovieId() {
        return mMovieId;
    }

    public void setMovieId(String movieId) {
        mMovieId = movieId;
    }
}
