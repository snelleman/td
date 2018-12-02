package com.example.emanu.td;

import com.example.emanu.td.Utils.Vector;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class VectorTest {

    final private float delta = 0.00001f;

    @Test
    public void vectorTest_construction(){
        Vector vector = new Vector(2f,3f);
        assertThat(vector.x, is(2f));
        assertThat(vector.y, is(3f));

        vector = new Vector(-1.2f,0f);
        assertThat(vector.x, is(-1.2f));
        assertThat(vector.y, is(0f));
    }

    @Test
    public void VectorTest_equals(){
        Vector vector1 = new Vector(100.5f,-33.33f);
        Vector vector2 = new Vector(100.5f,-33.33f);
        assertThat(vector1, is(vector2));

        vector1 = new Vector(0f,0f);
        vector2 = new Vector(0f,0f);
        assertThat(vector1, is(vector2));
    }

    @Test
    public void VectorTest_notEquals(){
        Vector vector1 = new Vector(100.5f,-33.33f);
        Vector vector2 = new Vector(100.5f,-33.32f);
        assertThat(vector1, is(not(vector2)));

        vector1 = new Vector(-3f,-2f);
        vector2 = new Vector(-2f,-3f);
        assertThat(vector1, is(not(vector2)));

        vector1 = new Vector(3f,1f);
        vector2 = new Vector(-3f,1f);
        assertThat(vector1, is(not(vector2)));
    }

    @Test
    public void VectorTest_notEquals_nonVectors(){
        Vector vector1 = new Vector(100.5f,-33.33f);
        assertThat(vector1, is(not(new Object())));
        assertThat(vector1.equals(null), is(false));
    }

    @Test
    public void vectorTest_set(){
        Vector vector1 = new Vector(2f,3f);
        Vector vector2 = new Vector(4.01f, -93f);
        Vector vector3 = vector1.set(4.01f, -93f);
        assertThat(vector1, is(vector2));
        assertThat(vector3, is(sameInstance(vector1)));
    }

    @Test
    public void vectorTest_set_vector(){
        Vector vector1 = new Vector(0f,0f);
        Vector vector2 = new Vector(-3.14f, 3.14f);
        Vector vector2Copy = new Vector(-3.14f, 3.14f);
        Vector vector3 = vector1.set(vector2);
        assertThat(vector1, is(vector2));
        assertThat(vector1, is(not(sameInstance(vector2))));
        assertThat(vector2, is(vector2Copy));
        assertThat(vector3, is(sameInstance(vector1)));
    }

    @Test
    public void vectorTest_add(){
        Vector vector1 = new Vector(5f,70f);
        Vector vector2 = new Vector(5.01f, -93f);
        Vector vector2Copy = new Vector(5.01f, -93f);
        Vector vector3 = vector1.add(vector2);

        assertEquals(vector1.x, 10.01f, delta);
        assertEquals(vector1.y, -23f, delta);
        assertThat(vector2, is(vector2Copy));
        assertThat(vector3, is(sameInstance(vector1)));
    }

    @Test
    public void vectorTest_subtract(){
        Vector vector1 = new Vector(5f,70f);
        Vector vector2 = new Vector(5.01f, -93f);
        Vector vector2Copy = new Vector(5.01f, -93f);
        Vector vector3 = vector1.subtract(vector2);

        assertEquals(vector1.x, -0.01f, delta);
        assertEquals(vector1.y, 163f, delta);
        assertThat(vector2, is(vector2Copy));
        assertThat(vector3, is(sameInstance(vector1)));
    }

    @Test
    public void vectorTest_multiply(){
        Vector vector1 = new Vector(-5.05f,70f);
        Vector vector3 = vector1.multiply(4f);
        assertEquals(vector1.x, -20.2f, delta);
        assertEquals(vector1.y, 280f, delta);
        assertThat(vector3, is(sameInstance(vector1)));

        vector1.set(-5.05f,70f);
        vector1.multiply(-5.05f);
        assertEquals(vector1.x, 25.5025f, delta);
        assertEquals(vector1.y, -353.5f, delta);
    }

    @Test
    public void vectorTest_multiply_zero(){
        Vector vector1 = new Vector(0f,0f);
        Vector product = new Vector(0f, 0f);
        vector1.multiply(-400.76f);
        assertThat(vector1, is(product));
    }


    @Test
    public void vectorTest_multiply_withZero(){
        Vector vector1 = new Vector(-5.05f,70f);
        Vector product = new Vector(0f, 0f);
        vector1.multiply(0f);
        assertThat(vector1, is(product));
    }

    @Test
    public void vectorTest_divide(){
        Vector vector1 = new Vector(-5.05f,70f);
        Vector vector3 = vector1.divide(3f);
        assertEquals(vector1.x, -1.68333333333f, delta);
        assertEquals(vector1.y, 23.3333333333f, delta);
        assertThat(vector3, is(sameInstance(vector1)));

        vector1.set(-5.05f,70f);
        vector1.divide(-5.05f);
        assertEquals(vector1.x, 1f, delta);
        assertEquals(vector1.y, -13.8613861386f, delta);
    }

    @Test
    public void vectorTest_divide_zero(){
        Vector vector1 = new Vector(0f,0f);
        Vector quotient = new Vector(0f, 0f);
        vector1.divide(75.75f);
        assertThat(vector1, is(quotient));
    }


    @Test
    public void vectorTest_divide_ByZero(){
        Vector vector1 = new Vector(3.00001f,-70f);
        Vector quotient = new Vector(Float.POSITIVE_INFINITY, Float.NEGATIVE_INFINITY);
        vector1.divide(0);
        assertThat(vector1, is(quotient));
    }

    @Test
    public void vectorTest_normalize(){
        Vector vector = new Vector(0f,1f);
        Vector normalized = new Vector(0f,1f);
        Vector vector2 = vector.normalize();
        assertThat(vector, is(normalized));
        assertThat(vector, is(sameInstance(vector2)));

        vector = new Vector(-1f,0f);
        normalized = new Vector(-1f,0f);
        vector.normalize();
        assertThat(vector, is(normalized));
        vector = new Vector(10.5f,23f);
        normalized = new Vector(0.415292349589646f, 0.90968800386303f);
        vector.normalize();
        assertThat(vector, is(normalized));
        vector = new Vector(-0.2f,0.1f);
        normalized = new Vector(-0.89442719099992f, 0.447213595499958f);
        vector.normalize();
        assertThat(vector, is(normalized));
    }

    @Test
    public void vectorTest_distanceTo(){
        Vector vector = new Vector(3.14f,-1001f);
        Vector vectorCopy = new Vector(3.14f,-1001f);
        assertThat(vector.distanceTo(3.14f,-1001f), is(0f));
        assertThat(vector, is(vectorCopy));
        assertThat(vector.distanceTo(3.14f,-1000f), is(1f));
        assertThat(vector.distanceTo(3.14f,-1002f), is(1f));
        assertThat(vector.distanceTo(0f,20.03f), is(1021.03485f));
    }


    @Test
    public void vectorTest_distanceTo_vector(){
        Vector vector1 = new Vector(3.14f,-1001f);
        Vector vector2 = new Vector(0f, 20.03f);
        Vector vector2Copy = new Vector(0f, 20.03f);
        assertThat(vector1.distanceTo(vector2), is(1021.03485f));
        assertThat(vector2, is(vector2Copy));
    }
}
