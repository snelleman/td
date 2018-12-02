package com.example.emanu.td.Graphics;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.emanu.td.Utils.Rect;

public class AndroidDrawer implements Drawer {

    public Canvas canvas;
    private Paint paint = new Paint();

    @Override
    public void drawBitmap(Bitmap bitmap, Rect srcRect, Rect dstRect) {
        android.graphics.Rect androidSrcRect = RectToAndroidRect(srcRect);
        android.graphics.Rect androidDstRect = RectToAndroidRect(dstRect);
        canvas.drawBitmap(bitmap, androidSrcRect, androidDstRect, null);
    }

    @Override
    public void drawRect(Rect rect, int color) {
        android.graphics.Rect androidRect = RectToAndroidRect(rect);
        paint.setColor(color);
        canvas.drawRect(androidRect, paint);
    }

    private android.graphics.Rect RectToAndroidRect(Rect rect){
        return new android.graphics.Rect((int)rect.left, (int)rect.top, (int)rect.right, (int)rect.bottom);
    }
}
