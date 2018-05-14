package hossain.example.android.com.DataUtil;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;


public class FileUtil {

    public static boolean storeAsJson(@NonNull Context context, @NonNull String fileName,@NonNull String data){
        FileOutputStream outputStream;
        Log.d(fileName, "Content:\n "+data);
        try {
            outputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            outputStream.write(data.getBytes());
            outputStream.close();
            return true;
        }catch (Exception e){
            Log.e("StoreAsJson", ""+e.getMessage());
            return false;
        }
    }

    public static String readJson(@NonNull Context context, @NonNull String fileName){
        String content;
        FileInputStream inputStream;
        try{
            StringBuilder stringBuilder = new StringBuilder();
            inputStream = context.openFileInput(fileName);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new BufferedInputStream(inputStream)));
            while ((content = bufferedReader.readLine()) != null){
                stringBuilder.append(content);
                stringBuilder.append('\n');
            }
            bufferedReader.close();
            inputStream.close();
            Log.d("ReadData", stringBuilder.toString());
            return stringBuilder.toString();
        }catch (Exception e){
            Log.e("readJson", ""+e.getMessage());
        }
        return null;
    }

    public static boolean fileExist(Context context, String fileName){
        File file = new File(context.getFilesDir(), fileName);
        return file.exists();
    }

    public static JSONObject parseToJson(String data){
        JSONObject json;
        try {
            json = new JSONObject(data);
        } catch (JSONException e) {
            json = null;
        }
        return json;
    }
}
