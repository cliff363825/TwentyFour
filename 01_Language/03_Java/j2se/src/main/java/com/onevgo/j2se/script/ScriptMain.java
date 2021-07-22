package com.onevgo.j2se.script;

import lombok.extern.slf4j.Slf4j;

import javax.script.*;
import java.util.HashMap;
import java.util.List;

@Slf4j
public class ScriptMain {
    public static void main(String[] args) throws ScriptException {
//        testEngineFactories();
//        testJs();
//        testEngineScope();
        testBindings();
    }

    private static void testEngineFactories() {
        ScriptEngineManager manager = new ScriptEngineManager();
        List<ScriptEngineFactory> engineFactories = manager.getEngineFactories();
        log.info("{}", engineFactories);
    }

    private static void testJs() throws ScriptException {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("nashorn");
        engine.eval("n = 1728");
        Object result = engine.eval("n + 1");
        log.info("n + 1 = {}", result);

        Object threading = engine.getFactory().getParameter("THREADING");
        log.info("THREADING = {}", threading);
    }

    private static void testEngineScope() throws ScriptException {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("nashorn");

        engine.put("k", 1728);
        Object result = engine.eval("k + 1");
        log.info("k + 1 = {}", result);

        engine.put("m", new HashMap<>());
        engine.eval("m['font-size'] = '14px'");
        engine.eval("m.fontSize = '14px'");
        Object m = engine.get("m");
        log.info("m = {}", m);
    }

    private static void testBindings() throws ScriptException {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("nashorn");

        Bindings bindings = engine.createBindings();
        bindings.put("k", 1728);
        Object result = engine.eval("k + 1", bindings);
        log.info("k + 1 = {}", result);
    }

}
