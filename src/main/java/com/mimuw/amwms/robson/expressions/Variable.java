package com.mimuw.amwms.robson.expressions;

import com.mimuw.amwms.robson.exceptions.BladWykonania;
import com.mimuw.amwms.robson.tools.Counter;
import com.mimuw.amwms.robson.tools.FunctionSignatureWriter;
import com.squareup.moshi.Json;
import lombok.ToString;

import java.util.Map;

@ToString
public class Variable implements Expression {
    @Json(name = "nazwa")
    private String name;

    public Variable(String name) {
        this.name = name;
    }

    @Override
    public double wykonaj(Map<String, Double> globalVariables) throws BladWykonania {
        if (!globalVariables.containsKey(name))
            globalVariables.put(name, 0.0);

        return globalVariables.get(name);
    }

    @Override
    public String toJava(Counter funCount) {
        FunctionSignatureWriter funWriter = new FunctionSignatureWriter(funCount.getCount());
        StringBuilder builder = new StringBuilder();

        builder.append(funWriter.getSignature());
        builder.append(" { return " ).append("getGlobalVariables(\"").append(name).append("\"); }");

        return builder.toString();
    }

}
