package com.mimuw.amwms.robson.expressions.arithmetic;

import com.mimuw.amwms.robson.exceptions.BladWykonania;
import com.mimuw.amwms.robson.expressions.Expression;
import lombok.ToString;

import java.util.Map;

@ToString
public class Division extends TwoArgumentExpression {
    public Division(Expression argument1, Expression argument2) {
        super(argument1, argument2);
    }

    @Override
    public double wykonaj(Map<String, Double> globalVariables) throws BladWykonania {
        double arg1 = argument1.wykonaj(globalVariables);
        double arg2 = argument2.wykonaj(globalVariables);

        if (arg2 == 0.0)
            throw new BladWykonania();

        return arg1 / arg2;
    }

    @Override
    public String getSign() {
        return " / ";
    }

}
