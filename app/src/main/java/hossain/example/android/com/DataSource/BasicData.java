package hossain.example.android.com.DataSource;

import android.support.annotation.NonNull;

import org.json.JSONObject;

import hossain.example.android.com.DataUtil.JsonUtil;

public class BasicData extends JSONObject {

    private class BaseKeys {
        public static final String TITLE ="title";
        public static final String NAME = "name";
        public static final String GENRES ="genre_ids";
        public static final String POPULARITY = "popularity";
        public static final String VOTE_COUNT = "vote_count";
        public static final String BACKDROP_PATH = "backdrop_path";
        public static final String ID = "id";
        public static final String VOTE_AVG = "vote_average";
        public static final String OVERVIEW = "overview";
        public static final String POSTER_PATH = "poster_path";
    }

    public int entryType;
    public int id;
    public String title;
    public Integer[] genryIds;
    public String posterPath;
    public Double popularity;
    public int voteCount;
    public String backdropPath;
    public Double avgVote;
    public String overView;


    public BasicData(JSONObject jsonObject, @NonNull int type){
        entryType = type;
        genryIds = JsonUtil.getGenryIds(jsonObject, BaseKeys.GENRES);
        if(jsonObject.has(BaseKeys.NAME))
            title = JsonUtil.getString(jsonObject, BaseKeys.NAME);
        else
            title = JsonUtil.getString(jsonObject, BaseKeys.NAME);
        popularity = JsonUtil.getDouble(jsonObject, BaseKeys.POPULARITY);
        voteCount = JsonUtil.getInt(jsonObject, BaseKeys.VOTE_COUNT);
        backdropPath = JsonUtil.getString(jsonObject, BaseKeys.BACKDROP_PATH);
        id = JsonUtil.getInt(jsonObject, BaseKeys.ID);
        avgVote = JsonUtil.getDouble(jsonObject, BaseKeys.VOTE_AVG);
        overView = JsonUtil.getString(jsonObject, BaseKeys.OVERVIEW);
        posterPath = JsonUtil.getString(jsonObject, BaseKeys.POSTER_PATH);
    }



}
