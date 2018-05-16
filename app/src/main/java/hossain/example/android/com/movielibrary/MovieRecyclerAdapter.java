package hossain.example.android.com.movielibrary;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.squareup.picasso.Picasso;

import hossain.example.android.com.DataSource.BasicData;
import hossain.example.android.com.NetworkUtil.MovieDBAPI;

public class MovieRecyclerAdapter extends RecyclerView.Adapter<MovieRecyclerAdapter.ViewHolder>{

    private Context mContext;
    private BasicData[] mBasicData;
    private int size = 0;

    MovieRecyclerAdapter(){
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.movie_recycle_adapter_view, parent, false);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity)mContext).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = ((displayMetrics.widthPixels)/3);
        int height = width * 3/ 2;
        view.setLayoutParams(new LinearLayout.LayoutParams(width, height));
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Picasso.with(mContext).
                load(MovieDBAPI.getApiImageUrl(mBasicData[position].posterPath))
                .placeholder(R.mipmap.loading_image_place_holder)
                .error(R.mipmap.error_loading_image)
                .into(holder.mMovieImageView);
    }

    @Override
    public int getItemCount() {
        if (null == mBasicData) size= 0;
        else size = mBasicData.length;
        return size;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public final ImageView mMovieImageView;


        public ViewHolder(View itemView) {
            super(itemView);
            mMovieImageView = itemView.findViewById(R.id.movie_image_view);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            Intent detailsIntent = new Intent(mContext, DetailsActivity.class);
            detailsIntent.putExtra(DetailsActivity.ENTRY_TYPE, mBasicData[position].entryType);
            detailsIntent.putExtra(DetailsActivity.ENTRY_ID, mBasicData[position].id);
            mContext.startActivity(detailsIntent);
        }
    }

    public void setMoviedata(BasicData[] basicData){
        mBasicData = basicData;
        size = mBasicData.length;
        notifyDataSetChanged();
    }

}
