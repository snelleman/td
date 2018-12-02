package com.example.emanu.td.Input;

import android.view.MotionEvent;
import android.view.View;

import com.example.emanu.td.Utils.Vector;

/**
 * Created by emanu on 2017-01-18.
 */

public class Input implements View.OnTouchListener {

    PanAndZoom panAndZoom = new PanAndZoom();

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        panAndZoom.onTouch(motionEvent);

        return true;
    }

    public Vector getOffset(){
        return panAndZoom.getOffset();
    }
    public float getScale(){
        return panAndZoom.getScale();
    }
}
