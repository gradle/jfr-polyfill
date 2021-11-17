/*
 * Copyright (c) 2021, Gradle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Gradle Inc., Gradle Inc., 2261 Market St #4081,
 * San FranciscoCA 94114, United States
 * or visit www.gradle.org if you need additional information or have any
 * questions.
 */

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
