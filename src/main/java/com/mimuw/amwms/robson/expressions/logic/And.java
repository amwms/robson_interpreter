package com.mimuw.amwms.robson.expressions.logic;

import com.mimuw.amwms.robson.exceptions.BladWykonania;
import com.mimuw.amwms.robson.expressions.Expression;
import com.mimuw.amwms.robson.expressions.arithmetic.TwoArgumentExpression;
import com.mimuw.amwms.robson.tools.Casting;
import lombok.ToString;

import java.util.Map;

@ToString
public class And extends LogicTwoArgumentsExpression {
    public And(Expression argument1, Expression argument2) {
        super(argument1, argument2);
    }

    @Override
    public double wykonaj(Map<String, Double> globalVariables) throws BladWykonania {
        return Casting.toDouble(Casting.toBoolean(argument1.wykonaj(globalVariables))
                && Casting.toBoolean(argument2.wykonaj(globalVariables)));
    }

    @Override
    public String getSign() {
        return " && ";
    }

}
