package com.mimuw.amwms.robson.tools;

public class FunctionSignatureWriter {

    private String signature;

    public FunctionSignatureWriter(Integer number) {
       signature = "public static double function" + number + "()";
    }

    public String getSignature() {
        return signature;
    }
}
