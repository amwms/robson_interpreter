package com.mimuw.amwms.robson.expressions.arithmetic;

import com.mimuw.amwms.robson.expressions.Expression;
import com.mimuw.amwms.robson.tools.Counter;
import com.mimuw.amwms.robson.tools.FunctionSignatureWriter;

public abstract class TwoArgumentExpression implements Expression {
    protected Expression argument1;
    protected Expression argument2;

    public TwoArgumentExpression(Expression argument1, Expression argument2) {
        this.argument1 = argument1;
        this.argument2 = argument2;
    }

    public abstract String getSign();

    @Override
    public String toJava(Counter funCount) {
        FunctionSignatureWriter funWriter = new FunctionSignatureWriter(funCount.getCount());
        StringBuilder builder = new StringBuilder();

        builder.append(funWriter.getSignature());
        int funId1 = funCount.getCount() + 1;
        String builderArgument1 = argument1.toJava(funCount.add());

        builder.append(" { return " )
                .append("function" + (funId1) + "()")
                .append(getSign());

        int funId2 = funCount.getCount() + 1;
        String builderArgument2 = argument2.toJava(funCount.add());

        builder.append("function" + (funId2) + "()")
                .append("; }");

        builder.append("\n")
                .append(builderArgument1);
        builder.append("\n")
                .append(builderArgument2);

        return builder.toString();
    }
}
