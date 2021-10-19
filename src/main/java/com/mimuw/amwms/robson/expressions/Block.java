package com.mimuw.amwms.robson.expressions;

import com.mimuw.amwms.robson.exceptions.BladWykonania;
import com.mimuw.amwms.robson.tools.Counter;
import com.mimuw.amwms.robson.tools.FunctionSignatureWriter;
import com.squareup.moshi.Json;
import lombok.ToString;

import java.util.List;
import java.util.Map;

@ToString
public class Block implements Expression {
    @Json(name = "instrukcje")
    private List<Expression> instructions;

    public Block(List<Expression> instructions) {
        this.instructions = instructions;
    }

    @Override
    public double wykonaj(Map<String, Double> globalVariables) throws BladWykonania {
        double result = 0;

        for (Expression e : instructions) {
            result = e.wykonaj(globalVariables);
        }

        return result;
    }

    @Override
    public String toJava(Counter funCount) {
        FunctionSignatureWriter funWriter = new FunctionSignatureWriter(funCount.getCount());
        StringBuilder builder = new StringBuilder();
        StringBuilder functions = new StringBuilder();

        builder.append(funWriter.getSignature());
        builder.append(" { \n");

        int funId1 = funCount.getCount();
        for (int i = 0; i < instructions.size(); i++) {
            funId1 = funCount.getCount() + 1;
            String builderInstructions = instructions.get(i).toJava(funCount.add());

            if (i < instructions.size() - 1) {
                builder.append("function" + (funId1) + "();\n");
            }

            functions.append(builderInstructions)
                    .append("\n");
        }

        // returning 0 if there are no instructions
        if (instructions.size() == 0) {
            builder.append(" return 0.0;\n}");
        }
        else {
            builder.append(" return ")
                    .append("function" + (funId1) + "()")
                    .append(";\n} ");

            builder.append("\n")
                    .append(functions.toString());
        }

        return builder.toString();
    }
}
