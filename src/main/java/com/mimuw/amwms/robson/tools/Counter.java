package com.mimuw.amwms.robson.tools;

public class Counter {
    private Integer count = 1;

    public Integer getCount() {
        return count;
    }

    public Counter add() {
        count++;
        return this;
    }
}
