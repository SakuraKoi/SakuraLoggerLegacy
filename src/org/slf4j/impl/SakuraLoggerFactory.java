package org.slf4j.impl;

import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class SakuraLoggerFactory implements ILoggerFactory {
    private final ConcurrentMap<String, Logger> loggerMap = new ConcurrentHashMap<>();

    public Logger getLogger(String name) {
        if (name.indexOf('.') != -1) {
            name = name.substring(name.lastIndexOf('.')+1);
        }
        Logger slf4jLogger = this.loggerMap.get(name);
        if (slf4jLogger != null) {
            return slf4jLogger;
        } else {
            Logger logger = sakura.kooi.logger.Logger.of(name);
            loggerMap.put(name, logger);
            return logger;
        }
    }

}
