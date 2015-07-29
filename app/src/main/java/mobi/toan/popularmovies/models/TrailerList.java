package mobi.toan.popularmovies.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by EastAgile Team on 7/29/15.
 */
public class TrailerList {
    @JsonProperty("results")
    private List<Trailer> trailers;

    public List<Trailer> getTrailers() {
        return trailers;
    }

    public void setTrailers(List<Trailer> trailers) {
        this.trailers = trailers;
    }

    @Override
    public String toString() {
        return "TrailerList{" +
                "trailers=" + trailers +
                '}';
    }

    public static class Trailer {
        @JsonProperty("id")
        private String mId;

        @JsonProperty("name")
        private String mName;

        @JsonProperty("site")
        private String mSite;

        @JsonProperty("key")
        private String mKey;

        public String getId() {
            return mId;
        }

        public void setId(String id) {
            mId = id;
        }

        public String getName() {
            return mName;
        }

        public void setName(String name) {
            mName = name;
        }

        public String getSite() {
            return mSite;
        }

        public void setSite(String site) {
            mSite = site;
        }

        public String getKey() {
            return mKey;
        }

        public void setKey(String key) {
            mKey = key;
        }

        @Override
        public String toString() {
            return "Trailer{" +
                    "mId='" + mId + '\'' +
                    ", mName='" + mName + '\'' +
                    ", mSite='" + mSite + '\'' +
                    ", mKey='" + mKey + '\'' +
                    '}';
        }
    }

}
