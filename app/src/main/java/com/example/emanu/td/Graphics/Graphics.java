package com.example.emanu.td.Graphics;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.emanu.td.Input.Input;
import com.example.emanu.td.Map.Map;
import com.example.emanu.td.Utils.Vector;

/**
 * Created by emanu on 2016-11-15.
 */

public class Graphics extends SurfaceView implements Runnable {

    private Input input;
    private Map map;

    private SurfaceHolder holder;

    private Vector offset;
    private float scale;

    public boolean loop;
    public Thread t;

    public Graphics(Context context, Input input, Map map) {
        super(context);

        offset = new Vector(0,0);
        scale = 1;

        loop = true;

        this.input = input;
        this.map = map;


        holder = getHolder();
    }

    public void run(){
        Canvas c;
        AndroidDrawer drawer = new AndroidDrawer();
        while (loop) {

            offset.set(input.getOffset());
            scale = input.getScale();

            if (!holder.getSurface().isValid()) {
                continue;
            }

            c = holder.lockCanvas();
            c.drawColor(Color.WHITE);

            drawer.canvas = c;
            map.draw(drawer, scale, offset);

            holder.unlockCanvasAndPost(c);
        }
    }

    public void pause() {
        loop = false;
        while(true){
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            break;
        }

    }

    public void resume() {
        loop = true;
        t = new Thread(this);
        t.start();

    }
}
