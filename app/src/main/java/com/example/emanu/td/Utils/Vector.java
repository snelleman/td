package com.example.emanu.td.Utils;

/**
 * Created by emanu on 2016-12-06.
 */

public class Vector {
    public float x,y;

    public Vector(float x, float y){
        this.x=x;
        this.y=y;
    }


    public Vector set(float x, float y){
        this.x = x;
        this.y = y;
        return this;
    }
    public Vector set(final Vector vector){
        set(vector.x, vector.y);
        return this;
    }

    public Vector normalize(){
        float length = this.distanceTo(0f,0f);
        this.divide(length);
        return this;
    }

    public float distanceTo(Vector vector){
        return distanceTo(vector.x, vector.y);
    }
    public float distanceTo(float x, float y){
        return (float)Math.sqrt( (x-this.x)*(x-this.x) + (y-this.y)*(y-this.y) );
    }

    public Vector add(final Vector v){
        x += v.x;
        y += v.y;
        return this;
    }

    public Vector subtract(final Vector v){
        x -= v.x;
        y -= v.y;
        return this;
    }

    public Vector multiply(float multiplier){
        x *= multiplier;
        y *= multiplier;
        return this;
    }

    public Vector divide(float divisor){
        x /= divisor;
        y /= divisor;
        return this;
    }


    @Override
    public int hashCode(){
        return (int)(x*17+y);
    }
    @Override
    public boolean equals(Object object){
        if(object instanceof Vector) {
            Vector other = (Vector) object;
            if (this.x == other.x && this.y == other.y) {
                return true;
            }
        }
        return false;
    }
    @Override
    public String toString(){
        return "" + x + ", " + + y;
    }
}
