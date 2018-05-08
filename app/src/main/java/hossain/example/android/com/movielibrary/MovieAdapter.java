package hossain.example.android.com.movielibrary;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import hossain.example.android.com.DataSource.BasicData;
import hossain.example.android.com.NetworkUtil.MovieDBAPI;


public class MovieAdapter extends BaseAdapter{

    private Context mContext;
    private BasicData[] mBasicData;
    private int size = 0;

    MovieAdapter(Context context){
        mContext = context;
    }

    @Override
    public int getCount() {
         return size;
    }

    @Override
    public Object getItem(int position) {
        return mBasicData[position];
    }

    @Override
    public long getItemId(int position) {
        return mBasicData[position].id;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ImageView movieImage;
        if(convertView == null){
            movieImage = new ImageView(mContext);
            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((Activity)mContext).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int width = (displayMetrics.widthPixels/3)+35;
            int height = width * 5/ 4;
            movieImage.setLayoutParams(new ViewGroup.LayoutParams(width, height));
            movieImage.setScaleType(ImageView.ScaleType.FIT_CENTER);
            movieImage.setPadding(0,0,0,0);
        }else {
            movieImage = (ImageView) convertView;
        }
        Picasso.with(mContext).load(MovieDBAPI.getApiImageUrl(mBasicData[position].posterPath)).into(movieImage);
        movieImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detailsIntent = new Intent(mContext, DetailsActivity.class);
                detailsIntent.putExtra(DetailsActivity.ENTRY_TYPE, mBasicData[position].entryType);
                detailsIntent.putExtra(DetailsActivity.ENTRY_ID, mBasicData[position].id);
                mContext.startActivity(detailsIntent);
            }
        });
        return movieImage;
    }

    public void setMoviedata(BasicData[] basicData){
        mBasicData = basicData;
        size = mBasicData.length;
        notifyDataSetChanged();
    }

}


