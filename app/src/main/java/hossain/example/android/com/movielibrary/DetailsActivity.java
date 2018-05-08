package hossain.example.android.com.movielibrary;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import hossain.example.android.com.DataSource.MovieData;
import hossain.example.android.com.DataSource.TVData;
import hossain.example.android.com.DataUtil.DataSync;
import hossain.example.android.com.NetworkUtil.MovieDBAPI;

public class DetailsActivity extends AppCompatActivity {

    public static final String ENTRY_TYPE = "type";
    public static final String ENTRY_ID = "id";

    private int type;
    private int id;

    private MovieData mMovieData;
    private TVData mTvData;
    private Context mContext;

    private TextView titleView;
    private ImageView imageView;
    private TextView descriptionView;
    private TextView popularityView;
    private TextView reviewView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Intent intent = getIntent();
        mContext = getApplicationContext();
        if (intent.hasExtra(ENTRY_TYPE))
            type = intent.getIntExtra(ENTRY_TYPE, 1);
        if (intent.hasExtra(ENTRY_ID))
            id = intent.getIntExtra(ENTRY_ID, 0);
        titleView = findViewById(R.id.entry_title);
        imageView = findViewById(R.id.entry_image);
        descriptionView = findViewById(R.id.entry_description);
        popularityView = findViewById(R.id.entry_popularity);
        reviewView = findViewById(R.id.entry_user_rating);
    }

    @Override
    protected void onResume(){
        super.onResume();
        if(type == MainActivity.LoadData.MOVIE_DATA){
            mMovieData = searchMovie(id);
//        Log.d(mMovieData.title, mMovieData.toString());
            titleView.setText(mMovieData.title);
            Picasso.with(mContext).
                    load(MovieDBAPI.getApiImageUrl(mMovieData.posterPath)).
                    into(imageView);
            descriptionView.setText(mMovieData.overView);
            popularityView.setText(mMovieData.popularity.toString());
            reviewView.setText(mMovieData.avgVote.toString());
        }else if(type == MainActivity.LoadData.TV_DATA){
            mTvData = searchTVSeries(id);
            titleView.setText(mTvData.title);
            Picasso.with(mContext).
                    load(MovieDBAPI.getApiImageUrl(mTvData.posterPath)).
                    into(imageView);
            descriptionView.setText(mTvData.overView);
            popularityView.setText(mTvData.popularity.toString());
            reviewView.setText(mTvData.avgVote.toString());
        }
    }


    private MovieData searchMovie(int id) {
        JSONObject responseJson = DataSync.readPopularMovieData(mContext);
        try {
            if (responseJson.has("results")
                    && responseJson.getJSONArray("results").length() > 0) {
                JSONArray MovieJsonList = responseJson.getJSONArray("results");
                int length = MovieJsonList.length();
                MovieData movieData;
                for (int i =0; i<length; i++){
                    movieData = new MovieData(MovieJsonList.getJSONObject(i));
                    if (movieData.id == id)
                        return movieData;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private TVData searchTVSeries(int id) {
        JSONObject responseJson = DataSync.readPopularTvData(mContext);
        try {
            if (responseJson.has("results")
                    && responseJson.getJSONArray("results").length() > 0) {
                JSONArray TVJsonList = responseJson.getJSONArray("results");
                int length = TVJsonList.length();
                TVData tvData;
                for (int i =0; i<length; i++){
                    tvData = new TVData(TVJsonList.getJSONObject(i));
                    if (tvData.id == id)
                        return tvData;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

}
