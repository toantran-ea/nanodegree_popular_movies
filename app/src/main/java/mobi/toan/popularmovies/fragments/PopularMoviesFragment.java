package mobi.toan.popularmovies.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Map;

import mobi.toan.popularmovies.Constants;
import mobi.toan.popularmovies.MovieDetailActivity;
import mobi.toan.popularmovies.R;
import mobi.toan.popularmovies.models.PopularMovieData;
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

    private RecyclerView mPopularMovieRecyclerView;
    private PopularMovieAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public static PopularMoviesFragment newInstance() {
        PopularMoviesFragment fragment = new PopularMoviesFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    public PopularMoviesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_popular_movies_fragments, container, false);
        initUIComponents(root);
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        loadPopularMovieData();
    }

    private void loadPopularMovieData() {
        Map<String, String> queryParams = RestUtils.createDiscoverRequestParams(true);
        TheMovieDBAPI.getInstance().getService().discoverMovies(queryParams, new Callback<PopularMovieData>() {
            @Override
            public void success(PopularMovieData popularMovieData, Response response) {
                presentMovieData(popularMovieData);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(TAG, "failed " + error.getMessage());
            }
        });
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

    private void presentMovieData(PopularMovieData popularMovieData) {
        mAdapter.updateDataSet(popularMovieData.getResults());
    }
}
