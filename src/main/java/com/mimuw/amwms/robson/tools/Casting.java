package com.mimuw.amwms.robson.tools;

public class Casting {

    public static double toDouble(boolean bool) {
        return bool ? 1.0 : 0.0;
    }

    public static boolean toBoolean(double d) {
        return d != 0.0;
    }
}
