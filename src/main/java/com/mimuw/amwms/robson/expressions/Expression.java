package com.mimuw.amwms.robson.expressions;

import com.mimuw.amwms.robson.exceptions.BladWykonania;
import com.mimuw.amwms.robson.tools.Counter;

import java.util.Map;

public interface Expression {

    double wykonaj(Map<String, Double> globalVariables) throws BladWykonania;
    String toJava(Counter funCount);

}
