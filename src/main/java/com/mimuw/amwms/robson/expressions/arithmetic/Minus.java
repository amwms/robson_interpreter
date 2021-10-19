package com.mimuw.amwms.robson.expressions.arithmetic;

import com.mimuw.amwms.robson.exceptions.BladWykonania;
import com.mimuw.amwms.robson.expressions.Expression;
import lombok.ToString;

import java.util.Map;

@ToString
public class Minus extends TwoArgumentExpression {
    public Minus(Expression argument1, Expression argument2) {
        super(argument1, argument2);
    }

    @Override
    public double wykonaj(Map<String, Double> globalVariables) throws BladWykonania {
        return argument1.wykonaj(globalVariables) - argument2.wykonaj(globalVariables);
    }

    @Override
    public String getSign() {
        return " - ";
    }

}
