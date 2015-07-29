package mobi.toan.popularmovies.views;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import mobi.toan.popularmovies.R;
import mobi.toan.popularmovies.models.TrailerList;

/**
 * Created by toan on 7/29/15.
 */
public class TrailerRecyclerViewAdapter extends RecyclerView.Adapter<TrailerRecyclerViewAdapter.ViewHolder> {
    public static final String TAG = TrailerRecyclerViewAdapter.class.getSimpleName();
    private TrailerList mTrailerList;
    private Context mContext;

    public TrailerRecyclerViewAdapter(Context context, TrailerList trailerList) {
        mContext  = context;
        mTrailerList = trailerList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View cellView =  LayoutInflater.from(mContext).inflate(R.layout.trailer_cell, parent, false);
        return new ViewHolder(cellView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TrailerList.Trailer trailer = mTrailerList.getTrailers().get(position);
        holder.mTrailerNameTextView.setText(trailer.getName());

    }

    @Override
    public int getItemCount() {
        return (mTrailerList != null && mTrailerList.getTrailers() != null) ? mTrailerList.getTrailers().size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView mTrailerNameTextView;
        public ViewHolder(View rootView) {
            super(rootView);
            mTrailerNameTextView = (TextView) rootView.findViewById(R.id.trailer_name_text_view);
        }
    }
}
