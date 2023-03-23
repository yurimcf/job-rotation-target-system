package org.targetsystem.jobrotation;

import java.util.Scanner;

public class Fibonacci {
    public static void main(String[] args) {
        Scanner scr = new Scanner(System.in);
        System.out.print("Digite um número: ");
        int number = scr.nextInt();
        scr.close();

        Boolean isFound = Fibonacci(number);

        if (isFound) {
            System.out.printf("[%d] pertence à sequência de Fibonacci", number);
        } else {
            System.out.printf("[%d] ão pertence à sequência de Fibonacci", number);
        }

    }

    public static Boolean Fibonacci(Integer number) {
        Integer fib1 = 0, fib2 = 1, fib3;
        Boolean isFound = false;
        while (fib1 <= number) {
            if (fib1 == number) {
                isFound = true;
                break;
            }
            fib3 = fib1 + fib2;
            fib1 = fib2;
            fib2 = fib3;
        }
        return isFound;
    }
}