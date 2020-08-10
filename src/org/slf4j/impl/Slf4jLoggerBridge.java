package org.slf4j.impl;

import org.slf4j.helpers.MarkerIgnoringBase;
import sakura.kooi.logger.LogLevel;
import sakura.kooi.logger.Logger;
import sakura.kooi.logger.SakuraLogger;

public class Slf4jLoggerBridge extends MarkerIgnoringBase {
    private Logger backend;

    public Slf4jLoggerBridge(final String module) {
        backend = Logger.of(module);
    }

    //region Basic logging
    @Override
    public void trace(String s) {
        backend.trace(s);
    }

    @Override
    public void debug(String s) {
        backend.debug(s);
    }

    @Override
    public void info(String s) {
        backend.info(s);
    }

    @Override
    public void warn(String s) {
        backend.warn(s);
    }

    @Override
    public void error(String s) {
        backend.error(s);
    }
    //endregion

    //region Array format
    @Override
    public void trace(String s, Object... objects) {
        backend.trace(s, objects);
    }

    @Override
    public void debug(String s, Object... objects) {
        backend.debug(s, objects);
    }

    @Override
    public void info(String s, Object... objects) {
        backend.info(s, objects);
    }

    @Override
    public void warn(String s, Object... objects) {
        backend.warn(s, objects);
    }

    @Override
    public void error(String s, Object... objects) {
        backend.error(s, objects);
    }
    //endregion

    //region Exception format
    @Override
    public void trace(String s, Throwable throwable) {
        backend.trace(s, throwable);
    }

    @Override
    public void debug(String s, Throwable throwable) {
        backend.debug(s, throwable);
    }

    @Override
    public void info(String s, Throwable throwable) {
        backend.info(s, throwable);
    }

    @Override
    public void warn(String s, Throwable throwable) {
        backend.warn(s, throwable);
    }

    @Override
    public void error(String s, Throwable throwable) {
        backend.error(s, throwable);
    }
    //endregion

    //region slf4j format method
    @Override
    public void trace(String s, Object o) {
        backend.trace(s, o);
    }

    @Override
    public void trace(String s, Object o, Object o1) {
        backend.trace(s, o, o1);
    }

    @Override
    public void debug(String s, Object o) {
        backend.debug(s, o);
    }

    @Override
    public void debug(String s, Object o, Object o1) {
        backend.debug(s, o, o1);
    }

    @Override
    public void info(String s, Object o) {
        backend.info(s, o);
    }

    @Override
    public void info(String s, Object o, Object o1) {
        backend.info(s, o, o1);
    }

    @Override
    public void warn(String s, Object o) {
        backend.warn(s, o);
    }

    @Override
    public void warn(String s, Object o, Object o1) {
        backend.warn(s, o, o1);
    }

    @Override
    public void error(String s, Object o) {
        backend.error(s, o);
    }

    @Override
    public void error(String s, Object o, Object o1) {
        backend.error(s, o, o1);
    }
    //endregion

    //region slf4j isLevelEnabled
    @Override
    public boolean isTraceEnabled() {
        return SakuraLogger.isLogLevelEnabled(LogLevel.TRACE);
    }

    @Override
    public boolean isDebugEnabled() {
        return SakuraLogger.isLogLevelEnabled(LogLevel.DEBUG);
    }

    @Override
    public boolean isInfoEnabled() {
        return SakuraLogger.isLogLevelEnabled(LogLevel.INFO);
    }

    @Override
    public boolean isWarnEnabled() {
        return SakuraLogger.isLogLevelEnabled(LogLevel.WARN);
    }

    @Override
    public boolean isErrorEnabled() {
        return SakuraLogger.isLogLevelEnabled(LogLevel.ERROR);
    }
    //endregion
}
