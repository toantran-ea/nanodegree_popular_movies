package mobi.toan.popularmovies.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by Toan on 7/30/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReviewList {
    @JsonProperty("results")
    private List<Review> mReviewList;

    public List<Review> getReviewList() {
        return mReviewList;
    }

    public void setReviewList(List<Review> reviewList) {
        mReviewList = reviewList;
    }

    @Override
    public String toString() {
        return "ReviewList{" +
                "mReviewList=" + mReviewList +
                '}';
    }

    public static class Review {
        @JsonProperty("id")
        private String mId;

        @JsonProperty("author")
        private String mAuthor;

        @JsonProperty("content")
        private String mContent;

        @JsonProperty("url")
        private String mUrl;

        public String getId() {
            return mId;
        }

        public void setId(String id) {
            mId = id;
        }

        public String getAuthor() {
            return mAuthor;
        }

        public void setAuthor(String author) {
            mAuthor = author;
        }

        public String getContent() {
            return mContent;
        }

        public void setContent(String content) {
            mContent = content;
        }

        public String getUrl() {
            return mUrl;
        }

        public void setUrl(String url) {
            mUrl = url;
        }

        @Override
        public String toString() {
            return "Review{" +
                    "mId='" + mId + '\'' +
                    ", mAuthor='" + mAuthor + '\'' +
                    ", mContent='" + mContent + '\'' +
                    ", mUrl='" + mUrl + '\'' +
                    '}';
        }
    }
}
