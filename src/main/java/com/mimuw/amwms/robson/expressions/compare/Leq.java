package com.mimuw.amwms.robson.expressions.compare;

import com.mimuw.amwms.robson.exceptions.BladWykonania;
import com.mimuw.amwms.robson.expressions.Expression;
import com.mimuw.amwms.robson.tools.Casting;
import com.mimuw.amwms.robson.tools.Counter;
import com.mimuw.amwms.robson.tools.FunctionSignatureWriter;
import lombok.ToString;

import java.util.Map;

@ToString
public class Leq extends ComparisonExpression {
    public Leq(Expression argument1, Expression argument2) {
        super(argument1, argument2);
    }

    @Override
    public double wykonaj(Map<String, Double> globalVariables) throws BladWykonania {
        return Casting.toDouble(argument1.wykonaj(globalVariables) <= argument2.wykonaj(globalVariables));
    }

    @Override
    public String getSign() {
        return " <= ";
    }

}
