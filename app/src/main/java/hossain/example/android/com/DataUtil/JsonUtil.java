package hossain.example.android.com.DataUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by hossain on 4/27/18.
 */

public class JsonUtil {

    public static int getInt(JSONObject json, String key){
        try {
            if(json.has(key)
                    && json.getInt(key)> 0)
                return json.getInt(key);
        } catch (Exception e){}
        return 0;
    }


    public static String getString(JSONObject json, String key){
        try{
            if(json.has(key)
                    && json.getString(key) != null)
                return json.getString(key);
        }catch (Exception e){}
        return "";
    }

    public static Double getDouble(JSONObject json, String key){
        try{
            if(json.has(key)
                    && json.getDouble(key) >= 0)
                return json.getDouble(key);
        }catch (Exception e){}
        return 0.0;
    }

    public static boolean getBoolean(JSONObject json, String key){
        try{
            if(json.has(key))
                return json.getBoolean(key);
        }catch (Exception e){}
        return false;
    }

    public static Integer[] getGenryIds(JSONObject jsonObject, String key){
        Integer[] genres = new Integer[0];
        try {
            if(jsonObject.has(key)
                    && jsonObject.getJSONArray(key).length() >0){
                JSONArray genryArrey =  jsonObject.getJSONArray(key);
                int length = genryArrey.length();
                genres = new Integer[length];
                for (int i =0; i<length; i++){
                    genres[i] = (int) genryArrey.get(i);
                }
            }else {
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return genres;
    }
}
