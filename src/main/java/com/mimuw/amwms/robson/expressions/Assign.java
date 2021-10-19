package com.mimuw.amwms.robson.expressions;

import com.mimuw.amwms.robson.exceptions.BladWykonania;
import com.mimuw.amwms.robson.tools.Counter;
import com.mimuw.amwms.robson.tools.FunctionSignatureWriter;
import com.squareup.moshi.Json;
import lombok.ToString;

import java.util.Map;

@ToString
public class Assign implements Expression {
    @Json(name = "nazwa")
    private String name;
    @Json(name = "wartosc")
    private Expression value;

    public Assign(String name, Expression value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public double wykonaj(Map<String, Double> globalVariables) throws BladWykonania {
        double val = value.wykonaj(globalVariables);
        globalVariables.put(name, val);
        return val;
    }

    @Override
    public String toJava(Counter funCount) {
        FunctionSignatureWriter funWriter = new FunctionSignatureWriter(funCount.getCount());
        StringBuilder builder = new StringBuilder();

        builder.append(funWriter.getSignature());
        int funId1 = funCount.getCount() + 1;
        String builderValue = value.toJava(funCount.add());

        String variableInit = "globalVariables.put(\"" + name + "\", function" + (funId1) + "());\n";
        builder.append(" { \n    " + variableInit);

        builder.append("    return " )
                .append("function" + (funId1) + "()")
                .append("; \n}");

        builder.append("\n")
                .append(builderValue);

        return builder.toString();
    }
}
