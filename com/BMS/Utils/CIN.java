package com.BMS.Utils;

public class CIN {

    private static final java.util.Scanner cin = new java.util.Scanner(System.in);

    public static String nextLine() {
        return cin.nextLine();
    }

    public static String next() {
        return cin.next();
    }

    public static int nextInt() {
        while (true) {
            try {
                return Integer.parseInt(cin.next());
            } catch (NumberFormatException e) {
                System.out.println("Invalid Input");
                cin.nextLine();
            }
        }
    }

    public static double nextDouble() {

        while (true) {
            try {
                return Double.parseDouble(cin.next());
            } catch (NumberFormatException e) {
                System.out.println("Invalid Input");
                cin.nextLine();
            }
        }
    }

}
