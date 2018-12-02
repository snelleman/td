package com.example.emanu.td;


import com.example.emanu.td.Graphics.Drawer;
import com.example.emanu.td.Map.Map;
import com.example.emanu.td.Utils.Rect;
import com.example.emanu.td.Utils.Utils;
import com.example.emanu.td.Utils.Vector;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

/**
 * Created by emanu on 2018-05-01.
 */

@RunWith(MockitoJUnitRunner.class)
public class MapTest {

    @Mock
    private Drawer mockDrawer;

    private final String testJson = "" +
            "{\n" +
            "  \"tileSet\": \"tile_set\",\n" +
            "  \"height\": 2,\n" +
            "  \"width\": 3,\n" +
            "  \"tileSize\": 50,\n" +
            "  \"tileSizeSrc\": 100,\n" +
            "  \"tileSetWidth\": 2,\n" +
            "  \"tiles\": [\n" +
            "    [1,2,3],\n" +
            "    [4,5,6]\n" +
            "  ]\n" +
            "}";

    private final Map testMap1 = new Map(
            null,
            2,
            3,
            50,
            100,
            2,
            new int[][]{
                    {1,2,3},
                    {4,5,6}
            });

    private final String testJson2 = "" +
            "{\n" +
            "  \"tileSet\": \"tile_set\",\n" +
            "  \"height\": 2,\n" +
            "  \"width\": 2,\n" +
            "  \"tileSize\": 1,\n" +
            "  \"tileSizeSrc\": 2,\n" +
            "  \"tileSetWidth\": 2,\n" +
            "  \"tiles\": [\n" +
            "    [1,2],\n" +
            "    [3,4]\n" +
            "  ]\n" +
            "}";

    private final Map testMap2 = new Map(
            null,
            2,
            2,
            1,
            2,
            2,
            new int[][]{
                    {1,2},
                    {3,4}
            });

    @Test
    public void mapTest_draw(){
        Utils.height = 100;
        Utils.width = 150;

        doNothing().when(mockDrawer).drawBitmap(any(), any(), any());

        Vector offset = new Vector(0, 0);
        testMap1.draw(mockDrawer, 1, offset);

        InOrder inOrder = inOrder(mockDrawer);
        Rect srcRect = new Rect(0,0,100,100);
        Rect dstRect = new Rect(-50,-50,50,50);
        inOrder.verify(mockDrawer).drawBitmap(any(), eq(srcRect), eq(dstRect));
        srcRect.set(100,0,200,100);
        dstRect.set(0,-50,100,50);
        inOrder.verify(mockDrawer).drawBitmap(any(), eq(srcRect), eq(dstRect));
        srcRect.set(0,100,100,200);
        dstRect.set(50,-50,150,50);
        inOrder.verify(mockDrawer).drawBitmap(any(), eq(srcRect), eq(dstRect));
        srcRect.set(100,100,200,200);
        dstRect.set(-50,0,50,100);
        inOrder.verify(mockDrawer).drawBitmap(any(), eq(srcRect), eq(dstRect));
        srcRect.set(0,200,100,300);
        dstRect.set(0,0,100,100);
        inOrder.verify(mockDrawer).drawBitmap(any(), eq(srcRect), eq(dstRect));
        srcRect.set(100,200,200,300);
        dstRect.set(50,0,150,100);
        inOrder.verify(mockDrawer).drawBitmap(any(), eq(srcRect), eq(dstRect));
    }

    @Test
    public void mapTest_draw_scale(){
        Utils.height = 200;
        Utils.width = 300;

        doNothing().when(mockDrawer).drawBitmap(any(), any(), any());

        Vector offset = new Vector(0, 0);
        testMap1.draw(mockDrawer, 2, offset);

        InOrder inOrder = inOrder(mockDrawer);
        Rect srcRect = new Rect(0,0,100,100);
        Rect dstRect = new Rect(-100,-100,100,100);
        inOrder.verify(mockDrawer).drawBitmap(any(), eq(srcRect), eq(dstRect));
        srcRect.set(100,0,200,100);
        dstRect.set(0,-100,200,100);
        inOrder.verify(mockDrawer).drawBitmap(any(), eq(srcRect), eq(dstRect));
    }

