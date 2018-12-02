package com.example.emanu.td;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by emanu on 2016-12-12.
 */

public class Initializer {
    static public JSONObject getMap(Context context, String mapName)throws Exception{
        InputStream is = context.getAssets().open(mapName);
        int size = is.available();
        byte[] buffer = new byte[size];
        is.read(buffer);
        is.close();
        String json = new String(buffer, "UTF-8");
        return new JSONObject(json);
    }
}
