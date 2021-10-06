package org.gradle.jfr.example;

import jdk.jfr.Category;
import jdk.jfr.Event;
import jdk.jfr.Name;
import jdk.jfr.StackTrace;

@Category("sample")
@StackTrace(value = false)
@Name("sample.event")
public class SampleEvent extends Event {
}
