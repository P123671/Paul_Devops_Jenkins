package com.example;
 
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
 
public class AppTest {
 
    @Test
    public void testGreeting() {
        String result = App.getGreeting();
        assertEquals("Hello from Jenkins!", result);
    }
}
