package mobi.toan.popularmovies.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by toan on 7/12/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PopularMovieData {
    public PopularMovieData() {

    }

    @JsonProperty("results")
    private List<Movie> mResults;


    public List<Movie> getResults() {
        return mResults;
    }

    public void setResults(List<Movie> mResults) {
        this.mResults = mResults;
    }

    @Override
    public String toString() {
        return "PopularMovieData{" +
                "mResults=" + mResults +
                '}';
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Movie implements Parcelable {

        public Movie() {

        }

        private String id;

        @JsonProperty("poster_path")
        private String posterPath;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPosterPath() {
            return posterPath;
        }

        public void setPosterPath(String posterPath) {
            this.posterPath = posterPath;
        }

        @Override
        public String toString() {
            return "Movie{" +
                    "id='" + id + '\'' +
                    ", posterPath='" + posterPath + '\'' +
                    '}';
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(id);
            parcel.writeString(posterPath);
        }
    }
}
