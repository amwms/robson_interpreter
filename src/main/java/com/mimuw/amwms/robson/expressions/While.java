package com.mimuw.amwms.robson.expressions;

import com.mimuw.amwms.robson.exceptions.BladWykonania;
import com.mimuw.amwms.robson.tools.Casting;
import com.mimuw.amwms.robson.tools.Counter;
import com.mimuw.amwms.robson.tools.FunctionSignatureWriter;
import com.squareup.moshi.Json;
import lombok.ToString;

import java.util.Map;

@ToString
public class While implements Expression {
    @Json(name = "warunek")
    private Expression condition;
    @Json(name = "blok")
    private Expression block;

    public While(Expression condition, Expression block) {
        this.condition = condition;
        this.block = block;
    }

    @Override
    public double wykonaj(Map<String, Double> globalVariables) throws BladWykonania {
        while (Casting.toBoolean(condition.wykonaj(globalVariables))) {
            block.wykonaj(globalVariables);
        }

        return 0;
    }

    @Override
    public String toJava(Counter funCount) {
        FunctionSignatureWriter funWriter = new FunctionSignatureWriter(funCount.getCount());
        StringBuilder builder = new StringBuilder();

        builder.append(funWriter.getSignature());
        int funId1 = funCount.getCount() + 1;
        String builderCondition = condition.toJava(funCount.add());

        builder.append(" { while ( " )
                .append("toBool(function" + (funId1) + "()))")
                .append(" { ");

        int funId2 = funCount.getCount() + 1;
        String builderBlock = block.toJava(funCount.add());

        builder.append("function" + (funId2) + "();")
                .append(" } return 0;}");

        builder.append("\n")
                .append(builderCondition);
        builder.append("\n")
                .append(builderBlock);

        return builder.toString();
    }
}
