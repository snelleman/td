package com.example.emanu.td.Map;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import org.json.JSONArray;
import org.json.JSONObject;

public class MapFactory {
    public static Map createMap(Context context, JSONObject jsonObject) throws Exception {
        Bitmap tileSet = BitmapFactory.decodeResource(
                context.getResources(),
                context.getResources().getIdentifier(
                        jsonObject.getString("tileSet"),
                        "drawable",
                        context.getPackageName()));
        int height = jsonObject.getInt("height");
        int width = jsonObject.getInt("width");
        int tileSize = jsonObject.getInt("tileSize");
        int tileSizeSrc = jsonObject.getInt("tileSizeSrc");
        int tileSetWidth = jsonObject.getInt("tileSetWidth");
        JSONArray rows = jsonObject.getJSONArray("tiles");
        int[][] tiles = new int[height][width];
        for (int y = 0; y < rows.length(); y++) {
            JSONArray columns = rows.getJSONArray(y);
            for (int x = 0; x < columns.length(); x++) {
                tiles[y][x] = columns.getInt(x);
            }
        }
        return new Map(tileSet, height, width, tileSize, tileSizeSrc, tileSetWidth, tiles);
    }
}
