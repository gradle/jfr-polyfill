package org.gradle.jfr.example

import jdk.jfr.ValueDescriptor

class JfrInfo {
    static boolean isPolyfillActive() {
        // Check if the Polyfill classes have been loaded
        new ValueDescriptor(String, "test").typeName == "jfr.polyfill.DummyType"
    }

    static boolean isJfrPolyfillExpected() {
        Boolean.getBoolean("polyfill.expected")
    }
}
