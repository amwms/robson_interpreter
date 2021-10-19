package com.mimuw.amwms.robson.expressions;

import com.mimuw.amwms.robson.exceptions.BladWykonania;
import com.mimuw.amwms.robson.tools.Casting;
import com.mimuw.amwms.robson.tools.Counter;
import com.mimuw.amwms.robson.tools.FunctionSignatureWriter;
import com.squareup.moshi.Json;
import lombok.ToString;

import java.util.Map;

@ToString
public class If implements Expression {
    @Json(name = "warunek")
    private Expression condition;
    @Json(name = "blok_prawda")
    private Expression trueBlock;
    @Json(name = "blok_falsz")
    private Expression falseBlock = null;

    public If(Expression condition, Expression trueBlock, Expression falseBlock) {
        this.condition = condition;
        this.trueBlock = trueBlock;
        this.falseBlock = falseBlock;
    }

    public If(Expression condition, Expression trueBlock) {
        this.condition = condition;
        this.trueBlock = trueBlock;
    }

    @Override
    public double wykonaj(Map<String, Double> globalVariables) throws BladWykonania {
        return Casting.toBoolean(condition.wykonaj(globalVariables)) ? trueBlock.wykonaj(globalVariables) :
                (falseBlock != null ? falseBlock.wykonaj(globalVariables) : 0.0);
    }

    @Override
    public String toJava(Counter funCount) {
        FunctionSignatureWriter funWriter = new FunctionSignatureWriter(funCount.getCount());
        StringBuilder builder = new StringBuilder();

        builder.append(funWriter.getSignature());
        int funId1 = funCount.getCount() + 1;
        String builderCondition = condition.toJava(funCount.add());

        builder.append(" { return " )
                .append("toBool(function" + (funId1) + "())")
                .append(" ? ");

        int funId2 = funCount.getCount() + 1;
        String builderTrueBlock = trueBlock.toJava(funCount.add());

        builder.append("function" + (funId2) + "()")
                .append(" : ");

        if (falseBlock != null) {
            int funId3 = funCount.getCount() + 1;
            String builderFalseBlock = falseBlock.toJava(funCount.add());

            builder.append("function" + (funId3) + "()")
                    .append("; } ");
            builder.append("\n")
                    .append(builderFalseBlock);
        }
        else {
            builder.append("0.0")
                    .append("; } ");
        }

        builder.append("\n")
                .append(builderCondition);
        builder.append("\n")
                .append(builderTrueBlock);

        return builder.toString();
    }
}
