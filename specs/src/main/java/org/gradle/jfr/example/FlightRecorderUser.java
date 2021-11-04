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
