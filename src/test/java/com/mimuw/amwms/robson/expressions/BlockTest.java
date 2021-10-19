package com.mimuw.amwms.robson.expressions;

import com.mimuw.amwms.robson.expressions.logic.And;
import com.mimuw.amwms.robson.expressions.logic.Or;
import com.mimuw.amwms.robson.tools.Counter;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class BlockTest {
    @Test
    public void toJavaTest() {
        Block block = new Block(new ArrayList<Expression>(Arrays.asList(new And(new Number(1.0), new Number(0.0)),
                new Or(new Number(1.0), new Number(0.0)))));
        Counter ct = new Counter();

        assertEquals("public static double function1() { \n" +
                "function2();\n" +
                " return function5();\n" +
                "} \n" +
                "public static double function2() { return toDouble(toBool(function3()) && toBool(function4())); }\n" +
                "public static double function3() { return 1.0; }\n" +
                "public static double function4() { return 0.0; }\n" +
                "public static double function5() { return toDouble(toBool(function6()) || toBool(function7())); }\n" +
                "public static double function6() { return 1.0; }\n" +
                "public static double function7() { return 0.0; }\n", block.toJava(ct));
    }

}