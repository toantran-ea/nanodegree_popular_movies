package mobi.toan.popularmovies.models.realm;

import io.realm.RealmObject;
import mobi.toan.popularmovies.models.ReviewList;

/**
 * Created by Toan on 7/30/15.
 */
public class Review extends RealmObject {
    private String id;
    private String author;
    private String content;
    private String url;
    private String movieId;

    public Review() {}

    public Review(ReviewList.Review review) {
        setId(review.getId());
        setAuthor(review.getAuthor());
        setContent(review.getContent());
        setUrl(review.getUrl());
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
