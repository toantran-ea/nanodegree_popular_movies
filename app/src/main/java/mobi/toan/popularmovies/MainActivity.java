package mobi.toan.popularmovies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import de.greenrobot.event.EventBus;
import mobi.toan.popularmovies.fragments.MovieDetailsFragment;
import mobi.toan.popularmovies.fragments.PopularMoviesFragment;
import mobi.toan.popularmovies.models.events.MenuOptionMessage;

public class MainActivity extends AppCompatActivity {
    private static final  String TAG = MainActivity.class.getSimpleName();
    private PopularMoviesFragment mFragment;

    // Whether or not we are in dual-pane mode
    private boolean mIsDualPane = false;

    private PopularMoviesFragment mPopularMoviesFragment;
    private MovieDetailsFragment mMovieDetailsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate");
        setContentView(R.layout.activity_main_layout);

        mPopularMoviesFragment = (PopularMoviesFragment) getSupportFragmentManager().findFragmentById(R.id.popular_movies_fragment);
        mMovieDetailsFragment = (MovieDetailsFragment) getSupportFragmentManager().findFragmentById(R.id.movie_details_fragment);

        // If we are in two-pane layout mode, this activity is no longer necessary
        mIsDualPane = getResources().getBoolean(R.bool.has_two_panes);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onDestroy() {
        Log.e(TAG, "onDestroy");
        super.onDestroy();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
     switch(item.getItemId()) {
         case R.id.action_by_popularity:
             sendMenuEvent(MenuOptionMessage.MENU_OPTIONS.BY_POPULARITY);
             break;
         case R.id.action_by_rating:
             sendMenuEvent(MenuOptionMessage.MENU_OPTIONS.BY_RATING);
             break;
         case R.id.action_favourite:
             sendMenuEvent(MenuOptionMessage.MENU_OPTIONS.FAVOURITE);
             break;
     }
        return super.onOptionsItemSelected(item);
    }

    private void sendMenuEvent(MenuOptionMessage.MENU_OPTIONS option) {
        EventBus.getDefault().post(new MenuOptionMessage(option));
    }
}
