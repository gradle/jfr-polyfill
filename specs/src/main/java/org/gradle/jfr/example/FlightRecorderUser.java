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

import jdk.jfr.FlightRecorder;
import jdk.jfr.FlightRecorderListener;
import jdk.jfr.Recording;

import java.util.Objects;

public class FlightRecorderUser {
    public void nonNullReturnValues() {
        FlightRecorder recorder = Objects.requireNonNull(FlightRecorder.getFlightRecorder());
        Objects.requireNonNull(recorder.getRecordings());
        Objects.requireNonNull(recorder.getEventTypes());
        Objects.requireNonNull(recorder.takeSnapshot());
    }

    public boolean isJfrAvailable() {
        return FlightRecorder.isAvailable();
    }

    public void checkListeners() {
        FlightRecorderListener listener = new FlightRecorderListener() {
            @Override
            public void recorderInitialized(FlightRecorder recorder) {
            }

            @Override
            public void recordingStateChanged(Recording recording) {
            }
        };
        FlightRecorder.addListener(listener);
        FlightRecorder.removeListener(listener);
    }

    public void checkPeriodicEvent() {
        Runnable hook = () -> {};
        FlightRecorder.addPeriodicEvent(SampleEvent.class, hook);
        FlightRecorder.removePeriodicEvent(hook);
    }

    public void checkEventRegistration() {
        FlightRecorder.register(SampleEvent.class);
        FlightRecorder.unregister(SampleEvent.class);
    }
}
