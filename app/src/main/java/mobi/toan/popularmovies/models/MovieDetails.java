package mobi.toan.popularmovies.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by toan on 7/16/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieDetails {
    private String id;

    @JsonProperty("original_title")
    private String mTitle;

    @JsonProperty("overview")
    private String mOverview;

    @JsonProperty("poster_path")
    private String mPosterPath;

    @JsonProperty("runtime")
    private String mLength;

    @JsonProperty("release_date")
    private String mYear;

    @JsonProperty("vote_average")
    private String mRating;


        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return mTitle;
        }

        public void setTitle(String title) {
            mTitle = title;
        }

        public String getOverview() {
            return mOverview;
        }

        public void setOverview(String overview) {
            mOverview = overview;
        }

        public String getPosterPath() {
            return mPosterPath;
        }

        public void setPosterPath(String posterPath) {
            mPosterPath = posterPath;
        }

        public String getLength() {
            return mLength;
        }

        public void setLength(String length) {
            mLength = length;
        }

        public String getYear() {
            return mYear;
        }

        public void setYear(String year) {
            mYear = year;
        }

        public String getRating() {
            return mRating;
        }

        public void setRating(String rating) {
            mRating = rating;
        }
}
