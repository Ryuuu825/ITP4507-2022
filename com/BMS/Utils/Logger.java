package com.BMS.Utils;

public final class Logger {
    public static void debug(String msg) {
       //  System.err.println("\033[37;41m" + msg + "\033[0m");
        System.err.println(msg);
    }

}
