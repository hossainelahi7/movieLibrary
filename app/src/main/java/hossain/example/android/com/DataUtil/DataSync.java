package hossain.example.android.com.DataUtil;

import android.content.Context;
import android.util.Log;

import org.json.JSONObject;

import hossain.example.android.com.NetworkUtil.MovieDBAPI;

public class DataSync {

    private static final String POPULAR_MOVIE_FILE_NAME = "populerMovie.json";
    private static final String POPULAR_TV_FILE_NAME = "populerTV.json";
    private static final String TOP_RATED_MOVIE_FILE_NAME = "topRatedMovie.json";

    public static void syncData(Context context){
        String popularMovieResponse = MovieDBAPI.getPopulerMovieResponse();
        String popularTVResponse = MovieDBAPI.getPopulerTVResponse();
        String topratedMovieResponse = MovieDBAPI.getTopRatedMovieResponse();
        if(!FileUtil.fileExist(context, POPULAR_MOVIE_FILE_NAME) && popularMovieResponse !=null)
            FileUtil.storeAsJson(context, POPULAR_MOVIE_FILE_NAME, popularMovieResponse);
        if(!FileUtil.fileExist(context, POPULAR_TV_FILE_NAME) && popularTVResponse != null)
            FileUtil.storeAsJson(context, POPULAR_TV_FILE_NAME, popularTVResponse);
        if(!FileUtil.fileExist(context, TOP_RATED_MOVIE_FILE_NAME) && topratedMovieResponse != null)
            FileUtil.storeAsJson(context, TOP_RATED_MOVIE_FILE_NAME, topratedMovieResponse);
    }


    public static JSONObject readPopularMovieData(Context context){
        if(FileUtil.fileExist(context, POPULAR_MOVIE_FILE_NAME)){
            String response = FileUtil.readJson(context, POPULAR_MOVIE_FILE_NAME);
            Log.d("PopularMovieData", response);
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

    public static JSONObject readTopRatedMovieData(Context context){
        if(FileUtil.fileExist(context, TOP_RATED_MOVIE_FILE_NAME)){
            String response = FileUtil.readJson(context, TOP_RATED_MOVIE_FILE_NAME);
//            Log.d("TopRatedMovieData", response);
            return FileUtil.parseToJson(response);
        }
        return null;
    }

    public static boolean dataExist(Context context){
        return FileUtil.fileExist(context, POPULAR_TV_FILE_NAME)
                && FileUtil.fileExist(context, POPULAR_MOVIE_FILE_NAME) &&
                FileUtil.fileExist(context, TOP_RATED_MOVIE_FILE_NAME);
    }


}
