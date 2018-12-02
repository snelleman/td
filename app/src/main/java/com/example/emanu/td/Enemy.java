package com.example.emanu.td;

import com.example.emanu.td.Graphics.Drawable;
import com.example.emanu.td.Graphics.Drawer;
import com.example.emanu.td.Utils.Rect;
import com.example.emanu.td.Utils.Vector;

public class Enemy implements Drawable {

    private int hp = 100;
    private float speed = 7;
    private float size = 8;
    private int damage = 5;
    private int color = 0xFF0000;

    private Vector pos;


    public Enemy(float x, float y){
        pos = new Vector(x,y);
    }
    public Enemy(Vector p) {
        this(p.x,p.y);
    }

    private Rect rect = new Rect();
    @Override
    public void draw(Drawer draw, float scale, Vector offset) {
        rect.left = (pos.x - size/2) * scale + offset.x;
        rect.top =  (pos.y - size/2) * scale + offset.y;
        rect.right = (pos.x + size/2) * scale + offset.x;
        rect.bottom =  (pos.y + size/2) * scale + offset.y;
        draw.drawRect(rect, color);
    }
}
