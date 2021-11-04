package org.gradle.jfr.example

import jdk.jfr.Recording
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.TempDir

import java.nio.file.Files
import java.nio.file.Path


@Subject(Recording)
class RecorderSpec extends Specification {

    def recorder = new SampleRecorder()

    @TempDir
    Path tempDir

    def "recorder works"() {
        when:
        recorder.start()

        then:
        noExceptionThrown()

        when:
        recorder.stop()

        then:
        noExceptionThrown()

        when:
        def recording = tempDir.resolve("recording.jfr")
        recorder.dumpToFile(recording)

        then:
        Files.isRegularFile(recording)
    }

}
