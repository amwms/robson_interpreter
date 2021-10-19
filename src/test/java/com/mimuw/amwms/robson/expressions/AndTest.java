package com.mimuw.amwms.robson.expressions;

import com.mimuw.amwms.robson.expressions.logic.And;
import com.mimuw.amwms.robson.tools.Counter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AndTest {

    @Test
    public void toJavaTest() {
        And and = new And(new And(new Number(3), new Number(2)), new Number(5));
        Counter ct = new Counter();
        assertEquals("public static double function1() { return toDouble(toBool(function2()) && toBool(function5())); }\n" +
                "public static double function2() { return toDouble(toBool(function3()) && toBool(function4())); }\n" +
                "public static double function3() { return 3.0; }\n" +
                "public static double function4() { return 2.0; }\n" +
                "public static double function5() { return 5.0; }", and.toJava(ct));
    }
}