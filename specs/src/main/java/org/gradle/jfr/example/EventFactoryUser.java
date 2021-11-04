package org.gradle.jfr.example;

import jdk.jfr.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EventFactoryUser {
    public Event createDynamicEvent(EventFactory f) {
        Event event = f.newEvent();
        event.set(0, "hello, world!");
        event.set(1, 4711);
        event.commit();

        return event;
    }

    public EventFactory createEventFactory() {
        List<ValueDescriptor> fields = new ArrayList<>();
        List<AnnotationElement> messageAnnotations = Collections.singletonList(new AnnotationElement(Label.class, "Message"));
        fields.add(new ValueDescriptor(String.class, "message", messageAnnotations));
        List<AnnotationElement> numberAnnotations = Collections.singletonList(new AnnotationElement(Label.class, "Number"));
        fields.add(new ValueDescriptor(int.class, "number", numberAnnotations));

        String[] category = { "Example", "Getting Started" };
        List<AnnotationElement> eventAnnotations = new ArrayList<>();
        eventAnnotations.add(new AnnotationElement(Name.class, "com.example.HelloWorld"));
        eventAnnotations.add(new AnnotationElement(Label.class, "Hello World"));
        eventAnnotations.add(new AnnotationElement(Description.class, "Helps programmer getting started"));
        eventAnnotations.add(new AnnotationElement(Category.class, category));

        return EventFactory.create(eventAnnotations, fields);
    }
}
