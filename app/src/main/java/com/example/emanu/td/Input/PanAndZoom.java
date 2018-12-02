package com.example.emanu.td.Input;

import android.view.MotionEvent;

import com.example.emanu.td.Utils.Utils;
import com.example.emanu.td.Utils.Vector;

public class PanAndZoom {

    int panIndex =-1, panId =-1,
            zoomIndex=-1, zoomId=-1;

    private Vector panPointerDown = new Vector(0,0);
    private Vector panPointer = new Vector(0,0);
    private Vector newOffset = new Vector(0,0);
    private Vector originalOffset = new Vector(0,0);

    private Vector zoomPointer = new Vector(0,0);
    private float originalPinchDistance;
    private float originalScale;

    private Vector zoomMidPointDown = new Vector(0,0);
    private Vector zoomMidPoint = new Vector(0,0);

    private Vector offset = new Vector(0,0);
    private float scale = 3;

    public void onTouch(MotionEvent motionEvent){

        // ACTION UP
        int indexUp = -1;
        if(motionEvent.getAction() == motionEvent.ACTION_UP){
            indexUp = 0;
        }else if(motionEvent.getActionMasked() == motionEvent.ACTION_POINTER_UP){
            indexUp = motionEvent.getActionIndex();
        }
        if(indexUp != -1) {
            panPointerUp(motionEvent, indexUp);
            zoomPointerUp(motionEvent, indexUp);
        }

        // ACTION DOWN
        if(motionEvent.getAction() == motionEvent.ACTION_DOWN){
            panPointerDown(motionEvent);
        }else if(motionEvent.getActionMasked() == motionEvent.ACTION_POINTER_DOWN){
            zoomPointerDown(motionEvent);
        }

        // ACTION MOVE
        panPointerMove(motionEvent);
        zoomPointerMove(motionEvent);
        panZoomMove(motionEvent);
    }

    private void panPointerUp(MotionEvent motionEvent, int index){
        if(panId==motionEvent.getPointerId(index)){
            if(zoomId != -1){
                panPointerDown.x=motionEvent.getX(zoomIndex);
                panPointerDown.y=motionEvent.getY(zoomIndex);
                originalOffset.set(offset);
                panId = zoomId;

                zoomId = -1;
                zoomIndex = -1;
            }else{
                panId = -1;
                panIndex = -1;
            }
        }
    }

    private void panPointerDown(MotionEvent motionEvent) {
        int downIndex = motionEvent.getActionIndex();
        panPointerDown.x = motionEvent.getX(downIndex);
        panPointerDown.y = motionEvent.getY(downIndex);
        originalOffset.set(offset);
        panId = motionEvent.getPointerId(downIndex);
    }

    private void panPointerMove(MotionEvent motionEvent){
        panIndex = motionEvent.findPointerIndex(panId);
        if (panIndex != -1){
            panPointer.x = motionEvent.getX(panIndex);
            panPointer.y = motionEvent.getY(panIndex);
            if(zoomId == -1){
                newOffset.set(panPointer).subtract(panPointerDown);
                offset.set(originalOffset).add(newOffset);
            }
        }
    }

    private void zoomPointerUp(MotionEvent motionEvent, int index) {
        if(zoomId==motionEvent.getPointerId(index)){
            panPointerDown.x = motionEvent.getX(panIndex);
            panPointerDown.y = motionEvent.getY(panIndex);
            originalOffset.set(offset);

            zoomId = -1;
            zoomIndex = -1;
        }
    }

    private void zoomPointerDown(MotionEvent motionEvent) {
        int downIndex = motionEvent.getActionIndex();
        zoomPointer.x = motionEvent.getX(downIndex);
        zoomPointer.y = motionEvent.getY(downIndex);
        originalPinchDistance = panPointer.distanceTo(zoomPointer);
        zoomMidPointDown.set(panPointer).add(zoomPointer).divide(2);
        originalOffset.set(offset);
        originalScale = scale;
        zoomId = motionEvent.getPointerId(downIndex);

    }

    private void zoomPointerMove(MotionEvent motionEvent) {
        zoomIndex = motionEvent.findPointerIndex(zoomId);
        if (zoomIndex != -1){
            zoomPointer.x = motionEvent.getX(zoomIndex);
            zoomPointer.y = motionEvent.getY(zoomIndex);
            float newPinchDistance = panPointer.distanceTo(zoomPointer);
            scale = originalScale * newPinchDistance/originalPinchDistance;
        }

    }

    private Vector zoomCompensation = new Vector(0, 0);
    private Vector zoomPointDistance = new Vector(0, 0);
    private void panZoomMove(MotionEvent motionEvent) {
        panIndex = motionEvent.findPointerIndex(panId);
        zoomIndex = motionEvent.findPointerIndex(zoomId);
        if (panIndex != -1 && zoomIndex != -1) {
            zoomMidPoint.set(panPointer).add(zoomPointer).divide(2);
            newOffset.set(zoomMidPoint).subtract(zoomMidPointDown);

            zoomPointDistance.set(zoomMidPointDown).subtract(originalOffset);
            zoomCompensation.set(zoomPointDistance).subtract(zoomPointDistance.multiply(scale / originalScale));
            offset.set(originalOffset).add(newOffset).add(zoomCompensation);
        }
    }

    public Vector getOffset(){
        return offset;
    }
    public float getScale(){
        return scale;
    }
}
