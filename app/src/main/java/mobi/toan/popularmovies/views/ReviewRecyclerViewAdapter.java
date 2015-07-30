package mobi.toan.popularmovies.views;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import mobi.toan.popularmovies.R;
import mobi.toan.popularmovies.models.ReviewList;

/**
 * Created by toan on 7/30/15.
 */
public class ReviewRecyclerViewAdapter extends RecyclerView.Adapter<ReviewRecyclerViewAdapter.ViewHolder> {
    private static String TAG = ReviewRecyclerViewAdapter.class.getSimpleName();
    private Context mContext;
    private List<ReviewList.Review> mDataSource;


    public ReviewRecyclerViewAdapter(Context context, ReviewList reviewList) {
        mDataSource = new ArrayList<>();
        if(reviewList != null && reviewList.getReviewList() != null) {
            mDataSource.addAll(reviewList.getReviewList());
        }
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootview = LayoutInflater.from(mContext).inflate(R.layout.review_cell, parent, false);
        return new ViewHolder(rootview);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mDataSource.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
