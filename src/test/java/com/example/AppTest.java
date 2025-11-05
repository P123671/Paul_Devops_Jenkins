package com.example;
 
import org.junit.Test;
import static org.junit.Assert.assertEquals;
 
public class AppTest {
 
    @Test
    public void testMultiply() {
        assertEquals(6, App.multiply(2, 3));
    }
 
    @Test
    public void testNegativeNumbers() {
        assertEquals(-6, App.multiply(2, -3));
    }
}
