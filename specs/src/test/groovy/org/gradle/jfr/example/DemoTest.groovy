package org.gradle.jfr.example

import spock.lang.Specification

class DemoTest extends Specification {
    def "exercise"() {
        when:
        new Demo().trigger()

        then:
        noExceptionThrown()
    }
}