    @Test
    public void mapTest_draw_offset(){
        Utils.height = 200;
        Utils.width = 300;

        doNothing().when(mockDrawer).drawBitmap(any(), any(), any());

        Vector offset = new Vector(3, -25);
        testMap1.draw(mockDrawer, 1, offset);

        InOrder inOrder = inOrder(mockDrawer);
        Rect srcRect = new Rect(0,0,100,100);
        Rect dstRect = new Rect(-47,-75,53,25);
        inOrder.verify(mockDrawer).drawBitmap(any(), eq(srcRect), eq(dstRect));
        srcRect.set(100,0,200,100);
        dstRect.set(3,-75,103,25);
        inOrder.verify(mockDrawer).drawBitmap(any(), eq(srcRect), eq(dstRect));
    }

    @Test
    public void mapTest_draw_onlyOnScreen_bottomRight(){
        Utils.height = 50;
        Utils.width = 100;

        doNothing().when(mockDrawer).drawBitmap(any(), any(), any());

        Vector offset = new Vector(50, 50);
        testMap1.draw(mockDrawer, 1, offset);

        InOrder inOrder = inOrder(mockDrawer);
        Rect srcRect = new Rect(0,0,100,100);
        Rect dstRect = new Rect(0,0,100,100);
        inOrder.verify(mockDrawer).drawBitmap(any(), eq(srcRect), eq(dstRect));
        srcRect.set(100,0,200,100);
        dstRect.set(50,0,150,100);
        inOrder.verify(mockDrawer).drawBitmap(any(), eq(srcRect), eq(dstRect));
        verifyNoMoreInteractions(mockDrawer);
    }
    @Test
    public void mapTest_draw_onlyOnScreen_topLeft(){
        Utils.height = 50;
        Utils.width = 100;

        doNothing().when(mockDrawer).drawBitmap(any(), any(), any());

        Vector offset = new Vector(-50, -50);
        testMap1.draw(mockDrawer, 1, offset);

        InOrder inOrder = inOrder(mockDrawer);
        Rect srcRect = new Rect(0,200,100,300);
        Rect dstRect = new Rect(-50,-50,50,50);
        inOrder.verify(mockDrawer).drawBitmap(any(), eq(srcRect), eq(dstRect));
        srcRect.set(100,200,200,300);
        dstRect.set(0,-50,100,50);
        inOrder.verify(mockDrawer).drawBitmap(any(), eq(srcRect), eq(dstRect));
        verifyNoMoreInteractions(mockDrawer);
    }
    @Test
    public void mapTest_draw_onlyOnScreen_edges(){
        Utils.height = 1;
        Utils.width = 1;

        doNothing().when(mockDrawer).drawBitmap(any(), any(), any());

        Vector offset = new Vector(0, 0);
        testMap2.draw(mockDrawer, 1, offset);

        verify(mockDrawer, times(4)).drawBitmap(any(), any(), any());
    }

    @Test
    public void MapTest_posToTile(){
        int tile = testMap1.posToTile(3, 4);
        assertThat(tile, is(1));
        tile = testMap1.posToTile(75, 10);
        assertThat(tile, is(2));
        tile = testMap1.posToTile(105, 75);
        assertThat(tile, is(6));
    }
    @Test
    public void MapTest_posToTile_vector(){
        Vector vector = new Vector(3, 4);
        int tile = testMap1.posToTile(vector);
        assertThat(tile, is(1));
        vector.y = 90;
        tile = testMap1.posToTile(vector);
        assertThat(tile, is(4));
    }
    @Test
    public void MapTest_posToTile_edges(){
        int tile = testMap1.posToTile(0, 0);
        assertThat(tile, is(1));
        tile = testMap1.posToTile(50, 50);
        assertThat(tile, is(5));
        tile = testMap1.posToTile(149, 95);
        assertThat(tile, is(6));
    }
    @Test
    public void MapTest_posToTile_OutOfBounds(){
        try {
            testMap1.posToTile(-1, 10);
            Assert.fail("should throw indexOutOfBoundsException");
        }catch (IndexOutOfBoundsException ignored){}
        try {
            testMap1.posToTile(-1, -1);
            Assert.fail("should throw indexOutOfBoundsException");
        }catch (IndexOutOfBoundsException ignored){}
        try {
            testMap1.posToTile(150, 50);
            Assert.fail("should throw indexOutOfBoundsException");
        }catch (IndexOutOfBoundsException ignored){}
        try {
            testMap1.posToTile(50, 100);
            Assert.fail("should throw indexOutOfBoundsException");
        }catch (IndexOutOfBoundsException ignored){}
    }
}
