package hossain.example.android.com.DataSource;

import org.json.JSONObject;

import hossain.example.android.com.DataUtil.JsonUtil;


public class TVData extends JSONObject {

    private class TVKeys {
        public static final String ORIGINAL_NAME ="original_name";
        public static final String GENRES ="genre_ids";
        public static final String NAME = "name";
        public static final String POPULARITY = "popularity";
        public static final String ORIGIN_COUNTRY = "origin_country";
        public static final String VOTE_COUNT = "vote_count";
        public static final String FIRST_AIR_DATE = "first_air_date";
        public static final String BACKDROP_PATH = "backdrop_path";
        public static final String ORIGINAL_LANGUAGE = "original_language";
        public static final String ID = "id";
        public static final String VOTE_AVG = "vote_average";
        public static final String OVERVIEW = "overview";
        public static final String POSTER_PATH = "poster_path";
    }

    public String originalTitle;
    public Integer[] genryIds;
    public String title;
    public Double popularity;
    public String originCountry;
    public int voteCount;
    public String releaseData;
    public String backdropPath;
    public String originalLanguage;
    public int id;
    public Double avgVote;
    public String overView;
    public String posterPath;


    public TVData(JSONObject jsonObject){
        originalTitle = JsonUtil.getString(jsonObject, TVKeys.ORIGINAL_NAME);
        genryIds = JsonUtil.getGenryIds(jsonObject, TVKeys.GENRES);
        title = JsonUtil.getString(jsonObject, TVKeys.NAME);
        popularity = JsonUtil.getDouble(jsonObject, TVKeys.POPULARITY);
        originCountry = JsonUtil.getString(jsonObject, TVKeys.ORIGIN_COUNTRY);
        voteCount = JsonUtil.getInt(jsonObject, TVKeys.VOTE_COUNT);
        releaseData = JsonUtil.getString(jsonObject, TVKeys.FIRST_AIR_DATE);
        backdropPath = JsonUtil.getString(jsonObject, TVKeys.BACKDROP_PATH);
        originalLanguage = JsonUtil.getString(jsonObject, TVKeys.ORIGINAL_LANGUAGE);
        id = JsonUtil.getInt(jsonObject, TVKeys.ID);
        avgVote = JsonUtil.getDouble(jsonObject, TVKeys.VOTE_AVG);
        overView = JsonUtil.getString(jsonObject, TVKeys.OVERVIEW);
        posterPath = JsonUtil.getString(jsonObject, TVKeys.POSTER_PATH);
    }

}
