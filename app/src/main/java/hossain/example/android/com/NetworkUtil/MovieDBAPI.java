package hossain.example.android.com.NetworkUtil;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import hossain.example.android.com.movielibrary.BuildConfig;

/**
 * Created by hossain on 4/25/18.
 */

public class MovieDBAPI {
    private static final String API_KEY = BuildConfig.MOVIE_API_KEY;
    private static final String API_LINK = "https://api.themoviedb.org/3";
    private static final String POPULAR_MOVIE_STRING = "/movie/popular";
    private static final String POPULAR_TV_STRING = "/tv/popular";
    private static final String TOP_RATED_MOVIE_STRING = "/movie/top_rated";
    private static final String API_LINK_POPULAR = "https://api.themoviedb.org/3/movie/popular";
    private static final String API_IMAGE_URL = "https://image.tmdb.org/t/p/w500";


    public static String getPopulerMovieResponse(){
        URL populerURL = buildURL(POPULAR_MOVIE_STRING);
        String response = getURLResponse(populerURL);
        return response;
    }

    public static String getApiImageUrl(String imageLink){
        return API_IMAGE_URL+imageLink;
    }



    public static String getPopulerTVResponse(){
        URL populerURL = buildURL(POPULAR_TV_STRING);
        return getURLResponse(populerURL);
    }

    public static String getTopRatedMovieResponse(){
        URL topRatedURL = buildURL(TOP_RATED_MOVIE_STRING);
        return getURLResponse(topRatedURL);
    }


    private static String getURLResponse(URL url){
        String response = "";
        try{
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-length", "0");
            connection.setUseCaches(false);
            connection.setAllowUserInteraction(false);
            try {
                connection.connect();
                int status = connection.getResponseCode();
                switch (status){
                    case 200:
                    case 201:
                        InputStream in = connection.getInputStream();
                        Scanner scanner = new Scanner(in);
                        while (scanner.hasNext()) {
                            response = response + scanner.next()+" ";
                        }
                        scanner.close();
                        break;
                    default:
                        response = "Unexpected network Error";
                        break;
                }
            } finally {
                connection.disconnect();
            }
        }
        catch (MalformedURLException e) {}
        catch (IOException e) {}
        Log.d("Response", response);
        return response;
    }

    private static URL buildURL(String type){
        String link = API_LINK+type;
        Uri queryUri = Uri.parse(link).buildUpon()
                .appendQueryParameter("api_key", API_KEY)
                .build();
        try {
            URL url = new URL(queryUri.toString());
            Log.d("URL:", url.toString());
            return url;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }

}
