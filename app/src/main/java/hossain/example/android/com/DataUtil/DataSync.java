package hossain.example.android.com.DataUtil;

import android.content.Context;
import android.util.Log;

import org.json.JSONObject;

import hossain.example.android.com.NetworkUtil.MovieDBAPI;

public class DataSync {

    private static final String POPULAR_MOVIE_FILE_NAME = "populerMovie.json";
    private static final String POPULAR_TV_FILE_NAME = "populerTV.json";

    public static void syncData(Context context){
        String popularMovieResponse = MovieDBAPI.getPopulerMovieResponse();
        String popularTVResponse = MovieDBAPI.getPopulerTVResponse();
        if(!FileUtil.fileExist(context, POPULAR_MOVIE_FILE_NAME) && popularMovieResponse !=null)
            FileUtil.storeAsJson(context, POPULAR_MOVIE_FILE_NAME, popularMovieResponse);
        if(!FileUtil.fileExist(context, POPULAR_TV_FILE_NAME) && popularTVResponse != null)
            FileUtil.storeAsJson(context, POPULAR_TV_FILE_NAME, popularTVResponse);
    }


    public static JSONObject readPopularMovieData(Context context){
        if(FileUtil.fileExist(context, POPULAR_MOVIE_FILE_NAME)){
            String response = FileUtil.readJson(context, POPULAR_MOVIE_FILE_NAME);
            Log.d("MovieData", response);
            return FileUtil.parseToJson(response);
        }
        return null;
    }

    public static JSONObject readPopularTvData(Context context){
        if(FileUtil.fileExist(context, POPULAR_TV_FILE_NAME)){
            String data = FileUtil.readJson(context, POPULAR_TV_FILE_NAME);
            return FileUtil.parseToJson(data);
        }
        return null;
    }

    public static boolean dataExist(Context context){
        return FileUtil.fileExist(context, POPULAR_TV_FILE_NAME) && FileUtil.fileExist(context, POPULAR_MOVIE_FILE_NAME);
    }


}
