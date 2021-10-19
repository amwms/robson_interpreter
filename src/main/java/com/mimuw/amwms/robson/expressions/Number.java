package com.mimuw.amwms.robson.expressions;

import com.mimuw.amwms.robson.tools.Counter;
import com.mimuw.amwms.robson.tools.FunctionSignatureWriter;
import com.squareup.moshi.Json;
import lombok.ToString;

import java.util.Map;

@ToString
public class Number implements Expression {
    @Json(name = "wartosc")
    private double value;

    public Number(double value) {
        this.value = value;
    }

    @Override
    public double wykonaj(Map<String, Double> globalVariables) {
        return value;
    }

    @Override
    public String toJava(Counter funCount) {
        FunctionSignatureWriter funWriter = new FunctionSignatureWriter(funCount.getCount());
        StringBuilder builder = new StringBuilder();

        builder.append(funWriter.getSignature());
        builder.append(" { return " ).append(value).append("; }");

        return builder.toString();
    }

}
