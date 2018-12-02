package com.example.emanu.td;

import com.example.emanu.td.Graphics.Drawer;
import com.example.emanu.td.Utils.Rect;
import com.example.emanu.td.Utils.Vector;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class EnemyTest {

    @Mock
    Drawer mockDrawer;

    @Test
    public void enemyTest_draw(){
        Enemy enemy = new Enemy(7f, -30.6f);

        doNothing().when(mockDrawer).drawRect(any(), anyInt());

        enemy.draw(mockDrawer, 1, new Vector(0, 0));

        Rect rect = new Rect(3f, -34.6f, 11f, -26.6f);
        verify(mockDrawer).drawRect(rect, 0xFF0000);
    }
}
