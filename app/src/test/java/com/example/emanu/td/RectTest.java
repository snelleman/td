package com.example.emanu.td;

import com.example.emanu.td.Utils.Rect;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static junit.framework.TestCase.assertFalse;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNot.not;

@RunWith(MockitoJUnitRunner.class)
public class RectTest {

    @Test
    public void rectTest_emptyConstructor(){
        Rect rect = new Rect();
        assertThat(rect.left, is(0f));
        assertThat(rect.top, is(0f));
        assertThat(rect.right, is(0f));
        assertThat(rect.bottom, is(0f));
    }

    @Test
    public void rectTest_nonEmptyConstructor(){
        Rect rect = new Rect(-1.1f, 0f, 1.1f, 2.2f);
        assertThat(rect.left, is(-1.1f));
        assertThat(rect.top, is(0f));
        assertThat(rect.right, is(1.1f));
        assertThat(rect.bottom, is(2.2f));
    }


    @Test
    public void rectTest_equals(){
        Rect rect1 = new Rect(-1.1f, 0f, 1.1f, 2.2f);
        Rect rect2 = new Rect(-1.1f, 0f, 1.1f, 2.2f);
        assertThat(rect1, is(rect2));

        rect2 = new Rect(0f, 0f, 0f, 0f);
        assertThat(rect1, is(not(rect2)));

        rect2 = new Rect(1.1f, 0f, 1.1f, 2.2f);
        assertThat(rect1, is(not(rect2)));

        assertThat(rect1, is(not(new Object())));
        assertFalse(rect1.equals(null));
    }


    @Test
    public void rectTest_set() {
        Rect rect1 = new Rect(-1.1f, 0f, 1.1f, 2.2f);
        Rect rect2 = new Rect(2.2f, -1.1f, 0f, 1.1f);
        rect1.set(2.2f, -1.1f, 0f, 1.1f);
        assertThat(rect1, is(rect2));

        rect1.set(0f, 0f, 0f, 0f);
        Rect rect3 = new Rect(0f,0f,0f,0f);
        assertThat(rect1, is(rect3));
    }

    @Test
    public void rectTest_hashCode(){
        Rect rect1 = new Rect(-1.1f, 0f, 1.1f, 2.2f);
        Rect rect2 = new Rect(-1.1f, 0f, 1.1f, 2.2f);
        assertThat(rect1.hashCode(), is(rect2.hashCode()));

        rect2 = new Rect(0f, 0f, 0f, 0f);
        assertThat(rect1.hashCode(), is(not(rect2.hashCode())));

        rect2 = new Rect(1.1f, 0f, 1.1f, 2.2f);
        assertThat(rect1.hashCode(), is(not(rect2.hashCode())));

        rect2 = new Rect(1.1f, 0f, -1.1f, 2.2f);
        assertThat(rect1.hashCode(), is(not(rect2.hashCode())));
    }
}
