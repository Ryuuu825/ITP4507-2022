package com.BMS.Utils;

@Deprecated(forRemoval = true)
public final class Logger {
    public static void debug(String msg) {
        System.out.println("\033[37;41m" + msg + "\033[0m");
    }

}
