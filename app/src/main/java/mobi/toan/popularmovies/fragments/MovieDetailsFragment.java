package mobi.toan.popularmovies.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Map;

import mobi.toan.popularmovies.Constants;
import mobi.toan.popularmovies.R;
import mobi.toan.popularmovies.models.MovieDetails;
import mobi.toan.popularmovies.models.TrailerList;
import mobi.toan.popularmovies.rest.RestUtils;
import mobi.toan.popularmovies.rest.TheMovieDBAPI;
import mobi.toan.popularmovies.views.TrailerRecyclerViewAdapter;
import mobi.toan.popularmovies.views.Utils;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MovieDetailsFragment extends Fragment {
    private static final String TAG = MovieDetailsFragment.class.getSimpleName();
    public static final String MOVIE_ID = "movie_id";
    private View mFragmentView;
    private String mMovieId;
    private RecyclerView mTrailerRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private TrailerRecyclerViewAdapter mAdapter;

    public static MovieDetailsFragment newInstance(String movieId){
        MovieDetailsFragment fragment = new MovieDetailsFragment();
        Bundle args = new Bundle();
        args.putString(MOVIE_ID, movieId);
        fragment.setArguments(args);
        return fragment;
    }

    public MovieDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mFragmentView = inflater.inflate(R.layout.fragment_movie_details, container, false);
        initializeComponents(mFragmentView);
        return mFragmentView;
    }

    @Override
    public void onStart() {
        super.onStart();
        getMovieDetails();
        getTrailers();
    }

    private void initializeComponents(View rootView) {
        Bundle args  = getArguments();
        mMovieId = args.get(MOVIE_ID).toString();
        mTrailerRecyclerView = (RecyclerView) rootView.findViewById(R.id.trailers_recycler_view);
        mTrailerRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mTrailerRecyclerView.setLayoutManager(mLayoutManager);
    }

    private void getMovieDetails() {
        Map<String, String> baseParams = RestUtils.createBaseRequestParam();
        TheMovieDBAPI.getInstance().getService().getMovieDetails(baseParams, mMovieId, new Callback<MovieDetails>() {
            @Override
            public void success(MovieDetails movieDetails, Response response) {
                Log.e(TAG, movieDetails.toString());
                renderStaticFields(mFragmentView, movieDetails);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(TAG, error.getMessage());
            }
        });
    }

    private void getTrailers() {
        Map<String, String> params = RestUtils.createBaseRequestParam();
        params.put(Constants.PARAM_LANG, Constants.LANG_639_ISO);
        TheMovieDBAPI.getInstance().getService().getTrailers(params, mMovieId, new Callback<TrailerList>() {
            @Override
            public void success(TrailerList trailerList, Response response) {
                Log.e(TAG, trailerList.toString());
                renderTrailers(trailerList);
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }

    private void renderStaticFields(View rootView, MovieDetails movieDetails) {
        TextView title = (TextView) rootView.findViewById(R.id.movie_title_text_view);
        title.setText(movieDetails.getTitle());
        TextView yearTextView = (TextView) rootView.findViewById(R.id.year_text_view);
        yearTextView.setText(Utils.getYear(movieDetails.getYear()));
        TextView lengthTextView = (TextView) rootView.findViewById(R.id.length_text_view);
        lengthTextView.setText(Utils.getLength(movieDetails.getLength()));
        TextView ratingTextView = (TextView) rootView.findViewById(R.id.rating_text_view);
        ratingTextView.setText(Utils.getRating(movieDetails.getRating()));
        ImageView posterImageView = (ImageView) rootView.findViewById(R.id.poster_image_view);
        Picasso.with(getActivity()).load(RestUtils.getPosterPath(movieDetails.getPosterPath())).into(posterImageView);
        TextView synopsisTextView = (TextView) rootView.findViewById(R.id.synopsis_text_view);
        synopsisTextView.setText(movieDetails.getOverview());
    }

    private void renderTrailers(TrailerList trailerList) {
        mAdapter = new TrailerRecyclerViewAdapter(getActivity(), trailerList);
        mTrailerRecyclerView.setAdapter(mAdapter);
    }
}
