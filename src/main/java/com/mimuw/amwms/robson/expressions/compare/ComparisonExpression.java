package com.mimuw.amwms.robson.expressions.compare;

import com.mimuw.amwms.robson.expressions.Expression;
import com.mimuw.amwms.robson.expressions.arithmetic.TwoArgumentExpression;
import com.mimuw.amwms.robson.tools.Counter;
import com.mimuw.amwms.robson.tools.FunctionSignatureWriter;

public abstract class ComparisonExpression extends TwoArgumentExpression {
    public ComparisonExpression(Expression argument1, Expression argument2) {
        super(argument1, argument2);
    }

    @Override
    public String toJava(Counter funCount) {
        FunctionSignatureWriter funWriter = new FunctionSignatureWriter(funCount.getCount());
        StringBuilder builder = new StringBuilder();

        builder.append(funWriter.getSignature());
        int funId1 = funCount.getCount() + 1;
        String builderArgument1 = argument1.toJava(funCount.add());

        builder.append(" { return " )
                .append("toDouble(function" + (funId1) + "()");

        int funId2 = funCount.getCount() + 1;
        String builderArgument2 = argument2.toJava(funCount.add());

        builder.append(getSign())
                .append("function" + (funId2) + "())")
                .append("; }");

        builder.append("\n")
                .append(builderArgument1);
        builder.append("\n")
                .append(builderArgument2);

        return builder.toString();
    }
}
