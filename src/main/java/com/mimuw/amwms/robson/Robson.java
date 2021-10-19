package com.mimuw.amwms.robson;

import com.mimuw.amwms.robson.exceptions.BladWykonania;
import com.mimuw.amwms.robson.exceptions.NieprawidlowyProgram;
import com.mimuw.amwms.robson.expressions.*;
import com.mimuw.amwms.robson.expressions.Number;
import com.mimuw.amwms.robson.expressions.bool.False;
import com.mimuw.amwms.robson.expressions.bool.True;
import com.mimuw.amwms.robson.expressions.compare.*;
import com.mimuw.amwms.robson.expressions.logic.And;
import com.mimuw.amwms.robson.expressions.logic.Not;
import com.mimuw.amwms.robson.expressions.logic.Or;
import com.mimuw.amwms.robson.expressions.arithmetic.*;
import com.mimuw.amwms.robson.tools.Counter;
import com.mimuw.amwms.robson.tools.FunctionWriter;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.adapters.PolymorphicJsonAdapterFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Robson {
    private Expression myProgram;
    private Map<String, Double> globalVariables = new HashMap<>();
    public static Integer funCount = 1;
    private Moshi moshi = new Moshi.Builder()
            .add(
                    PolymorphicJsonAdapterFactory.of(Expression.class, "typ")
                            .withSubtype(Plus.class, "Plus")
                            .withSubtype(Number.class, "Liczba")
                            .withSubtype(Block.class, "Blok")
                            .withSubtype(Assign.class, "Przypisanie")
                            .withSubtype(Eq.class, "==")
                            .withSubtype(Geq.class, ">=")
                            .withSubtype(Leq.class, "<=")
                            .withSubtype(Neq.class, "!=")
                            .withSubtype(Lesser.class, "<")
                            .withSubtype(Greater.class, ">")
                            .withSubtype(And.class, "And")
                            .withSubtype(Division.class, "Dzielenie")
                            .withSubtype(If.class, "If")
                            .withSubtype(Minus.class, "Minus")
                            .withSubtype(Multiply.class, "Razy")
                            .withSubtype(Not.class, "Not")
                            .withSubtype(Or.class, "Or")
                            .withSubtype(Variable.class, "Zmienna")
                            .withSubtype(While.class, "While")
                            .withSubtype(True.class, "True")
                            .withSubtype(False.class, "False")
            )
            .build();
    private JsonAdapter<Expression> jsonAdapter = moshi.adapter(Expression.class);

    public void fromJSON(String filename) throws NieprawidlowyProgram {
        try {
            File file = new File(filename);
            Scanner scan = null;

            scan = new Scanner(file);

            StringBuilder wholeFileBuilder = new StringBuilder();
            while (scan.hasNext()) {
                wholeFileBuilder.append(scan.next());
            }

            String myFile = wholeFileBuilder.toString();
            myProgram = jsonAdapter.fromJson(myFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void toJSON(String filename) {
        try {
            PrintWriter printer = new PrintWriter(filename);
            printer.write(jsonAdapter.toJson(myProgram));
            printer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void toJava(String filename) {
        try {
            Counter functionCount = new Counter();
            FunctionWriter funWriter = new FunctionWriter();
            PrintWriter printer = new PrintWriter(filename);

            int id = filename.lastIndexOf("/");

            String className = filename.replace(".java", "").substring(id + 1);

            printer.write(funWriter.writeImportMap());
            printer.write("public class " + className + " { \n");
            printer.write(funWriter.writeMap());
            printer.write(funWriter.writeToBool());
            printer.write(funWriter.writeToDouble());
            printer.write(funWriter.writeGetVariables());
            printer.write("\n");

            printer.write(myProgram.toJava(functionCount));
            printer.write("\n");
            printer.write(funWriter.writeMain());

            printer.write("}");
            printer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public double wykonaj() throws BladWykonania {
        globalVariables.clear();
        return myProgram.wykonaj(globalVariables);
    }

}
