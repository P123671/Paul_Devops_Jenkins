package com.example;
 
public class App {
 
    // Simple method to demonstrate a unit test
    public static int multiply(int a, int b) {
        return a * b;
    }
 
    // Another example method for demonstration
    public static boolean isEven(int number) {
        return number % 2 == 0;
    }
 
    public static void main(String[] args) {
        System.out.println("Hello from Jenkins CI/CD!");
        System.out.println("2 x 3 = " + multiply(2, 3));
        System.out.println("Is 4 even? " + isEven(4));
    }
}
