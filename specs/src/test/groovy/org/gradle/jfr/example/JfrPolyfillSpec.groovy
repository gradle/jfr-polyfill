package org.gradle.jfr.example

import spock.lang.Specification
import spock.util.environment.Jvm


class JfrPolyfillSpec extends Specification {

    def "check if jfr-polyfill is active as expected"() {
        expect:
        JfrInfo.polyfillActive == Jvm.current.isJava9() || Jvm.current.isJava10()
    }
}
