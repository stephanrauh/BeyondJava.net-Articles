import java.io.*;
import java.lang.System;
import java.nio.file.*;
import java.nio.charset.*;
import javax.script.*;
import org.mozilla.javascript.*;
import org.graalvm.polyglot.Value;

public class Speedtest {

    static final int RUNS = 300;

    static String readFile(String fileName) throws IOException,FileNotFoundException {
        return new String(Files.readAllBytes(Paths.get(fileName)), StandardCharsets.UTF_8);
    }

    static void rhino(String parser, String code) {
        String source = "speedtest";
        int line = 1;
        Context context = Context.enter();
        context.setOptimizationLevel(9);
        try {
            Scriptable scope = context.initStandardObjects();
            context.evaluateString(scope, parser, source, line, null);
            ScriptableObject.putProperty(scope, "$code", Context.javaToJS(code, scope));

            Object tree = new Object();
            Object tokens = new Object();
            for (int i = 1; i <= RUNS; ++i) {
                long start = System.nanoTime();
                tree = context.evaluateString(scope, "esprima.parse($code)", source, line, null);
                tokens = context.evaluateString(scope, "esprima.tokenize($code)", source, line, null);
                long stop = System.nanoTime();
                if (i <= 2 || (i % 10 == 0 && i < 30) || (i % 50 == 0)) {
                    System.out.println("#" + i + ":\t" + Math.round((stop - start) / 1e6) + " ms");
                }
            }
        } finally {
            Context.exit();
            System.gc();
        }
    }

    static void nashorn(String parser, String code) throws ScriptException,NoSuchMethodException {
        ScriptEngineManager factory = new ScriptEngineManager();
        ScriptEngine engine = factory.getEngineByName("nashorn");

        engine.eval(parser);
        Invocable inv = (Invocable) engine;
        Object esprima = engine.get("esprima");

        Object tree = new Object();
        Object tokens = new Object();
        for (int i = 1; i <= RUNS; ++i) {
            long start = System.nanoTime();
            tree = inv.invokeMethod(esprima, "parse", code);
            tokens = inv.invokeMethod(esprima, "tokenize", code);
            long stop = System.nanoTime();
            if (i <= 2 || (i % 10 == 0 && i < 30) || (i % 50 == 0)) {
                System.out.println("#" + i + ":\t" + Math.round((stop - start) / 1e6) + " ms");
            }
        }
    }

    static void graal(String parser, String code) throws ScriptException, NoSuchMethodException {
        try (org.graalvm.polyglot.Context context = org.graalvm.polyglot.Context.create()) {
            Value jsBindings = context.getBindings("js");
            Value jsProgram = context.eval("js", parser);
            for (int i = 1; i <= RUNS; ++i) {
                long start = System.nanoTime();
                Value esprima = jsBindings.getMember("esprima");
                Value tree = esprima.invokeMember("parse", parser);
                Value tokens = esprima.invokeMember("tokenize", parser);
                long stop = System.nanoTime();
                if (i <= 2 || (i % 10 == 0 && i < 30) || (i % 50 == 0)) {
                    System.out.println("#" + i + ":\t" + Math.round((stop - start) / 1e6) + " ms");
                }
            }
        }
    }

    public static void main(String[] args) {
        try {
            String parser = readFile("src/main/resources/esprima.js");
            String code = readFile("src/main/resources/jquery-3.5.1.min.js");
            System.out.println();
            System.out.println("== Truffle ==");
            try {
                Class.forName("org.graalvm.polyglot.Context");
                graal(parser, code);
            } catch (Exception e) {
                System.out.println("Truffle is only available in the GraalVM.");
            }

            System.out.println("== Rhino ==");
            rhino(parser, code);
            System.out.println();
            System.out.println("== Nashorn ==");
            nashorn(parser, code);
            System.out.println();
        } catch (Exception e) {
            System.err.println("Trouble: " + e.toString());
        }
    }
}

