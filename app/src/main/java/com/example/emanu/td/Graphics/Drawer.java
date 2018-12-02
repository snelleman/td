package com.example.emanu.td.Graphics;

import android.graphics.Bitmap;
import android.graphics.Paint;

import com.example.emanu.td.Utils.Rect;

public interface Drawer {
    void drawBitmap(Bitmap bitmap, Rect srcRect, Rect dstRect);
    void drawRect(Rect rect, int color);
}
