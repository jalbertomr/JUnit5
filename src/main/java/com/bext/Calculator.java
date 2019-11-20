package com.bext;

public class Calculator {

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        float result = calculator.divide(0, 0);
        System.out.println( result );
    }

    public float add(float a, float b) {
        return a + b;
    }

    public float multiply(float a, float b) {
        return a * b;
    }

    public float divide(float a, float b) throws ArithmeticException{
        float result;
        if (b == 0 && a != 0) throw new ArithmeticException("/ by zero");
        result = a / b;  // with float if number/0 returns infinity, 0/0 return NaN
        return result;
    }
}
