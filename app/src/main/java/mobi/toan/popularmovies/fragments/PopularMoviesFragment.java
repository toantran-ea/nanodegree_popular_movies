package mobi.toan.popularmovies.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import de.greenrobot.event.EventBus;
import mobi.toan.popularmovies.Constants;
import mobi.toan.popularmovies.MovieDetailActivity;
import mobi.toan.popularmovies.R;
import mobi.toan.popularmovies.models.PopularMovieData;
import mobi.toan.popularmovies.models.events.MenuOptionMessage;
import mobi.toan.popularmovies.rest.RestUtils;
import mobi.toan.popularmovies.rest.TheMovieDBAPI;
import mobi.toan.popularmovies.views.PopularMovieAdapter;
import mobi.toan.popularmovies.views.RecyclerItemClickListener;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class PopularMoviesFragment extends Fragment {
    private static final String TAG = PopularMoviesFragment.class.getSimpleName();
    private static final int GRID_COLUMNS = 2;
    public static final String MOVIE_LIST = "movie-list";
    public static final String DISPLAY_TYPE = "display-type";

    private RecyclerView mPopularMovieRecyclerView;
    private PopularMovieAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public static PopularMoviesFragment newInstance() {
        return new PopularMoviesFragment();
    }

    public PopularMoviesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public void onEvent(MenuOptionMessage event) {
        switch(event.getMenuOption()){
            case BY_POPULARITY:
                Log.e(TAG, "by popular");
                loadPopularMovieData(true);
                break;
            case BY_RATING:
                loadPopularMovieData(false);
                break;
            case FAVOURITE:
                loadOfflineFavouriteMovies();
                break;
            default: return;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_popular_movies, container, false);
        initUIComponents(root);
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.e(TAG, "onActivityCreated");
        super.onActivityCreated(savedInstanceState);
        if(savedInstanceState != null) {
            List<PopularMovieData.Movie> movies = savedInstanceState.getParcelableArrayList(MOVIE_LIST);
            Log.e(TAG, "movies " + movies.toString());
            presentMovieData(movies);
        } else {
            Log.e(TAG, "oooooohhhhh!!!");
            loadPopularMovieData(true);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        Log.e(TAG , "save STATE");
        outState.putParcelableArrayList(MOVIE_LIST, (ArrayList<? extends Parcelable>) mAdapter.getDataSource());
    }

    private void loadPopularMovieData(boolean byPopularity) {
        Map<String, String> queryParams = RestUtils.createDiscoverRequestParams(byPopularity);
        TheMovieDBAPI.getInstance().getService().discoverMovies(queryParams, new Callback<PopularMovieData>() {
            @Override
            public void success(PopularMovieData popularMovieData, Response response) {
                presentMovieData(popularMovieData.getResults());
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(TAG, "failed " + error.getMessage());
            }
        });
    }

    private void loadOfflineFavouriteMovies() {

    }

    private void initUIComponents(View rootView) {
        mPopularMovieRecyclerView = (RecyclerView) rootView.findViewById(R.id.popular_movie_recyclerview);
        mPopularMovieRecyclerView.setHasFixedSize(true);
        mAdapter = new PopularMovieAdapter(getActivity(), null);
        mLayoutManager = new GridLayoutManager(getActivity(), GRID_COLUMNS);
        mPopularMovieRecyclerView.setLayoutManager(mLayoutManager);
        mPopularMovieRecyclerView.setAdapter(mAdapter);
        mPopularMovieRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                PopularMovieData.Movie movie = mAdapter.getItem(position);
                Log.e(TAG, movie.toString());
                Intent intent = new Intent(getActivity(), MovieDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString(Constants.MOVIE_ID_DB, movie.getId());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        }));
    }

    private void presentMovieData(List<PopularMovieData.Movie> movies) {
        mAdapter.updateDataSource(movies);
    }
}
