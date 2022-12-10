package com.github.ckroeger.javajs;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.Scriptable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Rhino {

    private static final Logger LOGGER = LoggerFactory.getLogger(Rhino.class);
    public static final String STATIC_POWER_JS = "/static/power.js";

    private final Helper helper = new Helper();

    public Object execPower(int num) {
        Context cx = Context.enter();
        Scriptable scope = cx.initStandardObjects();
        evalScript(cx, scope, STATIC_POWER_JS);

        return executeFunction(cx, scope, "power", num);
    }

    public Object exec(int num) {
        Context cx = Context.enter();
        Scriptable scope = cx.initStandardObjects();
        evalScript(cx, scope, STATIC_POWER_JS);
        evalScript(cx, scope, "/static/cubic.js");

        Object power = executeFunction(cx, scope, "power", num);
        Object cubic = executeFunction(cx, scope, "cubic", num);

        LOGGER.info("power({}) = {}, cubic({}) = {}", num, power, num, cubic);

        return power;
    }


    public Object execDynamic(int num) {
        Context cx = Context.enter();
        Scriptable scope = cx.initStandardObjects();
        evalScript(cx, scope, STATIC_POWER_JS);
        evalScript(cx, scope, "/static/cubic.js");

        var script = String.format("cubic(%d)/power(%d)", num, num);
        return cx.evaluateString(scope, script, "dynamic script", 1, null);
    }

    private static Object executeFunction(Context cx, Scriptable scope, String functionName, Object... functionArgs) {
        Object fObj = scope.get(functionName, scope);
        if (!(fObj instanceof Function f)) {
            LOGGER.info("f is undefined or not a function.");
        } else {
            return f.call(cx, scope, scope, functionArgs);
        }
        return null;
    }

    private void evalScript(Context cx, Scriptable scope, String pathToResource) {
        helper.loadFromClassPath(pathToResource).ifPresentOrElse(
                s -> cx.evaluateString(scope, s, pathToResource, 1, null),
                () -> LOGGER.warn("No script found ({})", pathToResource));

    }
}
