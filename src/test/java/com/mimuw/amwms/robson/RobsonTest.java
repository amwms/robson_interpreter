package com.mimuw.amwms.robson;

import com.mimuw.amwms.robson.exceptions.BladWykonania;
import com.mimuw.amwms.robson.exceptions.NieprawidlowyProgram;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class RobsonTest {
    private Robson robson;

    @BeforeEach
    public void init() {
        robson = new Robson();
    }

    @Test
    public void TestAdditionRead() throws NieprawidlowyProgram {
        robson.fromJSON("test_programs/addition.json");
    }

    @Test
    public void TestFibbonaciRead() throws NieprawidlowyProgram {
        robson.fromJSON("test_programs/fibbonaci.json");
    }

    @Test
    public void TestFibbonaci() throws NieprawidlowyProgram, BladWykonania {
        robson.fromJSON("test_programs/fibbonaci.json");
        assertEquals(55.0, robson.wykonaj());
    }

    @Test
    public void TestAdd() throws NieprawidlowyProgram, BladWykonania {
        robson.fromJSON("test_programs/addition.json");
        assertEquals(15.0, robson.wykonaj());
    }

    @Test
    public void TestToJavaAdd() throws NieprawidlowyProgram {
        robson.fromJSON("test_programs/addition.json");
        robson.toJava("test_programs/wynikAdditionTest.java");
    }

    @Test
    public void TestToJavaFib() throws NieprawidlowyProgram {
        robson.fromJSON("test_programs/fibbonaci.json");
        robson.toJava("test_programs/wynikFibbonaciTest.java");
    }

    @Test
    public void TestToJson() throws NieprawidlowyProgram {
        robson.fromJSON("test_programs/addition.json");
        robson.toJSON("test_programs/wynikAdditionTestToJson.json");
    }

    // my test program in robson
    // uninitialized variable
    @Test
    public void TestInit() throws NieprawidlowyProgram, BladWykonania {
        robson.fromJSON("test_programs/uninitializedVariable.json");
        assertEquals(7.0, robson.wykonaj());
    }

    @Test
    public void TestToJavaInit() throws NieprawidlowyProgram {
        robson.fromJSON("test_programs/uninitializedVariable.json");
        robson.toJava("test_programs/wynikUninitializedVariableTest.java");
    }

    // finding gcd
    @Test
    public void TestGcd() throws NieprawidlowyProgram, BladWykonania {
        robson.fromJSON("test_programs/euklides.json");
        assertEquals(5.0, robson.wykonaj());
    }

    @Test
    public void TestToJavaGcd() throws NieprawidlowyProgram {
        robson.fromJSON("test_programs/euklides.json");
        robson.toJava("test_programs/wynikGcdTest.java");
    }
}