package mobi.toan.popularmovies.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import mobi.toan.popularmovies.R;

public class MovieDetailsFragment extends Fragment {
    public static final String MOVIE_ID = "movie_id";
    private View mFragmentView;

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
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    private void initializeComponents(View rootView) {
        Bundle args  = getArguments();
        String movieId = args.get(MOVIE_ID).toString();
        Log.e("movie details", ">> " + movieId);
    }

    private void renderStaticFields(View rootView) {

    }
}
