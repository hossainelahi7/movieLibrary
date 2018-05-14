package hossain.example.android.com.DataSource;

import org.json.JSONObject;

import hossain.example.android.com.DataUtil.JsonUtil;

/**
 * Created by hossain on 4/25/18.
 */

public class MovieData extends JSONObject{

    private class MovieKeys{
        public static final String VOTE_COUNT = "vote_count";
        public static final String ID = "id";
        public static final String VIDEO = "video";
        public static final String AVG_VOTE = "vote_average";
        public static final String TITLE = "title";
        public static final String POPULARITY = "popularity";
        public static final String POSTER_PATH = "poster_path";
        public static final String ORIGINAL_LANGUAGE = "original_language";
        public static final String ORIGINAL_TITLE = "original_titile";
        public static final String GENRES = "genre_ids";
        public static final String BACKDROP_PATH = "";
        public static final String ADULT = "adult";
        public static final String OVERVIEW = "overview";
        public static final String RELEASE_DATE = "release_date";
    }

    public int voteCount;
    public int id;
    public boolean video;
    public Double avgVote;
    public String title;
    public Double popularity;
    public String posterPath;
    public String originalLanguage;
    public String originalTitle;
    public Integer[] genryIds;
    public String backdropPath;
    public boolean adult;
    public String overView;
    public String releaseData;

    public MovieData(JSONObject jsonObject){
        voteCount = JsonUtil.getInt(jsonObject, MovieKeys.VOTE_COUNT);
        id = JsonUtil.getInt(jsonObject, MovieKeys.ID);
        video = JsonUtil.getBoolean(jsonObject, MovieKeys.VIDEO);
        avgVote = JsonUtil.getDouble(jsonObject, MovieKeys.AVG_VOTE);
        title = JsonUtil.getString(jsonObject, MovieKeys.TITLE);
        popularity = JsonUtil.getDouble(jsonObject, MovieKeys.POPULARITY);
        posterPath = JsonUtil.getString(jsonObject, MovieKeys.POSTER_PATH);
        originalLanguage = JsonUtil.getString(jsonObject, MovieKeys.ORIGINAL_LANGUAGE);
        originalTitle = JsonUtil.getString(jsonObject, MovieKeys.ORIGINAL_TITLE);
        backdropPath = JsonUtil.getString(jsonObject, MovieKeys.BACKDROP_PATH);
        adult = JsonUtil.getBoolean(jsonObject, MovieKeys.ADULT);
        overView  = JsonUtil.getString(jsonObject, MovieKeys.OVERVIEW);
        releaseData = JsonUtil.getString(jsonObject, MovieKeys.RELEASE_DATE);
        genryIds = JsonUtil.getGenryIds(jsonObject, MovieKeys.GENRES);
    }
}
