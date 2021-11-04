package org.gradle.jfr.example;

import jdk.jfr.Recording;

import java.io.IOException;
import java.nio.file.Path;

public class SampleRecorder {
    private Recording recording;

    public void start() {
        recording = new Recording();
        recording.enable(SampleEvent.class);
        recording.start();
    }

    public void dumpToFile(Path path) throws IOException {
        recording.dump(path);
    }

    public void stop() {
        recording.stop();
    }
}
