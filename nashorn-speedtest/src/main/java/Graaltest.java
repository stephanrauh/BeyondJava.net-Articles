import java.io.*;
import java.lang.System;
import java.nio.file.*;
import java.nio.charset.*;
import javax.script.*;

import org.graalvm.polyglot.*;

public class Graaltest {

    static final int RUNS = 30;

    static String readFile(String fileName) throws IOException, FileNotFoundException {
        return new String(Files.readAllBytes(Paths.get(fileName)), StandardCharsets.UTF_8);
    }

    static void graal(String parser, String code) throws ScriptException, NoSuchMethodException {
        try (Context context = Context.create()) {
            Value jsBindings = context.getBindings("js");
            Value jsProgram = context.eval("js", parser);
            for (int i = 1; i <= RUNS; ++i) {
                long start = System.nanoTime();

                Value esprima = jsBindings.getMember("esprima");
                Value tree = esprima.invokeMember("parse", parser);
                Value tokens = esprima.invokeMember("tokenize", parser);
                long stop = System.nanoTime();
                if (i <= 2 || (i % 10 == 0 && i < 30) || (i % 50 == 0)) {
                    System.out.println("Run #" + i + ": " + Math.round((stop - start) / 1e6) + " ms");
                }
            }
        }
    }

    public static void main(String[] args) {
        try {
            String parser = readFile("src/main/resources/esprima.js");
            String code = readFile("src/main/resources/jquery-3.5.1.min.js");
            System.out.println("Test code: " + code.length() + " bytes.");
            System.out.println();
            System.out.println("== GraalVM ==");
            graal(parser, code);
        } catch (Exception e) {
            System.err.println("Trouble: " + e.toString());
        }
    }
}
