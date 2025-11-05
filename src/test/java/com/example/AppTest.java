package com.example;
 
import org.junit.Test;
import static org.junit.Assert.*;
 
public class AppTest {
 
    @Test
    public void testMultiply() {
    	assertEquals(7, App.multiply(2, 3)); // WRONG ON PURPOSE
    }
 
    @Test
    public void testNegativeMultiply() {
        assertEquals(-6, App.multiply(-2, 3)); // -2x3 = -6 ✅
    }
 
    @Test
    public void testIsEvenTrue() {
        assertTrue(App.isEven(4)); // 4 is even ✅
    }
 
    @Test
    public void testIsEvenFalse() {
        assertFalse(App.isEven(5)); // 5 is odd ✅
    }
}
