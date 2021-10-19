package com.mimuw.amwms.robson.expressions.bool;

import com.mimuw.amwms.robson.exceptions.BladWykonania;

import java.util.Map;

public class True extends BooleanExpression {
    @Override
    public double wykonaj(Map<String, Double> globalVariables) throws BladWykonania {
        return 1.0;
    }

    @Override
    public String getValue() {
        return "1.0";
    }
}
