package com.example.emanu.td.Utils;

public class Rect {
    public float left;
    public float top;
    public float right;
    public float bottom;

    public Rect(float left, float top, float right, float bottom) {
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
    }
    public Rect() {
        this(0,0,0,0);
    }

    public void set(float left, float top, float right, float bottom){
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!Rect.class.isAssignableFrom(obj.getClass())) {
            return false;
        }
        final Rect other = (Rect) obj;
        return this.left == other.left &&
                this.top == other.top &&
                this.right == other.right &&
                this.bottom == other.bottom;
    }

    @Override
    public int hashCode() {
        int hash = 17;
        hash = (int) (hash * 31 + left);
        hash = (int) (hash * 31 + top);
        hash = (int) (hash * 31 + right);
        hash = (int) (hash * 31 + bottom);
        return hash;
    }

    @Override
    public String toString(){
        return "left: " + left + ", top: " + top + ", right" + right + ", bottom: " + bottom;
    }
}
