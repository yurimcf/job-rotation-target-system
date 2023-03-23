package org.targetsystem.jobrotation;

import java.util.Scanner;

public class ReverseString {
    public static void main(String[] args) {
        Scanner scr = new Scanner(System.in);
        System.out.println("Digite uma String: ");
        String str = scr.nextLine();

        System.out.println("String original " + str);

        String strReverse = "";
        for (int i = str.length()-1; 0 <= i ; i--) {
            strReverse = strReverse + str.charAt(i);
        }
        System.out.println("String Invertida: " + strReverse.trim());
    }
}
