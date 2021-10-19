package com.mimuw.amwms.robson.expressions.bool;

import com.mimuw.amwms.robson.expressions.Expression;
import com.mimuw.amwms.robson.tools.Counter;
import com.mimuw.amwms.robson.tools.FunctionSignatureWriter;

public abstract class BooleanExpression implements Expression {

    public abstract String getValue();

    @Override
    public String toJava(Counter funCount) {
        FunctionSignatureWriter funWriter = new FunctionSignatureWriter(funCount.getCount());
        StringBuilder builder = new StringBuilder();

        builder.append(funWriter.getSignature());
        builder.append(" { return " )
                .append(getValue())
                .append("; }");

        return builder.toString();
    }
}
