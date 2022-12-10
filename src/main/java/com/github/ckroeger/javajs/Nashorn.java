package com.github.ckroeger.javajs;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Nashorn {

    public static final String ENGINE_NAME = "nashorn";
    private static final Logger LOGGER = LoggerFactory.getLogger(Nashorn.class);

    public static void main(String[] args) throws ScriptException {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName(ENGINE_NAME);
        LOGGER.info("{}", engine.getClass());
        engine.eval("print('Hello World!');");

    }

    public Object exec() throws ScriptException, NoSuchMethodException {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName(ENGINE_NAME);
        Helper helper = new Helper();
        @SuppressWarnings("java:S3655") String script = helper.loadFromClassPath("/static/power.js").get();
        engine.eval(script);

        Invocable invocable = (Invocable) engine;

        Object result = invocable.invokeFunction("power", 10);
        LOGGER.info("{}", result);
        LOGGER.info("{}", result.getClass());
        return result;
    }

    public Object dynamicExec() throws ScriptException {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName(ENGINE_NAME);
        Helper helper = new Helper();
        @SuppressWarnings("java:S3655") String power = helper.loadFromClassPath("/static/power.js").get();
        engine.eval(power);
        @SuppressWarnings("java:S3655") String cubic = helper.loadFromClassPath("/static/cubic.js").get();
        engine.eval(cubic);
        return engine.eval("cubic(3)/power(3)");
    }
}
