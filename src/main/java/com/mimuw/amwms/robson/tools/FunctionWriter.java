package com.mimuw.amwms.robson.tools;

public class FunctionWriter {
    public String writeToDouble() {
        return "    public static double toDouble(boolean bool) {\n" +
                "        return bool ? 1.0 : 0.0;\n" +
                "    }\n";
    }

    public String writeToBool() {
        return "    public static boolean toBool(double d) {\n" +
                "        return d != 0.0;\n" +
                "    }\n";
    }

    public String writeMain() {
        return "    public static void main(String[] args) {\n" +
                "        System.out.println(function1());\n" +
                "    }\n";
    }

    public String writeMap() {
        return "    public static Map<String, Double> globalVariables = new HashMap<>();\n\n";
    }

    public String writeImportMap() {
        return "import java.util.HashMap;\n" +
                "import java.util.Map;\n";
    }

    public String writeGetVariables() {
        return "public static double getGlobalVariables(String name) {\n" +
                "        if (!globalVariables.containsKey(name))\n" +
                "            globalVariables.put(name, 0.0);\n" +
                "        return globalVariables.get(name);\n" +
                "    }\n";
    }
}
