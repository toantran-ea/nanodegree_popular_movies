package mobi.toan.popularmovies;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import de.greenrobot.event.EventBus;
import mobi.toan.popularmovies.fragments.MovieDetailsFragment;
import mobi.toan.popularmovies.fragments.ReviewFragment;
import mobi.toan.popularmovies.models.MovieDetails;
import mobi.toan.popularmovies.models.events.ReviewFragmentRequestMessage;

public class MovieDetailActivity extends Activity {
    private static String TAG = MovieDetailActivity.class.getSimpleName();
    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // If we are in two-pane layout mode, this activity is no longer necessary
        if (getResources().getBoolean(R.bool.has_two_panes)) {
            finish();
            return;
        }

        setContentView(R.layout.activity_movie_detail);
    }

    @Override
    protected void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    public void onEvent(ReviewFragmentRequestMessage event) {
        // add review fragment into this view.
        Log.e(TAG, "Opening Review fragment for movie " + event.getMovieId());
        // Create new fragment and transaction
        ReviewFragment reviewFragment = ReviewFragment.newInstance(event.getMovieId());
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack
        transaction.add(R.id.movie_details_fragment_container, reviewFragment);
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_movie_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
