package mobi.toan.popularmovies.fragments;

import android.app.Fragment;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.Map;

import de.greenrobot.event.EventBus;
import mobi.toan.popularmovies.Constants;
import mobi.toan.popularmovies.R;
import mobi.toan.popularmovies.models.MovieDetails;
import mobi.toan.popularmovies.models.TrailerList;
import mobi.toan.popularmovies.models.events.ReviewFragmentRequestMessage;
import mobi.toan.popularmovies.rest.RestUtils;
import mobi.toan.popularmovies.rest.TheMovieDBAPI;
import mobi.toan.popularmovies.utils.DBUtils;
import mobi.toan.popularmovies.views.TrailerRecyclerViewAdapter;
import mobi.toan.popularmovies.utils.Utils;
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
    private Button mFavouriteButton;
    private MovieDetails mMovieDetails;
    private Toast mToast;

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
    }

    private void initializeComponents(View rootView) {
        Bundle args  = getArguments();
        mMovieId = args.getString(MOVIE_ID);
        mTrailerRecyclerView = (RecyclerView) rootView.findViewById(R.id.trailers_recycler_view);
        mTrailerRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mTrailerRecyclerView.setLayoutManager(mLayoutManager);
        mFavouriteButton = (Button) rootView.findViewById(R.id.favourite_mark_button);
        if(DBUtils.getDefaultInstance().isFavourited(mMovieId)) {
            mFavouriteButton.setText(getActivity().getString(R.string.label_favourited));
        }
        mFavouriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onFavouriteChanged();
            }
        });
        TextView reviewTextView = (TextView) rootView.findViewById(R.id.review_text_view);
        reviewTextView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(new ReviewFragmentRequestMessage(mMovieId));
            }
        });
    }

    private void getMovieDetails() {
        Map<String, String> baseParams = RestUtils.createBaseRequestParam();
        TheMovieDBAPI.getInstance().getService().getMovieDetails(baseParams, mMovieId, new Callback<MovieDetails>() {
            @Override
            public void success(MovieDetails movieDetails, Response response) {
                mMovieDetails = movieDetails;
                renderStaticFields(mFragmentView, movieDetails);
                getTrailers();
            }

            @Override
            public void failure(RetrofitError error) {
                showToastMessage(getString(R.string.error_network_request));
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
                renderTrailers(trailerList);
                mMovieDetails.setTrailerList(trailerList);
            }

            @Override
            public void failure(RetrofitError error) {
                showToastMessage(getString(R.string.error_network_request));
                Log.e(TAG, error.getMessage());
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
        TextView reviewTextView = (TextView) rootView.findViewById(R.id.review_text_view);
        reviewTextView.setPaintFlags(reviewTextView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    }

    private void renderTrailers(TrailerList trailerList) {
        mAdapter = new TrailerRecyclerViewAdapter(getActivity(), trailerList);
        mTrailerRecyclerView.setAdapter(mAdapter);
    }

    private void onFavouriteChanged() {
        if(DBUtils.getDefaultInstance().isFavourited(mMovieId)) {
            // Remove from favourited list
            DBUtils.getDefaultInstance().removeFromFavourite(mMovieId);
            // Update label on button
            mFavouriteButton.setText(getString(R.string.mark_as_favorite));
        } else {
            // Add to favorited list
            DBUtils.getDefaultInstance().addToFavourite(mMovieDetails);
            // Update label on button
            mFavouriteButton.setText(getActivity().getString(R.string.label_favourited));
        }
    }

    private void showToastMessage(String message) {
        if(mToast != null) {
            mToast.cancel();
        }
        mToast = Toast.makeText(getActivity(), message, Toast.LENGTH_LONG);
        mToast.show();
    }
}
