package org.slf4j.impl;

import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;

public class SakuraLoggerFactory implements ILoggerFactory {

    public Logger getLogger(String name) {
        if (name.indexOf('.') != -1) {
            name = name.substring(name.lastIndexOf('.')+1);
        }
        return new Slf4jLoggerBridge(name);
    }

}
