package com.lfm.test;

import jdk.nashorn.api.scripting.NashornScriptEngine;

import javax.script.*;
import java.io.FileReader;

public class Nashorn {
    public static void main(String[] args){
        NashornScriptEngine  engine = (NashornScriptEngine) new ScriptEngineManager().getEngineByName("nashorn");
        try {
//            SimpleBindings simpleBindings = new SimpleBindings();
//            simpleBindings.put("value","ddsdsa");
//            Object eval = engine.eval("function eee(value){print(value);}");
            CompiledScript compile = engine.compile(new FileReader("fnn.js"));
            CompiledScript compile1 = engine.compile(new FileReader("ds.js"));
            compile.eval();
            compile1.eval();
            double eee = (double) engine.invokeFunction("f","value");
            engine.invokeFunction("f2");
            System.out.println(eee);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
