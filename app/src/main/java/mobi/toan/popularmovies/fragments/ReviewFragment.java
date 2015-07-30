package mobi.toan.popularmovies.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.Map;

import mobi.toan.popularmovies.Constants;
import mobi.toan.popularmovies.R;
import mobi.toan.popularmovies.models.ReviewList;
import mobi.toan.popularmovies.rest.RestUtils;
import mobi.toan.popularmovies.rest.TheMovieDBAPI;
import mobi.toan.popularmovies.views.ReviewRecyclerViewAdapter;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ReviewFragment extends Fragment {
    private static String TAG = ReviewFragment.class.getSimpleName();
    private String mMovieId;
    private RecyclerView mReviewRecyclerView;

    public static ReviewFragment newInstance(String movieId) {
        ReviewFragment fragment = new ReviewFragment();
        Bundle args = new Bundle();
        args.putString(Constants.MOVIE_ID, movieId);
        fragment.setArguments(args);
        return fragment;
    }

    public ReviewFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mMovieId = getArguments().getString(Constants.MOVIE_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_review, container, false);
        initUI(rootView);
        return rootView;
    }

    private void initUI(View rootView) {
        mReviewRecyclerView = (RecyclerView) rootView.findViewById(R.id.reviews_recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mReviewRecyclerView.setLayoutManager(layoutManager);
        mReviewRecyclerView.setHasFixedSize(true);
    }

    @Override
    public void onStart() {
        super.onStart();
        getReviews();
    }

    private void getReviews() {
        Map<String, String> options = RestUtils.createBaseRequestParam();
        options.put(Constants.PARAM_LANG, Constants.LANG_639_ISO);
        TheMovieDBAPI.getInstance().getService().getReviews(options, mMovieId, new Callback<ReviewList>() {
            @Override
            public void success(ReviewList reviewList, Response response) {
                Log.e(TAG, reviewList.toString());
                renderReviews(reviewList);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(TAG, error.getMessage());
                Toast.makeText(getActivity(), getString(R.string.error_network_request), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void renderReviews(ReviewList reviewList) {
        ReviewRecyclerViewAdapter adapter = new ReviewRecyclerViewAdapter(getActivity(), reviewList);
        mReviewRecyclerView.setAdapter(adapter);
    }
}
