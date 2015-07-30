package mobi.toan.popularmovies.models.realm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import mobi.toan.popularmovies.models.TrailerList;

/**
 * Created by EastAgile Team on 7/30/15.
 */
public class Trailer extends RealmObject {
    @PrimaryKey
    private String id;
    private String name;
    private String key;
    private String site;
    private String movieId;

    public Trailer() {}

    public Trailer(TrailerList.Trailer trailer) {
        setId(trailer.getId());
        setKey(trailer.getKey());
        setName(trailer.getName());
        setSite(trailer.getSite());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }
}
