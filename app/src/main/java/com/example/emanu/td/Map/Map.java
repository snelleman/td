package com.example.emanu.td.Map;

import android.graphics.Bitmap;

import com.example.emanu.td.Graphics.Drawable;
import com.example.emanu.td.Graphics.Drawer;
import com.example.emanu.td.Utils.Rect;
import com.example.emanu.td.Utils.Utils;
import com.example.emanu.td.Utils.Vector;

/**
 * Created by emanu on 2016-11-15.
 */

public class Map implements Drawable {

    private Bitmap tileSet;
    private int height, width;
    private int tileSize, tileSizeSrc;
    private int tileSetWidth;
    private int[][] tiles;

    public Map(Bitmap tileSet, int height, int width, int tileSize, int tileSizeSrc, int tileSetWidth, int[][] tiles) {
        this.tileSet = tileSet;
        this.height = height;
        this.width = width;
        this.tileSize = tileSize;
        this.tileSizeSrc = tileSizeSrc;
        this.tileSetWidth = tileSetWidth;
        this.tiles = tiles;
    }

    public void draw(Drawer drawer, float scale, Vector offset){
        for(int y = 0; y<height; y++){
            for(int x = 0; x<width; x++){
                drawTile(drawer, scale, offset, x, y);
            }

        }
    }

    private void drawTile(Drawer drawer, float scale, Vector offset, int x, int y){
        Rect srcRect = new Rect();
        Rect desRect = new Rect();

        srcRect.left =   (tiles[y][x]-1)% tileSetWidth * tileSizeSrc;
        srcRect.top =    (tiles[y][x]-1)/ tileSetWidth * tileSizeSrc;
        srcRect.right =  srcRect.left + tileSizeSrc;
        srcRect.bottom = srcRect.top + tileSizeSrc;

        desRect.left =   (x*tileSize + (tileSize - tileSizeSrc)) * scale + offset.x;
        desRect.top =    (y*tileSize + (tileSize - tileSizeSrc)) * scale + offset.y;
        desRect.right =  (x*tileSize + tileSize)*scale + offset.x;
        desRect.bottom = (y*tileSize + tileSize)*scale + offset.y;
        if (visible(desRect)){
            drawer.drawBitmap(tileSet, srcRect, desRect);
        }
    }

    private boolean visible(Rect rect){
        return 0 < rect.right && rect.left < Utils.width &&
                0 < rect.bottom && rect.top <  Utils.height;
    }

    public int posToTile(float x, float y){
        if(x<0 || y<0){
            throw new IndexOutOfBoundsException();
        }
        return tiles[(int)(y / tileSize)][(int)(x / tileSize)];
    }
    public int posToTile(Vector p){
        return posToTile(p.x, p.y);
    }
}
