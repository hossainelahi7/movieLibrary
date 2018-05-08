package hossain.example.android.com.movielibrary;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.GridView;

import org.json.JSONArray;
import org.json.JSONObject;

import hossain.example.android.com.DataSource.BasicData;
import hossain.example.android.com.DataUtil.DataSync;

public class MainActivity extends AppCompatActivity {

    private  Context mContext;
    private  MovieAdapter mMovieAdapter;
    private  GridView mMovieView;
    private  LoadData mLoadData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_main);
        mMovieView = findViewById(R.id.grid_view);
        mMovieAdapter = new MovieAdapter(this);
        mMovieView.setAdapter(mMovieAdapter);
        mLoadData = new LoadData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mLoadData.getStatus() == AsyncTask.Status.PENDING)
            mLoadData.execute(LoadData.MOVIE_DATA);
    }

    public void prioritySelected(View view) {
        int id;
        switch (view.getId()){
            case R.id.top_movie:
               id = LoadData.MOVIE_DATA;
               break;
            case R.id.top_tv:
                id = LoadData.TV_DATA;
                break;
            case R.id.user_choice:
                id = LoadData.USER_DATA;
                break;
            default:
                id = LoadData.MOVIE_DATA;
                break;
        }
        Log.d("Clicked", "Top Item Clicked, id="+id);
        mLoadData = new LoadData();
        mLoadData.execute(id);
    }


    public class LoadData extends AsyncTask<Integer, String, BasicData[]>{
        public static final int MOVIE_DATA = 1;
        public static final int TV_DATA = 2;
        public static final int USER_DATA = 3;

        private int loadDataType= 0;

        @Override
        protected BasicData[] doInBackground(Integer... dataType) {
            JSONObject responseJson;
            if(dataType!=null && dataType.length>0 && dataType[0]>0)
                loadDataType = dataType[0];
            else if (loadDataType == 0)
                loadDataType = MOVIE_DATA;
            if(!DataSync.dataExist(mContext))
                DataSync.syncData(mContext);
            try{
                if(loadDataType == MOVIE_DATA){
                    responseJson = DataSync.readPopularMovieData(mContext);
                }
                else if(loadDataType == TV_DATA){
                    responseJson = DataSync.readPopularTvData(mContext);
                }else {
                    responseJson = DataSync.readPopularMovieData(mContext);
                }
                if(responseJson.has("results")
                        && responseJson.getJSONArray("results").length()>0){
                    JSONArray BasicJsonList = responseJson.getJSONArray("results");
                    int length = BasicJsonList.length();
                    BasicData[] basicData = new BasicData[length];
                    for(int i =0 ; i < length; i++){
                        basicData[i] = new BasicData((JSONObject) BasicJsonList.get(i), loadDataType);
                    }
                    return basicData;
                }
            }
            catch (Exception e){
            }
            return null;
        }

        @Override
        protected void onPostExecute(@NonNull  BasicData[] basicData){
            if(basicData != null){
                mMovieAdapter.setMoviedata(basicData);
            }
        }

    }
}
