package com.example.emanu.td;

import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;

import com.example.emanu.td.Graphics.Graphics;
import com.example.emanu.td.Input.Input;
import com.example.emanu.td.Map.Map;
import com.example.emanu.td.Map.MapFactory;
import com.example.emanu.td.Utils.Utils;

import org.json.JSONObject;

/**
 * Created by emanu on 2016-11-15.
 */

public class GameActivity extends AppCompatActivity {

    private Input input;
    private Graphics graphics;

    private Map map;

    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            Display display = getWindowManager().getDefaultDisplay();
            Point point = new Point();
            display.getSize(point);
            Utils.height = point.y;
            Utils.width = point.x;

            Bundle bundle = getIntent().getExtras();
            String mapName = bundle.getString("map");

            JSONObject jsonObject = Initializer.getMap(this, mapName);

            map = MapFactory.createMap(this, jsonObject);


            input = new Input();
            graphics = new Graphics(this, input, map);
            graphics.setOnTouchListener(input);
            setContentView(graphics);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    protected void onPause(){
        graphics.pause();
        super.onPause();
    }

    protected void onResume() {
        super.onResume();
        graphics.resume();
    }
}
