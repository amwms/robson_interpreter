package com.mimuw.amwms.robson.expressions.logic;

import com.mimuw.amwms.robson.exceptions.BladWykonania;
import com.mimuw.amwms.robson.expressions.Expression;
import com.mimuw.amwms.robson.tools.Casting;
import com.mimuw.amwms.robson.tools.Counter;
import com.mimuw.amwms.robson.tools.FunctionSignatureWriter;
import lombok.ToString;

import java.util.Map;

@ToString
public class Not implements Expression {
    private Expression argument;

    public Not(Expression argument) {
        this.argument = argument;
    }

    @Override
    public double wykonaj(Map<String, Double> globalVariables) throws BladWykonania {
        return Casting.toDouble(!(argument.wykonaj(globalVariables) != 0.0));
    }

    @Override
    public String toJava(Counter funCount) {
        FunctionSignatureWriter funWriter = new FunctionSignatureWriter(funCount.getCount());
        StringBuilder builder = new StringBuilder();

        builder.append(funWriter.getSignature());
        int funId1 = funCount.getCount() + 1;
        String builderArgument1 = argument.toJava(funCount.add());

        builder.append(" { return " )
                .append("toDouble(!toBool(function" + (funId1) + "()))")
                .append("; }");

        builder.append("\n")
                .append(builderArgument1);

        return builder.toString();
    }

}
