package mobi.toan.popularmovies.views;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import mobi.toan.popularmovies.R;
import mobi.toan.popularmovies.models.PopularMovieData;
import mobi.toan.popularmovies.rest.RestUtils;

/**
 * Created by toan on 7/12/15.
 */
public class PopularMovieAdapter extends RecyclerView.Adapter<PopularMovieAdapter.ViewHolder> {
    private static final String TAG = PopularMovieAdapter.class.getSimpleName();

    private List<PopularMovieData.Movie> mDataSource;
    private Context mContext;

    public PopularMovieAdapter(Context context, List<PopularMovieData.Movie> dataSet) {
        this.mContext = context;
        mDataSource = new ArrayList<>();
        if(dataSet != null) {
            mDataSource.addAll(dataSet);
        }
    }

    public List<PopularMovieData.Movie> getDataSource() {
        return mDataSource;
    }

    public void updateDataSource(List<PopularMovieData.Movie> dataSet) {
        mDataSource.clear();
        if(dataSet != null) {
            mDataSource.addAll(dataSet);
        } else {
            Log.e(TAG, "updateDataSource() called with empty dataSet");
        }
        notifyDataSetChanged();
    }

    public PopularMovieData.Movie getItem(int position) {
        return mDataSource.get(position);
    }

    @Override
    public PopularMovieAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.poster_cell, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(PopularMovieAdapter.ViewHolder holder, int position) {
        PopularMovieData.Movie item = mDataSource.get(position);
        String path = RestUtils.getPosterPath(item.getPosterPath());
        Picasso.with(mContext).load(path).error(R.drawable.no_photo).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return mDataSource.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.poster_image_view);
        }
    }
}
