package fr.epita.assistants.maths;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MatrixTests {
    @Test
    void testConstructor1() {
        Matrix matrix = new Matrix(null);
        assertNull(matrix.getMatrix());
    }

    @Test
    void testConstructor2() {
        int[][] arr = {{1, 2, 3}, {4, 5, 6}};
        Matrix matrix = new Matrix(arr);
        assertNotNull(matrix.getMatrix());
    }

    @Test
    void testConstructor3() {
        int[][] arr = {{}};
        Matrix matrix = new Matrix(arr);
        assertNotNull(matrix.getMatrix());
    }

    @Test
    void testConstructor4() {
        int[][] arr = {{1, 2, 3}, {1, 2, 3}};
        Matrix matrix = new Matrix(arr);
        assertEquals(matrix.getMatrix()[0][0], matrix.getMatrix()[1][0]);
    }

    @Test
    void testConstructor5() {
        int[][] arr = {{1, 2}, {1, 2, 3}};
        Matrix matrix = new Matrix(arr);
        assertArrayEquals(matrix.getMatrix(), arr);
    }

    @Test
    void testEquals1() {
        int[][] arr = {{1, 2, 3}, {4, 5, 6}};
        Matrix m1 = new Matrix(arr);
        Matrix m2 = new Matrix(arr);
        assertTrue(m1.equals(m2));
    }

    @Test
    void testEquals2() {
        int[][] arr = {{1, 2, 3}, {4, 5, 6}};
        Matrix m1 = new Matrix(arr);
        Matrix m2 = new Matrix(arr);
        assertTrue(m2.equals(m1));
    }

    @Test
    void testEquals3() {
        int[][] arr = {{1, 2, 3}, {4, 5, 6}};
        int[][] arr2 = {{1, 5, 3}, {4, 5, 6}};
        Matrix m1 = new Matrix(arr);
        Matrix m2 = new Matrix(arr2);
        assertFalse(m1.equals(m2));
    }

    @Test
    void testEquals4() {
        int[][] arr = {{1, 2, 3}, {4, 5, 6}};
        Matrix m1 = new Matrix(arr);
        Matrix m2 = new Matrix(null);
        assertFalse(m1.equals(m2));
    }

    @Test
    void testEquals5() {
        int[][] arr = {{1, 2, 3}, {4, 5, 6}};
        Matrix m1 = new Matrix(arr);
        String s = "";
        assertFalse(m1.equals(s));
    }

    @Test
    void testMul1() {
        int[][] arr = {{1, 2, 3}, {4, 5, 6}};
        int[][] arr2 = {{1, 5, 3}, {4, 5, 6}};
        Matrix m1 = new Matrix(arr);
        Matrix m2 = new Matrix(arr2);

        Matrix res1 = m1.multiply(m2);
        Matrix res2 = m2.multiply(m1);
        assertFalse(res1.equals(res2));
    }

    @Test
    void testMul2() {
        int[][] arr = {{1, 2, 3}, {4, 5, 6}};
        Matrix m1 = new Matrix(arr);
        Matrix m2 = new Matrix(null);

        assertNotNull(m1.getMatrix());
        assertNotNull(m2.getMatrix());
        m1.multiply(m2);
    }
}
