package org.gradle.jfr.example;

public class Demo {

    public void trigger() {
        SampleEvent event = new SampleEvent();
        event.begin();
        System.out.println("some work");
        event.commit();
    }
}
