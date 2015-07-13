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

    private List<PopularMovieData.Movie> mDataSet;
    private Context mContext;

    public PopularMovieAdapter(Context context, List<PopularMovieData.Movie> dataSet) {
        this.mContext = context;
        mDataSet = new ArrayList<>();
        if(dataSet != null) {
            mDataSet.addAll(dataSet);
        }
    }

    public void updateDataSet(List<PopularMovieData.Movie> dataSet) {
        mDataSet.clear();
        if(dataSet != null) {
            mDataSet.addAll(dataSet);
        } else {
            Log.e(TAG, "updateDataSet() called with empty dataSet");
        }
        notifyDataSetChanged();
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
        PopularMovieData.Movie item = mDataSet.get(position);
        String path = RestUtils.getPosterPath(item.getPosterPath(), false);
        Picasso.with(mContext).load(path).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.poster_image_view);
        }
    }
}
